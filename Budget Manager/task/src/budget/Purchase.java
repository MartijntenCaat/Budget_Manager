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

import java.math.BigDecimal;

public class Purchase {
    private PurchaseType type;
    private String name;
    private BigDecimal price;

    public Purchase() {
        this.type = null;
    }

    public PurchaseType getType() {
        return type;
    }

    public void setType(PurchaseType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
