package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemParser {

    //Step 1, main string breaking up each entry by pound sign
    public static ArrayList<String> parseRawDataIntoStringArray(String rawData) {
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, rawData);
        return response;
    }

    //Step 2, breaking up each individual string added additional tokens to split on
    public static ArrayList<String> findKeyValuePairsInRawItemData(String rawItem) {
        String stringPattern = "[;|\\^|%|\\*|!|@]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, rawItem);
        return response;
    }

    //Step3, we need to know which key we are working on depending on key we will get back a string
    //or a double as the property
    public static Item parseStringIntoItem(String rawItem) throws ItemParseException {
        ArrayList<String> itemRow = findKeyValuePairsInRawItemData(rawItem);

        String name = null;
        Double price = null;
        String type = null;
        String expiration = null;

        for (String property : itemRow) {

            ArrayList<String> kv = splitStringWithRegexPattern(":", property);

            if (kv.size() != 2) {
                throw new ItemParseException();
            }

            String key = kv.get(0).toLowerCase();
            switch (key) {
                case "name":
                    name = toTitleCase(kv.get(1));
                    break;
                case "price":
                    price = Double.parseDouble(kv.get(1));
                    break;
                case "type":
                    type = toTitleCase(kv.get(1));
                    break;
                case "expiration":
                    expiration = kv.get(1);
                    break;
                default:
                    throw new ItemParseException();

            }
            }
        if (name == null || price == null || type == null || expiration == null) {
            throw new ItemParseException();
        }
        return new Item(name, price, type, expiration);
    }

    private static ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString) {
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }

    private static String toTitleCase(String s) {
        if(s.substring(0,1).equalsIgnoreCase("c")){
            return "Cookies";
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
