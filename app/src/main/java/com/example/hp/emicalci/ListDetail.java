package com.example.hp.emicalci;

/**
 * Created by hp on 18-03-2018.
 */

public class ListDetail {
    private double month, principle, interest, balance;

    ListDetail(double month, double principle, double interest, double balance){
        this.month = month;
        this.principle = principle;
        this.interest = interest;
        this.balance = balance;
    }

    public double getMonth(){
        return this.month;
    }
    public double getPrinciple(){
        return this.principle;
    }
    public double getInterest(){
        return this.interest;
    }
    public double getBalance(){
        return this.balance;
    }
}
