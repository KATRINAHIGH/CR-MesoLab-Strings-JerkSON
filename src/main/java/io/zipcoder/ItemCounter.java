package io.zipcoder;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class ItemCounter {

    private HashMap<String, HashMap<Double, Integer>> itemCounter = new HashMap<>();
    private Integer errors = 0;

    public void countItem(Item item) {
        HashMap<Double, Integer> mapForName = this.itemCounter.getOrDefault(item.getName(), new HashMap<>());

        Integer countForPrice = mapForName.getOrDefault(item.getPrice(), 0);
        countForPrice++;

        mapForName.put(item.getPrice(), countForPrice);
        this.itemCounter.put(item.getName(), mapForName);
    }

    public void countError() {
        this.errors++;
    }

    public Integer countItemTotal(String nameKey){
        HashMap<Double, Integer> sumCount = this.itemCounter.get(nameKey);
        return sumCount.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public String toString() {
        StringBuilder shoppingList = new StringBuilder();
        Formatter prettyPrint = new Formatter(shoppingList);
        for (Map.Entry<String, HashMap<Double, Integer>> item : itemCounter.entrySet()) {
            String countHeader = "seen:";
            prettyPrint.format("name: %s%20s %d%n=============            ============%n",
                    item.getKey(), countHeader, countItemTotal(item.getKey()) );

            for (Map.Entry<Double, Integer> priceInfo : item.getValue().entrySet()) {
                Double price = priceInfo.getKey();
                Integer count = priceInfo.getValue();
                prettyPrint.format(
                        "price: %.2f%20s %d%n-------------             -------------%n",
                        price, countHeader, count);
            }
            prettyPrint.format("%s%n", " ");
        }
        prettyPrint.format("Errors: %d", this.errors);
        return shoppingList.toString();
    }

}




