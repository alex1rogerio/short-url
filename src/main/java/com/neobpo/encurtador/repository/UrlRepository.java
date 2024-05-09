package com.neobpo.encurtador.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.neobpo.encurtador.entities.Url;

@Repository
public interface UrlRepository  extends MongoRepository<Url, String> {

}
