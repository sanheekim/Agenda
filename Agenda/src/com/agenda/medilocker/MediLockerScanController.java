package com.agenda.medilocker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/GoogleVisionController")
public class MediLockerScanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String filename;
	

    public MediLockerScanController() {
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		
			request.setCharacterEncoding("UTF-8");
			String file="";//변경된 이름 
			String onFile="";
			//프로젝트내에 만들어질 폴더를 저장할 이름 변수선언
			String realfolder="";
			//실제 만들어질 폴더명을 설정
			String savefolder="/upload";
			//한글설정
			String encType="UTF-8";
			//저장될 파일 사이즈를 설정 
			int maxSize = 1024*1024*5;//5mbyte
			
			
			
			//파일 저장될 경로값 읽어오는 객체
			ServletContext context = getServletContext();
			realfolder = context.getRealPath(savefolder);
			      
		    File targetDir = new File(realfolder);  
		        
		    if(!targetDir.exists()) {    //디렉토리 없으면 생성.
		         targetDir.mkdirs();
		    }



			
			try {
				MultipartRequest multi = new MultipartRequest(request, realfolder, maxSize, 
							encType, new DefaultFileRenamePolicy());//파일이름 변경 자동으로 가능(파일명 겹칠시)
				Enumeration files = multi.getFileNames();
				String str = (String)files.nextElement();
				
				file=multi.getFilesystemName(str);//파일이름 중복시 변경된 이름
				onFile = multi.getOriginalFileName(str);//원래 파일 이름
				
				filename = realfolder + "\\" + onFile;
				out.println(filename);
				List<String> list = MediLockerScan.detectText(filename);
				request.setAttribute("list", list);
				RequestDispatcher dispatch = request.getRequestDispatcher("medilockerscan/mediLockerScanMain.jsp");
				dispatch.forward(request, response);
				
				
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		
	}

