package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Reddit reddit = new Reddit();
        runMenu();
    }
    public static void runMenu() {
        while (true) {
            System.out.println("Enter your command\n1-Login 2-Sign up 3-Exit");
            Scanner in = new Scanner(System.in);
            switch (in.nextInt()) {
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
        System.out.println("username: ");
        Scanner p = new Scanner(System.in);
        String username = p.nextLine();
        System.out.println("password: ");
        Scanner i = new Scanner(System.in);
        String password = i.nextLine();
        System.out.println("EmailAddress: ");
        Scanner o = new Scanner(System.in);
        String emailAddress = o.nextLine();
        Account account = new Account(username, password, emailAddress);
        // account = account.login();
        System.out.println("TimeLine:");
        //timeline
    }
    public static void signUp() {
        //reddit.viewAllSubreddits();
    }
}