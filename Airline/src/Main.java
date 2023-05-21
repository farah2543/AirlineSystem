//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.math.BigInteger;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         final String DB_Url = "jdbc:mysql://localhost/airline?";
         final String Username = "root";
         final String Pass = "FarahHazem123@";
        CustomerAuthentication cust=new CustomerAuthentication();
        Scanner Scan=new Scanner(System.in);

        String name="insert into CUSTOMER (NATIONALID2,USERNAME,AGE,PASSWORD,EMAIL,TYPE) values (?,?,?,?,?,?)";
        try (Connection con=DriverManager.getConnection(DB_Url,Username,Pass)){
            System.out.println("1-register\n" +
                    "2-log in\n" +
                    "3-exit\n");
            int option=Scan.nextInt();
            //Register
            if(option ==1)
            {
                System.out.println("enter your National id-->");
                Long ID=Scan.nextLong();
                System.out.println("enter your name-->");
                String username=Scan.next();
                System.out.println("enter your password");
                String password=Scan.next();
                System.out.println("enter your email-->");
                String email=Scan.next();
                if(!cust.EmailValidator(email))
                {
                    while (!cust.EmailValidator(email))
                    {
                        System.out.println("enter Valid Email:-");
                        email=Scan.next();
                    }
                }
                System.out.println("enter your age-->");
                int age=Scan.nextInt();
                if(cust.register(con,ID,username,age,password,email))
                {
                    System.out.println("sign up successful");
                }
                else
                {
                    System.out.println("can't assign");
                }
            }
            //log in
            if(option ==2) {
                // Call the login verification method
                System.out.println("enter your id ");
                Long id=Scan.nextLong();
                System.out.println("enter you password");
                String pass=Scan.next();
                boolean loggedIn = cust.LogIn(con, id,pass);

                if (loggedIn)
                {
                    System.out.println("Login successful!");
                }
                else
                {
                    System.out.println("Invalid id or password.");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}