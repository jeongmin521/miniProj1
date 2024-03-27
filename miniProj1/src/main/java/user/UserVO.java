package user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/* 테이블에 필드 추가 스크립트 
 * ALTER TABLE BITUSER.USERS ADD USERUUID VARCHAR2(100) NULL;
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
	private String userid;
	private String username;
	private int    userage;
	private String userpassword;
	private String useremail;
	private String usergender;
	private String useraddress;
	private String userphone;
	
	
	//실행 명령 필드 
	private String action;

	//검색키
	private String searchKey;
	
	//uuid
	private String useruuid;
	
	//자동로그인 여부 
	private String autologin;
	
	public UserVO(String userid, String userpassword, String username, int userage, String useremail) {
		super();
		this.userid = userid;
		this.userpassword = userpassword;
		this.username = username;
		this.userage = userage;
		this.useremail = useremail;
	}
	
	public UserVO(String userid, String userpassword, String username, int userage, String useremail, String userphone, String useraddress) {
		super();
		this.userid = userid;
		this.userpassword = userpassword;
		this.username = username;
		this.userage = userage;
		this.useremail = useremail;
		this.userphone = userphone;
		this.useraddress = useraddress;
	}
	
	public boolean isEmptySearchKey() {
		return searchKey == null || searchKey.length() == 0; 
	}
	
	public boolean isEqualPassword(UserVO userVO) {
		return userVO != null && userpassword.equals(userVO.getUserpassword());
	}
	
}
