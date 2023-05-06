package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.User;
import dao.UserDao;
@WebServlet("/register")
public class RegisterUser extends HttpServlet {

	//private Object wallet;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname=req.getParameter("first");
		String lastname=req.getParameter("last");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		String email=req.getParameter("email");
		String gender=req.getParameter("email");
		String password1=req.getParameter("password1");
		String password2=req.getParameter("password2");
		Date dob=Date.valueOf(req.getParameter("dob"));
		
		//int age=LocalDate.now().getYear()-dob.toLocalDate().getYear(); //it counts like 2022-2021;
		int age=Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
		if(password1.equals(password2)) {
			//resp.getWriter().print("<h1>"+age+"</h1>");
			
			if(age>18) {
				User user=new User();
				user.setAge(age);
				user.setDob(dob);
				user.setEmail(email);
				user.setFirstName(firstname);
				user.setGender(gender);
				user.setLastName(lastname);
				
				user.setMobile(mobile);
				user.setPassword(password1);
				
				UserDao dao=new UserDao();
				dao.save(user);
				
				resp.getWriter().print("<h1 style='color:green'> Account created Successfully<?h1>");
				resp.getWriter().print("<h1 style='color:blue'>your userId is : "+user.getId() +"</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
			else {
				
				resp.getWriter().print("<h1 style='color:red'>You are not old enough </h1>");
				req.getRequestDispatcher("Register.html").include(req, resp);
			}
		}
		else {
			resp.getWriter().print("<h1 style='color:red'>password miss Match </h1> ");
			req.getRequestDispatcher("Register.html").include(req, resp);
		}
	}
}
