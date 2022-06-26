package com.example.demo.Repo;

import com.example.demo.entity.Property;
import org.springframework.data.repository.CrudRepository;


public interface PropertyRepo extends CrudRepository<Property,String> {
}
