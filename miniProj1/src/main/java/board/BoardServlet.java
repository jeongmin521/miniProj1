package board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardVO;
/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardDAO boardDAO = new BoardDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request, response);
	}
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");
		switch(action) {
		case "list" -> list(request, response);
		case "view" -> view(request, response);
//		case "updateForm" -> updateForm(request, response);
//		case "insertForm" -> insertForm(request, response);
		}
		
		//jsp 포워딩 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/board/"+action+".jsp");
		rd.forward(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("목록");
		String searchKey = request.getParameter("searchKey");
		System.out.println("검색값 :"+ searchKey);
		//1. 처리
		List<BoardVO> list = boardDAO.list(searchKey);

		//2. jsp출력할 값 설정
		request.setAttribute("list", list);

	}
	
	private String view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("상세보기");
		Integer bno = Integer.parseInt(request.getParameter("bno"));
		//1. 처리
		BoardVO board = boardDAO.read(bno);
		
		//2. jsp출력할 값 설정
		request.setAttribute("board", board);
		return "view";
	}
	

	
//	private void insertForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<String> insertForm = new ArrayList<>();
//		insertForm.add("등록하기");
//		request.setAttribute("insertForm", insertForm);	
//	}
//	
//	private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<String> updateForm = new ArrayList<>();
//		updateForm.add("수정하기");
//		request.setAttribute("updateForm", updateForm);
//	}
	
}
