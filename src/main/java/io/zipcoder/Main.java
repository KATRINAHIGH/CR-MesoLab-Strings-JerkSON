package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;


public class Main {

    public String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception {
        String output = (new Main()).readRawDataToString();
        ArrayList<Item> items = new ArrayList<Item>();

        ArrayList<String> losePounds = ItemParser.parseRawDataIntoStringArray(output);
        //System.out.println(losePounds);

        for(String s : losePounds){
           //System.out.println(s);
            try{
                Item item = ItemParser.parseStringIntoItem(s);
                //System.out.println(item);
            }catch (ItemParseException ex){
                //System.out.println("INVALID ROW");
            }
        }
        // TODO: parse the data in output into items, and display to console.
    }
}





package io.zipcoder;

        import org.apache.commons.io.IOUtils;

        import java.util.ArrayList;


public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();

        ItemCounter counter = new ItemCounter();

        ArrayList<String> losePounds = ItemParser.parseRawDataIntoStringArray(output);
        for (String s : losePounds) {
            try {
                Item item = ItemParser.parseStringIntoItem(s);
                counter.countItem(item);
            } catch (ItemParseException ex) {
                counter.countError();
            }
        }

        System.out.println(counter.getItemCounter());
        System.out.println(counter.getErrors());
    }
}