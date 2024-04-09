package org.example;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Reddit reddit = new Reddit();
        Account a = new Account("qw", "qw","qwerty@gmail.com");
        Reddit.addAccount(a);
        Reddit.createSubreddit("first subreddit", a);
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
            makeMenu(account);
        }
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
        makeMenu(account);
    }
    public static void makeMenu(Account account) {
        System.out.println("Enter your command\n1)Create Subreddit  2)View All Posts  3)View All Subreddits  4)View Profile  5)Create Post  6)Search  7)View TimeLine 8)Change Username & Password  9)Join Subreddit  10)Log Out");
        Scanner scanner = new Scanner(System.in);
        int cmd = scanner.nextInt();
        switch(cmd) {
            case 1:
                Scanner as = new Scanner(System.in);
                System.out.println("Enter a title for the subreddit");
                String title = as.nextLine();
                Reddit.createSubreddit(title, account);
                break;
            case 2:
                Reddit.viewAllPosts();
                break;
            case 3:
                Reddit.viewAllSubreddits();
                break;
            case 4:
                account.viewProfile();
                break;
            case 5:
                if (account.getJoinedSubreddits().isEmpty()) {
                    System.out.println("In order to post you need to join a subreddit");
                    break;
                }
                else {
                    account.viewJoinedSubreddits();
                    Scanner d = new Scanner(System.in);
                    int c = d.nextInt();
                    System.out.println("Title");
                    Scanner b = new Scanner(System.in);
                    String Title = b.nextLine();
                    System.out.println("Body");
                    Scanner m = new Scanner(System.in);
                    String body = m.nextLine();
                    Reddit.createPost(Title, body, account, account.getJoinedSubreddits().get(c - 1));
                }
                break;
            case 6:
                System.out.println("Enter whatever you want to search(add ‘r/’ to the subreddit name and ‘u/’ to the username)");
                Scanner a = new Scanner(System.in);
                String s = a.nextLine();
                if (s.charAt(0) != 'r' && s.charAt(0) != 'u') {
                    System.out.println("add ‘r/’ to the subreddit name and ‘u/’ to the username");
                    break;
                }
                else if (s.charAt(0) == 'r' && s.charAt(1) == '/') {
                    s = s.substring(2);
                    Subreddit result = Reddit.searchSubreddit(s);
                    if (result == null) {
                        System.out.println("Such a Subreddit does not exist");
                    }
                    else {
                        result.viewSubreddit(1);
                    }
                }
                else if (s.charAt(0) == 'u' && s.charAt(1) == '/') {
                    s = s.substring(2);
                    Account result = Reddit.searchAccount(s);
                    if (result == null) {
                        System.out.println("Such an Account does not exist");
                    }
                    else {
                        result.viewProfile();
                    }
                }
                break;
            case 7:
                account.viewJoinedPosts();
                break;
            case 8:
                Scanner sc = new Scanner(System.in);
                System.out.println("new username: ");
                String username = sc.nextLine();
                System.out.println("new password: ");
                scanner = new Scanner(System.in);
                String password = sc.nextLine();
                System.out.println("new EmailAddress: ");
                scanner = new Scanner(System.in);
                String emailAddress = sc.nextLine();
                while(!Account.validateEmail(emailAddress)) {
                    System.out.println("Not a valid emailAddress\nNew EmailAddress: ");
                    scanner = new Scanner(System.in);
                    emailAddress = sc.nextLine();
                }
                account.changePassword(password);
                account.changeUsername(username);
                account.changeEmailAddress(emailAddress);
                break;
            case 9:
                Reddit.viewAllSubreddits();
                Scanner d = new Scanner(System.in);
                int c = d.nextInt();
                Subreddit x = Reddit.getSubreddits().get(c - 1);
                boolean flag = true;
                for(Subreddit temp : account.getJoinedSubreddits()) {
                    if(Objects.equals(temp,x)) {
                        System.out.println("You've already joined this Subreddit");
                        flag = false;
                    }
                }
                if (flag) {
                    account.joinSubreddit(x, account);
                    break;
                }
                break;
        }
        makeMenu(account);
    }
}