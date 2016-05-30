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
 * Servlet implementation class Passengers
 */
@WebServlet("/Passengers")
public class Passengers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String names;
       
    public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Passengers() {
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
			String query = "select Customer_name from SEAT_RESERVATION where Flight_number='"+flightNumber+"' and Date='"+tdate+"';";
			ResultSet rs = stmt.executeQuery(query);
			ArrayList<Passengers>list = new ArrayList<Passengers>();
			String fnames;
			Passengers p;
			while(rs.next()){
				p = new Passengers();
				p.setNames(rs.getString("Customer_name"));
				list.add(p);
			}
			rs.close();
			stmt.close();
			conn.close();
			request.setAttribute("result", list);
			if(list.size()!=0){
				getServletContext().getRequestDispatcher("/PassengerNames.jsp").forward(request, response);
			}
			else{
				getServletContext().getRequestDispatcher("/NoInstances.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
