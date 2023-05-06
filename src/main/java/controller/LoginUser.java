package controller;

import java.io.IOException;

import dao.UserDao;
import dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginUser extends HttpServlet {
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userid=Integer.parseInt(req.getParameter("userid"));
		String password=req.getParameter("password");
		
		UserDao dao=new UserDao();
		User user=dao.find(userid);
			
		if(user == null) {
			resp.getWriter().print("<h1 style='color:red'>invalid Id</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		else {
			
			if(user.getPassword().equals(password)) {
				resp.getWriter().print("<h1 style='color:green'>Login Successfull</h1>");
				req.getRequestDispatcher("UserHome.html").include(req, resp);

			}
			else {
				resp.getWriter().print("<h1 style='color:red'>invalid Password</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
				
		}
		
		
	}
}
