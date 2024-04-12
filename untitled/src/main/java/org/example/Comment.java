package org.example;
public class Comment {
    private int karma;
    private Account creator;
    private String body;
    private Post post;
    public Comment(Account creator, String body, Post post) {
        this.creator = creator;
        this.body = body;
        this.post = post;
        this.karma = 0;
    }
    public int getKarma() {
        return karma;
    }
    public void setVote(int vote) {
        this.karma += vote;
    }
    public int getVote() {
        return karma;
    }
    public void setCreator(Account creator) {
        this.creator = creator;
    }
    public Account getCreator() {
        return creator;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getBody() {
        return body;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public Post getPost() {
        return post;
    }
    public void viewComment(int i) {
        System.out.println(i + ")Creator: " + creator.getUsername() + "\nBody: " + body + "\nvote: " + karma );
    }
}
