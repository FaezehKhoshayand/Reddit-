package org.example;
import java.util.ArrayList;
public class Post {
    private String title;
    private String body;
    //tags or flairs
    private Account creator;
    private Subreddit subreddit;
    private int karma;
    private ArrayList<Comment> comments;
    public Post(String title, String body, Account creator, Subreddit subreddit) {
        this.title = title;
        this.body = body;
        this.creator = creator;
        this.subreddit = subreddit;
        this.karma = 0;
        comments = new ArrayList<>();
    }
    public int getKarma() {
        return karma;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public Account getCreator() {
        return creator;
    }
    public void setCreator(Account creator) {
        this.creator = creator;
    }
    public Subreddit getSubreddit() {
        return subreddit;
    }
    public void setSubreddit(Subreddit subreddit) {
        this.subreddit = subreddit;
    }
    public int getVotes() {
        return karma;
    }
    public void setVotes(int karma) {
        this.karma += karma;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }
    public void setComments(Comment comment) {
        comments.add(comment);
    }
    public void viewPost(int i) {
        System.out.println(i + ")Subreddit: " + subreddit.getTitle());
        System.out.println("Title: " + title);
        System.out.println("Creator: " + creator.getUsername());
        System.out.println("Body: " + body);
        System.out.println("Votes: " + karma);
        System.out.println("Comments");
        if (comments != null) {
            int j = 1;
            for(Comment temp : comments) {
                temp.viewComment(j);
                j++;
            }
        }
    }
    public void addComment(Comment comment) {
          comments.add(comment);
    }
    public static Post getPostBy(String title, String username) {
        for (Post temp : Reddit.getPosts()) {
            if (temp.getTitle().equals(title) && temp.getCreator().getUsername().equals(username)) {
                return temp;
            }
        }
        return null;
    }
}
