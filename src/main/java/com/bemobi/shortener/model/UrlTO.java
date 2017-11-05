package com.bemobi.shortener.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.bemobi.shortener.util.UrlShortenerConstants;

@Component
@Document(collection = "urls")
public class UrlTO {

	@Id
	private String id;
	@NotNull (message = UrlShortenerConstants.MISSING_ORIGINAL_URL)
	private String originalUrl;
	private String alias;
	private Integer accessCounter;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getAccessCounter() {
		return accessCounter;
	}
	public void setAccessCounter(Integer accessCounter) {
		this.accessCounter = accessCounter;
	}
	@Override
	public String toString() {
		return "UrlTO [id=" + id + ", originalUrl=" + originalUrl + ", alias=" + alias
				+ ", accessCounter=" + accessCounter + "]";
	}

	
	
}
