package budget;

public class Main {
    public static void main(String[] args) {
        BudgetApp budgetApp = new BudgetApp();

        while (budgetApp.isOnline()) {
            budgetApp.printAppMenu();
            String userInput = budgetApp.readUserInput();

            switch (userInput) {
                case "1":
                budgetApp.processIncome(budgetApp.askForIncome());
                    break;
                case "2":
                    // code
                    break;
                case "3":
                    // code
                    break;
                case "4":
                    // code
                    break;
                case "0":
                    budgetApp.exitBudgetApp();
                    break;
                default:
                    System.out.println("Something went wrong, please try again!");
                    break;
            }
        }
    }
}