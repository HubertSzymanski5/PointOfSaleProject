package pl.szymanski.hubert;

import java.util.List;

public interface IPrinter {

    // gets List of Items and total price to print it
    void printBill(List<Item> items, float totalPrice );

}
