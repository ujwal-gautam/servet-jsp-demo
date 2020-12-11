package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class ServletDemo extends HttpServlet {

//	private final static Logger logger = LogManager.getLogger(ControllerServlet.class);
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "ujwal111";
	private static final long serialVersionUID = 1L;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

		@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
//			super.init();
			try {
			     Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				// TODO: handle exception
			}
		}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		/*
		 * resp.setContentType("text/plain");
		 * resp.getWriter().write("Hello World! Maven Web Project Example.");
		 * 
		 * 
		 * PrintWriter printWriter = resp.getWriter();
		 * printWriter.print("<h1>Hello</h1>");
		 */
		
		response.setContentType("text/html");
		PrintWriter log = response.getWriter();
		String name = "";
		int id = 0;
		String query = "select * from student_info;";
		
		try {
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				log.println("<center> <h3>Hello, World </h3><center>");
				return;
			}
			name =resultSet.getString("name");
			id = resultSet.getInt("id");
			
			log.println("<center><h3>Hello, " + name + " </h3><center>");
		    log.println("<center>");
            log.println("<table border='1' cellpadding ='5'>");
            log.println("<tr>");
            log.println("<th> ID </th>");
            log.println("<th> Student Name </th>");
            log.println("</tr>");

            log.println("<tr>");
            log.println("<td>" + id + "</td>");
            log.println("<td>" + name + "</td>");
            log.println("</tr>");
            log.println("</table>");
            log.println("</center>");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		 

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	public void destroy() {
	
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
}
