package budget;

public class AppMenu {
    private StringBuilder appMenu;

    public AppMenu() {
        StringBuilder appMenu = new StringBuilder("Choose your action:\n");
        appMenu.append("1) Add income\n")
                .append("2) Add purchase\n")
                .append("3) Show list of purchases\n")
                .append("4) Balance\n")
                .append("0) Exit");

        this.appMenu = appMenu;
    }

    public StringBuilder printAppMenu() {
        return appMenu;
    }
}
