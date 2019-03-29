package pl.szymanski.hubert;

import java.util.ArrayList;
import java.util.List;

public class PointOfSale implements IPointOfSale {

    // Input Dev - BarCodeScanner
    private ICodeScanner codeScanner;

    // Output Dev - LCD & Printer
    private IDisplay lcDisplay;
    private IPrinter billPrinter;

    // list of items to sell and totalPrice
    private List<Item> items;
    private float totalPrice;

    // connection with DB
    private ItemDataBase database;

    // constructor
    public PointOfSale(ICodeScanner scanner, IDisplay display, IPrinter printer, ItemDataBase database) {
        // get devices
        this.codeScanner = scanner;

        this.lcDisplay = display;
        this.billPrinter = printer;

        // get database
        this.database = database;

        // create item list and total prize
        items = new ArrayList<>();
        totalPrice = 0;

    }

    // try to scan and add item to bill
    @Override
    public void scanItem() {
        // obtain barcode
        String barCode = codeScanner.getCode();
        if ( barCode == null ) {
            lcDisplay.displayUpdate("Invalid bar-code");
            return;
        }

        // should be in public endBill() method ...
        else if (barCode.equals("exit")) {
            displayTotal();
            printBill();
            return;
        }

        Item item;

        // try to get item from database
        try {
            item = database.getItemWithCode(barCode);
        }
        // item is not in database
        catch (Exception e) {
            if ( e.getMessage().contentEquals("INVALID CODE") )
                lcDisplay.displayUpdate("Invalid bar-code");
            else if ( e.getMessage().contentEquals("NOT FOUND") )
                lcDisplay.displayUpdate("Product not found");

            return;
        }

        // if no error occurred add item to database
        items.add(item);
        // print it on lcd
        lcDisplay.displayUpdate(item.name + ": " + String.format("%.2f", item.value) + "$");
        // add value to sum
        totalPrice += item.value;
    }

    private void printBill() {
        billPrinter.printBill(items, totalPrice);
    }

    private void displayTotal() {
        lcDisplay.displayUpdate("TOTAL: " + String.format("%.2f", totalPrice) + "$");
    }


}
