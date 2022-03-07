package com.example.emybank.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "transaction_histories")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    @Column(name = "sender_id",insertable = false,updatable = false)
    private Long sender_id;
    private Long receiver_id;
    private BigDecimal amount;
    private String message;
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate created_at;
    private String status;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnore
    User user;

    @JsonCreator
    public TransactionHistory(@NotNull @JsonProperty("accountFromId") Long sender_id,
                              @NotNull @JsonProperty("accountToId") Long receiver_id,
                              @JsonProperty("amount") BigDecimal amount) {
        super();
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.amount = amount;
    }

    @JsonCreator
    public  TransactionHistory(){
        super();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sender_id == null) ? 0 : sender_id.hashCode());
        result = prime * result + ((receiver_id == null) ? 0 : receiver_id.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TransactionHistory other = (TransactionHistory) obj;
        if (sender_id == null) {
            if (other.sender_id != null)
                return false;
        } else if (!sender_id.equals(other.sender_id))
            return false;
        if (receiver_id == null) {
            if (other.receiver_id != null)
                return false;
        } else if (!receiver_id.equals(other.receiver_id))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        return true;
    }
}
