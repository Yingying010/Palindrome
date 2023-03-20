package com.example.demo.service;

import com.example.demo.entity.textDataset;

public interface iService {
    public boolean isPalindrome(String text);

    public boolean isValid(String text);

    public String storeInMongodb(textDataset dataset);
}
