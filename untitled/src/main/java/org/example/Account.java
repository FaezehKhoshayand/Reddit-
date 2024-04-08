package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;
public class Account {//TO DO CHECK KARMA VALUES IN REDDIT
    private String username;
    private String password;
    private String emailAddress;
    private int totalKarma;
    private int postKarma;
    private int commentKarma;
    private UUID id;
    private ArrayList<Subreddit> joinedSubreddits;////
    private ArrayList<Post> posts;////
    private ArrayList<Comment> comments;
    public Account(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.id = UUID.randomUUID();
        postKarma = 0;
        commentKarma = 0;
        totalKarma = 0;
        joinedSubreddits = new ArrayList<>();
        posts = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public int getTotalKarma() {
        return totalKarma;
    }
    public void setTotalKarma() {
        this.totalKarma = postKarma + commentKarma;
    }
    public void setPostKarma(int postKarma) {
        this.postKarma = postKarma;
    }
    public int getPostKarma() {
        return postKarma;
    }
    public void setCommentKarma(int commentKarma) {
        this.commentKarma = commentKarma;
    }
    public int getCommentKarma() {
        return commentKarma;
    }
    public ArrayList<Subreddit> getJoinedSubreddits() {
        return joinedSubreddits;
    }
    public void addJoinedSubreddits(Subreddit subreddit) {
        joinedSubreddits.add(subreddit);
    }
    public void addPost(Post post) {
        posts.add(post);
    }
    public void viewProfile() {
        System.out.println("Username: " + username + "EmailAddress: " + emailAddress + "\nPost Karma" + postKarma + "Comment Karma" + commentKarma + "Total Karma" + totalKarma + "\nNumber of Joined Subreddits: " + joinedSubreddits.size() + "\nJoined Subreddits:\n");
        for(Subreddit temp : joinedSubreddits) {
            System.out.println(temp.getTitle());
        }
        System.out.println("Posts:\n");
        int i = 1;
        for(Post temp : posts) {
            temp.viewPost(i);
            i++;
        }
        System.out.println("Comments:\n");
        for(Comment temp : comments) {
            temp.viewComment();
        }
    }
    public static boolean validateEmail(String emailAddress) {
        String regex = "^[A-Z0-9-_+&*]*@[A-Z0-9-_+&*]*\\.[A-Z0-9-_+&*]*$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.find();
    }
    //EDIT INFORMATION
    public Account login() {
        for (Account a : Reddit.getAccounts()) {
            if (Objects.equals(a.getUsername(), getUsername()) && Objects.equals(a.getPassword(), getPassword()) &&  Objects.equals(a.getEmailAddress(), getEmailAddress())) {
                System.out.println("You are logged in");
                return a;
            }
        }
        return null;
    }
    public void signup(Account account) {
        Reddit.addAccount(account);
        System.out.println("Welcome " + account.getUsername());
    }
    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
    public void changeEmailAddress(String newEmailAddress) {
        this.emailAddress = newEmailAddress;
    }
    public void viewJoinedSubreddits()  {
        int i = 1;
        for(Subreddit temp : joinedSubreddits) {
            temp.viewSubreddit(i);
            i++;
        }
    }
    public void joinSubreddit(Subreddit subreddit, Account account) {
        subreddit.setJoinedUsers(account);
        addJoinedSubreddits(subreddit);
    }
}
