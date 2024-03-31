package org.example;
import java.util.ArrayList;
public class Reddit {
    private ArrayList<Subreddit> subreddits;
    private ArrayList<Account> accounts;
    private ArrayList<Post> posts;
    ////
    ////
    ////
    ////
    public void createComment() {

    }
    public void addSubreddit(Subreddit subreddit) {
        subreddits.add(subreddit);
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }
    public void addPost(Post post) {
        posts.add(post);
    }
    public Account searchAccount(String username){
        for(Account temp : accounts) {
            if(temp.getUsername().equals(username)) {
                return temp;
            }
        }
        return null;
    }
    public Subreddit searchSubreddit(String title) {
        for(Subreddit temp : subreddits) {
            if(temp.getTitle().equals(title)) {
                return temp;
            }
        }
        return null;
    }
    public void viewAllSubreddits() {
        for(Subreddit temp : subreddits) {
            temp.viewSubreddit();
        }
    }
}
