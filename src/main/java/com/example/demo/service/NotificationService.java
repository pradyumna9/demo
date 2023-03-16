package com.example.demo.service;

import com.example.demo.model.Notifiable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    @Async
    public String sendNotification(Notifiable notifiable){
        LOGGER.info("Child Thread:"+Thread.currentThread().getName());
        String id = notifiable.getId();
        String creditorName = notifiable.getCreditorName();
        String creditorAccount = notifiable.getCreditorAccountNumber();
        notifiable.setId("Id:"+id);
        notifiable.setCreditorName("creditorName:"+creditorName.toUpperCase());
        LOGGER.info("creditor Name:{},Creditor Account Number{}",creditorName,creditorAccount);
        notifiable.setCreditorAccountNumber("creditorAccount:"+creditorAccount);
        return "Notification Send Successfully:";
    }
}
