package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private static PreparedStatement userDetailPstmt = null; //회원 상세보기
    private static PreparedStatement userInsertPstmt = null; //회원가입
    private static PreparedStatement userUpdatePstmt = null;
    private static PreparedStatement userDeletePstmt = null;
    private static PreparedStatement userValidationIdPstmt = null; //로그인
    private static PreparedStatement userValidationPasswordPstmt = null;//로그인

    
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

            //조회
            userListPstmt = conn.prepareStatement("select * from tb_users");
            userListPstmt2 = conn.prepareStatement("select * from tb_users where username like ?");
            userDetailPstmt = conn.prepareStatement("select * from tb_users where userid like ?");
            //입력, 수정, 삭제
            userInsertPstmt = conn.prepareStatement("insert into tb_users (userid, username, userpassword, userage, useremail, userphone, useraddress) values (?, ?, ?, ?, ?, ?, ?)");
            userUpdatePstmt = conn.prepareStatement("update tb_users set username=?, userpassword=?,userage=?, useremail=?, userphone=?, useraddress=? where userid=?");//정보수정
            userDeletePstmt = conn.prepareStatement("delete from tb_users where userid=?");
            //로그인
            userValidationIdPstmt = conn.prepareStatement("select userid from tb_users where userid=?  ");
            userValidationPasswordPstmt  = conn.prepareStatement("select userpassword from tb_users whrere userpassword=? ");
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
                        , rs.getString("useremail")
                        , rs.getString("userphone")
                        , rs.getString("useraddress"));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public int join(UserVO users){
        int updated = 0;
        try{
            userInsertPstmt.setString(1, users.getUserid());
            userInsertPstmt.setString(2, users.getUsername());
            userInsertPstmt.setString(3, users.getUserpassword());
            userInsertPstmt.setInt(4, users.getUserage());
            userInsertPstmt.setString(5, users.getUseremail());
            userInsertPstmt.setString(6, users.getUserphone());
            userInsertPstmt.setString(7, users.getUseraddress());
            updated = userInsertPstmt.executeUpdate();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return updated;
    }
    
    public int update(UserVO users) {
        int updated = 0;
        try {
            userUpdatePstmt.setString(1, users.getUsername());
            userUpdatePstmt.setString(2, users.getUserpassword());
            userUpdatePstmt.setInt(3, users.getUserage());
            userUpdatePstmt.setString(4, users.getUseremail());
            userUpdatePstmt.setString(5, users.getUserphone());
            userUpdatePstmt.setString(6, users.getUseraddress());
            userUpdatePstmt.setString(7, users.getUserid());
            updated = userUpdatePstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
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
    
    public boolean validationId(String userid){
        boolean result = false;
        try {
            userValidationIdPstmt.setString(1, userid);
            ResultSet rs = userValidationIdPstmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
    }
        return result;
    }

    public boolean  validationPassword(String userpassword){
        boolean result = false;
        try {
            userValidationPasswordPstmt.setString(1, userpassword);
            ResultSet rs = userValidationPasswordPstmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    
    
}
