package com.basics;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.*;

public class Algorithm {


/*
Gross taxable income = Basic pay + Taxable benefits + Allowances
Net taxable income= Gross taxable income - Deductions
Deductions:Nssf(up to 1,080),mortgage(up to 25,000/m) and home ownership savings plan(up to 4,000/m)
Reliefs:Personal relief(1,408/m) ,Insurance relief(15% of premiums paid, up to 5,000/m)
Read more: https://www.tuko.co.ke/273392-kra-tax-calculator-kra-paye-tax-rates-2019-2020.html
 */

    /*
       Upper Earning Limit (UEL) - 18,000
       Lower Earnings Limit (LEL) - 6,000
               */
    public static double calculateNssf(double grossEarnings){
        double nssf = 0, uel = 18000, lel = 6000, tier1 = 0, tier2 = 0;
        if(grossEarnings <= lel)
            nssf = 0.06 * grossEarnings;
        else if(grossEarnings <= uel) {
            tier1 = lel * 0.06;
            tier2 = (grossEarnings - lel) * 0.06;
            nssf = tier1 + tier2;
        }
        else if(grossEarnings > uel)
            nssf = 1080;
        else
            nssf = 0;

        return nssf;
    }

    public static double calculateTotalDeductions(double mortgage, double nssf, double homeSavingsPlan){
        double totalDeductions = 0;
        if(mortgage >= 25000)
            mortgage = 25000;
        if(homeSavingsPlan >= 4000)
            homeSavingsPlan = 4000;
        if(mortgage > 0 && homeSavingsPlan > 0)
            totalDeductions = mortgage + nssf;
        else
            totalDeductions = mortgage + nssf + homeSavingsPlan;

        return totalDeductions;

    }
    public static double calculateTotalRelief(double insurancePremiums, double personalRelief){
        double totalRelief = 0;
        if((insurancePremiums * 0.15) > 5000)
            totalRelief = personalRelief + 5000;
        else
            totalRelief = personalRelief + (insurancePremiums * 0.15);
        return totalRelief;
    }

    public static String calculatePaye(double grossTaxablePay, double totalRelief, double totalDeductions){
        double  incomeTax =0, paye=0;
        double firstBand = 12298, secondBand = 23885, thirdBand = 35472, fourthBand = 47059, bandWidth = 11587;
        //calculate net taxable pay
        double netTaxablePay = grossTaxablePay - totalDeductions;
        if(netTaxablePay <= firstBand)
            incomeTax = netTaxablePay * 0.1;
        else if(netTaxablePay <= secondBand)
            incomeTax = (firstBand * 0.1) + ((netTaxablePay - firstBand) * 0.15);
        else if(netTaxablePay <= thirdBand)
            incomeTax = (firstBand * 0.1) + (bandWidth * 0.15) + ((netTaxablePay - secondBand) * 0.2);
        else if(netTaxablePay <= fourthBand)
            incomeTax = (firstBand * 0.1) + (bandWidth * 0.15) + (bandWidth * 0.2) + ((netTaxablePay - thirdBand) * 0.25);
        else if(netTaxablePay > fourthBand)
            incomeTax = (firstBand * 0.1) + (bandWidth * 0.15) + (bandWidth * 0.2) + (bandWidth * 0.25) + ((netTaxablePay - fourthBand) * 0.3);
        else
            System.out.println("invalid net taxable pay!");

        //calculate paye by subtracting total relief from the income tax
        if(totalRelief >= incomeTax)
            paye = 0;
        else
            paye = incomeTax - totalRelief;

        //format the paye
        NumberFormat numberformat = NumberFormat.getCurrencyInstance();
        numberformat.setCurrency(Currency.getInstance("KES"));
        String payeFormatted = numberformat.format(paye);
        return payeFormatted;
    }

    public static double calculateNhif(double grossEarning){
        double nhif = 0;
        if(grossEarning < 6000)
            nhif = 150;
        else if(grossEarning < 8000)
            nhif = 300;
        else if(grossEarning < 12000)
            nhif= 400;
        else if(grossEarning < 15000)
            nhif = 500;
        else if(grossEarning < 20000)
            nhif = 600;
        else if(grossEarning < 25000)
            nhif = 750;
        else if(grossEarning < 30000)
            nhif = 850;
        else if(grossEarning < 35000)
            nhif = 900;
        else if(grossEarning < 40000)
            nhif = 950;
        else if(grossEarning < 45000)
            nhif = 1000;
        else if(grossEarning < 50000)
            nhif = 1100;
        else if(grossEarning < 60000)
            nhif = 1200;
        else if(grossEarning < 70000)
            nhif = 1300;
        else if(grossEarning < 80000)
            nhif = 1400;
        else if(grossEarning < 90000)
            nhif = 1500;
        else if(grossEarning < 100000)
            nhif = 1600;
        else if(grossEarning >= 100000)
            nhif = 1700;

        return nhif;
    }

    /*
    A function to calculate monthly payments for a mortgage loan
    Parameters : principal loan amount, annual interest rate and period in years
    formula : M = P ( r(1+r)^n / (1+r)^n-1  )
    where :M - is your monthly payment.
           P -  is your principal.
           r - is your monthly interest rate, calculated by dividing your annual interest rate by 12.
           n - is your number of payments (the number of months you will be paying the loan)[6]
     */
public void calculateMortgage(){
    //Declare global variables
    int principal;
    float monthlyRate, paymentMonths ;

    //Mortgage calculator function
    Scanner scanner = new Scanner(System.in);

    //Get principal amount from the user
    while (true) {//use infinite loop with a terminating condition to capture data
        System.out.println("Principal amount(1M to 10M):");
        principal = scanner.nextInt();
        if(principal >= 1_000_000 &&  principal <= 100_000_000)
            break;
        System.out.println("Loan amount must be between 1,000,000 and 100,000,000");
    }
    //Get annual interest rate from the user
    while (true) {
        System.out.println("Annual Interest rate( eg..6,10, 15 etc..):");
        float rate = scanner.nextFloat();
        if(rate >= 1 && rate <= 30) {
            monthlyRate = rate / 100 / 12;
            break;
        }
        System.out.println("Interest rate must be between 1 and 30 percent:");
    }
    //Get the payment period from the user
    while (true) {
        System.out.println("Period(Years):");
        float years = scanner.nextFloat();
        if(years >= 1 && years <= 30) {
            paymentMonths = years * 12;
            break;
        }
        System.out.println("Period of payment must between 1 to 30 years");
    }
    //calculate the monthly payments applicable for this loan amount
    double mortgage = principal * ((monthlyRate * (Math.pow(1 + monthlyRate, paymentMonths)))
            / (Math.pow(1 + monthlyRate, paymentMonths) - 1));

    //Format the amount in currency format and output the result to the user
    NumberFormat numberformat = NumberFormat.getCurrencyInstance();
    numberformat.setCurrency(Currency.getInstance("KES"));
    String mortgageFormatted = numberformat.format(mortgage);
    System.out.println(mortgageFormatted);
}

/*
Function to check if a string is a palindrome
-a palindrome reads the same after reversing the character order
e.g deleveled,madam,dad,mom,abiba,12321 e.t.c
 */
public boolean isPalindrome(String s){
       String  letters = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        int n = letters.length();
        for(int i=0; i < (n/2); i++){
            if(s.charAt(i) != s.charAt(n-i-1))
                return false;
        }
        return true;
}
/*
a prime number is only divisible by one and itself
e.g 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
 */
public static boolean isPrime (int number) {
    if(number < 2) {
        return false;
    }
    int check = (int) Math.sqrt(number);

    for(int i = 2; i <= check; i++) {
        if(number % i == 0) {
            return false;
        }
    }
    return true;
}


//  Lowest common multiple for two integers
    public static int lcm(int First_number, int Second_number) {
            int x,max=0,min=0,lcm=0;
            if(First_number>Second_number)
            {
                max=First_number;
                min=Second_number;
            }
            else
            {
                max=Second_number;
                min=First_number;
            }
            for(int i=1;i<=min;i++)
            {
                x=max*i;
                if(x%min==0)
                {
                    lcm=x;
                    break;
                }
            }
            return lcm;
        }

/*
A function to print the fibonacci series up to a given n number
In mathematics, the Fibonacci numbers, commonly denoted Fn form a sequence, called the Fibonacci sequence,
such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,[1]
0,1, 1, 2, 3, 5, 8, 13, 21, 34
 */
    public static void fibonacciSequence(int num){
        int t1 = 0, t2 = 1;
        System.out.print("First " + num + " series: ");
        for (int i = 1; i <= num; ++i)
        {
            System.out.print(t1 + " ");
            int sum = t1 + t2;
            t1 = t2;
            t2 = sum;
        }
    }

    /*
    A function to find length of a string
     */
    public static int stringLength()
    {
        int i=0;
        String str;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string");
        str=sc.nextLine();
        char ch[]=str.toCharArray();

        for(char c : ch)
        {
            i++;
        }
        return i;
    }

    public static HashMap<String, Integer> getNumbers(int [] arr, Integer noValue, Integer ArraySize){
        //convert array to Hashmap
        HashMap<String, Integer> numbers = new HashMap<String, Integer>();

        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            hmap.put(i, arr[i]);
        }

        //loop through the array list
        for(int i=0; i<ArraySize; i++){
         int number1 = arr[i];
            Iterator hmIterator = hmap.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)hmIterator.next();
                int number2 = ((int)mapElement.getValue());
                int sum = number1 + number2;
                if(sum == noValue){
                    numbers.put("firstNumber", number1);
                    numbers.put("secondNumber", number2);
                    break;
                }

            }

        }
        return numbers;
    }

    }
