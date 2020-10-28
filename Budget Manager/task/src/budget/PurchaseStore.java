package budget;

import java.math.BigDecimal;
import java.util.LinkedHashSet;

public class PurchaseStore {
    private final LinkedHashSet<Purchase> purchaseStore;
    private BigDecimal balance;
    private BigDecimal income;

    public PurchaseStore() {
        this.purchaseStore = new LinkedHashSet<>();
        this.balance = BigDecimal.ZERO;
        this.income = BigDecimal.ZERO;
    }

    public LinkedHashSet<Purchase> getPurchaseStore() {
        return purchaseStore;
    }

    public void addPurchase(Purchase purchase) {
        purchaseStore.add(purchase);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void subtractFromBalance(BigDecimal purchasePrice) {
        setBalance(balance.subtract(purchasePrice));
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
