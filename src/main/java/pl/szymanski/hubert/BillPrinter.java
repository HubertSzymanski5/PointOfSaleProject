package pl.szymanski.hubert;

import java.util.List;

public class BillPrinter implements IPrinter {

    @Override
    public void printBill(List<Item> items, float totalPrice) {

        System.out.println("--- BILL ---");

        // print all of the items
        for ( var i : items ) {
            System.out.println(i.name + ": " + i.value + "$");
        }
        // print total prize
        System.out.println("TOTAL: " + totalPrice);

        System.out.println("--- --- ---");

    }
}
