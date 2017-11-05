package com.bemobi.shortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public abstract class AbstractRepository {

	@Autowired
	protected MongoOperations mongoOperation;
	
	
}
