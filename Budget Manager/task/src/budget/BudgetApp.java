package budget;

import java.math.BigDecimal;
import java.util.Scanner;

public class BudgetApp {
    private final Scanner scanner;
    private final PurchaseStore purchaseStore;
    private final Income income;
    private final Balance balance;
    private boolean isOnline;

    public BudgetApp() {
        this.scanner = new Scanner(System.in);
        this.purchaseStore = new PurchaseStore();
        this.income = new Income();
        this.balance = new Balance();
        this.isOnline = true;
    }

    public void run() {
        printAppMenu();

        String userInput = readUserInput();

        switch (userInput) {
            case "1":
                processIncome(askForIncome());
                break;
            case "2":
                Purchase purchase = askPurchase();
                if (purchase == null) {
                    printError();
                } else {
                    processPurchase(purchase);
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
                printError();
                break;
        }
    }

    public void printError() {
        System.out.println("Something went wrong, please try again!");
    }

    public void printAppMenu() {
        String appMenu = "Choose your action:\n" + "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "0) Exit";
        System.out.println(appMenu);
    }

    public void exitBudgetApp() {
        System.out.println("\nBye!");
        isOnline = false;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String readUserInput() {
        return scanner.nextLine();
    }

    public BigDecimal askForIncome() {
        System.out.println("Enter Income:");
        String income = scanner.nextLine();
        return new BigDecimal(income);
    }

    public void processIncome(BigDecimal newIncome) {
        income.setIncome(newIncome);
        balance.setBalance(newIncome);
        System.out.println("\nIncome was added!\n");
    }

    public void printAllPurchasesAndTotalPrice() {
        if (purchaseStore.getPurchaseStore().isEmpty()) {
            System.out.println("\nPurchase list is empty\n");
            return;
        }

        StringBuilder stringbuilder = new StringBuilder();

        for (Purchase purchase : purchaseStore.getPurchaseStore()) {
            stringbuilder.append("\n")
                    .append(purchase.getName())
                    .append(" ")
                    .append("$")
                    .append(purchase.getPrice());
        }

        System.out.println(stringbuilder);

        BigDecimal total = new BigDecimal("0.0");

        for (Purchase purchase : purchaseStore.getPurchaseStore()) {
            BigDecimal price = purchase.getPrice();
            total = total.add(price);
        }

        System.out.println("Total sum: $" + total + "\n");
    }

    public Purchase askPurchase() {
        Purchase purchase = new Purchase();

        PurchaseType userInputType = askPurchaseType();
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
        System.out.println("\nEnter purchase name:");
        return scanner.nextLine();
    }

    private PurchaseType askPurchaseType() {
        System.out.println("Choose the type of purchase");
        String userInputType = scanner.nextLine();

        if (userInputType.equals("5")) { // back to menu
            return null;
        }

        for (PurchaseType purchaseType : PurchaseType.values()) {
            if (purchaseType.getValue().equals(userInputType)) {
                return purchaseType;
            }
        }

        System.out.println("Something went wrong, try again!");
        return null;
    }

    public BigDecimal askPurchasePrice() {
        System.out.println("Enter its price:");
        String price = scanner.nextLine();
        return new BigDecimal(price);
    }

    public void processPurchase (Purchase purchase) {
        purchaseStore.addPurchase(purchase);
        subtractFromBalance(purchase.getPrice());
        System.out.println("\nPurchase was added!");
    }

    public void subtractFromBalance(BigDecimal purchasePrice) {
        balance.setBalance(balance.getBalance().subtract(purchasePrice));
    }

    public void printBalance() {
        System.out.print("\nBalance: $");
        System.out.printf("%.2f%n", balance.getBalance());
        System.out.print("\n");
    }

}

