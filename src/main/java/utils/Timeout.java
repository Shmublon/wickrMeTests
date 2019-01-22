package utils;

public class Timeout {
    public static final Long DEFAULT_IMPLICITLY_WAIT = 30L;

    public static void timeout(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
