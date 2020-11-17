package budget;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortByType implements ISortMethod {
    @Override
    public ArrayList<String> sort(PurchaseStore purchaseStore) {
        Purchase[] array = purchaseStore.getPurchaseStore().toArray(new Purchase[0]);
        Purchase[] sortedArray = BubbleSort.bubbleSort(array);

        Map<String, BigDecimal> purchaseTypeMap = new LinkedHashMap<>();

        for (Purchase purchase : sortedArray) {
            String type = purchase.getType().toString();
            var price = purchase.getPrice();

            if (purchaseTypeMap.containsKey(type)) {
                BigDecimal typePriceTotal = purchaseTypeMap.get(type);
                typePriceTotal = typePriceTotal.add((purchase.getPrice()));
                purchaseTypeMap.put(type, typePriceTotal);
            } else {
                purchaseTypeMap.put(type, price);
            }
        }

        BigDecimal totalOfAllTypes = new BigDecimal(BigInteger.ZERO);
        ArrayList<String> result = new ArrayList<>();

        result.add("\nTypes:\n");
        for (String type : purchaseTypeMap.keySet()) {
            result.add(type + " $" + String.format("%.2f%n", purchaseTypeMap.get(type)));
            totalOfAllTypes = totalOfAllTypes.add(purchaseTypeMap.get(type));
        }

        result.add("Total sum: $" + String.format("%.2f%n", totalOfAllTypes) + "\n");
        return result;
    }

}
