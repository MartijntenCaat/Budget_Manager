package budget;

import java.math.BigDecimal;

public class Purchase {
    private int id;
    private int latestId = 0;
    private PurchaseType type;
    private String name;
    private BigDecimal price;

    public Purchase() {
        this.id = latestId;
        latestId++;
    }

    public int getId() {
        return id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
