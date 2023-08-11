package com.springmongo.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springmongo.springbootmongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
}
