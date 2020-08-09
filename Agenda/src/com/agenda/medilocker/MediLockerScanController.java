package com.agenda.medilocker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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


@WebServlet("/MediLockerScanController")
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
		System.out.println("처방전스캔컨트롤러진입");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		

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
				//out.println(filename);
				List<String> list = MediLockerScan.detectText(filename);
				List<String> listArr = new ArrayList<String>();
				System.out.println("추출된 문자열 길이 : "+ list.size());						
				String[] strArr = new String[list.size()];
				
				MediLockerRegist mlr = new MediLockerRegist();
				
				String strList = "";
				for(String i : list) {
					if(i == list.get(0)) {
						strList += i.trim();						
					}else {
						strList += "," + i.trim();
					}
				}
				strArr = mlr.replaceStr(strList);
				listArr = mlr.removeDuplicate(strArr);
				
				String strlist = "";
				for(String i : listArr) {
					if(i == listArr.get(0)) {
						strlist += i;			
					}else {
						strlist += "," + i;
					}
				}
				
				System.out.println(strlist);
				PrintWriter pr = response.getWriter();
				pr.write(strlist);
				pr.flush();
			} catch (IOException e) {
				System.out.println("[ERROR] 문자 추출 오류");
				e.printStackTrace();
			}
		}
		
	}

