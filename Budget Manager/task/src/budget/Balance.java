package budget;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal balance;

    public Balance(){
        this.balance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
