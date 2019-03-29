package pl.szymanski.hubert;

public class LCDisplay implements IDisplay {

    // store message
    private String message;

    public LCDisplay() {
        this.message = "";
    }

    @Override
    public void displayUpdate(String message) {
        this.message = message;
        System.out.println("LDC: " + message);
    }

    public String getMessage() {
        return message;
    }
}
