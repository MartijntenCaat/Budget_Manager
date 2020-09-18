package budget;

import java.math.BigDecimal;

public class Purchase {
    private PurchaseType type;
    private String name;
    private BigDecimal price;

    public Purchase() {
        this.type = null;
    }

    public PurchaseType getType() {
        return type;
    }

    public void setType(PurchaseType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
