package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import user.UserDAO;
import user.UserVO;


public class UserService {
	private static final long serialVersionUID = 1L;
    
	UserDAO userDAO = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserService() {
        super();
        // TODO Auto-generated constructor stub
    }

    public List<UserVO> list(UserVO user) throws ServletException, IOException {
		return userDAO.list(user);
	}
	
	public UserVO view(UserVO user) throws ServletException, IOException {
		return userDAO.read(user);
	}

}
