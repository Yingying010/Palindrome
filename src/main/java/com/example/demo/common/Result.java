package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result{
    private String code;
    private String msg;

    public static Result result(String code, String msg){
        return new Result(code,msg);
    }


}
