package budget;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetApp {
    private PurchaseStore purchaseStore;

    public BudgetApp() {
        this.purchaseStore = new PurchaseStore();
    }

    private void print(String string) {
        System.out.print(string);
    }

    public void print(ArrayList<String> result) {
        if (result != null) {
            for (String string : result) {
                print(string);
            }
        }
    }

    public void run() {
        boolean isOnline = true;
        while (isOnline) {
            switch (Menu.getOptionFromGeneralMenu()) {
                case "1": // 1) Add income
                    processIncome(askForIncome());
                    break;
                case "2": // 2) Add purchase
                    askPurchase();
                    break;
                case "3": // 3) Show list of purchases
                    printAllPurchasesAndTotalPrice();
                    break;
                case "4": // 4) Balance
                    printBalance();
                    break;
                case "5": // 5) Save
                    SavePurchases.savePurchases(purchaseStore);
                    print("\nPurchases were saved!\n\n");
                    break;
                case "6": // 6) Load
                    this.purchaseStore = LoadPurchases.loadPurchases();
                    print("\nPurchases were loaded!\n\n");
                    break;
                case "7": // 7) Analyze (Sort)
                    processSorting();
                    break;
                case "0": // 0) Exit
                    print("\nBye!\n\n");
                    isOnline = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void processSorting() {
        if (purchaseStore.getPurchaseStore().isEmpty()) {
            print("\nPurchase list is empty\n\n");
            return;
        }

        boolean goBack = false;
        while (!goBack) {
            switch (Menu.getOptionFromSortMenu()) {
                case "1":
                    print(new SortAll().sort(purchaseStore));
                    break;
                case "2":
                    print(new SortByType().sort(purchaseStore));
                    break;
                case "3":
                    print(new SortCertainType().sort(purchaseStore, Menu.getOptionFromInputSortMenu()));
                    break;
                case "4":
                    goBack = true;
                    print("\n");
                    break;
            }
        }
    }

    private String readUserInput() {
        return new Scanner(System.in).nextLine();
    }

    private BigDecimal askForIncome() {
        print("\nEnter Income:\n");
        return new BigDecimal(readUserInput());
    }

    private void processIncome(BigDecimal newIncome) {
        purchaseStore.setIncome(newIncome);
        purchaseStore.setBalance(newIncome);
        print("Income was added!\n\n");
    }

    private void printAllPurchasesAndTotalPrice() {
        if (purchaseStore.getPurchaseStore().isEmpty()) {
            print("\nPurchase list is empty\n\n");
            return;
        }

        boolean goBack = false;
        while (!goBack) {
            PurchaseType purchaseType = askPurchaseTypeForChecking();

            if (purchaseType == null) {
                goBack = true;
                print("\n");
                continue;
            }

            BigDecimal total = new BigDecimal(BigInteger.ZERO);
            print("\n" + purchaseType.toString() + ":\n");
            if (!purchaseType.getValue().equals(PurchaseType.ALL.getValue())) {
                for (Purchase purchase : purchaseStore.getPurchaseStore()) {
                    if (purchase.getType().equals(purchaseType)) {
                        print(purchase.getName() + " $" + String.format("%.2f%n", purchase.getPrice()));
                        total = total.add(purchase.getPrice());
                    }
                }
            } else {
                for (Purchase purchase : purchaseStore.getPurchaseStore()) {
                    print(purchase.getName() + " $" + String.format("%.2f%n", purchase.getPrice()));
                    total = total.add(purchase.getPrice());
                }
            }

            if (!total.equals(BigDecimal.ZERO)) {
                print("Total sum: $" + String.format("%.2f%n", total));
            }
        }
    }

    private void askPurchase() {
        boolean goBack = false;
        while (!goBack) {
            PurchaseType userInputType = askPurchaseTypeForAdding();

            if (userInputType == null) {
                goBack = true;
                print("\n");
                continue;
            }

            Purchase purchase = new Purchase();
            purchase.setType(userInputType);
            purchase.setName(askPurchaseName());
            purchase.setPrice(askPurchasePrice());
            processPurchase(purchase);
        }
    }

    private String askPurchaseName() {
        print("\nEnter purchase name:\n");
        return readUserInput();
    }

    private PurchaseType askPurchaseTypeForChecking() {
        String optionFromPrintTypeMenu = Menu.getOptionFromPrintTypeMenu();

        if (optionFromPrintTypeMenu.equals("6")) { // back to menu
            return null;
        }

        return PurchaseType.extractTypeFromInput(optionFromPrintTypeMenu);
    }

    private PurchaseType askPurchaseTypeForAdding() {
        String optionFromInputTypeMenu = Menu.getOptionFromInputTypeMenu();

        if (optionFromInputTypeMenu.equals("5")) { // back to menu
            return null;
        }

        return PurchaseType.extractTypeFromInput(optionFromInputTypeMenu);
    }

    private BigDecimal askPurchasePrice() {
        print("Enter its price:\n");
        return new BigDecimal(readUserInput());
    }

    private void processPurchase(Purchase purchase) {
        purchaseStore.addPurchase(purchase);
        purchaseStore.subtractFromBalance(purchase.getPrice());
        print("Purchase was added!\n");
    }

    public void printBalance() {
        print("\nBalance: $" + String.format("%.2f%n", purchaseStore.getBalance()) + "\n");
    }

}
