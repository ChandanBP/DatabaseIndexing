package com.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReserveSeats
 */
@WebServlet("/ReserveSeats")
public class ReserveSeats extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String fno;
	String tdate;
	String sno;
	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	String ph;
	String nm;
	
    public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveSeats() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Airlines","root","root");
			Statement stmt = conn.createStatement();
			String flightNumber = request.getParameter("FlightNumber");
			String tdate = request.getParameter("traveldate");
			String phone = request.getParameter("phone");
			String name = request.getParameter("Name");
			int total=0;
			ReserveSeats obj = new ReserveSeats();
			obj.setFno(flightNumber);
			obj.setTdate(tdate);
			obj.setPh(phone);
			obj.setNm(name);
			
			String q1 = "select Number_of_available_seats from FLIGHT_INSTANCE where Flight_number='"+flightNumber+"' and Date='"+tdate+"';";
			ResultSet rs = stmt.executeQuery(q1);
			int seats=0;
			
			while(rs.next()){
				seats = Integer.parseInt(rs.getString("Number_of_available_seats"));
			}
			if(seats==0){
				request.setAttribute("result1", "No Seats Available");
				getServletContext().getRequestDispatcher("/SeatsReserve.jsp").forward(request, response);
			}
			ArrayList<ReserveSeats>list = new ArrayList<ReserveSeats>();
			
			if(seats>=1){
				
            // First get the the airplane id for that flight
				String aid="";
				String q2 = "select Airplane_id from FLIGHT_INSTANCE where Flight_number="+flightNumber+";";
				rs = stmt.executeQuery(q2);
				while(rs.next()){
					aid = rs.getString("Airplane_id");
				}
				
				// Get the maximum number of flights from that airplane
				
				String q3 = "select Total_number_of_seats from AIRPLANE where Airplane_id="+Integer.parseInt(aid);
				rs = stmt.executeQuery(q3);
				while(rs.next()){
					total = Integer.parseInt(rs.getString("Total_number_of_seats"));
				}
				
				int row;
				String seatNo="";
				seats = total-seats;
				if(seats%2 == 0){
					row = seats/2;
					row+=1;
					seatNo = row+"A";
				}
				if(seats%2 != 0){
					row = seats/2;
					row+=1;
					seatNo = row+"B";
				}
				obj.setSno(seatNo);
				
				String sql = "INSERT INTO SEAT_RESERVATION (Flight_number, Date, Seat_number, Customer_name, Customer_phone)" +
				        "VALUES (?, ?, ?, ?, ?)";
				
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, flightNumber);
				pstmt.setString(2, tdate);
				pstmt.setString(3, seatNo);
				pstmt.setString(4, name);
				pstmt.setString(5, phone);
				pstmt.execute();
				
			}
			list.add(obj);
			request.setAttribute("result2", list);
			
			String q4 = "UPDATE FLIGHT_INSTANCE"
					  + " SET Number_of_available_seats = "+total+"-(Select count(*) FROM SEAT_RESERVATION WHERE Flight_number='"+flightNumber+"' AND Date='"+tdate+"')"
					  + " WHERE Flight_number='"+flightNumber+"' AND Date='"+tdate+"'";
			stmt.executeUpdate(q4);
			
			rs.close();
			stmt.close();
			conn.close();
			getServletContext().getRequestDispatcher("/SeatsReserve.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
