package budget;


import java.util.ArrayList;
import java.util.Map;

public class QuickSort {
    Map<String, Double> map;
    ArrayList<String> names;
    ArrayList<Double> prices;

    QuickSort(Map<String, Double> map) {
        this.map = map;
        names = new ArrayList<>();
        prices = new ArrayList<>();
        for (var entrySet : map.entrySet()) {
            names.add(entrySet.getKey());
            prices.add(entrySet.getValue());
        }
    }


    public void quicksort(int low, int high) {
        if (low < high) {
            int p = partition(low, high);
            quicksort(low, p - 1);
            quicksort(p + 1, high);
        }
    }

    void swap(int low, int pivot) {
        String tmp = names.get(low);
        Double tmpNum = prices.get(low);
        names.set(low, names.get(pivot));
        names.set(pivot, tmp);
        prices.set(low, prices.get(pivot));
        prices.set(pivot, tmpNum);
    }

    int partition(int low, int high) {
        int p = low;
        for (int i = low + 1; i <= high; i++) {
            if (prices.get(i).compareTo(prices.get(low)) == 0) {
                if(names.get(i).compareTo(names.get(low)) < 0) {
                    swap(++p, i);
                }
            }
            if (prices.get(i).compareTo(prices.get(low)) < 0) {
                swap(++p, i);
            }
        }
        swap(low, p);
        return p;
    }

}
