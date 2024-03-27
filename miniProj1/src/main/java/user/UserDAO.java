package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.UserVO;


public class UserDAO {
	// 1. 회원가입 목록 만들기
    // 2. 삭제 구현
    // 3. 수정 구현
    // 4. 상세보기 구현
    // 5. 전체삭제 구현
    // 6. 등록 구현
	private static Connection conn = null;
    private static PreparedStatement userListPstmt = null;
    private static PreparedStatement userListPstmt2 = null; //회원 검색
    private static PreparedStatement userDetailPstmt = null;
    public static PreparedStatement userDeletePstmt = null;
    
    static {

        try {
            // 1. JDBC 드라이버 로딩
            Class.forName("oracle.jdbc.OracleDriver");
            // 2. DB 연결
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/xe",
                    "bituser", //계정이름
                    "1004" //계정비밀번호
            );
            // 3. SQL 실행 객체 준비
            // 4. SQL 실행
            System.out.println("연결 성공");
            conn.setAutoCommit(false);

            userListPstmt = conn.prepareStatement("select * from tb_users");
            userListPstmt2 = conn.prepareStatement("select * from tb_users where username like ?");
            userDetailPstmt = conn.prepareStatement("select * from tb_users where userid like ?");
            userDeletePstmt = conn.prepareStatement("delete * from tb_users where userid like ?");
            // 5. 결과 처리
            // 6. 연결 해제
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<UserVO> list(UserVO user) {
        List<UserVO> list = new ArrayList<>();
        try {
        	ResultSet rs = null;
        	if (user != null && !user.isEmptySearchKey()) {
        		//검색 키워드 설정 
        		userListPstmt2.setString(1, user.getSearchKey());
        		rs = userListPstmt2.executeQuery();
        	} else {
                rs = userListPstmt.executeQuery();
        	}
            while (rs.next()) {
                UserVO users = new UserVO(rs.getString("userid")
                        , rs.getString("userpassword")
                        , rs.getString("username")
                        , rs.getInt("userage")
                        , rs.getString("useremail"));
                
                list.add(users);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public UserVO read(UserVO user) {
    	System.out.println(user);

        UserVO users = null;
        try {
            userDetailPstmt.setString(1, user.getUserid());

            ResultSet rs = userDetailPstmt.executeQuery();
            if (rs.next()) {
                users = new UserVO(rs.getString("userid")
                        , rs.getString("userpassword")
                        , rs.getString("username")
                        , rs.getInt("userage")
                        , rs.getString("useremail"));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    
    
    public int delete(UserVO user) {
        int updated = 0;

        try {
            userDeletePstmt.setString(1, user.getUserid());
            updated = userDeletePstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }
    
    
}
