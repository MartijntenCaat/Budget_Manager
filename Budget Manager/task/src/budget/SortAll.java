package budget;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SortAll implements ISortMethod {

    @Override
    public ArrayList<String> sort(PurchaseStore purchaseStore) {
        ArrayList<String> result = new ArrayList<>();

        Map<BigDecimal, Integer> purchaseListWithId = new HashMap<>();

        for (Purchase purchase : purchaseStore.getPurchaseStore()) {
            purchaseListWithId.put(purchase.getPrice(), purchase.getId());
        }

        purchaseListWithId.entrySet()
                .stream()
                .sorted(Map.Entry.<BigDecimal, Integer>comparingByKey())
                .forEach(System.out::println);


        int lowestPrice = 0;
        for (Purchase purchase : purchaseStore.getPurchaseStore()) {
            if (purchase.getPrice().intValue() > lowestPrice) {

            }
        }


        return new ArrayList<String>();
    }
}
