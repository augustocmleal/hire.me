package com.bemobi.shortener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bemobi.shortener.exception.ShortenerException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationcontext.xml")
public class UrlServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(UrlServiceTest.class);

	private static final String ORIGINAL_URL_TEST = "www.bemobi.com";
	
	private static final String ALIAS_TEST = "bemobi";
	
	@Test
	public void createTest() throws ShortenerException{
		
		log.info("Iniciando teste do método CREATE");
 		
 		log.info("Finalizando teste do método CREATE");

	}
}
