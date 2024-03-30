package board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserVO;

public class BoardController {
	private static final long serialVersionUID = 1L;

	//xml 또는 어노터이션 처리하면 스프링 
	//어노터이션 처리하면 스프링 부트   
	BoardService boardService = new BoardService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public Object list(HttpServletRequest request, BoardVO board) throws ServletException, IOException {

		List<BoardVO> list = boardService.list(board);
		
		request.setAttribute("list", list);
		
		return "list";
	}
	
	public Object view(HttpServletRequest request, BoardVO board, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("board", boardService.view(board));
		return "view";
	}
	
	public Object delete(HttpServletRequest request, BoardVO board) throws ServletException, IOException {
		System.out.println("삭제");
		int updated = boardService.delete(board);
		
		Map<String, Object> map = new HashMap<>();
		if (updated == 1) { //성공
			map.put("status", 0);
			map.put("statusMessage", "게시물 삭제 완료");
		} else {
			map.put("status", -99);
			map.put("statusMessage", "게시물 삭제 실패");
		}
		
		return map;
	}
	
	public Object updateForm(HttpServletRequest request, BoardVO board) throws ServletException, IOException {
		System.out.println("수정화면");
		request.setAttribute("board", boardService.updateForm(board));
		return "updateForm"; 
	}
	
	public Object update(HttpServletRequest request, BoardVO board) throws ServletException, IOException {
		System.out.println("수정");
		int updated = boardService.update(board);
		
		Map<String, Object> map = new HashMap<>();
		if (updated == 1) { //성공
			map.put("status", 0);
			map.put("statusMessage", "게시물 수정 완료");
		} else {
			map.put("status", -99);
			map.put("statusMessage", "게시물 수정 실패");
		}
		
		return map;
	}
	
	public Object insertForm(HttpServletRequest request) throws ServletException, IOException {
		System.out.println("등록화면");
		return "insertForm";
	}
	
	public Object insert(HttpServletRequest request, BoardVO board) throws ServletException, IOException {
		System.out.println("등록");
		Map<String, Object> map = new HashMap<>();
		
		//전처리로 세션정보를 얻는다
		HttpSession session = request.getSession();
		System.out.println("게시물등록시 sessionId = " + session.getId());
		//로그인 사용자 설정 
		UserVO loginVO = (UserVO) session.getAttribute("loginVO");
		if (loginVO != null) {
			//로그인한 사용자를 게시물 작성자로 설정한다 
			board.setBuserid(loginVO.getUserid());
		}
		
		//1. 처리
		int updated = boardService.insert(board);
		
		if (updated == 1) { //성공
			map.put("status", 0);
			map.put("statusMessage", "글 등록 완료");
		} else {
			map.put("status", -99);
			map.put("statusMessage", "글쓰기 실패");
		}
		return map;
	}
}