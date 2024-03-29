package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}