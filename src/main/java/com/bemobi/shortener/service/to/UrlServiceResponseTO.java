package com.bemobi.shortener.service.to;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UrlServiceResponseTO {

	private String url;
	private String alias;
	private String shortUrl;
	private StatisticsTO statistics;
	
	private boolean success;
    private String code;
    private String message;

    @JsonIgnore
    public Integer httpStatus;
	
    public UrlServiceResponseTO() {
		// TODO Auto-generated constructor stub
	}

	public UrlServiceResponseTO(String url, String alias, String shortUrl, StatisticsTO statistics, boolean success,
			String code, String message, Integer httpStatus) {
		super();
		this.url = url;
		this.alias = alias;
		this.shortUrl = shortUrl;
		this.statistics = statistics;
		this.success = success;
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public StatisticsTO getStatistics() {
		return statistics;
	}

	public void setStatistics(StatisticsTO statistics) {
		this.statistics = statistics;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "UrlServiceResponseTO [url=" + url + ", alias=" + alias + ", shortUrl=" + shortUrl + ", statistics="
				+ statistics + "]";
	}

	
    
	
	

	
	
	
	
	
	

	
	
}
