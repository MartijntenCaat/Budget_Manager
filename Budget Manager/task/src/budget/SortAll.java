package budget;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class SortAll implements ISortMethod {

    @Override
    public ArrayList<String> sort(PurchaseStore purchaseStore) {

        Purchase[] array = purchaseStore.getPurchaseStore().toArray(new Purchase[0]);
        Purchase[] sortedArray = bubbleSort(array);

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

    private Purchase[] bubbleSort(Purchase[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (array[j].getPrice().longValue() < array[j + 1].getPrice().longValue()) {
                    Purchase temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

}
