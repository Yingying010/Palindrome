package com.example.demo.service;

import com.example.demo.entity.textDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;

@Service
public class ServiceImpl implements iService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean isPalindrome(String text) {
        int length=text.length();
        int rounds=0;
        boolean flag = true;

        //odd
        if(length%2!=0){
            rounds=length/2;
            int curr=rounds;

            for(int i=0;i<rounds;i++){
                int pre=rounds-i;
                int next=rounds+i;
                if(text.charAt(pre)!=text.charAt(next)){
                    flag=false;
                    break;
                }
            }

        //even
        }else{
            rounds=length/2-1;
            int curr=rounds;

            for(int i=0;i<rounds;i++){
                int pre=rounds-i;
                int next=rounds+i+1;
                if(text.charAt(pre)!=text.charAt(next)){
                    flag=false;
                    break;
                }
            }

        }

        if(flag){
           return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isValid(String text) {
        boolean flag=false;
        for(int i=0;i<text.length();i++){
            if((text.charAt(i)>=65 && text.charAt(i)<=90) || (text.charAt(i)>=97 && text.charAt(i)<=122)){
                flag=true;
            }else{
                flag=false;
                break;
            }
        }

        return flag;
    }

    public textDataset findText(String text) {
        Query query = new Query();
        Criteria criteria = new Criteria("text").is(text);
        query.addCriteria(criteria);
        textDataset find = mongoTemplate.findOne(query, textDataset.class, "dataset");
        if (find != null) {
            return find;
        } else {
            return null;
        }
    }

    public String storeInMongodb(textDataset dataset){
        if (dataset == null)
            return null;
        textDataset exist = findText(dataset.getText());
        if (exist != null)
            return "Text has already inserted";

        try {
            textDataset insert = mongoTemplate.insert(dataset, "dataset");
            if (insert != null)
                return "Inserted successfully";
            else
                return "Insertion failed";
        } catch (Exception e) {
            return e.getClass().getName() + ": " + e.getMessage();
        }
    }
}
