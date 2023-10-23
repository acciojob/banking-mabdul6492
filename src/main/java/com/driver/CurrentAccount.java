package com.driver;

import java.util.ArrayList;
import java.util.List;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;

        if(balance < 5000) throw new Exception("Insufficient Balance");

    }

    private boolean isValid(String license){
        for(int i = 1; i < license.length(); i++){
            if(license.charAt(i-1) == license.charAt(i)) return false;
        }

        return true;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if(isValid(tradeLicenseId)) return;
        String newLicense = rearrange();
        if(isValid(newLicense)) tradeLicenseId = newLicense;
        else throw new Exception("Valid License can not be generated");
    }

    private String rearrange() {
        int[] freq = new int[26];
        for(char c: tradeLicenseId.toCharArray()) freq[c - 'A']++;

        List<int[]> freqList = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            if(freq[i] > 0) freqList.add(new int[]{i, freq[i]});
        }

        freqList.sort((a, b) -> (b[1] - a[1]));

        List<StringBuilder> licenseList = new ArrayList<>();
        for(int i = 0; i < freqList.get(0)[1]; i++) licenseList.add(new StringBuilder());

        int index = 0;
        for(int[] ch: freqList){
            for(int i = 0; i < ch[1]; i++){
                licenseList.get(index++).append((char)(ch[0]+'A'));
                index %= licenseList.size();
            }
        }

        StringBuilder newLicense = new StringBuilder();
        for(StringBuilder s: licenseList) newLicense.append(s);

        return newLicense.toString();
    }
}
