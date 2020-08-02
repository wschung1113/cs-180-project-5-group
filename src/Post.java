import java.util.ArrayList;
import java.time.*;
import java.util.Scanner;

public class Post {
    /**
     * Project 5 -- Post
     *
     * a post that a user makes.
     * Each post consists of the user's
     * name and the text that is in the post.
     * Includes a method to format the post
     * in a way that it can be written to a
     * file.
     */
    private String name; //user's name that made the post
    private String postString; //text in post
    private LocalDateTime time0; //time post was made
    private String time; //String version of time post was made
    private int panelLoc;
    private ArrayList<Comment> allComments;
    private static final int SIZE = 25; //limit to length of line
    private User user;

    public Post(User user, String name, String postString, LocalDateTime time0, String time, int panelLoc, ArrayList<Comment> allComments) {
        this.user = user;
        this.name = name; //name of user making post
        this.postString = formatPost(postString);
        this.time0 = time0;
        this.time = time;
        this.panelLoc = panelLoc;
        this.allComments= allComments;
    }

    /**
     * returns name of user that wrote post
     *
     * @return String, user's name
     */

    public String getName() {
        return name;
    }

    public User getUser() {return user; }

    /**
     * returns text in post
     * @return String, text in post
     */

    public String getPostString() {
        return postString;
    }


    public LocalDateTime getTime0() {return time0;}

    /**
     * returns time post was made
     * @return String, time post was made
     *
     */
    public String getTime() {
        return time;
    }

    /**
     * sets postString of post to parameter
     *
     * @param postString, String to be new postString of post
     */


    public void setPostString(String postString) {
        this.postString = postString;
    }

    public int getPanelLoc() {
        return this.panelLoc;
    }

    public void setPanelLoc(int panelLoc) {
        this.panelLoc = panelLoc;
    }


    public void setAllComments(ArrayList<Comment> allComments) {
        this.allComments = allComments;
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    /**
     * formats post to fit within width
     * @return String formatted text
     */

    public static String formatPost(String postString) {
        String format = "";

        if (postString == null) {
            System.out.println("Error!  Cannot format a null String.");
            return null;
        }

        int length = postString.length();
        int j = 0;

        for (int i = 0; i < length; i++) {
            format += postString.charAt(i);

            if (j > SIZE) {
                if (postString.charAt(i) == '.' || postString.charAt(i) == ' ') {
                    format += "\n";
                    while (i < length - 1 && postString.charAt(++i) == ' ');
                    i--;
                    j = 0;
                }
            }
            j++;
        }

        return format;
    }

    //this is a bubble sort (learned about these in CS159)
    public static ArrayList<Post> sortPosts(ArrayList<Post> posts) {
        String timePost;
        String[] timeArray;
        int length = posts.size();
        Post temp;

        if (length == 0) {
            System.out.println("Error!  Cannot sort empty list");
            return null;
        }

        for (int i = length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (posts.get(j).getTime0().isAfter(posts.get(j + 1).getTime0())) {
                    temp = posts.get(j);
                    posts.set(j, (posts.get(j + 1)));
                    posts.set(j + 1, temp);
                }
            }
        }

        return posts;
    }

    public static void main(String[] args) {
        User user = new User("userName", "password", "John Doe");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        ArrayList<Post> posts = new ArrayList<Post>();
        Poster poster = new Poster(user);

        if (choice.equals("sort")) {
            String postS = scanner.nextLine();
            String postS1 = scanner.nextLine();
            LocalDateTime time0 = LocalDateTime.now();
            String timeString = time0.toString();
            String[] timeArray = timeString.split("T");
            String date = timeArray[0];

            String[] time1a = timeArray[1].split(":");
            String hour = time1a[0];
            String minute = time1a[1];
            String time = date + " " + hour + ":" + minute;
            Post post = poster.createPost(user, postS, time0, time, 0);
            LocalDateTime time1 = LocalDateTime.now();
            String timeString1 = time1.toString();
            String[] timeArray1 = timeString.split("T");
            String date1 = timeArray[0];

            String[] time2 = timeArray[1].split(":");
            String hour1 = time2[0];
            String minute1 = time2[1];
            String time3 = date1 + " " + hour1 + ":" + minute1;
            Post post1 = poster.createPost(user, postS1, time1, time3, 1);
            posts.add(post1);
            posts.add(post);
            posts = Post.sortPosts(posts);
            System.out.println(posts.get(0).getPostString());
        } else if (choice.equals("sortInvalid")) {
            ArrayList<Post> invalid = new ArrayList<>();
            Post.sortPosts(invalid);
        } else if (choice.equals("format")) {
            String post1 = scanner.nextLine();
            System.out.println(Post.formatPost(post1));
        } else if (choice.equals("invalidFormat")) {
            System.out.println(Post.formatPost(null));

        }

    }


}