package pl.szymanski.hubert;


// simple implementation of ICodeScanner interface
// for tests
public class BarCodeScanner implements ICodeScanner {

    // value of a code stored as String
    private String code;

    // constructor
    public BarCodeScanner( ) {
        this.code = null;
    }

    public BarCodeScanner( String code ) {
        this.code = code;
    }

    // from ICodeScanner interface
    @Override
    public String getCode() {

        return this.code;
    }

    // test method
    public void setCode( String code ) {
        this.code = code;
    }

}
