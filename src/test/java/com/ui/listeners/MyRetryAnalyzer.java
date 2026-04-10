 package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;

public class MyRetryAnalyzer implements IRetryAnalyzer{

    private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(Env.INT).getMAX_NUMBER_OF_ATTEMPTS();
    private int currentAttempt = 1;

    @Override
    public boolean retry(ITestResult result) {
        if(currentAttempt <= MAX_NUMBER_OF_ATTEMPTS){
            currentAttempt++;
            return true;
        }
        return false;
    }


}
