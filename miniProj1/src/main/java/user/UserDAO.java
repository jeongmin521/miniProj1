package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.BoardVO;
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
            // 5. 결과 처리
            // 6. 연결 해제
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<UserVO> list(String searchKey) {
        List<UserVO> list = new ArrayList<>();
        try {
        	ResultSet rs = null;
        	if (searchKey != null && searchKey.length() != 0) {
        		//검색 키워드 설정 
        		userListPstmt2.setString(1, searchKey);
        		rs = userListPstmt2.executeQuery();
        	} else {
                rs = userListPstmt.executeQuery();
        	}
            while (rs.next()) {
                UserVO userVO = new UserVO(rs.getString("userid")
                        , rs.getString("userpassword")
                        , rs.getString("username")
                        , rs.getInt("userage")
                        , rs.getString("useremail"));
                
                list.add(userVO);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public UserVO read(String userid) {
        UserVO user = null;
        try {
            userDetailPstmt.setString(1, userid);

            ResultSet rs = userDetailPstmt.executeQuery();
            if (rs.next()) {
                user = new UserVO (rs.getString("userid")
                		, rs.getString("userpassword")
                		, rs.getString("username")
                		, rs.getInt("userage")
                        , rs.getString("useremail")
                        , rs.getString("userphone")
                        , rs.getString("useraddress"));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
