package com.bemobi.shortener.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bemobi.shortener.exception.ShortenerException;
import com.bemobi.shortener.model.UrlTO;
import com.bemobi.shortener.service.UrlService;
import com.bemobi.shortener.service.to.UrlServiceResponseTO;

@RestController
@RequestMapping
public class UrlRetrieveController {
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private UrlService urlService;
	
	@GetMapping("/retrieve")
    public UrlServiceResponseTO retriever(@PathParam(value = "shortUrl") String shortUrl) throws ShortenerException, IOException{
		
			UrlServiceResponseTO urlServiceResponseTo = urlService.retriever(shortUrl);
			if(urlServiceResponseTo.getUrl() != null){
				response.sendRedirect(urlServiceResponseTo.getUrl());
			}
	       
			return urlServiceResponseTo;                
    }
	
	@GetMapping("/more-accessed")
    public List<UrlTO> getMoreAccessed(@RequestParam(value = "quantity", required = false) String quantity){
		
		return urlService.getTenMore(quantity);
    }
	
	
}
