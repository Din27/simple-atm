package com.chelyadin.test.simple_atm.domain;

import com.chelyadin.test.simple_atm.exception.WithdrawNotEnoughMoneyException;
import com.chelyadin.test.simple_atm.exception.WithdrawRulesConflictException;
import com.chelyadin.test.simple_atm.exception.WithdrawZeroAmountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author Dmitriy Chelyadin
 */
@Entity
@Table(name = "credit_cards")
public class CreditCard implements UserDetails {

    private static final Integer FAILED_LOGINS_TO_BLOCK = 4;
    private static final Logger logger = LoggerFactory.getLogger(CreditCard.class);

    @Id
    @Column(name = "number", length = 16, nullable = false, updatable = false)
    private String number;

    @Column(name = "pin", length = 100, nullable = false)
    private String pin;

    @Column(name = "amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "failed_login_attempts", nullable = false)
    private Integer failedLoginAttempts = 0;

    public CreditCard() {}

    public CreditCard(String number, String pin, BigDecimal amount) {
        this.number = number;
        this.pin = pin;
        this.amount = amount;
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

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    /**
     * IMPORTANT! This method should be run in @Transactional context!
     */
    public void incrementFailedLoginAttempts() {
        failedLoginAttempts++;
    }

    /**
     * IMPORTANT! This method should be run in @Transactional context!
     */
    public void resetFailedLoginAttempts() {
        failedLoginAttempts = 0;
    }

    /**
     * IMPORTANT! This method should be run in @Transactional context!
     */
    public BigDecimal withdraw(BigDecimal withdrawalAmount) {
        if (withdrawalAmount.compareTo(new BigDecimal(0)) < 0) {
            logger.info(String.format("Withdrawal amount is negative: %s", withdrawalAmount));
            throw new WithdrawRulesConflictException("Withdrawal amount is negative");
        } else if (withdrawalAmount.compareTo(new BigDecimal(0)) == 0) {
            logger.info(String.format("Withdrawal amount is zero: %s", withdrawalAmount));
            throw new WithdrawZeroAmountException("Withdrawal amount is zero");
        }
        if (amount.compareTo(withdrawalAmount) < 0) {
            logger.info(String.format("Not enough money on card %s to withdraw %s", number, withdrawalAmount));
            throw new WithdrawNotEnoughMoneyException("Not enough money to withdraw");
        }
        this.amount = this.amount.subtract(withdrawalAmount);
        logger.info(String.format("%s$ were withdrawn from card %s", withdrawalAmount, number));
        return this.amount;
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
        return failedLoginAttempts < FAILED_LOGINS_TO_BLOCK;
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
        if (failedLoginAttempts != null ? !failedLoginAttempts.equals(that.failedLoginAttempts) : that.failedLoginAttempts != null)
            return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (pin != null ? !pin.equals(that.pin) : that.pin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (failedLoginAttempts != null ? failedLoginAttempts.hashCode() : 0);
        return result;
    }
}
