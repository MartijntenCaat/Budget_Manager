package budget;

import java.math.BigDecimal;

public class Income {
    private BigDecimal income;

    public Income() {
        this.income = BigDecimal.ZERO;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
