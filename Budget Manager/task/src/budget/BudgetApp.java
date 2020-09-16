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
                processPurchase(askPurchase(), askPurchasePrice());
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
                System.out.println("Something went wrong, please try again!");
                break;
        }
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

        for (String item : purchaseStore.getPurchaseStore().keySet()) {
            stringbuilder.append("\n")
                    .append(item)
                    .append(" ")
                    .append("$")
                    .append(purchaseStore.getPurchaseStore().get(item));
        }

        System.out.println(stringbuilder);

        BigDecimal total = new BigDecimal("0.0");

        for (String item : purchaseStore.getPurchaseStore().keySet()) {
            BigDecimal price = purchaseStore.getPurchaseStore().get(item);
            total = total.add(price);
        }

        System.out.println("Total sum: $" + total + "\n");
    }

    public String askPurchase() {
        System.out.println("\nEnter purchase name:");
        return scanner.nextLine();
    }

    public BigDecimal askPurchasePrice() {
        System.out.println("Enter its price:");
        String price = scanner.nextLine();
        return new BigDecimal(price);
    }

    public void processPurchase (String purchase, BigDecimal price) {
        purchaseStore.addPurchase(purchase, price);
        subtractFromBalance(price);
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

