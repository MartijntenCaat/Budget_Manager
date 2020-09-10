package budget;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class BudgetApp {
    private Scanner scanner;
    private LinkedHashMap<String, Integer> budgetStore;

    public BudgetApp() {
        this.scanner = new Scanner(System.in);
        this.budgetStore = new LinkedHashMap<>();
    }

    public String readUserInput() {
        return scanner.nextLine();
    }

    public void addItem(String item, int price) {
        budgetStore.put(item, price);
    }

    public LinkedHashMap<String, Integer> getBudgetStore() {
        return budgetStore;
    }
}

