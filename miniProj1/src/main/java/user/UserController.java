package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


public class UserController {
	private static final long serialVersionUID = 1L;

	//xml 또는 어노터이션 처리하면 스프링 
	//어노터이션 처리하면 스프링 부트   
	UserService userService = new UserService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public Object list(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("목록");
		
		//1. 처리
		List<UserVO> list = userService.list(user);
		
		//2. jsp출력할 값 설정
		request.setAttribute("list", list);
		
		return "list";
	}
	
	public Object view(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("상세보기");
		//String userid = request.getParameter("userid");
		//1. 처리
		
		//2. jsp출력할 값 설정
		request.setAttribute("user", userService.view(user));
		return "view";
	}

}
