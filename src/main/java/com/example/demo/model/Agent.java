package com.example.demo.model;

import lombok.Data;

import java.util.Map;

@Data
public class Agent {
    private String name;
    private String address;
    private Map<String,String> addressMap;
}
