package budget;

public class Main {
    public static void main(String[] args) {
        BudgetApp budgetApp = new BudgetApp();

        while (budgetApp.isOnline()) {
            budgetApp.run();
        }
    }
}
