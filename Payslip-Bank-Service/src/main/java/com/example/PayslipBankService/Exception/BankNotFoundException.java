package com.example.PayslipBankService.Exception;

public class BankNotFoundException extends RuntimeException{
    public BankNotFoundException(String message){
        super(message);
    }
}
