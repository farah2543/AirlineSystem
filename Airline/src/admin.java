import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

import static java.lang.Math.abs;

public class admin {
    private  int id;
    private String username ;
    private String password;
    private String email;



    public void set_username(String username){

            this.username = username;
    }

    public void set_email(String email){
        this.email = email;
    }

    /*private void set_info(){
        set_username();
        set_email();
        set_password();
    }*/

    public void set_password(String pass){
        this.password = pass;
    }

    public int get_id(){return id;}

    public String get_name(){return username;}

    public String get_email(){return email;}

    public String get_password(){return password;}



    public boolean sign_up(){

        final String DB_Url = "jdbc:mysql://localhost/first?serverTimezone=UTC";
        final String Username = "root";
        final String Pass = "Boody_500";
        if (email.isEmpty() || username.isEmpty() || password.isEmpty() ){
            return false;
        }
        else{
            try(Connection con = DriverManager.getConnection(DB_Url,Username,Pass)){

                String fir_query = "select email from admin WHERE EMAIL = ?";

                PreparedStatement prepare_fir = con.prepareStatement(fir_query);
                prepare_fir.setString(1,this.email);
                ResultSet result =  prepare_fir.executeQuery();

                while (result.next()){

                    String temp = result.getString("email");
                    if (email.equals(temp)){
                        return false;
                    }



                }


                String sec_query = "insert into admin (username,email,password) values (?,?,?)";
                PreparedStatement prepare_sec = con.prepareStatement(sec_query);
                prepare_sec.setString(1,username);
                prepare_sec.setString(2,email);
                prepare_sec.setString(3,password);

                int addedrow = prepare_sec.executeUpdate();

                if (addedrow > 0){
                    System.out.println("\n\naccount added successfully\n\n");
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
            return true;
        }

    }

    public boolean login(){

        final String DB_Url = "jdbc:mysql://localhost/first?serverTimezone=UTC";
        final String Username = "root";
        final String Pass = "Boody_500";


        try (Connection con = DriverManager.getConnection(DB_Url,Username,Pass)){
            String query = "select * from admin where email = ? and password = ?";

            PreparedStatement stmnt = con.prepareStatement(query);
            stmnt.setString(1,get_email());
            stmnt.setString(2,get_password());
            ResultSet result = stmnt.executeQuery();


            while (!result.next()){
                System.out.println("invalid credentials!,try again");
                return false;
            }

            System.out.println("\n\nlogged in successfully\n\n ");
            username = result.getString("USERNAME");


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;

    }

    public void add_aircraft(){
        AIRCRAFT a = new AIRCRAFT();
        a.ADD_Aircraft();
    }

    public void add_flight(){
        Flight f = new Flight();
        //f.addFlight();
    }

    public void update_aircraft(){
        AIRCRAFT a = new AIRCRAFT();
        a.Update_Aircraft();
        SEATS s = new SEATS();
        int k = a.get_Updated_CAPACITY();
        if (k > 0){
            s.ADD_Seat(k);
        }
        else if (k < 0){
            s.Remove_Seat(abs(k));
        }
    }

    public void update_flight(){
        Flight f = new Flight();
        //f.updateFlightInfo();
    }




    public void avalible_flights(){
        Flight f = new Flight();

    }

    /*public void menu(){


        int choice;

        System.out.print("\n\n1.  Signup\n2.  Login\n3.  back to main menu\nselect a choice:");
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        boolean loggedin = false;

        while (choice != 3){

                if (choice == 1){

                    sign_up();
                    System.out.print("\n\n1.  Signup\n2.  Login\n3.  back to main menu\nselect a choice:");
                    choice = input.nextInt();
                }

                else if (choice == 2){
                    loggedin = login();

                    while (loggedin){
                        int temp;
                        System.out.print("1.   Add Aircraft\n2.   Update Aircraft\n3.   Add Flight\n4.   Update Flight\n5.   display available flights\n6.   Logout\nSelect Option:");
                        temp = input.nextInt();

                        if (temp == 1){
                            add_aircraft();
                        }

                        else if (temp == 2){
                            update_aircraft();
                        }

                        else if (temp == 3){
                            add_flight();
                        }

                        else if (temp == 4){
                            update_flight();
                        }

                        else if (temp == 5){
                           avalible_flights();
                        }

                        else if (temp == 6){
                            loggedin = false;
                        }

                        else{
                            System.out.print("InValid Option!\nSelect Option:");
                            temp = input.nextInt();
                        }
                    }

                    System.out.print("\n\n1.  Signup\n2.  Login\n3.  back to main menu\nselect a choice:");
                    choice = input.nextInt();
                }



                else{
                    System.out.println("InValid Choice!\nselect a choice:");
                    choice = input.nextInt();
                }


            }

        System.out.println("\n");

    }*/
}
