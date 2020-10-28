package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class LoadPurchases {
    private final File file;

    public LoadPurchases() {
        this.file = new File("purchases.txt");
    }

    public PurchaseStore loadPurchases() {
        PurchaseStore purchaseStore = new PurchaseStore();

        try (Scanner scanner = new Scanner(file)) {

            purchaseStore.setBalance(BigDecimal.valueOf(Float.parseFloat(scanner.nextLine())));

            while (scanner.hasNext()) {
                Purchase purchase = new Purchase();
                String[] inputParts = scanner.nextLine().split(";");

                purchase.setName(inputParts[0]);

                for (PurchaseType purchaseType : PurchaseType.values()) {
                    if (purchaseType.getValue().equals(inputParts[1])) {
                        purchase.setType(purchaseType);
                    }
                }

                purchase.setPrice(BigDecimal.valueOf(Double.parseDouble(inputParts[2])));

                purchaseStore.addPurchase(purchase);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: \n" + e);
        }

        return purchaseStore;
    }
}
