import java.util.ArrayList;
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
public class User {
    String username;
    String password;
    String alias;
    ArrayList<Post> posts;

    public User(String username, String password, String alias) {
        this.username = username;
        this.password = password;
        this.alias = alias;
        this.posts = new ArrayList<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
