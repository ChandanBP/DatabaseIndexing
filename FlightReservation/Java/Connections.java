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
 * Servlet implementation class Connections
 */
@WebServlet("/Connections")
public class Connections extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String fno;
	String weekdays;
	
    public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Connections() {
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
			
			int maxValue = Integer.parseInt(request.getParameter("options"));
			String aCode = request.getParameter("acode");
			String dCode = request.getParameter("dcode");
			String sql="";
			
			String weeks[] = {"Sun%","%Mon%","%Tue%","%Wed%","%Thu%","%Fri%","%Sat"};
			ArrayList<ArrayList<Connections>>mainList = new ArrayList<ArrayList<Connections>>();

			ResultSet rs=null;
			for (int i = 0; i < weeks.length; i++) {
				
			
			switch (maxValue) {
			
			case 0: sql = "select FLIGHT_NUMBER,Weekdays from FLIGHT where Departure_airport_code='"+dCode+"' and Arrival_airport_code='"+aCode+"';";
				break;
			case 1: sql = "Select a.FLIGHT_NUMBER,b.Weekdays"
					  +" From FLIGHT a JOIN FLIGHT b ON (b.Departure_airport_code=a.Arrival_airport_code)"
					  +" AND a.Departure_airport_code='"+dCode+"'"
					  +" AND b.Arrival_airport_code='"+aCode+"'"
					  +" AND TIME_TO_SEC(TIMEDIFF(b.Scheduled_departure_time,a.Scheduled_arrival_time))/3600 >1"
					  +" AND a.Weekdays LIKE '"+weeks[i]+"' AND b.Weekdays LIKE '"+weeks[i]+"';";
					  
				break;
			case 2: sql= "Select a.FLIGHT_NUMBER,b.Weekdays From FLIGHT a,FLIGHT b,FLIGHT c"
					+" where a.Departure_airport_code='"+dCode+"'"
					+" AND a.Arrival_airport_code=b.Departure_airport_code"
					+" AND b.Arrival_airport_code=c.Departure_airport_code"
					+" AND c.Arrival_airport_code='"+aCode+"'"
					+" AND TIME_TO_SEC(TIMEDIFF(b.Scheduled_departure_time,a.Scheduled_arrival_time))/3600 >1"
					+" AND TIME_TO_SEC(TIMEDIFF(c.Scheduled_departure_time,b.Scheduled_arrival_time))/3600 >1"
					+" AND a.Weekdays LIKE '"+weeks[i]+"' AND b.Weekdays LIKE '"+weeks[i]+"' AND c.Weekdays LIKE '"+weeks[i]+"';";
					//+" AND a.Weekdays=b.Weekdays"
					//+" AND b.Weekdays=c.Weekdays";
		
				break;
			case 3: sql="Select a.FLIGHT_NUMBER,b.Weekdays From FLIGHT a,FLIGHT b,FLIGHT c,FLIGHT d"
					+" where a.Departure_airport_code='"+dCode+"'"
					+" AND a.Arrival_airport_code=b.Departure_airport_code"
					+" AND b.Arrival_airport_code=c.Departure_airport_code"
					+" AND c.Arrival_airport_code=d.Departure_airport_code"
					+" AND d.Arrival_airport_code='"+aCode+"'"
					+" AND TIME_TO_SEC(TIMEDIFF(b.Scheduled_departure_time,a.Scheduled_arrival_time))/3600 >1"
					+" AND TIME_TO_SEC(TIMEDIFF(c.Scheduled_departure_time,b.Scheduled_arrival_time))/3600 >1"
					+" AND TIME_TO_SEC(TIMEDIFF(d.Scheduled_departure_time,c.Scheduled_arrival_time))/3600 >1"
					+" AND a.Weekdays LIKE '"+weeks[i]+"' AND b.Weekdays LIKE '"+weeks[i]+"' AND c.Weekdays LIKE '"+weeks[i]+"' AND c.Weekdays LIKE '"+weeks[i]+"';";
//					+" AND a.Weekdays=b.Weekdays"
//					+" AND b.Weekdays=c.Weekdays"
//					+" AND c.Weekdays=d.Weekdays";
				break;
			}
			
			rs = stmt.executeQuery(sql);
			ArrayList<Connections>list = new ArrayList<Connections>();
			Connections c;
			if(rs.next() != false){
				while(rs.next()){
					c = new Connections();
					c.setFno(rs.getString("FLIGHT_NUMBER"));
					c.setWeekdays(rs.getString("Weekdays"));
					list.add(c);
				}
				mainList.add(list);
			}
//			else{
//				rs.close();
//				stmt.close();
//				conn.close();
//				getServletContext().getRequestDispatcher("/NoInstances.jsp").forward(request, response);
//			}
			
			}
			if(rs!=null)
			rs.close();
			stmt.close();
			conn.close();
			request.setAttribute("result", mainList);
			
			getServletContext().getRequestDispatcher("/Connection.jsp").forward(request, response);
			return;
//			String query1="select FLIGHT_NUMBER,Weekdays from FLIGHT where Departure_airport_code='"+dCode+"' and Arrival_airport_code='"+aCode+"';";
//
//			ResultSet rs = stmt.executeQuery(query1);
//			ArrayList<Connections>list = new ArrayList<Connections>();
//			Connections c;
//			
//			if(rs.next() != false && maxValue==0){
//				while(rs.next()){
//					c = new Connections();
//					c.setFno(rs.getString("FLIGHT_NUMBER"));
//					c.setWeekdays(rs.getString("Weekdays"));
//					list.add(c);
//				}
//				rs.close();
//				stmt.close();
//				conn.close();
//				request.setAttribute("result", list);
//				
//				getServletContext().getRequestDispatcher("/Connection.jsp").forward(request, response);
//				return;
//			}
//
//			
//			// For One connecting Flight
//			String query2="Select f1.FLIGHT_NUMBER,f1.Weekdays"
//						  +" From FLIGHT f1 JOIN FLIGHT f2 ON (f2.Departure_airport_code=f1.Arrival_airport_code)"
//						  +" AND f1.Departure_airport_code='"+dCode+"'"
//						  +" AND f2.Arrival_airport_code='"+aCode+"'"
//						  +" AND f1.Weekdays=f2.Weekdays"
//						  +" AND TIME_TO_SEC(TIMEDIFF(f2.Scheduled_departure_time,f1.Scheduled_arrival_time))/3600 >1;";
//			rs = stmt.executeQuery(query2);
//			if(rs.next() != false && maxValue==1){
//				while(rs.next()){
//					c = new Connections();
//					c.setFno(rs.getString("FLIGHT_NUMBER"));
//					c.setWeekdays(rs.getString("Weekdays"));
//					list.add(c);
//				}
//				rs.close();
//				stmt.close();
//				conn.close();
//				request.setAttribute("result", list);
//				getServletContext().getRequestDispatcher("/Connection.jsp").forward(request, response);
//				return;
//			}
//			
//			// For two connecting flight
//			String query3 = "Select a.FLIGHT_NUMBER,b.Weekdays From FLIGHT a,FLIGHT b,FLIGHT c"
//							+" where a.Departure_airport_code='"+dCode+"'"
//							+" AND a.Arrival_airport_code=b.Departure_airport_code"
//							+" AND b.Arrival_airport_code=c.Departure_airport_code"
//							+" AND c.Arrival_airport_code='"+aCode+"'"
//							+" AND a.Weekdays=b.Weekdays"
//							+" AND b.Weekdays=c.Weekdays"
//							+" AND TIME_TO_SEC(TIMEDIFF(b.Scheduled_departure_time,a.Scheduled_arrival_time))/3600 >1"
//							+" AND TIME_TO_SEC(TIMEDIFF(c.Scheduled_departure_time,b.Scheduled_arrival_time))/3600 >1;";
//			rs = stmt.executeQuery(query3);
//			if(rs.next() != false && maxValue==2){
//				while(rs.next()){
//					c = new Connections();
//					c.setFno(rs.getString("FLIGHT_NUMBER"));
//					c.setWeekdays(rs.getString("Weekdays"));
//					list.add(c);
//				}
//				rs.close();
//				stmt.close();
//				conn.close();
//				request.setAttribute("result", list);
//				getServletContext().getRequestDispatcher("/Connection.jsp").forward(request, response);
//				return;
//			}
//		
//			// For three connecting Flights;
//			String query4 = "Select a.FLIGHT_NUMBER,b.Weekdays From FLIGHT a,FLIGHT b,FLIGHT c,FLIGHT d"
//							+" where a.Departure_airport_code='"+dCode+"'"
//							+" AND a.Arrival_airport_code=b.Departure_airport_code"
//							+" AND b.Arrival_airport_code=c.Departure_airport_code"
//							+" AND c.Arrival_airport_code=d.Departure_airport_code"
//							+" AND d.Arrival_airport_code='"+aCode+"'"
//							+" AND a.Weekdays=b.Weekdays"
//							+" AND b.Weekdays=c.Weekdays"
//							+" AND c.Weekdays=d.Weekdays"
//							+" AND TIME_TO_SEC(TIMEDIFF(b.Scheduled_departure_time,a.Scheduled_arrival_time))/3600 >1"
//							+" AND TIME_TO_SEC(TIMEDIFF(c.Scheduled_departure_time,b.Scheduled_arrival_time))/3600 >1"
//							+" AND TIME_TO_SEC(TIMEDIFF(d.Scheduled_departure_time,c.Scheduled_arrival_time))/3600 >1;";
//			rs = stmt.executeQuery(query3);
//			if(rs.next() != false && maxValue==3){
//				while(rs.next()){
//					c = new Connections();
//					c.setFno(rs.getString("FLIGHT_NUMBER"));
//					c.setWeekdays(rs.getString("Weekdays"));
//					list.add(c);
//				}
//				rs.close();
//				stmt.close();
//				conn.close();
//				request.setAttribute("result", list);
//				getServletContext().getRequestDispatcher("/Connection.jsp").forward(request, response);
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
