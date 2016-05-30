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
 * Servlet implementation class FlInstances
 */
@WebServlet("/FlInstances")
public class FlInstances extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int fnumber;
    String tdate;
    public int getFnumber() {
		return fnumber;
	}

	public void setFnumber(int fnumber) {
		this.fnumber = fnumber;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public FlInstances() {
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
			Connection conn;
			String pName = request.getParameter("Name");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Airlines","root","root");
			Statement stmt = conn.createStatement();
			String query = "select Flight_number,Date from SEAT_RESERVATION where Customer_name='"+pName+"';";
			ResultSet rs = stmt.executeQuery(query);
			ArrayList<FlInstances>list = new ArrayList<FlInstances>();
			FlInstances fl;
			Passengers p;
			while(rs.next()){
				fl = new FlInstances();
				fl.setFnumber(Integer.parseInt(rs.getString("Flight_number")));
				fl.setTdate(rs.getString("Date"));
				list.add(fl);
			}
			rs.close();
			stmt.close();
			conn.close();
			request.setAttribute("result", list);
			if(list.size()!=0){
				getServletContext().getRequestDispatcher("/FlInstances.jsp").forward(request, response);
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
