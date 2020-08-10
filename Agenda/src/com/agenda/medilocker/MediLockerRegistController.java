package com.agenda.medilocker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.agenda.login.LoginDto;

@WebServlet("/MediLockerRegistController")
public class MediLockerRegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MediLockerRegistController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		HttpSession session = request.getSession();
		MediLockerDao dao = new MediLockerDao();
		MediLockerDto mediDto = new MediLockerDto();
		LoginDto dto = (LoginDto) session.getAttribute("logindto");
		if (command.equals("mediLocker")) {
			if (dto != null) {
				List<MediLockerDto> list = dao.selectList(dto.getMember_id());
				System.out.println(dto.getMember_id());
				List<String> scanList = new ArrayList<String>();
//				scanList = (List<String>) request.getAttribute("scanList");
				System.out.println("list들어왔다~");
//				System.out.println(scanList);
//				request.setAttribute("scanList", scanList);
				request.setAttribute("list", list);
				dispatch(request, response, "medilocker/mediLockerScanMain.jsp");

			} else {
				System.out.println("로그인후 이용해주세요");
				jsResponse("로그인 후 이용해주세요", "login/loginForm.jsp", response);
			}
		} else if (command.equals("scanRegist")) {
			MediLockerRegist rg = new MediLockerRegist();
			String member_id = dto.getMember_id();
			String strJson = request.getParameter("strObj");
			String strArr = request.getParameter("strArr");
			String str = request.getParameter("str");
			rg.MediRegist(strJson, strArr, str, member_id);
		} else if(command.equals("delete")) {
			int pres_no = Integer.parseInt(request.getParameter("pres_no"));
			int res = dao.delete(pres_no);
			
				PrintWriter pr = response.getWriter();
				pr.println(res);
			
		}
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws ServletException, IOException {
		String s = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";
		response.getWriter().append(s);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
