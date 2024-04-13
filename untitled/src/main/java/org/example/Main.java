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
    public static void runMenu() {//Login/SignUp/Exit
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
    public static void login() {//Login
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
    public static void signUp() {//Sign up
        Scanner scanner = new Scanner(System.in);
        System.out.println("username: ");
        String username = scanner.nextLine();
        while(Account.usernameIsUsed(username)){
            scanner = new Scanner(System.in);
            System.out.println("This username is already taken\nnew username: ");
            username = scanner.nextLine();
        }
        System.out.println("password: ");
        scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        System.out.println("EmailAddress: ");
        scanner = new Scanner(System.in);
        String emailAddress = scanner.nextLine();
        while(Account.emailIsUsed(emailAddress)) {
            System.out.println("An account has already signed up with this emailAddress\nNew EmailAddress: ");
            scanner = new Scanner(System.in);
            emailAddress = scanner.nextLine();
        }
        while(!Account.validateEmail(emailAddress)) {
            System.out.println("Not a valid emailAddress\nNew EmailAddress: ");
            scanner = new Scanner(System.in);
            emailAddress = scanner.nextLine();
        }
        Account account = new Account(username, password, emailAddress);
        account.signup(account);
        makeMenu(account);
    }
    public static void makeMenu(Account account) {//Main Options
        boolean hasLoggedOut = false;
        while(!hasLoggedOut) {
            System.out.println("Enter your command\n1)Create Subreddit  2)View All Posts  3)View All Subreddits  4)View All Profiles  5)Create Post  6)Search  7)View TimeLine 8)Change Username & Password  9)Join Subreddit  10)Log Out  11)View Saved Posts  12)Chat  13)View followed accounts  14)View posts created by my followings");
            Scanner scanner = new Scanner(System.in);
            int cmd = scanner.nextInt();
            switch(cmd) {
                case 1://Create Subreddit
                    Scanner as = new Scanner(System.in);
                    System.out.println("Enter a title for the subreddit");
                    String title = as.nextLine();
                    while(Subreddit.titleIsUsed(title)) {
                        System.out.println("A subreddit with this title exists.\nNew Title:\nEnter 1 to return menu");
                        as = new Scanner(System.in);
                        title = as.nextLine();
                        if(title.equals("1")) {
                            makeMenu(account);
                        }
                    }
                    Reddit.createSubreddit(title, account);
                    break;
                case 2://View All Posts
                    Reddit.viewAllPosts();
                    if(Reddit.getPosts().isEmpty()) {
                        System.out.println("No Posts");
                    }
                    else {
                        while (true) {
                            System.out.println("Select a post by entering its index");
                            Scanner t = new Scanner(System.in);
                            int index = t.nextInt();
                            System.out.println("1)Post Comment  2)Up Vote Post  3)Down Vote Post  4)Up Vote Comment  5)Down Vote Comment  6)Retract Comment Vote  7)Retract Post Vote  8)View Comment Poster's Profile  9)View Post Creator's Profile  10)Join the Subreddit related to the Post  11)Save Post  12)return");
                            Scanner d = new Scanner(System.in);
                            int v = d.nextInt();
                            if (v == 1) {//Post Comment
                                System.out.println("Body of the Comment: ");
                                Scanner e = new Scanner(System.in);
                                String body = e.nextLine();
                                Reddit.addComment(Reddit.getPosts().get(index - 1), account, body);
                            } else if (v == 2) {//Up Vote Post
                                account.postKarma(account, true, Reddit.getPosts().get(index - 1));
                            } else if (v == 3) {//Down Vote Post
                                account.postKarma(account, false, Reddit.getPosts().get(index - 1));
                            } else if (v == 4) {//Up Vote Comment
                                if (!Reddit.getPosts().get(index - 1).getComments().isEmpty()) {
                                    int j = 1;
                                    for (Comment temp : Reddit.getPosts().get(index - 1).getComments()) {
                                        temp.viewComment(j);
                                        j++;
                                    }
                                    System.out.println("Select a comment by entering its index");
                                    Scanner h = new Scanner(System.in);
                                    int inx = h.nextInt();
                                    account.commentKarma(account, true, Reddit.getPosts().get(index - 1).getComments().get(inx - 1));
                                }
                            } else if (v == 5) {//Down Vote Comment
                                if (!Reddit.getPosts().get(index - 1).getComments().isEmpty()) {
                                    int i = 1;
                                    for (Comment temp : Reddit.getPosts().get(index - 1).getComments()) {
                                        temp.viewComment(i);
                                        i++;
                                    }
                                    System.out.println("Select a comment by entering its index");
                                    Scanner h = new Scanner(System.in);
                                    int inx = h.nextInt();
                                    account.commentKarma(account, false, Reddit.getPosts().get(index - 1).getComments().get(inx - 1));
                                }
                            } else if (v == 6) {//Retract Comment Vote
                                if (!Reddit.getPosts().get(index - 1).getComments().isEmpty()) {
                                    int i = 1;
                                    for (Comment temp : Reddit.getPosts().get(index - 1).getComments()) {
                                        temp.viewComment(i);
                                        i++;
                                    }
                                    System.out.println("Select a comment by entering its index");
                                    Scanner h = new Scanner(System.in);
                                    int inx = h.nextInt();
                                    account.retractVote(Reddit.getPosts().get(index - 1).getComments().get(inx - 1));
                                }
                            } else if (v == 7) {//Retract Post Vote
                                account.retractVote(Reddit.getPosts().get(index - 1));
                            } else if (v == 8) {//View Comment Poster's Profile
                                if (!Reddit.getPosts().get(index - 1).getComments().isEmpty()) {
                                    int i = 1;
                                    for (Comment temp : Reddit.getPosts().get(index - 1).getComments()) {
                                        temp.viewComment(i);
                                        i++;
                                    }
                                    System.out.println("Select a comment by entering its index");
                                    Scanner h = new Scanner(System.in);
                                    int inx = h.nextInt();
                                    Reddit.getAccounts().get(inx - 1).viewProfile();

                                }
                                //break;
                            } else if (v == 9) {//View Post Creator's Profile
                                Reddit.getPosts().get(index - 1).getCreator().viewProfile();
                                //break;
                            } else if (v == 10) {//Join the Subreddit related to the Post
                                boolean flag = true;
                                for (Subreddit temp : account.getJoinedSubreddits()) {
                                    if (Objects.equals(temp, Reddit.getPosts().get(index - 1).getSubreddit())) {
                                        System.out.println("You've already joined this Subreddit");
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    account.joinSubreddit(Reddit.getPosts().get(index - 1).getSubreddit(), account);
                                }
                            }
                            else if (v == 11) {//Save Post
                                if(!account.getSavedPosts().contains(Reddit.getPosts().get(index - 1))) {
                                    account.setSavedPosts(Reddit.getPosts().get(index - 1));
                                }
                            }
                            else {
                                break;
                            }
                        }
                    }
                    break;
                        case 3://View All Subreddits
                            Reddit.viewAllSubreddits();
                            if (Reddit.getSubreddits().isEmpty()) {
                                System.out.println("No Subreddits");
                            } else {
                                System.out.println("Select a Subreddit by entering its index");
                                Scanner p = new Scanner(System.in);
                                int o = p.nextInt();
                                Reddit.getSubreddits().get(o - 1).viewSubreddit(1);
                                boolean hasJoinedSub = false;
                                for (Subreddit temp : account.getJoinedSubreddits()) {
                                    if (Objects.equals(temp, Reddit.getSubreddits().get(o - 1))) {
                                        hasJoinedSub = true;
                                    }
                                }
                                boolean isAdmin = false;
                                for (Account temp : Reddit.getSubreddits().get(o - 1).getAdmins()) {
                                    if (temp == account) {
                                        isAdmin = true;
                                    }
                                }
                                if (isAdmin) {
                                    System.out.println("Admin Managements?? 1)YES  2)NO");
                                    Scanner r = new Scanner(System.in);
                                    int f = r.nextInt();
                                    if (f == 1) {
                                        System.out.println("1)Remove a Post 2)Remove a Member  3)Add an Admin");
                                        Scanner j = new Scanner(System.in);
                                        int b = j.nextInt();
                                        if (b == 1) {//Remove a Post
                                            for (Post temp : Reddit.getSubreddits().get(o - 1).getPosts()) {
                                                temp.viewPost(1);
                                            }
                                            if (Reddit.getSubreddits().get(o - 1).getPosts().isEmpty()) {
                                                System.out.println("No Posts");
                                                break;
                                            }
                                            Scanner c = new Scanner(System.in);
                                            int x = c.nextInt();
                                            Post temp = Reddit.getSubreddits().get(o - 1).getPosts().get(x - 1);
                                            temp.getCreator().setPostKarma(-temp.getKarma());
                                            temp.getCreator().setTotalKarma();
                                            for (Comment comment : Reddit.getSubreddits().get(o - 1).getPosts().get(x - 1).getComments()) {
                                                comment.getCreator().setCommentKarma(-comment.getKarma());
                                                comment.getCreator().setTotalKarma();
                                            }
                                            for (Comment comment : temp.getComments()) {
                                                Account creator = comment.getCreator();
                                                creator.getComment().remove(comment);
                                            }
                                            temp.getCreator().getPosts().remove(temp);
                                            Reddit.getSubreddits().get(o - 1).getPosts().remove(temp);
                                            Reddit.getPosts().remove(temp);
                                        } else if (b == 2) {//Remove a Member
                                            System.out.println("-All the Members-");
                                            int i = 1;
                                            for (Account temp : Reddit.getSubreddits().get(o - 1).getJoinedUsers()) {
                                                System.out.print(i + ")");
                                                temp.viewProfile();
                                                i++;
                                            }
                                            Scanner c = new Scanner(System.in);
                                            int x = c.nextInt();
                                            Account temp = Reddit.getSubreddits().get(o - 1).getJoinedUsers().get(x - 1);
                                            if (Reddit.getSubreddits().get(o - 1).getAdmins().contains(temp) && Objects.equals(account, Reddit.getSubreddits().get(o - 1).getCreator()) && !Objects.equals(temp, account)) {
                                                Reddit.getSubreddits().get(o - 1).getJoinedUsers().remove(temp);
                                                temp.getJoinedSubreddits().remove(Reddit.getSubreddits().get(o - 1));
                                                Reddit.getSubreddits().get(o - 1).getAdmins().remove(temp);
                                            } else if (Reddit.getSubreddits().get(o - 1).getAdmins().contains(temp) && Objects.equals(temp, account)) {
                                                System.out.println("You cannot remove an admin unless you are the creator of the subreddit");
                                            } else {
                                                Reddit.getSubreddits().get(o - 1).getJoinedUsers().remove(temp);
                                                temp.getJoinedSubreddits().remove(Reddit.getSubreddits().get(o - 1));
                                            }
                                        } else if (b == 3) {//Add an Admin
                                            for (Account temp : Reddit.getSubreddits().get(o - 1).getJoinedUsers()) {
                                                temp.viewProfile();
                                            }
                                            Scanner c = new Scanner(System.in);
                                            int x = c.nextInt();
                                            Account temp = Reddit.getSubreddits().get(o - 1).getJoinedUsers().get(x - 1);
                                            if (Reddit.getSubreddits().get(o - 1).getAdmins().contains(temp)) {
                                                System.out.println("This user is an admin");
                                            } else {
                                                Reddit.getSubreddits().get(o - 1).addAdmins(Reddit.getSubreddits().get(o - 1).getJoinedUsers().get(x - 1));
                                            }
                                        } else {
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
                                if (hasJoinedSub && !Objects.equals(account,Reddit.getSubreddits().get(o - 1).getCreator())) {
                                    System.out.println("Would you like to leave this Subreddit?  1)YES  2)NO");
                                    Scanner k = new Scanner(System.in);
                                    int l = k.nextInt();
                                    if (l == 1) {
                                        account.getJoinedSubreddits().remove(Reddit.getSubreddits().get(o - 1));
                                    }
                                }
                                if (Reddit.getSubreddits().get(o - 1).getPosts().isEmpty()) {
                                    System.out.println("No Posts");
                                } else {
                                    System.out.println("Select a post by entering its index ");
                                    Scanner t = new Scanner(System.in);
                                    int index = t.nextInt();
                                    System.out.println("1)Post Comment  2)Up Vote Post  3)Down Vote Post  4)Up Vote Comment  5)Down Vote Comment  6)Retract Comment Vote  7)Retract Post Vote  8)View Comment Poster's Profile  9)View Post Creator's Profile  else)return");
                                    Scanner d = new Scanner(System.in);
                                    int v = d.nextInt();
                                    if (v == 1) {//Post Comment
                                        System.out.println("Body of the Comment: ");
                                        Scanner e = new Scanner(System.in);
                                        String body = e.nextLine();
                                        Reddit.addComment(Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1), account, body);
                                    } else if (v == 2) {//Up Vote Post
                                        account.postKarma(account, true, Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1));
                                    } else if (v == 3) {//Down Vote Post
                                        account.postKarma(account, false, Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1));
                                    } else if (v == 4) {//Up Vote Comment
                                        if (!Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().isEmpty()) {
                                            int j = 1;
                                            for (Comment temp : Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments()) {
                                                temp.viewComment(j);
                                                j++;
                                            }
                                            System.out.println("Select a comment by entering its index");
                                            Scanner h = new Scanner(System.in);
                                            int inx = h.nextInt();
                                            account.commentKarma(account, true, Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().get(inx - 1));
                                        }
                                    } else if (v == 5) {//Down Vote Comment
                                        if (!Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().isEmpty()) {
                                            int i = 1;
                                            for (Comment temp : Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments()) {
                                                temp.viewComment(i);
                                                i++;
                                            }
                                            System.out.println("Select a comment by entering its index");
                                            Scanner h = new Scanner(System.in);
                                            int inx = h.nextInt();
                                            account.commentKarma(account, false, Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().get(inx - 1));
                                        }
                                    } else if (v == 6) {//Retract Comment Vote
                                        if (!Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().isEmpty()) {
                                            int i = 1;
                                            for (Comment temp : Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments()) {
                                                temp.viewComment(i);
                                                i++;
                                            }
                                            System.out.println("Select a comment by entering its index");
                                            Scanner h = new Scanner(System.in);
                                            int inx = h.nextInt();
                                            account.retractVote(Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().get(inx - 1));
                                        }
                                    } else if (v == 7) {//Retract Post Vote
                                        account.retractVote(Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1));
                                    } else if (v == 8) {//View Comment Poster's Profile
                                        if (!Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().isEmpty()) {
                                            int i = 1;
                                            for (Comment temp : Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments()) {
                                                temp.viewComment(i);
                                                i++;
                                            }
                                            System.out.println("Select a comment by entering its index");
                                            Scanner h = new Scanner(System.in);
                                            int inx = h.nextInt();
                                            Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getComments().get(inx - 1).getCreator().viewProfile();
                                        }
                                        break;
                                    } else if (v == 9) {//View Post Creator's Profile
                                        Reddit.getSubreddits().get(o - 1).getPosts().get(index - 1).getCreator().viewProfile();
                                        break;
                                    }
                                    else {
                                        break;
                                    }
                                }
                    }
                    break;
                case 4://View all profiles
                    for(Account temp : Reddit.getAccounts()) {
                        temp.viewProfile();
                    }
                    System.out.println("Would you like to follow anyone?  1)YES  2)NO");
                    Scanner k = new Scanner(System.in);
                    int g = k.nextInt();
                    if (g == 1) {
                        System.out.println("Enter the index of the user you want to follow");
                        Scanner i = new Scanner(System.in);
                        int u = i.nextInt();
                        if(account.getFollowedAccounts().contains(Reddit.getAccounts().get(u - 1))) {
                            System.out.println("You've followed this account before");
                        }
                        else if (!Objects.equals(account, Reddit.getAccounts().get(u - 1))){
                            account.addFollowedAccounts(Reddit.getAccounts().get(u - 1));
                        }
                    }
                    break;
                case 5://Create post
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
                case 6://Search
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
                case 7://View timeline
                    account.viewJoinedPosts();
                    int r = 0;
                    for(Subreddit temp : account.getJoinedSubreddits()) {
                        if (!temp.getPosts().isEmpty()) {
                            r++;
                        }
                    }
                    if(r != 0) {
                        System.out.println("Enter the title of the post");
                        Scanner t = new Scanner(System.in);
                        String Title = t.nextLine();
                        System.out.println("Enter the creator of the post");
                        Scanner y = new Scanner(System.in);
                        String creator = y.nextLine();
                        System.out.println("1)Post Comment  2)Up Vote Post  3)Down Vote Post  4)Up Vote Comment  5)Down Vote Comment  6)Retract Comment Vote  7)Retract Post Vote  8)View Comment Poster's Profile  9)View Post Creator's Profile  10)Join the Subreddit related to the Post  11)return");
                        Scanner d = new Scanner(System.in);
                        int v = d.nextInt();
                        if (v == 1) {
                            System.out.println("Body of the Comment: ");
                            Scanner e = new Scanner(System.in);
                            String body = e.nextLine();
                            Post p = Post.getPostBy(Title, creator);
                            Reddit.addComment(p, account, body);
                        } else if (v == 2) {
                            account.postKarma(account, true, Post.getPostBy(Title, creator));
                        } else if (v == 3) {
                            account.postKarma(account, false, Post.getPostBy(Title, creator));
                        } else if (v == 4) {
                            if (!Post.getPostBy(Title, creator).getComments().isEmpty()) {
                                int j = 1;
                                for (Comment temp : Post.getPostBy(Title, creator).getComments()) {
                                    temp.viewComment(j);
                                    j++;
                                }
                                System.out.println("Select a comment by entering its index");
                                Scanner h = new Scanner(System.in);
                                int inx = h.nextInt();
                                account.commentKarma(account, true, Post.getPostBy(Title, creator).getComments().get(inx - 1));
                            }
                        } else if (v == 5) {
                            if (!Post.getPostBy(Title, creator).getComments().isEmpty()) {
                                int i = 1;
                                for (Comment temp : Post.getPostBy(Title, creator).getComments()) {
                                    temp.viewComment(i);
                                    i++;
                                }
                                System.out.println("Select a comment by entering its index");
                                Scanner h = new Scanner(System.in);
                                int inx = h.nextInt();
                                account.commentKarma(account, false, Post.getPostBy(Title, creator).getComments().get(inx - 1));
                            }
                        } else if (v == 6) {
                            if (!Post.getPostBy(Title, creator).getComments().isEmpty()) {
                                int i = 1;
                                for (Comment temp : Post.getPostBy(Title, creator).getComments()) {
                                    temp.viewComment(i);
                                    i++;
                                }
                                System.out.println("Select a comment by entering its index");
                                Scanner h = new Scanner(System.in);
                                int inx = h.nextInt();
                                account.retractVote(Post.getPostBy(Title, creator).getComments().get(inx - 1));
                            }
                        } else if (v == 7) {
                            account.retractVote(Post.getPostBy(Title, creator));
                        } else if (v == 8) {
                            if (!Post.getPostBy(Title, creator).getComments().isEmpty()) {
                                int i = 1;
                                for (Comment temp : Post.getPostBy(Title, creator).getComments()) {
                                    temp.viewComment(i);
                                    i++;
                                }
                                System.out.println("Select a comment by entering its index");
                                Scanner h = new Scanner(System.in);
                                int inx = h.nextInt();
                                Reddit.getAccounts().get(inx - 1).viewProfile();

                            }
                            //break;
                        } else if (v == 9) {
                            Post.getPostBy(Title, creator).getCreator().viewProfile();
                            //break;
                        } else if (v == 10) {
                            boolean flag = true;
                            for (Subreddit temp : account.getJoinedSubreddits()) {
                                if (Objects.equals(temp, Post.getPostBy(Title, creator).getSubreddit())) {
                                    System.out.println("You've already joined this Subreddit");
                                    flag = false;
                                }
                            }
                            if (flag) {
                                account.joinSubreddit(Post.getPostBy(Title, creator).getSubreddit(), account);
                            }
                        } else {
                            break;
                        }
                    }
                    break;
                case 8://user settings
                    Scanner sc = new Scanner(System.in);
                    System.out.println("new username: ");
                    String username = sc.nextLine();
                    System.out.println("new password: ");
                    scanner = new Scanner(System.in);
                    String password = sc.nextLine();
                    //System.out.println("new EmailAddress: ");
                    //scanner = new Scanner(System.in);
                    //String emailAddress = sc.nextLine();
//                    while(!Account.validateEmail(emailAddress)) {
//                        System.out.println("Not a valid emailAddress\nNew EmailAddress: ");
//                        scanner = new Scanner(System.in);
//                        emailAddress = sc.nextLine();
//                    }
                    account.changePassword(password);
                    account.changeUsername(username);
                    break;
                case 9://Join subreddit
                    Reddit.viewAllSubreddits();
                    Scanner i = new Scanner(System.in);
                    int c = i.nextInt();
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
                case 11://Save post
                    account.viewSavedPosts();
                    if (account.getSavedPosts().isEmpty()) {
                        System.out.println("You haven't saved a post");
                    }
                    else {
                        System.out.println("Would you like to remove a post from saved posts? 1)YES  2)NO");
                        Scanner e = new Scanner(System.in);
                        int m = e.nextInt();
                        if(m == 1) {
                            System.out.println("Enter the index of the post you are willing to remove");
                            Scanner z = new Scanner(System.in);
                            int qw = z.nextInt();
                            account.getSavedPosts().remove(account.getSavedPosts().get(qw - 1));
                        }
                    }
                    break;
                case 12://Chat
                    int q = 1;
                    for (Account temp : Reddit.getAccounts()) {
                        System.out.println(q + ")" + temp.getUsername());
                        q++;
                    }
                    System.out.println("Enter the index of receiver:");
                    Scanner b = new Scanner(System.in);
                    int receiver = b.nextInt();
                    if (Objects.equals(account,Reddit.getAccounts().get(receiver - 1))) {
                        System.out.println("You can't chat with yourself");
                        break;
                    }
                    Chat chat = Chat.getChat(account, Reddit.getAccounts().get(receiver - 1));
                    if (chat == null) {
                        chat = new Chat(account, Reddit.getAccounts().get(receiver - 1));
                        Reddit.getChats().add(chat);
                    }
                    chat.displayChat();
                    System.out.println("Enter the text:");
                    Scanner n = new Scanner(System.in);
                    String text = n.nextLine();
                    Message message = new Message(account, Reddit.getAccounts().get(receiver - 1), text);
                    chat.addMessage(message);
                case 13:
                    for(Account temp : account.getFollowedAccounts()) {
                        temp.viewProfile();
                    }
                    break;
                case 14:
                    for (Account temp : account.getFollowedAccounts()) {
                        int h = 1;
                        for (Post tempp : temp.getPosts()) {
                            tempp.viewPost(h);
                            h++;
                        }
                    }
                default:
                    break;
            }
        }
    }
}