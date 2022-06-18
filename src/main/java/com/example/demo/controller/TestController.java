package com.example.demo.controller;

import com.example.demo.exception.AppException;
import com.example.demo.exception.InternalStandardErrors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;


@RestController
@Validated
public class TestController {

    @Value("${app.name}")
    private String name;

    @GetMapping("/send-otp")
    public String sendOtp(@Pattern(regexp = "^[0-9]{10}$",message = "Mobile Number should be 10 digit")@RequestParam String mobileNumber){
     //return "OTP send Successfully";

     boolean isValidMobileNumber = false;
     if(!isValidMobileNumber){
         throw new AppException(InternalStandardErrors.MOBILE_NUMBER_NOT_EXIST);
     }
        return "OTP send Successfully";

    }

    @GetMapping("/prof")
    public String getDataFromProfile(){
        return "Welcome to "+name;
    }
}
