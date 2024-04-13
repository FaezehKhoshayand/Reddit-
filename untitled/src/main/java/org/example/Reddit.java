package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Reddit {

    //Fields
    private static ArrayList<Subreddit> subreddits;
    private static ArrayList<Account> accounts;
    private static ArrayList<Post> posts;
    public static ArrayList<Chat> chats;
    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    //Constructor
    public Reddit() {
        subreddits = new ArrayList<>();
        accounts = new ArrayList<>();
        posts = new ArrayList<>();
        chats = new ArrayList<>();
    }

    //Setters and Getters
    public static ArrayList<Chat> getChats() {
        return chats;
    }
    public static ArrayList<Subreddit> getSubreddits() {
        return subreddits;
    }
    public static ArrayList<Post> getPosts() {
        return posts;
    }
    public static void addComment(Post post, Account account, String body) {
        Comment comment = new Comment(account, body, post);
        post.addComment(comment);
        account.addComment(comment);
    }

    //Adding stuff
    public static void addSubreddit(Subreddit subreddit) {
        subreddits.add(subreddit);
    }
    public static void addAccount(Account account) {
        accounts.add(account);
    }
    public static void addPost(Post post) {
        posts.add(post);
    }

    //Searching among accounts and subreddits
    public static Account searchAccount(String username){
        for(Account temp : accounts) {
            if(temp.getUsername().equals(username)) {
                return temp;
            }
        }
        return null;
    }
    public static Subreddit searchSubreddit(String title) {
        for(Subreddit temp : subreddits) {
            if(temp.getTitle().equals(title)) {
                return temp;
            }
        }
        return null;
    }

    //Viewing all subreddits
    public static void viewAllSubreddits() {
        int i = 1;
        for(Subreddit temp : subreddits) {
            temp.viewSubreddit(i);
            i++;
        }
    }

    //viewing all posts
    public static void viewAllPosts() {
        int i = 1;
        for(Post temp : posts) {
            temp.viewPost(i);
            i++;
        }
    }

    //Creating subreddit and post
    public static void createSubreddit(String title, Account account) {
        Subreddit subreddit = new Subreddit(title, account);
        addSubreddit(subreddit);
        subreddit.setJoinedUsers(account);
        account.addJoinedSubreddits(subreddit);
    }
    public static void createPost(String title, String body,Account account, Subreddit subreddit) {
        Post post = new Post(title, body, account, subreddit);
        addPost(post);
        account.addPost(post);
        subreddit.setPosts(post);
    }

}
