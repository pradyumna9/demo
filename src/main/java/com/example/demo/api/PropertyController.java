package com.example.demo.api;

import com.example.demo.Repo.PropertyRepo;
import com.example.demo.entity.Property;
import com.example.demo.exception.InternalStandardErrors;
import com.example.demo.exception.PropertyNotFoundException;
import com.example.demo.model.Agent;
import com.example.demo.model.Server;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ApiOperation("Property API")
public class PropertyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);
    private final PropertyRepo propertyRepo;
    private final Agent agent;
    private final Server server;

    public PropertyController(PropertyRepo propertyRepo, @Qualifier("baby") Agent agent, Server server) {
        this.propertyRepo = propertyRepo;
        this.agent = agent;
        this.server = server;
    }

    @PostMapping("/save-property")
    @ApiOperation(value = "CREATE PROPERTY", httpMethod = "POST", nickname = "CREATE")
    public Property saveProperty(@RequestBody Property property){
        return propertyRepo.save(property);
    }

    @GetMapping("/getAllProperty")
    public List<Property> getAllProperty(){
        LOGGER.info("agent Name: "+agent.getName());
        return (List<Property>) propertyRepo.findAll();
    }

    @GetMapping("/getPropertyByPid")
    public Property getPropertyById(@RequestParam String propertyId){
        return getPropertyFromOptional(propertyId)
                .orElseThrow( ()-> new PropertyNotFoundException(InternalStandardErrors.PROPERTY_NOT_FOUND));
    }

    private Optional<Property> getPropertyFromOptional(String propertyId) {
        return propertyRepo.findById(propertyId);
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
