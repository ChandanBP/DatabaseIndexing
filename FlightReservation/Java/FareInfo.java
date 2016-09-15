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
 * Servlet implementation class FareInfo
 */
@WebServlet("/FareInfo")
public class FareInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	String flightNo;
	String fareCode;
	float amount;
	String restrictions;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FareInfo() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFareCode() {
		return fareCode;
	}

	public void setFareCode(String fareCode) {
		this.fareCode = fareCode;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		try 
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Airlines","root","root");
			Statement stmt = conn.createStatement();
			String flightNumber = request.getParameter("FlightNumber");
			String query = "Select * from Fare where Flight_number="+flightNumber+";";
			ResultSet rs = stmt.executeQuery(query);
			ArrayList<FareInfo>list = new ArrayList<FareInfo>();
			FareInfo info;
			while(rs.next()){
				info = new FareInfo();
				info.setFlightNo(rs.getString("Flight_number"));
				info.setFareCode(rs.getString("Fare_code"));
				info.setAmount(Float.parseFloat(rs.getString("Amount")));
				info.setRestrictions(rs.getString("Restrictions"));
				list.add(info);
			}
			rs.close();
			stmt.close();
			conn.close();
			request.setAttribute("result", list);
			if(list.size()!=0){
				getServletContext().getRequestDispatcher("/ALLFARE.jsp").forward(request, response);
			}
			else{
				getServletContext().getRequestDispatcher("/NoInstances.jsp").forward(request, response);
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
