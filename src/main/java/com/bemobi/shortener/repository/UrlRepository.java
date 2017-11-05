package com.bemobi.shortener.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.bemobi.shortener.model.UrlTO;

@Repository
public class UrlRepository extends AbstractRepository{

	public void create(UrlTO urlTo){
		mongoOperation.insert(urlTo);
	}
	
	public UrlTO findByAlias(String alias){
		Query searchQuery = new Query(Criteria.where("alias").is(alias));
		UrlTO urlTo = new UrlTO();
		urlTo = mongoOperation.findOne(searchQuery, UrlTO.class);
		 
		return urlTo;
	}
	
	public void updateAccessCounter(UrlTO urlTo){
		int accessCounter = urlTo.getAccessCounter() + 1;

		Query searchQuery = new Query(Criteria.where("alias").is(urlTo.getAlias()));
		Update updateQuery = Update.update("accessCounter", accessCounter);;
		
		mongoOperation.updateFirst(searchQuery, updateQuery, UrlTO.class);
	}
	
	public List<UrlTO> getTheTenMore(Integer quantity){
		
		List<UrlTO> urls = new ArrayList<UrlTO>();
		Query searchQuery = new Query();
		
		if(quantity != null){
			searchQuery.limit(quantity);
		} else {
			searchQuery.limit(10);
		}
		
		searchQuery.with(new Sort(new Order(Direction.DESC, "accessCounter")));
		
		urls = mongoOperation.find(searchQuery, UrlTO.class);
		
		return urls;
	}
	
}
