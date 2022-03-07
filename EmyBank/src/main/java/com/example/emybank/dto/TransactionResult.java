package com.example.emybank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionResult {
    private Long sender_id;
    private BigDecimal balanceAfterTransfer;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sender_id == null) ? 0 : sender_id.hashCode());
        result = prime * result + ((balanceAfterTransfer == null) ? 0 : balanceAfterTransfer.hashCode());
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
        TransactionResult other = (TransactionResult) obj;
        if (sender_id == null) {
            if (other.sender_id != null)
                return false;
        } else if (!sender_id.equals(other.sender_id))
            return false;
        if (balanceAfterTransfer == null) {
            if (other.balanceAfterTransfer != null)
                return false;
        } else if (!balanceAfterTransfer.equals(other.balanceAfterTransfer))
            return false;
        return true;
    }
}
