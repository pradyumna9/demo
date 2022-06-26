package com.example.demo.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Server {
    private Map<String,String> applicationPortMap;
    private Map<String, List<String>> configMap;
    private Map<String,Credential> credentialMap;
   @Data
    private static class Credential{
        private String userName;
        private String password;
    }
}
