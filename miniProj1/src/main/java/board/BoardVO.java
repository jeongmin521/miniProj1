package board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
	
	public BoardVO(int bno, String btitle, String bcontent) {
		this(bno, btitle, bcontent, "", "");
	}

	
	public BoardVO(int bno, String btitle, String bcontent, String buserid, String bdate) {
		this(bno, btitle, bcontent, buserid, bdate, "", "");
	}


	private int bno;
	private String btitle;
	private String bcontent;
	private String buserid;
	private String bdate;
	
	//실행 명령 필드 
	private String action;

	//검색키
	private String searchKey;

}