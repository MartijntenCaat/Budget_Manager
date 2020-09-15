package budget;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class BudgetApp {
    private final Scanner scanner;
    private final LinkedHashMap<String, BigDecimal> budgetStore;
    private final AppMenu appMenu;
    private BigDecimal income;
    private BigDecimal balance;
    private boolean isOnline;

    public BudgetApp() {
        this.scanner = new Scanner(System.in);
        this.budgetStore = new LinkedHashMap<>();
        this.income = BigDecimal.ZERO;
        this.balance = BigDecimal.ZERO;
        this.appMenu = new AppMenu();
        this.isOnline = true;
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

    public void printAppMenu() {
        System.out.println(appMenu.printAppMenu());
    }

    public BigDecimal askForIncome() {
        System.out.println("Enter Income:");
        String income = scanner.nextLine();
        return new BigDecimal(income);
    }

    public void processIncome(BigDecimal newIncome) {
        income = income.add(newIncome);
        balance = balance.add(newIncome);
        System.out.println("\nIncome was added!\n");
    }

    public void printAllPurchasesAndTotalPrice() {
        if (budgetStore.isEmpty()) {
            System.out.println("\nPurchase list is empty\n");
            return;
        }

        StringBuilder stringbuilder = new StringBuilder();

        for (String item : budgetStore.keySet()) {
            stringbuilder.append("\n")
                    .append(item)
                    .append(" ")
                    .append("$")
                    .append(budgetStore.get(item));
        }

        System.out.println(stringbuilder);

        BigDecimal total = new BigDecimal("0.0");

        for (String item : budgetStore.keySet()) {
            BigDecimal price = budgetStore.get(item);
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
        budgetStore.put(purchase, price);
        subtractFromBalance(price);
        System.out.println("\nPurchase was added!");
    }

    public void subtractFromBalance(BigDecimal purchasePrice) {
        balance = balance.subtract(purchasePrice);
    }

    public void printBalance() {
        System.out.print("\nBalance: $");
        System.out.printf("%.2f%n", balance );
        System.out.print("\n");
    }

}

