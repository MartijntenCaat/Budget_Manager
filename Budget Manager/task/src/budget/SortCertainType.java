package budget;

import java.util.ArrayList;

public class SortCertainType implements ISortMethod {
    @Override
    public ArrayList<String> sort(PurchaseStore purchaseStore) {

        Purchase[] array = purchaseStore.getPurchaseStore().toArray(new Purchase[0]);
        Purchase[] sortedArray = BubbleSort.bubbleSort(array);

        // wip

        return null;
    }

}
