package com.example.PayslipTaxService.Exception;

public class TaxNotFoundException extends RuntimeException{
    public TaxNotFoundException(String message){
        super(message);
    }
}
