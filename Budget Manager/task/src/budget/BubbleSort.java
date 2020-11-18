package budget;

public class BubbleSort {

    public BubbleSort() {
        // nothing
    }

    public static Purchase[] bubbleSort(Purchase[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (array[j].getPrice().doubleValue() < array[j + 1].getPrice().doubleValue()) {
                    Purchase temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
