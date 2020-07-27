import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.*;
import java.lang.StringBuilder;
public class comment {

    private String comtext;
    private String commenterID;
    private String commentername;
    private String commentID;
    private int likes;
    private String postID;
    private String time;
    //private int

    public comment(){
    }

    public comment(String comtext, String commenterID, String commentID, int likes, String postID,String commentername) {
        this.comtext = comtext;
        this.commenterID = commenterID;
        this.commentID = commentID;
        this.likes = likes;
        this.postID = postID;
        this.commentername= commentername;
    }

    public String getPostID() {
        return postID;
    }


    public void setPostID(String postID) {
        this.postID = postID;
    }


//    public comment(String comtext, String commenterID, int likes){
//        this.commenterID= commenterID;
//        this.comtext= comtext;
//        this.likes= likes;
//    }



    public String getComtext(){
        return comtext;
    }

    public String getCommenterID(){
        return commenterID;
    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComtext(String comtext){
        this.comtext= comtext;
    }
    public void setCommenterID(String commenterID){
        this.commenterID= commenterID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void SetTime(){
        Date date=java.util.Calendar.getInstance().getTime();
         time= String.format("%s",date);
    }

    public String getCommentername() {
        return commentername;
    }

    public void setCommentername(String commentername) {
        this.commentername = commentername;
    }

    public String getTime() {
        return time;
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("\t"+getCommentername()+" @"+getCommenterID()+" ."+getTime()+"\n");
        sb.append("\t"+getComtext()+"\n");
        return sb.toString();
    }
}
