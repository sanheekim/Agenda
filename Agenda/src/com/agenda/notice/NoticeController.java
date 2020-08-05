package com.agenda.notice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agenda.login.LoginDto;

@WebServlet("/NoticeController.do")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeController() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		LoginDto logindto = new LoginDto();
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		NoticeDao dao = new NoticeDao();
		
		//공지사항 게시판 보기 
		if(command.equals("list")) {
			System.out.println("Notice Controller 로 넘어옴");
			String searchOption = request.getParameter("searchOption");
			String keyword = request.getParameter("keyword");
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			
			System.out.println("서치옵션:"+searchOption+"키워드:"+keyword+"현재페이지:"+curPage);
			
			//레코드의 갯수 계산
			int count = dao.listCount(searchOption,keyword);
			System.out.println("글개수:"+count);
			
			//페이지 나누기 관련처리
			NoticeBoardPager noticePager = new NoticeBoardPager(count,curPage);
			
			//페이지당 게시물 수 -완료
			int start = noticePager.getPageBegin();
			int end = noticePager.getPageEnd();
			
			List<NoticeDto> list = dao.selectList(start, end, searchOption, keyword);
			
			//데이터를 맵에 저장
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", list);
			map.put("count",count);
			map.put("searchOption", searchOption);
			map.put("keyword", keyword);
			map.put("boardPager", noticePager);			
			request.setAttribute("map",map);
			
			dispatch("notice/noticeList.jsp",request,response);
			System.out.println("noticeList보기로 넘어감");
			
		} 
		//글쓰기 
		else if(command.equals("write")) {
			String member_role = request.getParameter("member_role");
			System.out.println(member_role);
			response.sendRedirect("notice/noticeInsert.jsp");
			
		}
		//공지사항 글쓰기 페이지
		else if (command.equals("writreres")) {
			
			String member_id = request.getParameter("member_id");
			String notice_title = request.getParameter("notice_title");
			String notice_content = request.getParameter("notice_content");
			System.out.println("Controller->제목: "+notice_title +"내용:"+notice_content+"아이디:"+member_id);
		
			NoticeDto dto = new NoticeDto(notice_title, notice_content, member_id);
			
			int res = dao.insert(dto);
			
			if(res>0) {
				
				response.sendRedirect("NoticeContorller.do?command=list&curPage=1&searchOption+all&keyword=");
				
				
			} else {
				String msg ="작성실패";
				noticeResponse(msg,"NoticeController.do?command=write",request,response);
			}
			
		}
		
		else if(command.equals("detail")) {
			
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			NoticeDto dto = dao.selectOne(notice_no);		
			request.setAttribute("detail",dto);
			dispatch("notice/noticeDetail.jsp",request,response);
			
		}
		
		else if(command.equals("delete")) {
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			int res = dao.delete(notice_no);
			System.out.println("delete"+res);
			if(res>0) {
				noticeResponse("글 삭제 성공" , "NoticeController.do?command=list&curpage=1&searchOption=all&keyword=",request, response);
			} else {
				noticeResponse("글 삭제 실패", "NoticeController.do?command=detail",request,response);
			}
		}
		
		else if (command.equals("update")) {
			
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			NoticeDto dto = dao.selectOne(notice_no);
			request.setAttribute("update",dto);
			dispatch("notice/noticeUpdate.jsp",request,response);
			
		}
		
		else if (command.equals("updateres")) {
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			String notice_title = request.getParameter("notice_title");
			String notice_content = request.getParameter("notice_content");
			String member_id = request.getParameter("member_id");
			System.out.println("제목"+notice_title+"내용"+notice_content+"아이디"+member_id);
			
			NoticeDto dto = new NoticeDto(notice_no,notice_title, notice_content, member_id);
			
			int res = dao.update(dto);
			System.out.println("update res: "+ res);
			
			if(res>0) {
				dispatch("NoticeController.do?command=detail&notice_no="+notice_no,request,response);
			} else {
				noticeResponse("글 수정 실패","NoticeController.do?command=update",request,response);
			}
		}
	}
	
	public void dispatch(String url,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	public void noticeResponse(String msg, String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String notice= "<script type = 'text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		response.getWriter().append(notice);
	}

}
