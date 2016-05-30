package com.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
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
		String action = request.getParameter("action");
		if(action.equals("FareInfo")){
			RequestDispatcher rd = request.getRequestDispatcher("FareInfo");
			rd.forward(request,response);
		}
		else if(action.equals("SeatsAvailable")){
			RequestDispatcher rd = request.getRequestDispatcher("SeatsAvailable");
			rd.forward(request,response);
		}
		else if(action.equals("Passengers")){
			RequestDispatcher rd = request.getRequestDispatcher("Passengers");
			rd.forward(request,response);
		}
		else if(action.equals("FlInstances")){
			RequestDispatcher rd = request.getRequestDispatcher("FlInstances");
			rd.forward(request,response);
		}
		else if(action.equals("Connections")){
			RequestDispatcher rd = request.getRequestDispatcher("Connections");
			rd.forward(request,response);
		}
		else if(action.equals("ReserveSeats")){
			RequestDispatcher rd = request.getRequestDispatcher("ReserveSeats");
			rd.forward(request,response);
		}
	}

}
