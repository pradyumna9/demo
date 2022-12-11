package com.example.demo.api;


import com.example.demo.Repo.PropertyRepo;
import com.example.demo.entity.Property;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class PropertyControllerTest {

    @Mock
    private PropertyRepo propertyRepo;
    @InjectMocks
    private PropertyController propertyController;


    public void success(){
        when(propertyRepo.save(Mockito.any(Property.class))).thenReturn(any(Property.class));
        //propertyController = new PropertyController(propertyRepo, agent, server);
        Property property = new Property();
       Property property1 =  propertyController.saveProperty(property);
       Mockito.verify(propertyRepo,Mockito.times(1)).save(any(Property.class));
    }


}