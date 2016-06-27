package lawsn.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bookmark {
	
	public Bookmark() {
		// default
	}

	@Id @GeneratedValue
	private int seq;
	
	private Long createTime;
	
	private String thumbnail;
	
	private String title;

	private String grades;
	
	private String openInfo;
	
	private String titleLink;
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public Long getCreateTime() {
		return createTime;
	}
	
	public String getCreateDate() {
		Date date = new Date(createTime);
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public String getOpenInfo() {
		return openInfo;
	}

	public void setOpenInfo(String openInfo) {
		this.openInfo = openInfo;
	}

	public String getTitleLink() {
		return titleLink;
	}

	public void setTitleLink(String titleLink) {
		this.titleLink = titleLink;
	}

	@Override
	public String toString() {
		
		return "Bookmark []";
	}
}
