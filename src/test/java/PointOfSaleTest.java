import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.szymanski.hubert.*;

public class PointOfSaleTest {

    private LCDisplay lcDisplay;

    @Before
    public void setup() {
        lcDisplay = new LCDisplay();
    }

    @Test
    public void shouldDisplayInvalidBarCode() {
        System.out.println(">> Test: invalid bar code");
        PointOfSale cash = new PointOfSale(new BarCodeScanner(null), lcDisplay, new BillPrinter(), new ItemDataBase());
        cash.scanItem();
        Assert.assertSame( "Invalid bar-code" , lcDisplay.getMessage() );
    }

    @Test
    public void shouldDisplayItemNotFound() {
        System.out.println(">> Test: item not found");
        PointOfSale cash = new PointOfSale(new BarCodeScanner("1111"), lcDisplay, new BillPrinter(), new ItemDataBase());
        cash.scanItem();

        Assert.assertSame("Product not found", lcDisplay.getMessage() );
    }

    @Test
    public void shouldDisplayTotalZero() {
        System.out.println(">> Test: total zero");
        PointOfSale cash = new PointOfSale(new BarCodeScanner("exit"), lcDisplay, new BillPrinter(), new ItemDataBase());
        cash.scanItem();

        Assert.assertTrue( lcDisplay.getMessage().equals("TOTAL: 0,00$") );
    }

    @Test
    public void shouldDisplayItem1() {
        System.out.println(">> Test: display item 1");
        BarCodeScanner bcScanner = new BarCodeScanner("0102");
        PointOfSale cash = new PointOfSale(bcScanner, lcDisplay, new BillPrinter(), new ItemDataBase());
        // add item
        cash.scanItem();

        Assert.assertTrue( lcDisplay.getMessage().equals("Orange Juice: 2,99$") );
//        Doesn't work... no idea why
//        Assert.assertSame("Orange Juice: 2,99$", lcDisplay.getMessage() );
    }

    @Test
    public void shouldDisplayItem2() {
        System.out.println(">> Test: display item 2");
        BarCodeScanner bcScanner = new BarCodeScanner("0100");
        PointOfSale cash = new PointOfSale(bcScanner, lcDisplay, new BillPrinter(), new ItemDataBase());
        // add item
        cash.scanItem();

        Assert.assertTrue( lcDisplay.getMessage().equals("Butter: 5,99$") );
    }

    @Test
    public void shouldDisplaySecondItem() {
        System.out.println(">> Test: display second item");
        BarCodeScanner bcScanner = new BarCodeScanner("0100");
        PointOfSale cash = new PointOfSale(bcScanner, lcDisplay, new BillPrinter(), new ItemDataBase());
        // add item
        cash.scanItem();
        // set new code and scan it
        bcScanner.setCode("0101");
        cash.scanItem();

        Assert.assertTrue( lcDisplay.getMessage().equals("Candies: 3,99$") );
    }

    @Test
    public void shouldDisplayTotal2_99() {
        System.out.println(">> Test: display total of 2,99$");
        BarCodeScanner bcScanner = new BarCodeScanner("0102");
        PointOfSale cash = new PointOfSale(bcScanner, lcDisplay, new BillPrinter(), new ItemDataBase());
        // add item
        cash.scanItem();
        // send exit
        bcScanner.setCode("exit");
        // "scan" it
        cash.scanItem();

        Assert.assertTrue( lcDisplay.getMessage().equals("TOTAL: 2,99$") );
    }

    @Test
    public void shouldDisplayTotal9_98() {
        System.out.println(">> Test: display total of two items");
        BarCodeScanner bcScanner = new BarCodeScanner("0100");
        PointOfSale cash = new PointOfSale(bcScanner, lcDisplay, new BillPrinter(), new ItemDataBase());
        // add item
        cash.scanItem();
        // set new code and scan it
        bcScanner.setCode("0101");
        cash.scanItem();
        // send exit
        bcScanner.setCode("exit");
        cash.scanItem();

        Assert.assertTrue( lcDisplay.getMessage().equals("TOTAL: 9,98$") );
    }

    @Test
    public void shouldDisplayTotal9_98WithInvalidBarCode() {
        System.out.println(">> Test: display total of two items and invalid bar code");
        BarCodeScanner bcScanner = new BarCodeScanner("0100");
        PointOfSale cash = new PointOfSale(bcScanner, lcDisplay, new BillPrinter(), new ItemDataBase());
        // add item
        cash.scanItem();
        // set new code and scan it
        bcScanner.setCode("0101");
        cash.scanItem();
        // send invalid bar code
        bcScanner.setCode( null );
        cash.scanItem();
        // send exit
        bcScanner.setCode("exit");
        cash.scanItem();

        Assert.assertTrue( lcDisplay.getMessage().equals("TOTAL: 9,98$") );
    }

    @Test
    public void shouldDisplayTotal9_98WithNotFound() {
        System.out.println(">> Test: display total of two items and not found");
        BarCodeScanner bcScanner = new BarCodeScanner("0100");
        PointOfSale cash = new PointOfSale(bcScanner, lcDisplay, new BillPrinter(), new ItemDataBase());
        // add item
        cash.scanItem();
        // set new code and scan it
        bcScanner.setCode("0101");
        cash.scanItem();
        // send empty bar code
        bcScanner.setCode( "3211" );
        cash.scanItem();
        // send exit
        bcScanner.setCode("exit");
        cash.scanItem();

        Assert.assertTrue( lcDisplay.getMessage().equals("TOTAL: 9,98$") );
    }

}