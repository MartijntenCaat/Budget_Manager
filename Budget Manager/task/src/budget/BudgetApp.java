package budget;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class BudgetApp {
    private final Scanner scanner;
    private final LinkedHashMap<String, BigDecimal> budgetStore;
    private final AppMenu appMenu;
    private boolean isOnline;

    public BudgetApp() {
        this.scanner = new Scanner(System.in);
        this.budgetStore = new LinkedHashMap<>();
        this.appMenu = new AppMenu();
        this.isOnline = true;
    }

//    public void run() {
//
//
//        }
//
////        if (scanner.hasNext()) {
////            String rawInput = readUserInput();
////            parseUserInput(rawInput);
////            return;
////        }
////
//    }

    public void exitBudgetApp() {
        System.out.println("Bye!");
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

    public void parseUserInput(String rawInput) {
        String[] input = rawInput.split("\\$");
        String item = input[0];
        BigDecimal price = new BigDecimal(input[1]);

        addItem(item, price);
    }

    public void addItem(String item, BigDecimal price) {
        budgetStore.put(item, price);
    }

    public void printData() {
        StringBuilder stringbuilder = new StringBuilder("");

        for (String item : budgetStore.keySet()) {
            stringbuilder.append(item)
                    .append("$")
                    .append(budgetStore.get(item))
                    .append("\n");
        }

        System.out.println(stringbuilder);
    }

    public void printTotal() {
        BigDecimal total = new BigDecimal("0.0");

        for (String item : budgetStore.keySet()) {
            BigDecimal price = budgetStore.get(item);
            total = new BigDecimal(String.valueOf(total.add(price)));
        }

        System.out.println("Total: $" + total);
    }
}

