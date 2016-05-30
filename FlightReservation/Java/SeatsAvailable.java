package com.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class SeatsAvailable
 */
@WebServlet("/SeatsAvailable")
public class SeatsAvailable extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	int seats;
    public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SeatsAvailable() {
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Airlines","root","root");
			String query = "select Number_of_available_seats from FLIGHT_INSTANCE where Flight_number='"+flightNumber+"' and Date='"+tdate+"';";
			ResultSet rs = stmt.executeQuery(query);
			int seats=0;
			while(rs.next()){
				seats = Integer.parseInt(rs.getString("Number_of_available_seats"));
			}
			rs.close();
			stmt.close();
			conn.close();
			if(seats!=0){
				request.setAttribute("result", seats);
				getServletContext().getRequestDispatcher("/SEATS_AVAIL.jsp").forward(request, response);
			}
			if(seats==0){
				getServletContext().getRequestDispatcher("/NoInstances.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
