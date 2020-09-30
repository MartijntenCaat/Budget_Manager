package budget;

import java.io.File;
import java.io.IOException;

public class SavePurchases {
    private File file;

    public SavePurchases(File file) {
        this.file = file;
    }

    public void savePurchases(PurchaseStore purchaseStore) {
        file = new File("purchases.txt");

        try {
            boolean createdNew = file.createNewFile();
            if (createdNew) {
                System.out.println("The file was successfully created.");
            } else {
                System.out.println("The file already exists.");
            }
        } catch (IOException e) {
            System.out.println("Cannot create the file: " + file.getPath());
        }


    }

}
