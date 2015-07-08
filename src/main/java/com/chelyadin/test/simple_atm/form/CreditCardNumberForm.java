package com.chelyadin.test.simple_atm.form;

/**
 * @author Dmitriy Chelyadin
 *
 * Object to gather result from credit card entering form on Home Page
 */
public class CreditCardNumberForm {

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCardNumberForm that = (CreditCardNumberForm) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
