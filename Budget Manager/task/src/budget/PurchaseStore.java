//     _____                       _       _     _     ___   ___ ___   ___
//    / ____|                     (_)     | |   | |   |__ \ / _ |__ \ / _ \
//   | |     ___  _ __  _   _ _ __ _  __ _| |__ | |_     ) | | | | ) | | | |
//   | |    / _ \| '_ \| | | | '__| |/ _` | '_ \| __|   / /| | | |/ /| | | |
//   | |___| (_) | |_) | |_| | |  | | (_| | | | | |_   / /_| |_| / /_| |_| |
//    \_____\___/| .__/ \__, |_|  |_|\__, |_| |_|\__| |____|\___|____|\___/
//               | |     __/ |        __/ |
//               |_|    |___/        |___/
//    __  __            _   _ _         _                _____            _
//   |  \/  |          | | (_(_)       | |              / ____|          | |
//   | \  / | __ _ _ __| |_ _ _ _ __   | |_ ___ _ __   | |     __ _  __ _| |_
//   | |\/| |/ _` | '__| __| | | '_ \  | __/ _ | '_ \  | |    / _` |/ _` | __|
//   | |  | | (_| | |  | |_| | | | | | | ||  __| | | | | |___| (_| | (_| | |_
//   |_|  |_|\__,_|_|   \__|_| |_| |_|  \__\___|_| |_|  \_____\__,_|\__,_|\__|
//                          _/ |
//                         |__/

package budget;

import java.util.LinkedHashSet;

public class PurchaseStore {
    private final LinkedHashSet<Purchase> purchaseStore;

    public PurchaseStore() {
        this.purchaseStore = new LinkedHashSet<>();
    }

    public LinkedHashSet<Purchase> getPurchaseStore() {
        return purchaseStore;
    }

    public void addPurchase(Purchase purchase) {
        purchaseStore.add(purchase);
    }
}
