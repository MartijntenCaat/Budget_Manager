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

public class Income {
    private BigDecimal income;

    public Income() {
        this.income = BigDecimal.ZERO;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
