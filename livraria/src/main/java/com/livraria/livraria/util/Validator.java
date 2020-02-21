package com.livraria.livraria.util;

public class Validator {
    public boolean validString(String word) throws ValidatorException{
        if (word.equals("")) {
            throw new ValidatorException("String: " + word + " is empty.");
        } else if (word.startsWith(" ")) {
            throw new ValidatorException("String: " + word + " starts with space.");
        }
        return true;

    }

    public boolean validEmail(String email) throws ValidatorException{
        if (email.equals("admin"))
            return true;

        if (((email.endsWith(".com") || (email.endsWith(".com.br"))) && (email
                .matches("(.*)@(.*)")) == true)) {
            return true;
        } else {
            throw new ValidatorException("Email: " + email + " isn't valid");
        }
    }

    public boolean validValue(float value) throws ValidatorException {
        if (value <= 0)
            throw new ValidatorException("Value: " + value + "is <= 0");

        return value >= 0;
    }

    public boolean validValue(Double value) throws ValidatorException{
        if (value <= 0)
            throw new ValidatorException("Value: " + value + "is <= 0");

        return value >= 0;
    }
    public boolean validValueInt(int value) throws ValidatorException{
        if (!(value % 1 == 0 && value > 0))
            throw new ValidatorException("Value: " + value + "is <= 0");

        return value % 1 == 0 && value > 0;
    }


    public boolean validPassword(String password) throws ValidatorException{
        if (password.equals("")) {
            throw new ValidatorException("Password: " + password + "is empty");
        } else if (password.startsWith(" ")) {
            throw new ValidatorException("Password: " + password + "starts with space");
        } else if (password.length() < 5) {
            throw new ValidatorException("Password: " + password + "has less than 5 digits");
        }

        return true;
    }

}
