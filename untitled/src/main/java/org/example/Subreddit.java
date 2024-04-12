package org.example;

import java.util.ArrayList;

public class Subreddit {
    private String title;
    private Account creator;
    private ArrayList<Account> admins = new ArrayList<>();
    private ArrayList<Account> joinedUsers = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    public Subreddit(String title, Account admin) {
        this.title = title;
        admins.add(admin);
        creator = admin;
    }
    public Account getCreator() {
        return creator;
    }
    public ArrayList<Account> getAdmins() {
        return admins;
    }
    public void addAdmins(Account admin) {
        admins.add(admin);
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
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
        System.out.println(i + ")Title:\n" + title + "\nAdmins:");
        for(Account temp : admins) {
            System.out.println(temp.getUsername());
        }
        System.out.println("Joined Users:");
        for (Account temp : joinedUsers) {
            System.out.println(temp.getUsername());
        }
        System.out.println("Posts:\n");
        int u = 1;
        for(Post temp : posts) {
            temp.viewPost(u);
            u++;
        }
    }
    public static boolean titleIsUsed(String title) {
        for(Subreddit temp : Reddit.getSubreddits()) {
            if(temp.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}
