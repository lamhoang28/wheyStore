package com.example.wheystore_nhom6.DAO;

import java.util.regex.Pattern;

public class Validate {
    public boolean checkEmail(String email){
        String EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        boolean type = Pattern.matches(EMAIL_PATTERN,email);
        if (!type){
            return false;
        }
        return true;
    }
    public boolean checkNumber(String input){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        if (pattern.matcher(input).find()){
            return true;
        }
        return false;
    }

    public boolean checkInputName(String inputName){
        Pattern pattern = Pattern.compile("^[a-z_A-Z\\s]+$");

        if (pattern.matcher(inputName).find()){
            return true;
        }
        return false;
    }

}
