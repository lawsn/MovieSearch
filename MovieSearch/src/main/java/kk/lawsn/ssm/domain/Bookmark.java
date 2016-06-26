package kk.lawsn.ssm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
