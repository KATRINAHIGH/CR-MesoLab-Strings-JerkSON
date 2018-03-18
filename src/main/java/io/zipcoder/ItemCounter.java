package io.zipcoder;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class ItemCounter {

    private HashMap<String, HashMap<Double, Integer>> itemCounter = new HashMap<>();

    private void countItem(Item item) {
        HashMap<Double, Integer> mapForName = this.itemCounter.getOrDefault(item.getName(), new HashMap<>());

        Integer countForPrice = mapForName.getOrDefault(item.getPrice(), 0);
        countForPrice++;

        mapForName.put(item.getPrice(), countForPrice);
        this.itemCounter.put(item.getName(), mapForName);
    }

    @Override
    public String toString() {
        StringBuilder shoppingList = new StringBuilder();
        Formatter prettyPrint = new Formatter(shoppingList);
        for (Map.Entry<String, HashMap<Double, Integer>> item : itemCounter.entrySet()) {

            // do some formatting here just for the item name

            for (Map.Entry<Double, Integer> priceInfo : item.getValue().entrySet()) {
                Double price = priceInfo.getKey();
                Integer count = priceInfo.getValue();

                // do some formatting here for each individual price
            }
            prettyPrint.format("name: %s " + "seen: %d%n" +
                            "=============%n" +
                            "price: %f " +
                            "seen: %d%n" +
                            "-------------%n",
                    item.getKey(), price, count);
        }

        return shoppingList.toString();
        //return "name:" + name + " price:" + price + " type:" + type + " expiration:" + expiration;


    }

}


//string = items name, key
//value = type double and integer
//double items price integer how many times seen that price for that item

        name:Milk seen:6times
                ============= =============
                Price:3.23seen:5times
                ------------- -------------
                Price:1.23seen:1time

                name:Bread seen:6times
                ============= =============
                Price:1.23seen:6times
                ------------- -------------

                name:Cookies seen:8times
                ============= =============
                Price:2.25seen:8times
                ------------- -------------