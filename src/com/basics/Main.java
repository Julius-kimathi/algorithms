package com.basics;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        /*
        data types
        1.primitive types-
        byte,short,int,long-storing numbers without decimals
        float,double -store numbers with decimals
        char,boolean
        2.Reference types - all other types

        byte myAge = 30;
        long viewCount = 3_123_456_789L;
        float price = 12.70F;
        char letter = 'A';
        boolean isElligible = false;
        String message = "c:\nwindows\\..";
        Date now = new Date();
        System.out.println(message);


          double insurancePremiums = 500, personalRelief = 1408, mortgage = 5000, homeSavingsPlan = 3000,  grossTaxablePay = 4000;
        double totalRelief = algorithm.calculateTotalRelief(insurancePremiums, personalRelief);
        double nssf = algorithm.calculateNssf(grossTaxablePay);
        double nhif = algorithm.calculateNhif(grossTaxablePay);
        double totalDeductions = algorithm.calculateTotalDeductions(mortgage, nssf, homeSavingsPlan);
            String payeFormatted = algorithm.calculatePaye(grossTaxablePay, totalRelief, totalDeductions);
         */


        //Driver code
        Algorithm algorithm = new Algorithm();
       // int []  arr = { 1, 2, 3, 4, 5 };
      //  System.out.println("The two numbers are :" +algorithm.getNumbers( arr, 6, 5));
        System.out.println("Nssf amount: " + algorithm.calculateNssf(75000));

    }
}