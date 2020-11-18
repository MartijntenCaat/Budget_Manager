package budget;

import java.util.Scanner;

public class Menu {

    public Menu() {
    }

    private static void print(String string) {
        System.out.print(string);
    }

    public static String getOptionFromInputSortMenu() {
        print("\nChoose the type of purchase" +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other" +
                "\n");
        return getUserInput();
    }

    public static String getOptionFromSortMenu() {
        print("\nHow do you want to sort?" +
                "\n1) Sort all purchases" +
                "\n2) Sort by type" +
                "\n3) Sort certain type" +
                "\n4) Back" +
                "\n");
        return getUserInput();
    }

    public static String getOptionFromPrintTypeMenu() {
        print("\nChoose the type of purchases" +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other" +
                "\n5) All" +
                "\n6) Back" +
                "\n");
        return getUserInput();
    }

    public static String getOptionFromInputTypeMenu() {
        print("\nChoose the type of purchase" +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other" +
                "\n5) Back" +
                "\n");
        return getUserInput();
    }

    public static String getOptionFromGeneralMenu() {
        print("Choose your action:" +
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

    private static String getUserInput() {
        return new Scanner(System.in).nextLine();
    }

}
