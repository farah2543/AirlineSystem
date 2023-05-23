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
}
