package com.iqs.iq_project.controllers;

public class SearchRequest {
    private Long userId;
    private String searchTerm;
	public SearchRequest() {
		super();
	}
	public SearchRequest(Long userId, String searchTerm) {
		super();
		this.userId = userId;
		this.searchTerm = searchTerm;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
    
}
