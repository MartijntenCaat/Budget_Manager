package budget;

public enum PurchaseType {
    FOOD("1"),
    CLOTHES("2"),
    ENTERTAINMENT("3"),
    OTHER("4");

    private final String value;

    PurchaseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
