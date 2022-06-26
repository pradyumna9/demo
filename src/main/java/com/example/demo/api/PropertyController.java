package com.example.demo.api;

import com.example.demo.Repo.PropertyRepo;
import com.example.demo.entity.Property;
import com.example.demo.exception.InternalStandardErrors;
import com.example.demo.exception.PropertyNotFoundException;
import com.example.demo.model.Agent;
import com.example.demo.model.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PropertyController {


    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private Agent agent;

    @Autowired
    private Server server;

    @PostMapping("/save-property")
    public Property saveProperty(@RequestBody Property property){
        return propertyRepo.save(property);
    }

    @GetMapping("/getAllProperty")
    public List<Property> getAllProperty(){
        System.out.println("agent Name: "+  agent.getName());
        return (List<Property>) propertyRepo.findAll();
    }

    @GetMapping("/getPropertyByPid")
    public Property getPropertyById(@RequestParam String propertyId){
        return getPropertyFromOptional(propertyId)
                .orElseThrow( ()-> new PropertyNotFoundException(InternalStandardErrors.PROPERTY_NOT_FOUND));
    }

    private Optional<Property> getPropertyFromOptional(String propertyId) {
        Optional<Property> property = propertyRepo.findById(propertyId);
        if(property.isPresent()){
            return property;
        }
        return Optional.empty();
    }

    @PutMapping("/updateProperty")
    public Property updateProperty(@RequestBody Property property){
        Property updateProperty =getPropertyById(property.getPropertyId());
        updateProperty.setPropertyName(property.getPropertyName());
        updateProperty.setAgentName(property.getAgentName());
        updateProperty.setPrice(property.getPrice());
        return propertyRepo.save(updateProperty);
    }
    @DeleteMapping("/deleteProperty/{propertyId}")
    public String deleteProperty(@PathVariable String propertyId){
        Property deleteProperty = getPropertyById(propertyId);
        propertyRepo.delete(deleteProperty);
        return "Property:"+deleteProperty.getPropertyName()+" deleted successfully";
    }
}
