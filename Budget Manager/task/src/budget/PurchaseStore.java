package budget;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class PurchaseStore {
    private final LinkedHashMap<String, BigDecimal> purchaseStore;

    public PurchaseStore() {
        this.purchaseStore = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, BigDecimal> getPurchaseStore() {
        return purchaseStore;
    }

    public void addPurchase(String purchase, BigDecimal price) {
        purchaseStore.put(purchase, price);
    }
}
