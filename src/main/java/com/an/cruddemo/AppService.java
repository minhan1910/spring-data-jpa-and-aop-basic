package com.an.cruddemo;

import org.springframework.stereotype.Service;

@Service
public class AppService {
    public void addAccount() {
        System.out.println(getClass() + ": add account");
    }
}
