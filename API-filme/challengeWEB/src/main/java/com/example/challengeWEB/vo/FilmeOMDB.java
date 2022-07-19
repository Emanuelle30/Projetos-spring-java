package com.example.challengeWEB.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmeOMDB {
	
	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("Year")
	private String year;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	

}
