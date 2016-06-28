package lawsn.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 북마크 DB Entity Object 클래스
 *
 * @version 1.0
 * @author 오범석
 */
@Entity
public class Bookmark {
	
	/**
	 * 북마크번호
	 */
	@Id
	@Column(name="seq")
	@GeneratedValue
	private int seq;
	
	/**
	 * 북마크 생성일시 (currentTimeMillis)
	 */
	@Column(name="create_time")
	private Long createTime;
	
	/**
	 * 섬네일
	 */
	@Column(name="thumbnail")
	private String thumbnail;
	
	/**
	 * 영화제목
	 */
	@Column(name="title")
	private String title;

	/**
	 * 영화평점
	 */
	@Column(name="grades")
	private String grades;
	
	/**
	 * 개봉일
	 */
	@Column(name="open_info")
	private String openInfo;
	
	/**
	 * 영화정보 URL
	 */
	@Column(name="title_link")
	private String titleLink;
	
	/**
	 * 기본 생성자
	 */
	public Bookmark() {
	}
	
	/**
	 * 북마크번호 get
	 * @return 북마크번호
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * 북마크번호 set
	 * @param seq 북마크번호
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * 북마크 생성일시 get
	 * @return 북마크 생성일시
	 */
	public Long getCreateTime() {
		return createTime;
	}
	
	/**
	 * 북마크 생성일시를 지정포맷으로 반환
	 * @return 북마크 생성일시 (yyyy-MM-dd HH:mm:ss)
	 */
	public String getCreateDate() {
		if(createTime == null) {
			return null;
		}
		Date date = new Date(createTime);
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
	}

	/**
	 * 북마크 생성일시 set
	 * @return 북마크 생성일시
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	 * 섬네일 get
	 * @return 섬네일
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * 섬네일 set
	 * @param thumbnail 섬네일
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * 영화제목 get
	 * @return 영화제목
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 영화제목 set
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 영화평점 get
	 * @return 영화평점
	 */
	public String getGrades() {
		return grades;
	}

	/**
	 * 영화평점 set
	 * @param grades 영화평점
	 */
	public void setGrades(String grades) {
		this.grades = grades;
	}

	/**
	 * 개봉일 get
	 * @return 개봉일
	 */
	public String getOpenInfo() {
		return openInfo;
	}

	/**
	 * 개봉일 set
	 * @param openInfo 개봉일
	 */
	public void setOpenInfo(String openInfo) {
		this.openInfo = openInfo;
	}

	/**
	 * 영화정보 URL get
	 * @return 영화정보 URL
	 */
	public String getTitleLink() {
		return titleLink;
	}

	/**
	 * 영화정보 URL set
	 * @param titleLink 영화정보 URL
	 */
	public void setTitleLink(String titleLink) {
		this.titleLink = titleLink;
	}

	@Override
	public String toString() {
		
		return "Bookmark [seq=" + seq + ", createDate=" + getCreateDate() + ", thumbnail=" + thumbnail + ", title=" + title + ", grades=" + grades + ", openInfo=" + openInfo + ", titleLink=" + titleLink + "]";
	}
}
