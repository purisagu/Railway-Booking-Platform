package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TrainDao;
import dto.Train;

@WebServlet("/updatetrain")
public class UpdateTrain extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int trainnumber = Integer.parseInt(req.getParameter("tnumber"));
	
	String trainname = req.getParameter("tname");
	
	int trainseat = Integer.parseInt(req.getParameter("tseat"));
	
	String station = req.getParameter("tstation");
	String[] trainstation = station.split(",");
	
	String price = req.getParameter("tprice");
	String[] trainprice = price.split(",");
	
	String time = req.getParameter("ttime");
	String[] traintime = time.split(",");
	
	String days = req.getParameter("tdays");
	String[] traindays = days.split(",");
	
	Train train =new Train();
	train.setNumber(trainnumber);
	train.setName(trainname);
	train.setDays(traindays);
	train.setPrice(trainprice);
	train.setSeat(trainseat);
	train.setTime(traintime);
	train.setStation(trainstation);
	
	TrainDao dao=new TrainDao();
	dao.update(train);
	
	List<Train> list=dao.fetchall();
	
	resp.getWriter().print("<h1 style='color:blue'>Railway info updated Sucessfully</h1>");
	req.setAttribute("list", list);
	req.getRequestDispatcher("FetchRailwayInfo.jsp").include(req, resp);
}
}