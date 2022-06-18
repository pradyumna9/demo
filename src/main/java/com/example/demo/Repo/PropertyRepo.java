package com.example.demo.Repo;

import com.example.demo.entity.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepo extends CrudRepository<Property,String> {
}
