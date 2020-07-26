
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
        this.postString = postString;//String that user enters to be post
    }

    /**
     * returns name of user that wrote post
     *
     * @return String, user's name
     */

    public String getName() {
        return this.name;
    }

    /**
     * returns text in post
     * @return String, text in post
     */

    public String getPostString() {
        return this.postString;
    }

    /**
     * formats post to fit within width
     * @return String[] where zeroth element is the user's nae
     *         and the first element is the formatted text
     */

    public String[] formatPost() {
        String format = "";
        String[] formattedPost = new String[2];
        String string = this.getPostString();
        int length = string.length();
        formattedPost[0] = name;
        int j = 0;

        for (int i = 0; i < length; i++) {
            format += string.charAt(i);

            if (j > SIZE) {
                if (string.charAt(i) == '.' || string.charAt(i) == ' ') {
                    format += "\n";
                    while (i < length - 1 && string.charAt(++i) == ' ');
                    i--;
                    j = 0;
                }
            }

            j++;
        }

        formattedPost[1] = format;

        return formattedPost;
    }
}