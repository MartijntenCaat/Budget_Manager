package budget;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class SortAll implements ISortMethod {

    @Override
    public ArrayList<String> sort(PurchaseStore purchaseStore) {

        Purchase[] array = purchaseStore.getPurchaseStore().toArray(new Purchase[0]);
        Purchase[] sortedArray = BubbleSort.bubbleSort(array);

        ArrayList<String> result = new ArrayList<>();
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        result.add("\nAll:\n");
        for (Purchase purchase : sortedArray) {
            result.add(purchase.getName() + " $" + String.format("%.2f%n", purchase.getPrice()));
            total = total.add(purchase.getPrice());
        }

        if (!total.equals(BigDecimal.ZERO)) {
            result.add("Total sum: $" + String.format("%.2f%n", total) + "\n");
        }

        return result;
    }

}
