package com.zx.rxjava;

public class BaseApiResponse<T>{

    private String Result;
    private String Error;


    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }



}
