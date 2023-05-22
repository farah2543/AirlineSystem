import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        menu m =new menu();


        while (true){
            int x = m.system_menu();

            if (x == 1){
                admin a = new admin();
                a.menu();
            }

            if (x == 2){
                CustomerAuthentication cust=new CustomerAuthentication();
                m.User_menu(cust);
                Flight f = new Flight();
                f.display_flights();
                ticket ticket = new ticket();
                ticket.bookTicket();


            }

            if (x == 3){
                System.exit(0);
            }
        }




    }

    }
