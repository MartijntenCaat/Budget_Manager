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
            "\n0) Exit";
    private final static String INPUT_TYPE_OPTIONS = "\nChoose the type of purchase" +
            "\n1) Food" +
            "\n2) Clothes" +
            "\n3) Entertainment" +
            "\n4) Other" +
            "\n5) Back";
    private final static String PRINT_TYPE_OPTIONS = "\nChoose the type of purchases" +
            "\n1) Food" +
            "\n2) Clothes" +
            "\n3) Entertainment" +
            "\n4) Other" +
            "\n5) All" +
            "\n6) Back";
    private final static String ERROR = "Something went wrong, please try again!";

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

    public void run() {
        print(APP_MENU);
        String userInput = readUserInput();

        switch (userInput) {
            case "1":
                processIncome(askForIncome());
                break;
            case "2":
                // TODO code a while loop that keeps running while input is not 6 nor 5.
                Purchase purchase = askPurchase();
                if (purchase != null) {
                    processPurchase(purchase);
                } else {
                    print("");
                }
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

    private void print(String string) {
        System.out.println(string);
    }

    public void exitBudgetApp() {
        print("\nBye!");
        isOnline = false;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String readUserInput() {
        return scan.nextLine();
    }

    public BigDecimal askForIncome() {
        print("Enter Income:");
        String income = scan.nextLine();
        return new BigDecimal(income);
    }

    public void processIncome(BigDecimal newIncome) {
        income.setIncome(newIncome);
        balance.setBalance(newIncome);
        print("\nIncome was added!\n");
    }

    public void printAllPurchasesAndTotalPrice() {
        if (purchaseStore.getPurchaseStore().isEmpty()) {
            print("\nPurchase list is empty\n");
            return;
        }

        PurchaseType purchaseType = askPurchaseTypeForChecking();

        if (purchaseType == null) {
            return;
        }

        BigDecimal total = new BigDecimal(BigInteger.ZERO);

        print("");
        for (Purchase purchase : purchaseStore.getPurchaseStore()) {
            if (purchase.getType().equals(purchaseType)) {
                print(purchase.getName() + " $" + purchase.getPrice());
                total = total.add(purchase.getPrice());
            }
        }

        // TODO add check to see if list is empty, if empty return type and empty line!

        print("Total sum: $" + total + "\n");
    }

    public Purchase askPurchase() {
        Purchase purchase = new Purchase();

        PurchaseType userInputType = askPurchaseTypeForAdding();
        if (userInputType == null) {
            return null;
        }
        purchase.setType(userInputType);

        String userInputName = askPurchaseName();
        purchase.setName(userInputName);

        BigDecimal userInputPrice = askPurchasePrice();
        purchase.setPrice(userInputPrice);

        return purchase;
    }

    public String askPurchaseName() {
        print("\nEnter purchase name:");
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

    public BigDecimal askPurchasePrice() {
        print("Enter its price:");
        String price = scan.nextLine();
        return new BigDecimal(price);
    }

    public void processPurchase(Purchase purchase) {
        purchaseStore.addPurchase(purchase);
        balance.subtractFromBalance(purchase.getPrice());
        print("Purchase was added!\n");
    }

    public void printBalance() {
        print("\nBalance: $" + String.format("%.2f%n", balance.getBalance()) + "\n");
    }

}
