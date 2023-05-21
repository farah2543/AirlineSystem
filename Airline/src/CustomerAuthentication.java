import java.math.BigInteger;
import java.sql.*;
import java.util.Scanner;

public class CustomerAuthentication {
    private Long NATIONALID2;
    private String USERNAME, PASSWORD, EMAIL, TYPE;
    private int AGE;
    final String DB_URL = "jdbc:mysql://localhost/airline?";
    final String Username = "root";
    final String Pass = "FarahHazem123@";

    public CustomerAuthentication () {
    }




    public CustomerAuthentication ( Long id , String name , String password , String email , String type , int age ) {
        NATIONALID2 = id;
        USERNAME = name;
        PASSWORD = password;
        EMAIL = email;
        AGE = age;

    }

    public String getUSERNAME () {
        return USERNAME;
    }

    public String getPASSWORD () {
        return PASSWORD;
    }

    public String getEMAIL () {
        return EMAIL;
    }

    public int getAGE () {
        return AGE;
    }

    public Long getNATIONALID2 () {
        return NATIONALID2;
    }

    public static boolean LogIn ( Connection con , Long id , String pass ) throws SQLException {
        String query = "SELECT 1 FROM costumer WHERE NATIONALID2 = ? AND PASSWORD = ?  LIMIT 1";
        try ( PreparedStatement statement = con.prepareStatement( query ) ) {
            statement.setLong( 1 , id );
            statement.setString( 2 , pass );
            try ( ResultSet rs = statement.executeQuery() ) {
                return rs.next(); // If there is a matching row, the login is valid; otherwise, it's invalid.
            }
        }
    }

    public boolean EmailValidator ( String email ) {
        String strongEmailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return email.matches( strongEmailRegex );
    }

    public boolean register ( Connection con , Long id , String name , int age , String password , String email ) throws SQLException {
        String query = "INSERT INTO costumer (NATIONALID2, AGE, EMAIL,PASSWORD, USERNAME ) VALUES (?, ?, ?, ?, ?)";
        try ( PreparedStatement statement = con.prepareStatement( query ) ) {
            statement.setLong( 1 , id );
            statement.setInt( 2 , age );
            statement.setString( 3 , email );
            statement.setString( 4 , password );
            statement.setString( 5 , name );

            if ( statement.executeUpdate() > 0 )
                return true;
            else
                return false;
        }

    }
}
