package com.chelyadin.test.simple_atm.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author Dmitriy Chelyadin
 */
@Entity
@Table(name = "creditcards")
public class CreditCard implements UserDetails {

    @Id
    @Column(name = "number", length = 16, nullable = false, updatable = false)
    private String number;

    @Column(name = "pin", length = 4, nullable = false)
    private String pin;

    @Column(name = "amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    public CreditCard() {}

    public CreditCard(String number, String pin) {
        this.number = number;
        this.pin = pin;
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public String getUsername() {
        return number;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (pin != null ? !pin.equals(that.pin) : that.pin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
