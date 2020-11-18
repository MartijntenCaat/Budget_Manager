package budget;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SavePurchases {
    public static void savePurchases(PurchaseStore purchaseStore) {

        try (FileWriter fileWriter = new FileWriter(new File("purchases.txt"), false)) {
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
