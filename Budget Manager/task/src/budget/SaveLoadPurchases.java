package budget;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLoadPurchases {
    private final File file;

    public SaveLoadPurchases() {
        this.file = new File("purchases.txt");
    }

    public void savePurchases(PurchaseStore purchaseStore, Balance balance) {

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write("Balance: " + balance.getBalance().toString());

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

//    public PurchaseStore loadPurchases() {
//        PurchaseStore purchaseStore = new PurchaseStore();
//
//        try (Scanner scanner = new Scanner(file)) {
//
//
//            while (scanner.hasNext()) {
//                Purchase purchase = new Purchase();
//                String[] input = scanner.nextLine().split(";");
//                purchase.setName(input[0]);
//
//                for (PurchaseType purchaseType : PurchaseType.values()) {
//                    if (purchaseType.getValue().equals(input[1])) {
//                        purchase.setType(purchaseType);
//                    }
//                }
//
//                purchase.setPrice(BigDecimal.valueOf(Double.parseDouble(input[2])));
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found: \n" + e);
//        }
//
//        return purchaseStore;
//
//    }

}
