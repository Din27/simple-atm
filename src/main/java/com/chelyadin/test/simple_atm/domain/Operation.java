package com.chelyadin.test.simple_atm.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Dmitriy Chelyadin
 */
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "card_id", length = 16, updatable = false, nullable = false)
    private String cardId;

    @Column(name = "time", updatable = false, nullable = false)
    private Date time;

    @Column(name = "operation_code", length = 20, updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationCode operationCode;

    @Column(name = "withdrawal_amount", precision = 15, scale = 2, updatable = false, nullable = true)
    private BigDecimal withdrawalAmount;

    public Operation(String cardId, Date time, OperationCode operationCode) {
        this(cardId, time, operationCode, null);
    }

    public Operation(String cardId, Date time, OperationCode operationCode, BigDecimal withdrawalAmount) {
        this.cardId = cardId;
        this.time = time;
        this.operationCode = operationCode;
        this.withdrawalAmount = withdrawalAmount;
    }

    public Long getId() {
        return id;
    }

    public String getCardId() {
        return cardId;
    }

    public Date getTime() {
        return time;
    }

    public OperationCode getOperationCode() {
        return operationCode;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (cardId != null ? !cardId.equals(operation.cardId) : operation.cardId != null) return false;
        if (id != null ? !id.equals(operation.id) : operation.id != null) return false;
        if (operationCode != operation.operationCode) return false;
        if (time != null ? !time.equals(operation.time) : operation.time != null) return false;
        if (withdrawalAmount != null ? !withdrawalAmount.equals(operation.withdrawalAmount) : operation.withdrawalAmount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (operationCode != null ? operationCode.hashCode() : 0);
        result = 31 * result + (withdrawalAmount != null ? withdrawalAmount.hashCode() : 0);
        return result;
    }
}
