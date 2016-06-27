package lawsn.vo;

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
		
		return "{\"content\":\"" + content + "\", \"link\":\"" + link + "\"}";
	}
	
}
