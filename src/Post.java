
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
    private static final int SIZE = 25; //limit to length of line

    public Post(String name, String postString) {
        this.name = name; //name of user making post
        this.postString = formatPost(postString);
    }

    /**
     * returns name of user that wrote post
     *
     * @return String, user's name
     */

    public String getName() {
        return name;
    }

    /**
     * returns text in post
     * @return String, text in post
     */

    public String getPostString() {
        return postString;
    }

    /**
     * sets postString of post to parameter
     *
     * @param postString, String to be new postString of post
     */


    public void setPostString(String postString) {
        this.postString = postString;
    }

    /**
     * formats post to fit within width
     * @return String formatted text
     */

    public String formatPost(String postString) {
        String format = "";

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
}