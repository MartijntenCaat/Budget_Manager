package budget;

import java.util.LinkedHashSet;

public class PurchaseStore {
    private final LinkedHashSet<Purchase> purchaseStore;

    public PurchaseStore() {
        this.purchaseStore = new LinkedHashSet<>();
    }

    public LinkedHashSet<Purchase> getPurchaseStore() {
        return purchaseStore;
    }

    public void addPurchase(Purchase purchase) {
        purchaseStore.add(purchase);
    }
}
