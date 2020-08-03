

/**
 * Project 5 -- Option 1 -- Group Project
 *
 *
 *
 * Our project implements a social posting application allowing multiple users to communicate simultaneously.
 *
 * @author Soham Arora, Mackenna Hawes, Lingbo Fang, Leo Dao, Wonseok Chung
 *
 * @version August 3rd, 2020
 *
 */

public class Comment {

    private String comtext;
    private String commentername;
    private int commentID;
    private int likes;
    private String time;
    private static final int SIZE = 15;



    public Comment() {
    }

    public Comment(String commentername, String comtext, int likes, String Time , int commentID) {
        this.comtext = comtext;
        this.likes = likes;
        this.commentername = commentername;
        this.commentID= commentID;
    }

    public String getComtext() {
        return comtext;
    }

    public String getCommentername() {
        return commentername;
    }

    public int getCommentID() {
        return commentID;
    }

    public int getLikes() {
        return likes;
    }

    public String getTime() {
        return time;
    }

    public void setComtext(String comtext) {
        this.comtext = comtext;
    }

    public void setCommentername(String commentername) {
        this.commentername = commentername;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setTime(String time) {
        this.time = time;
    }

}


