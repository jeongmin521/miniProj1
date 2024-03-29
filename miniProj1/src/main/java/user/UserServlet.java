package user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDAO userDAO = new UserDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
	
	private Map<String, Object> convertMap(Map<String, String[]> map) {
		Map<String, Object> result = new HashMap<>();

		for (var entry : map.entrySet()) {
			if (entry.getValue().length == 1) {
				//문자열 1건  
				result.put(entry.getKey(), entry.getValue()[0]);
			} else {
				//문자열 배열을 추가한다  
				result.put(entry.getKey(), entry.getValue());
			}
		}
		
		return result;
	}
	
	
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();
		
		ObjectMapper objectMapper = new ObjectMapper();
		UserVO userVO = null;
		if (contentType == null || contentType.startsWith("application/x-www-form-urlencoded")) {
			userVO = objectMapper.convertValue(convertMap(request.getParameterMap()), UserVO.class);
		} else if (contentType.startsWith("application/json")) {
			userVO = objectMapper.readValue(request.getInputStream(), UserVO.class);
		}
		System.out.println("userVO " + userVO);
		
		String action = userVO.getAction();
		switch(action) {
		case "list" -> list(request, userVO);
		case "view" -> view(request, userVO);
		case "jointForm" -> joinForm(request, response);
		case "updateForm" -> updateForm(request, response);
		}
		//jsp 포워딩 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/"+action+".jsp");
		rd.forward(request, response);
	}
	
	public Object list(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("목록");
		
		//1. 처리
		List<UserVO> list = userDAO.list(user);
		
		//2. jsp출력할 값 설정
		request.setAttribute("list", list);
		
		return "list";
	}
	
	public Object view(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("상세보기");
		//String userid = request.getParameter("userid");
		//1. 처리
		
		System.out.println("상세보기 유저" + user);
		//2. jsp출력할 값 설정
		request.setAttribute("user", userDAO.read(user));
		return "view";
	}
	
	
	private void joinForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> joinForm = new ArrayList<>();
		joinForm.add("회원가입하기");
		request.setAttribute("joinForm", joinForm);		
	}
	
	private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> updateForm = new ArrayList<>();
		updateForm.add("수정하기");
		request.setAttribute("updateForm", updateForm);		
	}
	
}
