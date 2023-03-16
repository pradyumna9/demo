package com.example.demo.api;

import com.example.demo.Repo.PropertyRepo;
import com.example.demo.entity.Property;
import com.example.demo.exception.InternalStandardErrors;
import com.example.demo.exception.PropertyNotFoundException;
import com.example.demo.model.Agent;
import com.example.demo.model.CountryMap;
import com.example.demo.model.Notifiable;
import com.example.demo.model.Server;
import com.example.demo.service.NotificationService;
import io.swagger.annotations.ApiOperation;
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
    private final CountryMap countryMap;
    private final NotificationService notificationService;

    public PropertyController(PropertyRepo propertyRepo, @Qualifier("baby") Agent agent, Server server, CountryMap countryMap, NotificationService notificationService) {
        this.propertyRepo = propertyRepo;
        this.agent = agent;
        this.server = server;
        this.countryMap = countryMap;
        this.notificationService = notificationService;
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

    @PostMapping("/notify")
    public String sendNotification(@RequestBody Notifiable notifiable){
        LOGGER.info("Parent Thread:"+Thread.currentThread().getName());
        String message =  notificationService.sendNotification(notifiable);
        return "Successfully Send Notification";
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
