package org.example;

import java.util.ArrayList;

public class Subreddit {
    private String title;
    private Account owner;
    private ArrayList<Account> admins = new ArrayList<>();
    private ArrayList<Account> joinedUsers = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
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
    public void viewSubreddit(int i ){
        System.out.println(i + ")Title:\n" + title + "\nOwner:\n" + owner.getUsername() + "\nJoined Users:");
        for (Account temp : joinedUsers) {
            System.out.println(temp.getUsername());
        }
        System.out.println("Posts:\n");
        for(Post temp : posts) {
            temp.viewPost();
        }
    }
}
