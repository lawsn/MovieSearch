package lawsn.vo;

/**
 * 영화정보 항목별 세부정보를 담는 클래스
 * 
 * @version 1.0
 * @author 오범석
 */
public class DataVO {

	/**
	 * 데이터정보
	 */
	private String content;
	
	/**
	 * 링크정보
	 */
	private String link;

	/**
	 * 데이터정보 get
	 * @return 데이터정보
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 데이터정보 set
	 * @param content 데이터정보
	 */
	public void setContent(String content) {
		if(content != null && content.length() > 0) {
			content = content.replaceAll("\r\n", "<br/>");
		}
		this.content = content;
	}

	/**
	 * 링크정보 get
	 * @return 링크정보
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 링크정보 set
	 * @param link 링크정보
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		
		return "DataVO [content=" + content + ", link=" + link + "]";
	}
	
}
