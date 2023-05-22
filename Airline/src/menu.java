import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class menu {

    public int system_menu(){
        System.out.println("welcome to airline\n");
        System.out.print("1.   Admin\n2.   User\n3.   exit\nselect your choice:");
        int choice;
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        return choice;
    }
    public void User_menu(CustomerAuthentication cust){
        final String URL = "jdbc:mysql://localhost/version2?";
        final String USERNAME = "salma";
        final String PASSWORD = "Salma.123456";


        Scanner Scan=new Scanner(System.in);

        String name="insert into CUSTOMER (NATIONALID2,USERNAME,AGE,PASSWORD,EMAIL,TYPE) values (?,?,?,?,?,?)";
        try ( Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
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
