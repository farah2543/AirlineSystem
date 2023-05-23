import java.math.BigInteger;
import java.sql.*;
import java.util.Scanner;

public class CustomerAuthentication {
    private String NATIONALID2;
    public static CustomerAuthentication cust;

    public CustomerAuthentication() {
    }

    public CustomerAuthentication(String id) {
        NATIONALID2 = id;

    }

    public void UpdateData(String id) {
        NATIONALID2 = id;

        cust.NATIONALID2 = id;

    }

    public static void InitializeCust(String id) {
        cust = new CustomerAuthentication(id);
    }

    public void setNATIONALID2(String id) {
        NATIONALID2 = id;
    }

    public String getNATIONALID2() {
        return NATIONALID2;
    }
}
