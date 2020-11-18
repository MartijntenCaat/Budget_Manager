package budget;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class SortCertainType implements ISortMethod {

    @Override
    public ArrayList<String> sort(PurchaseStore purchaseStore) {
        return null;
    }

    public ArrayList<String> sort(PurchaseStore purchaseStore, String type) {
        Purchase[] array = purchaseStore.getPurchaseStore().toArray(new Purchase[0]);
        Purchase[] sortedArray = BubbleSort.bubbleSort(array);

        BigDecimal totalOfType = new BigDecimal(BigInteger.ZERO);
        ArrayList<String> result = new ArrayList<>();

        result.add("\n" + PurchaseType.toString(type) + ":\n");
        for (Purchase purchase : sortedArray) {
            if (purchase.getType().getValue().equals(type)) {
                result.add(purchase.getName() + " $" + String.format("%.2f%n", purchase.getPrice()));
                totalOfType = totalOfType.add(purchase.getPrice());
            }
        }

        result.add("Total sum: $" + String.format("%.2f%n", totalOfType));
        return result;
    }


}
