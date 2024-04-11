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
        boolean hasLoggedOut = false;
        while(!hasLoggedOut) {
            System.out.println("Enter your command\n1)Create Subreddit  2)View All Posts  3)View All Subreddits  4)View All Profiles  5)Create Post  6)Search  7)View TimeLine 8)Change Username & Password  9)Join Subreddit  10)Log Out");
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
                    if(Reddit.getPosts().isEmpty()) {
                        System.out.println("No Posts");
                    }
                    else {
                        System.out.println("Select a post by entering its index");
                        Scanner t = new Scanner(System.in);
                        int index = t.nextInt();
                        System.out.println("1)Post Comment  2)Up Vote Post  3)Down Vote Post  4)Up Vote Comment  5)Down Vote Comment  6)Retract Comment Vote  7)Retract Post Vote  8)View Comment Poster's Profile  9)View Post Creator's Profile  10)Join the Subreddit related to the Post  else)return");
                        Scanner d = new Scanner(System.in);
                        int v = d.nextInt();
                        if(v == 1) {
                            System.out.println("Body of the Comment: ");
                            Scanner e = new Scanner(System.in);
                            String body = e.nextLine();
                            Reddit.addComment(Reddit.getPosts().get(index - 1), account, body);
                        }
                        else if (v == 2) {
                            account.postKarma(account, true, Reddit.getPosts().get(index - 1));
                        }
                        else if (v == 3) {
                            account.postKarma(account, false, Reddit.getPosts().get(index - 1));
                        }
                        else if (v == 4) {
                            if (!Reddit.getPosts().get(index - 1).getComments().isEmpty()) {
                                int j = 1;
                                for(Comment temp : Reddit.getPosts().get(index - 1).getComments()) {
                                    temp.viewComment(j);
                                    j++;
                                }
                                System.out.println("Select a comment by entering its index");
                                Scanner h = new Scanner(System.in);
                                int inx = h.nextInt();
                                account.commentKarma(account, true, Reddit.getPosts().get(index - 1).getComments().get(inx - 1));
                            }
                        }
                        else if (v == 5) {
                            if (!Reddit.getPosts().get(index - 1).getComments().isEmpty()) {
                                int i = 1;
                                for(Comment temp : Reddit.getPosts().get(index - 1).getComments()) {
                                    temp.viewComment(i);
                                    i++;
                                }
                                System.out.println("Select a comment by entering its index");
                                Scanner h = new Scanner(System.in);
                                int inx = h.nextInt();
                                account.commentKarma(account, false, Reddit.getPosts().get(index - 1).getComments().get(inx - 1));
                            }
                        }
                        else if (v == 6) {
                            if (!Reddit.getPosts().get(index - 1).getComments().isEmpty()) {
                                int i = 1;
                                for(Comment temp : Reddit.getPosts().get(index - 1).getComments()) {
                                    temp.viewComment(i);
                                    i++;
                                }
                                System.out.println("Select a comment by entering its index");
                                Scanner h = new Scanner(System.in);
                                int inx = h.nextInt();
                                account.retractVote(Reddit.getPosts().get(index - 1).getComments().get(inx - 1));
                            }
                        }
                        else if (v == 7) {
                            account.retractVote(Reddit.getPosts().get(index - 1));
                        }
                        else if (v == 8) {
                            if (!Reddit.getPosts().get(index - 1).getComments().isEmpty()) {
                                int i = 1;
                                for(Comment temp : Reddit.getPosts().get(index - 1).getComments()) {
                                    temp.viewComment(i);
                                    i++;
                                }
                                System.out.println("Select a comment by entering its index");
                                Scanner h = new Scanner(System.in);
                                int inx = h.nextInt();
                                Reddit.getAccounts().get(inx - 1).viewProfile();

                            }
                            break;
                        }
                        else if (v == 9) {
                            Reddit.getPosts().get(index - 1).getCreator().viewProfile();
                            break;
                        }
                        else if (v == 10) {
                            account.joinSubreddit(Reddit.getPosts().get(index - 1).getSubreddit(),account);
                        }
                        else {
                            break;
                        }
                    }
                    break;
                case 3:
                    Reddit.viewAllSubreddits();
                    if(Reddit.getSubreddits().isEmpty()) {
                        System.out.println("No Subreddits");
                    }
                    else {
                        System.out.println("Select a Subreddit by entering its index");
                        Scanner p = new Scanner(System.in);
                        int o = p.nextInt();
                        Reddit.getSubreddits().get(o - 1).viewSubreddit(1);
                        boolean hasJoinedSub = false;
                        for(Subreddit temp : account.getJoinedSubreddits()) {
                            if(Objects.equals(temp, Reddit.getSubreddits().get(o - 1))) {
                                hasJoinedSub = true;
                            }
                        }
                        boolean isAdmin = false;
                        for(Account temp : Reddit.getSubreddits().get(o - 1).getAdmins()) {
                            if (temp == account) {
                                isAdmin = true;
                            }
                        }
                        if(isAdmin) {
                            System.out.println("Admin Managements?? 1)YES  2)NO");
                            Scanner r = new Scanner(System.in);
                            int f = r.nextInt();
                            if(f == 1) {
                                System.out.println("1)Remove a Post 2)Remove a Member  3)Add an Admin");
                                Scanner j = new Scanner(System.in);
                                int b = j.nextInt();
                                if(b == 1) {
                                    for (Post temp : Reddit.getSubreddits().get(o - 1).getPosts()) {
                                        temp.viewPost(1);
                                    }
                                    Scanner c = new Scanner(System.in);
                                    int x = c.nextInt();
                                    Post temp = Reddit.getSubreddits().get(o - 1).getPosts().get(x - 1);
                                    temp.getCreator().getPosts().remove(temp);
                                    Reddit.getSubreddits().get(o - 1).getPosts().remove(temp);
                                    Reddit.getPosts().remove(temp);
                                }
                                else if(b == 2) {
                                    for (Account temp : Reddit.getSubreddits().get(o - 1).getJoinedUsers()) {
                                        temp.viewProfile();
                                    }
                                    Scanner c = new Scanner(System.in);
                                    int x = c.nextInt();
                                    Account temp = Reddit.getSubreddits().get(o - 1).getJoinedUsers().get(x - 1);
                                    if (Reddit.getSubreddits().get(o - 1).getJoinedUsers().contains(temp)) {
                                        Reddit.getSubreddits().get(o - 1).getJoinedUsers().remove(temp);
                                        temp.getJoinedSubreddits().remove(Reddit.getSubreddits().get(o - 1));
                                    }
                                }
                                else if (b == 3) {
                                    for(Account temp : Reddit.getSubreddits().get(o - 1).getJoinedUsers()) {
                                        temp.viewProfile();
                                    }
                                    Scanner c = new Scanner(System.in);
                                    int x = c.nextInt();
                                    Reddit.getSubreddits().get(o - 1).addAdmins(Reddit.getSubreddits().get(o - 1).getJoinedUsers().get(x - 1));
                                }
                                else {
                                    break;
                                }
                            }
                        }
                        if (!hasJoinedSub) {
                            System.out.println("Would you like to join this Subreddit?  1)YES  2)NO");
                            Scanner k = new Scanner(System.in);
                            int l = k.nextInt();
                            if (l == 1) {
                                account.joinSubreddit(Reddit.getSubreddits().get(o - 1), account);
                            }
                        }
                        if(Reddit.getSubreddits().get(o - 1).getPosts().isEmpty()) {
                            System.out.println("No Posts");
                        }
                        else {
                            System.out.println("Select a post by entering its index ");
                            Scanner t = new Scanner(System.in);
                            int index = t.nextInt();
                            System.out.println("1)Post Comment  2)Up Vote Post  3)Down Vote Post  4)Up Vote Comment  5)Down Vote Comment  6)Retract Comment Vote  7)Retract Post Vote  8)View Comment Poster's Profile  9)View Post Creator's Profile  else)return");
                            Scanner d = new Scanner(System.in);
                            int v = d.nextInt();
                            if(v == 1) {
                                System.out.println("Body of the Comment: ");
                                Scanner e = new Scanner(System.in);
                                String body = e.nextLine();
                                Reddit.addComment(Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1), account, body);
                            }
                            else if (v == 2) {
                                account.postKarma(account, true, Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1));
                            }
                            else if (v == 3) {
                                account.postKarma(account, false, Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1));
                            }
                            else if (v == 4) {
                                if (!Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().isEmpty()) {
                                    int j = 1;
                                    for(Comment temp : Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments()) {
                                        temp.viewComment(j);
                                        j++;
                                    }
                                    System.out.println("Select a comment by entering its index");
                                    Scanner h = new Scanner(System.in);
                                    int inx = h.nextInt();
                                    account.commentKarma(account, true, Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().get(inx - 1));
                                }
                            }
                            else if (v == 5) {
                                if (!Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().isEmpty()) {
                                    int i = 1;
                                    for(Comment temp : Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments()) {
                                        temp.viewComment(i);
                                        i++;
                                    }
                                    System.out.println("Select a comment by entering its index");
                                    Scanner h = new Scanner(System.in);
                                    int inx = h.nextInt();
                                    account.commentKarma(account, false, Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().get(inx - 1));
                                }
                            }
                            else if (v == 6) {
                                if (!Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().isEmpty()) {
                                    int i = 1;
                                    for(Comment temp : Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments()) {
                                        temp.viewComment(i);
                                        i++;
                                    }
                                    System.out.println("Select a comment by entering its index");
                                    Scanner h = new Scanner(System.in);
                                    int inx = h.nextInt();
                                    account.retractVote(Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().get(inx - 1));
                                }
                            }
                            else if (v == 7) {
                                account.retractVote(Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1));
                            }
                            else if (v == 8) {
                                if (!Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().isEmpty()) {
                                    int i = 1;
                                    for(Comment temp : Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments()) {
                                        temp.viewComment(i);
                                        i++;
                                    }
                                    System.out.println("Select a comment by entering its index");
                                    Scanner h = new Scanner(System.in);
                                    int inx = h.nextInt();
                                    Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().get(inx - 1).getCreator().viewProfile();
                                }
                                break;
                            }
                            else if (v == 9) {
                                Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getCreator().viewProfile();
                                break;
                            }
                            else {
                                break;
                            }
                        }
                    }
                    break;
                case 4:
                    for(Account temp : Reddit.getAccounts()) {
                        temp.viewProfile();
                    }
                    break;
                case 5:
                    if (account.getJoinedSubreddits().isEmpty()) {
                        System.out.println("In order to post you need to join a subreddit");
                        break;
                    }
                    else {
                        account.viewJoinedSubreddits();
                        Scanner p = new Scanner(System.in);
                        int c = p.nextInt();
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
                    Scanner k = new Scanner(System.in);
                    int c = k.nextInt();
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
                case 10:
                    hasLoggedOut = true;
                    break;
                default:
                    break;
            }
        }
    }
}