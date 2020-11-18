package budget;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetApp {
    private final static String ERROR = "\nSomething went wrong, please try again!\n";
    private final Scanner scan;
    private PurchaseStore purchaseStore;
    private boolean isOnline;

    public BudgetApp() {
        this.scan = new Scanner(System.in);
        this.purchaseStore = new PurchaseStore();
        this.isOnline = true;
    }

    public static void print(String string) {
        System.out.print(string);
    }

    public void printSortingResult(ArrayList<String> result) {
        if (result != null) {
            for (String string : result) {
                print(string);
            }
        }
    }

    public void run() {
        switch (Menu.getOptionFromGeneralMenu()) {
            case "1":
                processIncome(askForIncome());
                break;
            case "2":
                askPurchase();
                break;
            case "3":
                printAllPurchasesAndTotalPrice();
                break;
            case "4":
                printBalance();
                break;
            case "5":
                SavePurchases.savePurchases(purchaseStore);
                break;
            case "6":
                this.purchaseStore = LoadPurchases.loadPurchases();
                break;
            case "7":
                processSorting();
                break;
            case "0":
                exitBudgetApp();
                break;
            default:
                break;
        }
    }

    private void processSorting() {
        boolean goBack = false;
        while (!goBack) {
            switch (Menu.getOptionFromSortMenu()) {
                case "1":
                    printSortingResult(new SortAll().sort(purchaseStore));
                    break;
                case "2":
                    printSortingResult(new SortByType().sort(purchaseStore));
                    break;
                case "3":
                    printSortingResult(new SortCertainType().sort(purchaseStore, Menu.getOptionFromInputSortMenu()));
                    break;
                case "4":
                    goBack = true;
                    print("\n");
                    break;
            }
        }
    }

    private void exitBudgetApp() {
        print("\nBye!\n\n");
        isOnline = false;
    }

    boolean isOnline() {
        return isOnline;
    }

    private String readUserInput() {
        return scan.nextLine();
    }

    private BigDecimal askForIncome() {
        print("\nEnter Income:\n");
        String income = scan.nextLine();
        return new BigDecimal(income);
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
        return scan.nextLine();
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
        String price = scan.nextLine();
        return new BigDecimal(price);
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
