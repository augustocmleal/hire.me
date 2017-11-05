package com.bemobi.shortener.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bemobi.shortener.exception.ShortenerException;
import com.bemobi.shortener.service.UrlService;
import com.bemobi.shortener.service.to.UrlServiceResponseTO;

@RestController
@RequestMapping
public class UrlShortenController {

	@Autowired
	private UrlService urlService;
	
	@PutMapping("/shorten")
    public UrlServiceResponseTO shorter(@PathParam(value = "url") String url,
    						        @PathParam(value = "alias") String alias) throws ShortenerException{
		
			return urlService.shorter(url, alias);
			
    }
	
}
