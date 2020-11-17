package budget;

public enum PurchaseType {
    FOOD("1"),
    CLOTHES("2"),
    ENTERTAINMENT("3"),
    OTHER("4"),
    ALL("5");

    private final String value;

    PurchaseType(String value) {
        this.value = value;
    }

    public static String toString(String value) {
        if (value.equals("1")) {
            return "Food";
        }
        if (value.equals("2")) {
            return "Clothes";
        }
        if (value.equals("3")) {
            return "Entertainment";
        }
        if (value.equals("4")) {
            return "Other";
        }
        if (value.equals("5")) {
            return "All";
        }

        return null;
    }

    public String getValue() {
        return value;
    }
}
