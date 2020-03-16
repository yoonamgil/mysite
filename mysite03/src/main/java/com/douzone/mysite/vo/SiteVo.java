package com.douzone.mysite.vo;

public class SiteVo {
	private Long no; 
	private String title;
	private String welcomeMessage;
	private String profile;
	private String description;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMassage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SiteVo [no=" + no + ", title=" + title + ", welcomeMessage=" + welcomeMessage + ", profile=" + profile
				+ ", description=" + description + "]";
	}
	
	
	
	
}
