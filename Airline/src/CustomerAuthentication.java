import java.math.BigInteger;
import java.sql.*;
import java.util.Scanner;

public class CustomerAuthentication {
    private String NATIONALID2;
    private String USERNAME,PASSWORD,EMAIL,TYPE;
    private int AGE;
    public CustomerAuthentication(){}

    public CustomerAuthentication(String id,String name,String password, String email,String type,int age)
    {
        NATIONALID2=id;
        USERNAME=name;
        PASSWORD=password;
        EMAIL=email;
        TYPE=type;
        AGE=age;

    }
    public String getUSERNAME() {
        return USERNAME;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }
    public String getEMAIL() {
        return EMAIL;
    }
    public String getTYPE() {
        return TYPE;
    }
    public int getAGE() {
        return AGE;
    }
    public String getNATIONALID2() {
        return NATIONALID2;
    }
    public static boolean LogIn(Connection con, int id,String pass) throws SQLException {
        String query = "SELECT 1 FROM customer WHERE NATIONALID2 = ? AND PASSWORD = ?  LIMIT 1";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2,pass);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next(); // If there is a matching row, the login is valid; otherwise, it's invalid.
            }
        }
    }
    public boolean EmailValidator(String email){
        String strongEmailRegex ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(strongEmailRegex);
    }
    public boolean register(Connection con, int id, String name, int age, String password, String email, String type) throws SQLException
    {
        String query = "INSERT INTO CUSTOMER (NATIONALID2, USERNAME, AGE, PASSWORD, EMAIL, TYPE) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setString(4, password);
            statement.setString(5, email);
            statement.setString(6, type);
            if(statement.executeUpdate()>0)
                return true;
            else
                return false;
        }

    }
}

