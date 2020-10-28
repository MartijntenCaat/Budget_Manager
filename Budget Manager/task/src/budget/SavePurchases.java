package budget;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SavePurchases {
    private final File file;

    public SavePurchases() {
        this.file = new File("purchases.txt");
    }

    public void savePurchases(PurchaseStore purchaseStore) {

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(purchaseStore.getBalance().toString() + "\n");

            for (Purchase purchase : purchaseStore.getPurchaseStore()) {
                String line = purchase.getName() +
                        ";" +
                        purchase.getType().getValue() +
                        ";" +
                        purchase.getPrice();
                fileWriter.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: \n" + e);
        }
    }

}
