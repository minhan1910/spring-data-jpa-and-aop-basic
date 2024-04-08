package com.an.cruddemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {
    public void addAccount() {
        System.out.println(getClass() + ": add acccount");
    }

    public void updateAccount() {
        System.out.println(getClass() + ": update acccount");
    }
}
