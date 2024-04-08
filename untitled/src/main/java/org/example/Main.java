package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Reddit reddit = new Reddit();
        Reddit.addAccount(new Account("qw", "qw","qwerty@gmail.com"));
        runMenu();
    }
    public static void runMenu() {
        while (true) {
            System.out.println("Enter your command\n1-Login 2-Sign up 3-Exit");
            Scanner in = new Scanner(System.in);
            int cmd = in.nextInt();
            switch (cmd) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    return;
                default:
                    continue;
            }
        }
    }
    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("username: ");
        String username = scanner.nextLine();
        System.out.println("password: ");
        scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        System.out.println("EmailAddress: ");
        scanner = new Scanner(System.in);
        String emailAddress = scanner.nextLine();
        Account account = new Account(username, password, emailAddress);
        account = account.login();
        if(account == null) {
            System.out.println("Such an account does not exist 1)login 2)Sign up");
            Scanner in = new Scanner(System.in);
            int cmd = in.nextInt();
            switch(cmd) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
                    break;
                default:
                    login();
            }
        }
        else {
            System.out.println("1)View TimeLine  2)View Joined Subreddits  3)View All Subreddits  4)View Profile  5)Create Subreddit");
        }
        //timeline
    }
    public static void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("username: ");
        String username = scanner.nextLine();
        System.out.println("password: ");
        scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        System.out.println("EmailAddress: ");
        scanner = new Scanner(System.in);
        String emailAddress = scanner.nextLine();
        while(!Account.validateEmail(emailAddress)) {
            System.out.println("Not a valid emailAddress\nNew EmailAddress: ");
            scanner = new Scanner(System.in);
            emailAddress = scanner.nextLine();
        }
        Account account = new Account(username, password, emailAddress);
        account.signup(account);
    }
}