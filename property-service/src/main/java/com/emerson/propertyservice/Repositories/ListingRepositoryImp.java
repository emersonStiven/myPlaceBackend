/*
package com.emerson.propertyservice.Repositories;

import com.emerson.propertyservice.Models.Listing;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.bulk.DeleteRequest;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import java.util.*;
import static com.mongodb.client.model.Filters.eq;


@Repository
public class ListingRepositoryImp {
    @Autowired
    private MongoTemplate mongoTemplate;


    public Listing createListing(Listing listing){
        return mongoTemplate.save(listing);
    }

    public List<Listing> findByHost(String host){
        Query query = new Query(Criteria.where("host").is(host));
        return mongoTemplate.find(query, Listing.class);
    }

    public Optional<Listing> findById(String id){
        Query q = new Query(Criteria.where("host").is(id));
        Optional<Listing> l = Optional.ofNullable(mongoTemplate.findOne(q, Listing.class));
        System.out.println(l);
        return l;
    }

    public List<Listing> findAllById(List<String> list){
        Query q = new Query(Criteria.where("id") .all(list));
        return mongoTemplate.findAllAndRemove(q, "properties");
    }

    public boolean deleteById(String id){
        DeleteResult d = mongoTemplate.remove(Criteria.where("id").is(id));
        return d.wasAcknowledged();
    }

    public boolean deleteByAllById(List<String> l){
        return mongoTemplate.remove(Criteria.where("id").is(l)).wasAcknowledged();
    }

    public boolean updateField(String field, String newValue){
        Query q = new Query(Criteria.where(field).is(newValue));
        Update u = Update.update(field, newValue);
        UpdateResult r = mongoTemplate.updateFirst(q, u, Listing.class);
        return r.wasAcknowledged();
    }




}


 */