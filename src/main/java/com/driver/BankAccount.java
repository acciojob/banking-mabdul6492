package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        String accountNumber;
        if((digits*9) < sum) throw new Exception("Account Number can not be generated");
        else {
            accountNumber = "9".repeat(sum/9);
            int rem =sum%9;
            if(rem > 0) accountNumber += rem;
            accountNumber += "0".repeat(digits-accountNumber.length());
        }

        return accountNumber;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if((this.balance-amount) < this.minBalance) throw new Exception("Insufficient Balance");
        else this.balance -= amount;
    }

}