package budget;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetApp {

    private final static String INPUT_TYPE_OPTIONS = "\nChoose the type of purchase" +
            "\n1) Food" +
            "\n2) Clothes" +
            "\n3) Entertainment" +
            "\n4) Other" +
            "\n5) Back" +
            "\n";
    private final static String PRINT_TYPE_OPTIONS = "\nChoose the type of purchases" +
            "\n1) Food" +
            "\n2) Clothes" +
            "\n3) Entertainment" +
            "\n4) Other" +
            "\n5) All" +
            "\n6) Back" +
            "\n";
    private final static String SORT_MENU = "\nHow do you want to sort?" +
            "\n1) Sort all purchases" +
            "\n2) Sort by type" +
            "\n3) Sort certain type" +
            "\n4) Back" +
            "\n";
    private final static String INPUT_SORT_MENU = "\nChoose the type of purchase" +
            "\n1) Food" +
            "\n2) Clothes" +
            "\n3) Entertainment" +
            "\n4) Other" +
            "\n";
    private final static String ERROR = "\nSomething went wrong, please try again!\n";
    private final Scanner scan;
    private PurchaseStore purchaseStore;
    private boolean isOnline;
    private Menu menu;

    public BudgetApp() {
        this.scan = new Scanner(System.in);
        this.purchaseStore = new PurchaseStore();
        this.isOnline = true;
        this.menu = new Menu();
    }

    public static void print(String string) {
        System.out.print(string);
    }

    public void printSortingResult(ArrayList<String> result) {
        if (result != null) {
            for (String s : result) {
                print(s);
            }
        }
    }

    public void run() {
        switch (menu.getOptionFromGeneralMenu()) {
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
            print(SORT_MENU);
            var input = readUserInput();

            switch (input) {
                case "1":
                    printSortingResult(new SortAll().sort(purchaseStore));
                    break;
                case "2":
                    printSortingResult(new SortByType().sort(purchaseStore));
                    break;
                case "3":
                    print(INPUT_SORT_MENU);
                    var sortInput = readUserInput();
                    printSortingResult(new SortCertainType().sort(purchaseStore, sortInput));
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
            Purchase purchase = new Purchase();

            PurchaseType userInputType = askPurchaseTypeForAdding();
            if (userInputType == null) {
                goBack = true;
                print("\n");
                continue;
            }
            purchase.setType(userInputType);

            String userInputName = askPurchaseName();
            purchase.setName(userInputName);

            BigDecimal userInputPrice = askPurchasePrice();
            purchase.setPrice(userInputPrice);

            processPurchase(purchase);
        }
    }

    private String askPurchaseName() {
        print("\nEnter purchase name:\n");
        return scan.nextLine();
    }

    private PurchaseType askPurchaseTypeForChecking() {
        print(PRINT_TYPE_OPTIONS);
        String userInputType = scan.nextLine();

        if (userInputType.equals("6")) { // back to menu
            return null;
        }

        for (PurchaseType purchaseType : PurchaseType.values()) {
            if (purchaseType.getValue().equals(userInputType)) {
                return purchaseType;
            }
        }

        print(ERROR);
        return null;
    }

    private PurchaseType askPurchaseTypeForAdding() {
        print(INPUT_TYPE_OPTIONS);
        String userInputType = scan.nextLine();

        if (userInputType.equals("5")) { // back to menu
            return null;
        }

        for (PurchaseType purchaseType : PurchaseType.values()) {
            if (purchaseType.getValue().equals(userInputType)) {
                return purchaseType;
            }
        }

        print(ERROR);
        return null;
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
