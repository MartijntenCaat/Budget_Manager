package budget;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public String getOptionFromInputTypeMenu() {
        System.out.println("\nChoose the type of purchase" +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other" +
                "\n5) Back" +
                "\n");
        return getUserInput();
    }

    public String getOptionFromGeneralMenu() {
        System.out.println("Choose your action:" +
                "\n1) Add income" +
                "\n2) Add purchase" +
                "\n3) Show list of purchases" +
                "\n4) Balance" +
                "\n5) Save" +
                "\n6) Load" +
                "\n7) Analyze (Sort)" +
                "\n0) Exit" +
                "\n");
        return getUserInput();
    }

    private String getUserInput() {
        return scanner.nextLine();
    }

}
