package budget;

import java.math.BigDecimal;

public class Purchase {
    private PurchaseType type;
    private String description;
    private BigDecimal price;

    public Purchase(PurchaseType type, String description, BigDecimal price) {
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public PurchaseType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
