package pl.szymanski.hubert;

import java.util.HashMap;

public class ItemDataBase {

    // simple collection of item (key is code)
    private HashMap<String, Item> data;

    // constructor
    public ItemDataBase() {
        // create map
        data = new HashMap<>();
        // load some data
        loadData();
    }

    public Item getItemWithCode(String code) throws Exception {

        // item to return
        Item item;
        // get item and ...
        item = data.get( code );
        // check if it is there
        if ( item == null )
            // throw exception if not
            throw new Exception("NOT FOUND");
        else
            return item;
    }

    // method to generate some testing items
    private void loadData() {
        // butter item
        data.put("0100", new Item("Butter", 5.99f) );
        // candies item
        data.put("0101", new Item("Candies", 3.99f) );
        // juice item
        data.put("0102", new Item("Orange Juice", 2.99f) );
    }

}
