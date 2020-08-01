import javax.swing.JOptionPane;
import java.io.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The first option is to implement a social network "posting" application.
 * Most social networks allow users to make text based posts to share their thoughts or ideas. This will be a similar concept.
 *
 * Your implementation must have the following:
 *
 * Users can create, edit, and delete accounts for themselves.
 *
 * The attributes you collect as part of account creation are up to you.
 *
 * Users should be required to either create an account or sign in before gaining access to the application.
 *
 * Whichever identifier you maintain for the user must be unique.
 *
 * Users can create, edit, and delete posts. - ME
 *
 * Remember to restrict edit and delete access to the user who created the post. -ME
 *
 * Users can create, edit, and delete comments on specific posts. -Soham
 *
 * Remember to restrict edit and delete access to the user who created the comment. - Soham
 *
 * Users can view all of the current posts and their associated comments. - GUI?
 *
 * All posts and comments should include information about the user who created them (a name or username) and a timestamp. -ME
 *
 * The specific appearance and organization is your choice. -GUI
 *
 * Users can view all of the posts and comments made by a specific user. -ME?
 *
 * The application must support simultaneous use by multiple users over a network. Posts and comments should appear in real-time as users add them.
 *
 * All user interactions must be GUI based.
 *
 * Data must persist regardless of whether or not a user is connected. If a user disconnects and reconnects, their data should still be present.
 *
 * Descriptive errors should appear as appropriate. The application should not crash under any circumstances.
 *
 * Optional Features:
 *
 * Add emoji responses to posts.
 * Add polling features to posts.
 * Allow certain users to moderate posts by editing or removing them.
 */

public class Poster {
    private String fileName;

    public Poster(User user) {
        fileName = user.getUsername() + "PostFile.txt";
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) { this.fileName = fileName;}

    public static void main(String[] args) {
        //for local test cases
        ArrayList<Post> userPosts = new ArrayList<Post>();


        User user = new User("username", "password", "John Doe");
        Poster poster = new Poster(user);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        String postString = scanner.nextLine();
        LocalDateTime time0 = LocalDateTime.now();
        String timeString = time0.toString();
        String[] timeArray = timeString.split("T");
        String date = timeArray[0];

        String[] time1 = timeArray[1].split(":");
        String hour = time1[0];
        String minute = time1[1];
        String time = date + " " + hour + ":" + minute;
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Post post = new Post(user, postString, time0, time, 0, comments);
        if (choice.equals("create")) {

            System.out.println(post.getPostString());

            Post post1 = poster.createPost(user, postString, time0, time, userPosts.size());
            System.out.println(post1.getPostString());
            Post post2 = userPosts.get(post1.getPanelLoc());
            System.out.println(post2.getPostString());
        } else if (choice.equals("read")) {
            ArrayList<Post> posts = poster.readFromFile(user);
            posts.add(post);
            System.out.println(posts.get(0).getPostString());
        } else if (choice.equals("write")) {
            ArrayList<Post> posts = poster.readFromFile(user);
            poster.writeToFile(posts);
            System.out.println(posts.get(0).getPostString());
        } else if (choice.equals("readInvalid")) {
            poster.createPost(user, postString, time0, time, userPosts.size());
            poster.setFileName("Invalid.txt");
            poster.readFromFile(user);
        } else if (choice.equals("writeInvalid")) {
            post = poster.createPost(user, postString, time0, time, userPosts.size());
            poster.setFileName("Invalid.txt");
            ArrayList<Post> posts = new ArrayList<Post>();
            posts.add(post);
            poster.writeToFile(posts);
        } else if (choice.equals("findPost")) {
            int loc = poster.findPost(user, "this is a test");
            System.out.println(loc);
        } else if (choice.equals("invalidPost")) {
            int loc = poster.findPost(user, "invalid");
            System.out.println(loc);
        }

    }

    public Post createPost(User user, String postString, LocalDateTime time0, String time, int panelLoc) {

        String name = user.getAlias();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Post post = new Post(name, postString, time0, time, panelLoc, comments);
        ArrayList<Post> userPosts = user.getPosts();
        userPosts.add(post);
        user.setPosts(userPosts);
        writeToFile(userPosts);
        return post;


    }

    public Post editPost(User user, Post postEdit) {
        //this method assumes that the user has already selected which post to edit

        ArrayList<Post> userPosts = user.getPosts();
        String replacement = "";
        int loc = 0;
        int i = 0;
        int choice = -1;
        String postOriginal = postEdit.getPostString();
        String[] options = new String[3];
        options[0] = "Yes, post!";
        options[1] = "No, edit.";
        options[2] = "No, cancel.";

        for (Post post : userPosts) {
            if (post.getPostString().equals(postOriginal)) {
                loc = i;
                break;
            }
            i++;
        }

        do {

            replacement = JOptionPane.showInputDialog(null, "Original Post: " + postEdit.getPostString() + "\nNew Post:",
                    "Edit post", JOptionPane.PLAIN_MESSAGE);

            if (replacement != null && replacement != "") {

                choice = JOptionPane.showOptionDialog(null, replacement, "Are you sure you want to post this?",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            } else {
                return null;
            }

            if (choice == JOptionPane.CANCEL_OPTION) {
                return null;
            }

        } while (choice != JOptionPane.YES_OPTION);

        Post editedPost = new Post(postEdit.getName(), replacement, postEdit.getTime0(), postEdit.getTime(), postEdit.getPanelLoc(), postEdit.getAllComments());
        userPosts.set(loc, editedPost);

        //writing to GUI
        writeToFile(userPosts);
        user.setPosts(userPosts);

        //need to determine whether we are storing posts with user or elsewhere
        return editedPost;

    }

    /**
     *deletes post from file
     *
     * @param user - user that is trying to delete the post
     * @param postString - text in post
     */

    public void deletePost(User user, String postString) {
        ArrayList<Post> userPosts = readFromFile(user);
        int loc = 0;
        for (Post post : userPosts) {
            if (post.getPostString().equals(postString)) {
                break;
            }
            loc++;
        }

        int length = userPosts.size();

        for (int i = loc; i < length - 1; i++) {
            userPosts.set(i, userPosts.get(i + 1));
        }
        writeToFile(userPosts);
        user.setPosts(userPosts);

        //still need to make it so it's no longer visible
    }

    public void writeToFile(ArrayList<Post> userPosts) {

        try {
            File f = new File(fileName);
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);

            for (Post post : userPosts) {

                StringBuilder sb = new StringBuilder();
            sb.append(post.getName());
            sb.append(";:;");
            sb.append(post.getTime0());
            sb.append(";:;");
            sb.append(post.getTime());
            sb.append(";:;");
            sb.append(post.getPostString());
            sb.append(";;;\n");

            pw.write(sb.toString());
        }

            pw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file. That file does not exist.");
        }
    }

    public ArrayList<Post> readFromFile(User user) {

        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            ArrayList<String> postStrings = new ArrayList<String>(); //all posts in file
            ArrayList<Post> userPosts = new ArrayList<Post>(); //all posts by user
            String userName = user.getAlias();

            while ((line = br.readLine()) != null) {
                postStrings.add(line);
            }

            br.close();

            int i = 0;

            for (String s : postStrings) {

                String[] postSplit = s.split(";:;"); //info before colon is name, after is postString

                String name = postSplit[0];
                LocalDateTime time0 = LocalDateTime.parse(postSplit[1]);
                String time = postSplit[2];

                if (name.equals(user.getAlias())) {
                    String postString = postSplit[3];
                    postString = postString.substring(0, postString.length() - 3); //splitting semicolon off of end of line

                    Post post = new Post(name, postString, time0 , time, i, );
                    userPosts.add(post);
                }
                i++;
            }
            user.setPosts(userPosts);
            return userPosts;
        } catch (IOException e) {
            System.out.println("Error reading from file. That file does not exist.");
        }

        return null;
    }

    public int findPost(User user, String s) {

        ArrayList<Post> userPosts= readFromFile(user);
        int loc = 0;
        for (Post post : userPosts) {
            if (post.getPostString().equals(s)) {
                return loc;
            }
            loc++;
        }
        return loc;
    }
}
