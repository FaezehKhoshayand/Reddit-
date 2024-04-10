package org.example;

import java.util.ArrayList;

public class Subreddit {
    private String title;
    private ArrayList<Account> admins = new ArrayList<>();
    private ArrayList<Account> joinedUsers = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    public Subreddit(String title, Account admin) {
        this.title = title;
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
}
