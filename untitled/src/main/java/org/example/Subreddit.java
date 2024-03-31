package org.example;

import java.util.ArrayList;

public class Subreddit {
    private String title;
    private Account owner;
    private ArrayList<Account> joinedUsers;
    private ArrayList<Post> posts;
    public Subreddit(String title, Account owner) {
        this.title = title;
        this.owner = owner;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setOwner(Account owner) {
        this.owner = owner;
    }
    public Account getOwner() {
        return owner;
    }
    public void setJoinedUsers(Account joinedUser) {
        joinedUsers.add(joinedUser);
    }
    public ArrayList<Account> getJoinedUsers() {
        return joinedUsers;
    }
    public void setPosts(Post post) {
        posts.add(post);
    }
    public ArrayList<Post> getPosts() {
        return posts;
    }
    public void createPost(String title, String body, Account creator, Subreddit subreddit, int karma) {
        Post post = new Post(title, body, creator, subreddit, 0);
        posts.add(post);
    }
    public void viewSubreddit(){
        System.out.println("Title: " + title + "\nOwner: " + owner + "\nJoined Users:\n");
        for (Account temp : joinedUsers) {
            System.out.println(temp.getUsername());
        }
        System.out.println("Posts:\n");
        for(Post temp : posts) {
            temp.viewPost();
        }
    }
}
