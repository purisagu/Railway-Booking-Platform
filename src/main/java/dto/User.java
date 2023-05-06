package dto;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import lombok.Data;

@WebServlet
@Entity
@Data
public class User extends HttpServlet {
	@Id
	@GeneratedValue(generator = "x")
	@SequenceGenerator(name = "x",sequenceName = "x",initialValue = 1214561,allocationSize = 1)
	int id;
	String firstName;
	String lastName;
	String email;
	long mobile;
	String gender;
	String password;
	Date dob;
	int age;
	double wallet;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userid=Integer.parseInt(req.getParameter("userid"));
		String password=req.getParameter("password");
		
		UserDao dao=new UserDao();
		User user=dao.find(userid);
		if(user==null)
		{
			resp.getWriter().print("<h1 style='color:red'>Invalid Id</h1>");
			req.getRequestDispatcher("login.html").include(req,resp);
		}
		else{
			if(user.getPassword().equals(password)){
			resp.getWriter().print("<h1 style='color:green'>Login successfully</h1>");
			req.getRequestDispatcher("UserHome.html").include(req,resp);
			}
			else
			{
			resp.getWriter().print("<h1 style='color:red'>Invalid password</h1>");
			req.getRequestDispatcher("login.html").include(req,resp);
			}
	}
}
}
