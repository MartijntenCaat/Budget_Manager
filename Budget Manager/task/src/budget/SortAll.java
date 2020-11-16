package budget;

public class SortAll implements ISortMethod {

    @Override
    public Purchase[] sort(PurchaseStore purchaseStore) {

        Purchase[] array = purchaseStore.getPurchaseStore().toArray(new Purchase[0]);

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
