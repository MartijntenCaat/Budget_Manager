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
        System.out.println("\nBye!\n");
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
        return new BigDecimal(String.valueOf(income));
    }

    public void processIncome(BigDecimal newIncome) {
        income = new BigDecimal(String.valueOf(income.add(newIncome)));
        balance = new BigDecimal(String.valueOf(balance.add(newIncome)));
        System.out.println("\nIncome was added!\n");
    }

    public void parseUserInput(String rawInput) {
        String[] input = rawInput.split("\\$");
        String item = input[0];
        BigDecimal price = new BigDecimal(input[1]);

        addItem(item, price);
    }

    public void addItem(String item, BigDecimal price) {
        budgetStore.put(item, price);
    }

    public void printAllPurchases() {
        StringBuilder stringbuilder = new StringBuilder("");

        for (String item : budgetStore.keySet()) {
            stringbuilder.append(item)
                    .append(" ")
                    .append("$")
                    .append(budgetStore.get(item))
                    .append("\n");
        }

        System.out.println(stringbuilder);
    }

    public void printTotalPurchasePrice() {
        BigDecimal total = new BigDecimal("0.0");

        for (String item : budgetStore.keySet()) {
            BigDecimal price = budgetStore.get(item);
            total = new BigDecimal(String.valueOf(total.add(price)));
        }

        StringBuilder stringBuilder = new StringBuilder("\nTotal sum: $");
        stringBuilder.append(total).append("\n");

        System.out.println(stringBuilder);
    }

    public String askPurchase() {
        System.out.println("Enter purchase name:");
        return scanner.nextLine();
    }

    public BigDecimal askPurchasePrice() {
        System.out.println("Enter its price:");
        String price = scanner.nextLine();
        return new BigDecimal(String.valueOf(price));
    }

    public void processPurchase (String purchase, BigDecimal price) {
        budgetStore.put(purchase, price);
        subtractFromBalance(price);
        System.out.println("\nPurchase was added!");
    }

    public void subtractFromBalance(BigDecimal purchasePrice) {
        balance = new BigDecimal(String.valueOf(balance.min(purchasePrice)));
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void printBalance() {
        System.out.print("\nBalance: $");
        System.out.printf("%.2f%n", balance );
        System.out.print("\n");
    }

}

