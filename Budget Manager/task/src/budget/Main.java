/* (C) COPYRIGHT 2020
 __  __            _   _ _         _                _____            _
|  \/  |          | | (_(_)       | |              / ____|          | |
| \  / | __ _ _ __| |_ _ _ _ __   | |_ ___ _ __   | |     __ _  __ _| |_
| |\/| |/ _` | '__| __| | | '_ \  | __/ _ | '_ \  | |    / _` |/ _` | __|
| |  | | (_| | |  | |_| | | | | | | ||  __| | | | | |___| (_| | (_| | |_
|_|  |_|\__,_|_|   \__|_| |_| |_|  \__\___|_| |_|  \_____\__,_|\__,_|\__|
                       _/ |
                      |__/
*/

package budget;

public class Main {
    public static void main(String[] args) {
        BudgetApp budgetApp = new BudgetApp();

        while (budgetApp.isOnline()) {
            budgetApp.run();
        }
    }
}
