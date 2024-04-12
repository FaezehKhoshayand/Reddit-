package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;
public class Account {
    private String username;
    private String password;
    private String emailAddress;
    private int totalKarma;
    private int postKarma;
    private int commentKarma;
    private UUID id;
    private ArrayList<Subreddit> joinedSubreddits;
    private ArrayList<Post> posts;
    private ArrayList<Comment> comments;
    private ArrayList<Comment> upVotedComments;
    private ArrayList<Comment> downVotedComments;
    private ArrayList<Post> upVotedPosts;
    private ArrayList<Post> downVotedPosts;
    private ArrayList<Post> savedPosts;

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
        upVotedComments = new ArrayList<>();
        downVotedComments = new ArrayList<>();
        upVotedPosts = new ArrayList<>();
        downVotedPosts = new ArrayList<>();
        savedPosts = new ArrayList<>();

    }
    public ArrayList<Post> getPosts() {
        return posts;
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
        this.postKarma += postKarma;
    }
    public int getPostKarma() {
        return postKarma;
    }
    public void setCommentKarma(int commentKarma) {
        this.commentKarma += commentKarma;
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
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public ArrayList<Post> getSavedPosts() {
        return savedPosts;
    }
    public void setSavedPosts(Post post) {
        savedPosts.add(post);
    }
    public ArrayList<Comment> getComment() {
        return comments;
    }
    public void viewProfile() {
        System.out.println("Username: " + username + "\nEmailAddress: " + emailAddress + "\nPost Karma: " + postKarma + " Comment Karma: " + commentKarma + " Total Karma: " + totalKarma + "\nNumber of Joined Subreddits: " + joinedSubreddits.size() + "\nJoined Subreddits:");
        for(Subreddit temp : joinedSubreddits) {
            System.out.println(temp.getTitle());
        }
        System.out.println("Posts Created by the User:");
        int i = 1;
        for(Post temp : posts) {
            temp.viewPost(i);
            i++;
        }
        System.out.println("Comments Posted by the User:");
        int j = 1;
        for(Comment temp : comments) {
            temp.viewComment(j);
            j++;
        }
        System.out.println("Posts upvoted by the user:");
        int k = 1;
        for (Post temp : upVotedPosts) {
            temp.viewPost(k);
            k++;
        }
        System.out.println("Comments upvoted by the user");
        int l = 1;
        for (Comment temp : upVotedComments) {
            temp.viewComment(l);
            l++;
        }
    }
    public static boolean validateEmail(String emailAddress) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.find();
    }
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
    public void viewJoinedPosts() {
        for(Subreddit temp : joinedSubreddits) {
            for(Post post : temp.getPosts()) {
                post.viewPost(1);
            }
        }
    }
    public void postKarma(Account account, boolean voteType, Post post) {
        for(Post temp : upVotedPosts) {
            if(temp == post) {
              System.out.println("You have upVoted before;");
              return;
            }
        }
        for(Post temp : downVotedPosts) {
            if(temp == post) {
                System.out.println("You have downVoted before;");
                return;
            }
        }
        if (voteType) {
            upVotedPosts.add(post);
            post.setVotes(1);
            post.getCreator().postKarma++;
            post.getCreator().totalKarma++;
        }
        else {
            downVotedPosts.add(post);
            post.setVotes(-1);
            post.getCreator().postKarma--;
            post.getCreator().totalKarma--;
        }
    }
    public void commentKarma(Account account, boolean voteType, Comment comment) {
        for(Comment temp : upVotedComments) {
            if(temp == comment) {
                System.out.println("You have upVoted before;");
                return;
            }
        }
        for(Comment temp : downVotedComments) {
            if(temp == comment) {
                System.out.println("You have downVoted before;");
                return;
            }
        }
        if(voteType) {
            upVotedComments.add(comment);
            comment.setVote(1);
            comment.getCreator().commentKarma++;
            comment.getCreator().totalKarma++;
        }
        else {
            downVotedComments.add(comment);
            comment.setVote(-1);
            comment.getCreator().commentKarma--;
            comment.getCreator().totalKarma--;
        }
    }
    public void retractVote(Post post) {
        for(Post temp : upVotedPosts) {
            if(temp == post) {
                upVotedPosts.remove(post);
                post.setVotes(-1);
                post.getCreator().postKarma--;
                post.getCreator().totalKarma--;
                return;
            }
        }
        for(Post temp : downVotedPosts) {
            if(temp == post) {
                downVotedPosts.remove(post);
                post.setVotes(1);
                post.getCreator().postKarma++;
                post.getCreator().totalKarma++;
                return;
            }
        }
        System.out.println("You haven't voted");
    }
    public void retractVote(Comment comment) {
        for(Comment temp : upVotedComments) {
            if(temp == comment) {
                upVotedComments.remove(comment);
                comment.setVote(-1);
                comment.getCreator().commentKarma--;
                comment.getCreator().totalKarma--;
                return;
            }
        }
        for(Comment temp : downVotedComments) {
            if(temp == comment) {
                downVotedComments.remove(comment);
                comment.setVote(1);
                comment.getCreator().commentKarma++;
                comment.getCreator().totalKarma++;
                return;
            }
        }
        System.out.println("You haven't voted");
    }
    public static boolean usernameIsUsed(String username) {
        for (Account temp : Reddit.getAccounts()) {
            if(temp.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public static boolean emailIsUsed(String emailAddress) {
        for (Account temp : Reddit.getAccounts()) {
            if(temp.getEmailAddress().equals(emailAddress)) {
                return true;
            }
        }
        return false;
    }
    public void viewSavedPosts() {
        int i = 1;
        for (Post post : savedPosts) {
            post.viewPost(i);
            i++;
        }
    }
 }
