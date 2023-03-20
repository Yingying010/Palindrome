package com.example.demo.controller;

import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.textDataset;
import com.example.demo.service.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Relation;

@RestController
@Slf4j
public class controller {

    @Autowired
    public ServiceImpl inspector;

    @PostMapping("/palindrome")
    public Result inspector(@RequestParam String username, @RequestParam String text){
        //check text if it is validation
        boolean isValid=inspector.isValid(text);
        //when valid is false
        if(!isValid){
            log.error("Reject values with numbers, spaces and punctuation.");
            return Result.result(Constants.CODE_200,"Reject values with numbers, spaces and punctuation");
        }

        //search in database
        textDataset exist = inspector.findText(text);
        if (exist != null){
            log.info("The data already exists in the database.");
            if(exist.isResult()){
                log.info("This text value is not a palindrome");
                return Result.result(Constants.CODE_200,"This text value is a palindrome");
            }else{
                log.info("This text value is a palindrome");
                return Result.result(Constants.CODE_200,"This text value is not a palindrome");
            }
        }

        //check text if it is palindrome
        boolean isP=inspector.isPalindrome(text);

        //store text in database
        textDataset dataset = new textDataset(text,isP);
        String store=inspector.storeInMongodb(dataset);
        log.info(store);

        if(!isP){
            log.info("This text value is not a palindrome");
            return Result.result(Constants.CODE_200,"This text value is not a palindrome");
        }else{
            log.info("This text value is a palindrome");
            return Result.result(Constants.CODE_200,"This text value is a palindrome");
        }



    }

}
