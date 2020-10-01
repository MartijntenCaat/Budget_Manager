package budget;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SavePurchases {
    private File file;

    public SavePurchases() {
        this.file = new File("purchases.txt");
    }

    public void savePurchases(PurchaseStore purchaseStore) {

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write("Header\n");
            StringBuilder line = new StringBuilder();
            for (Purchase purchase : purchaseStore.getPurchaseStore()) {
                line.append(purchase.getName())
                        .append(";")
                        .append(purchase.getType())
                        .append(";")
                        .append(purchase.getPrice())
                        .append("\n");

                fileWriter.write(line.toString());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: \n" + e);
        }

    }

}
