package com.bemobi.shortener.service;

import java.security.MessageDigest;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bemobi.shortener.exception.ShortenerException;
import com.bemobi.shortener.model.UrlTO;
import com.bemobi.shortener.repository.UrlRepository;
import com.bemobi.shortener.service.to.StatisticsTO;
import com.bemobi.shortener.service.to.UrlServiceResponseTO;

@Component
public class UrlService {

	private static final Logger log = LoggerFactory.getLogger(UrlService.class);

	private String domain = "stnr.bmb/"; 

	@Autowired
	private UrlRepository urlRepository;
	
	
	public UrlServiceResponseTO shorter(String url, String alias) throws ShortenerException{

		
			log.info("Iniciando método shorter");
			
			long tStart = System.currentTimeMillis();
			
			UrlTO urlTo = null;
			
			if(alias != null){
				urlTo = urlRepository.findByAlias(alias);
			}
			if(urlTo == null){
				
				urlTo = new UrlTO();
				
				urlTo.setOriginalUrl(url);
				urlTo.setAccessCounter(0);
				String shortUrl = "";
				if(alias != null){
					urlTo.setAlias(alias);
					shortUrl = domain + urlTo.getAlias();
				}else{
					generateShortUrl(urlTo);	
					shortUrl = domain + urlTo.getAlias();
				}
				
				urlRepository.create(urlTo);
				
				long tEnd = System.currentTimeMillis();
				StatisticsTO statistics = new StatisticsTO();
				statistics.setElapsedTime(tEnd - tStart);
				
				UrlServiceResponseTO urlServiceResponse = new UrlServiceResponseTO(
						urlTo.getOriginalUrl(), 
						urlTo.getAlias(), 
						shortUrl, 
						statistics,
						true,
						"000",
						"Sucesso",
						200);
				
				log.info("Finalizando método shorter com sucesso");
				return urlServiceResponse;
				
			} else {
				
				long tEnd = System.currentTimeMillis();
				StatisticsTO statistics = new StatisticsTO();
				statistics.setElapsedTime(tEnd - tStart);
				UrlServiceResponseTO urlServiceResponse = new UrlServiceResponseTO(
						null, 
						urlTo.getAlias(), 
						null, 
						statistics,
						true,
						"001",
						"O álias já existe.",
						200);
				log.info("Finalizando método shorter com sucesso");
				return urlServiceResponse;
			}
	}
	
	public UrlServiceResponseTO retriever(String shortUrl) throws  ShortenerException{
		
		log.info("Iniciando método retriever");
		
		long tStart = System.currentTimeMillis();
		
		UrlTO urlTo = new UrlTO();
		
		String alias = getAlias(shortUrl);
		
		if (alias != null){
			urlTo = urlRepository.findByAlias(alias);
		} else {
			long tEnd = System.currentTimeMillis();
			StatisticsTO statistics = new StatisticsTO();
			statistics.setElapsedTime(tEnd - tStart);
			UrlServiceResponseTO urlServiceResponse = new UrlServiceResponseTO(
					null, 
					alias, 
					null, 
					statistics,
					false,
					"002",
					"Url inválida",
					200);
			log.info("Finalizando método retriever com sucesso");
			return urlServiceResponse;
		}
		
		if(urlTo != null){
			
			urlTo.setOriginalUrl(verifyURL(urlTo.getOriginalUrl()));
			urlRepository.updateAccessCounter(urlTo);
			
			long tEnd = System.currentTimeMillis();
			StatisticsTO statistics = new StatisticsTO();
			statistics.setElapsedTime(tEnd - tStart);
			
			UrlServiceResponseTO urlServiceResponse = new UrlServiceResponseTO(
					urlTo.getOriginalUrl(), 
					urlTo.getAlias(), 
					domain+urlTo.getAlias(), 
					statistics,
					true,
					"000",
					"Sucesso",
					200);
			
			log.info("Finalizando método retriever com sucesso");
			return urlServiceResponse;
			
		} else {
			
			long tEnd = System.currentTimeMillis();
			StatisticsTO statistics = new StatisticsTO();
			statistics.setElapsedTime(tEnd - tStart);
			UrlServiceResponseTO urlServiceResponse = new UrlServiceResponseTO(
					null, 
					alias, 
					null, 
					statistics,
					false,
					"003",
					"O álias não existe.",
					200);
			log.info("Finalizando método retriever com sucesso");
			return urlServiceResponse;
		}
	}
	
	public List<UrlTO> getTenMore(String quantity){
		
		if(quantity != null){
			return urlRepository.getTheTenMore(Integer.valueOf(quantity));
		} else {
			return urlRepository.getTheTenMore(null);
		}
		
	}
	
	private String getAlias(String url){
		if (url.substring(0, 16).equals("http://stnr.bmb/")){
			String alias = url.replace("http://stnr.bmb/", "");
			return alias;
		} else {
			return null;
		}
	}
	
	private String verifyURL(String url) {
		
		if (url.substring(0, 7).equals("http://"))
			return url;
		else if (url.substring(0, 8).equals("https://"))
			return url;
		else if (url.charAt(url.length() - 1) == '/'){
			url = url.substring(0, url.length() - 1);
			return url;
		}else{
			url = "http://" + url;
			return url;
		}
		
	}
	
	
	private void generateShortUrl(UrlTO urlTo) throws ShortenerException{
		
		try {
			
			MessageDigest md = MessageDigest.getInstance( "SHA-256" ); 
			md.update(urlTo.getOriginalUrl().getBytes()); 
			byte[] hash = md.digest();
			String alias = hash.toString(); 
			urlTo.setAlias(alias);
			
		} catch (Exception e) {
			log.info("O álias já existe.");
			throw new ShortenerException("003", "Erro ao tentar encurtar url", null);
		}
	}
	
}
