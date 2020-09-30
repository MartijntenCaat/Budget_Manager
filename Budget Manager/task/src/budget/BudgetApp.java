package budget;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class BudgetApp {
    private final static String APP_MENU = "Choose your action:" +
            "\n1) Add income" +
            "\n2) Add purchase" +
            "\n3) Show list of purchases" +
            "\n4) Balance" +
            "\n0) Exit" +
            "\n";
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
    private final static String ERROR = "\nSomething went wrong, please try again!\n";

    private final Scanner scan;
    private final PurchaseStore purchaseStore;
    private final Income income;
    private final Balance balance;
    private boolean isOnline;

    public BudgetApp() {
        this.scan = new Scanner(System.in);
        this.purchaseStore = new PurchaseStore();
        this.income = new Income();
        this.balance = new Balance();
        this.isOnline = true;
    }

    public void print(String string) {
        System.out.print(string);
    }

    public void run() {
        print(APP_MENU);
        String userInput = readUserInput();

        switch (userInput) {
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
            case "0":
                exitBudgetApp();
                break;
            default:
                break;
        }
    }

    private void exitBudgetApp() {
        print("\nBye!");
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
        income.setIncome(newIncome);
        balance.setBalance(newIncome);
        print("Income was added!\n\n");
    }

    private void printAllPurchasesAndTotalPrice() {
        if (purchaseStore.getPurchaseStore().isEmpty()) {
            print("\nPurchase list is empty\n");
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
//                print("\n");
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
        balance.subtractFromBalance(purchase.getPrice());
        print("Purchase was added!\n");
    }

    public void printBalance() {
        print("\nBalance: $" + String.format("%.2f%n", balance.getBalance()) + "\n");
    }

}
