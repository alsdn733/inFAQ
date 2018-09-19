package com.mycompany.vo;


public class faqContent {
	private int no;
	private String title;
	private String content;
	private int hit;
	private int deleteYN;
	 
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(int deleteYN) {
		this.deleteYN = deleteYN;
	}
	@Override
	public String toString() {
		return "faqContent [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", deleteYN="
				+ deleteYN + "]";
	}
}
