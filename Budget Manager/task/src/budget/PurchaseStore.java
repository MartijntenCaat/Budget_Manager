package budget;

import java.util.LinkedHashSet;

public class PurchaseStore {
    private final LinkedHashSet<Purchase> purchaseStore;
    private Balance balance;
    private Income income;

    public PurchaseStore() {
        this.purchaseStore = new LinkedHashSet<>();
        this.balance = new Balance();
        this.income = new Income();
    }

    public LinkedHashSet<Purchase> getPurchaseStore() {
        return purchaseStore;
    }

    public void addPurchase(Purchase purchase) {
        purchaseStore.add(purchase);
    }

    public Balance getBalance() {
        return balance;
    }
}
