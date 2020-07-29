import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Commenter {

    private String fileName;

    JButton postButton;

    //JButton like;
    //JButton reply;
    //JButton postReply;
    JTextField comment;
    //JTextField reply1;
    Implement imp;
    int numcom = 0;
    ArrayList<comment> commentsOnPostList = new ArrayList<>();
    ArrayList<JButton> commentLikeButton = new ArrayList<>();
    ArrayList<JButton> commentEditButton = new ArrayList<>();
    ArrayList<JButton> commentDeleteButton = new ArrayList<>();

    ArrayList<String> likeOnComments = new ArrayList<>();
    //int i=0;
    JButton templikeButton;
    JButton tempeditButton;
    JButton tempdeleteButton;

    // Mackenna, can we combine both out write to file and read from file methods so that its easier ?

    public Commenter(User user) {
        fileName = user.getUsername() + "commentFile.txt";
    }

    public String getFileName() {
        return fileName;
    }


    public JPanel createComment(String comtext, String Time, User user) {
        numcom++;
        comment temp = new comment(user.getAlias(), comtext,0, Time );
        commentsOnPostList.add(temp);
        JLabel label = new JLabel(temp.toString());

        templikeButton = new JButton("Likes " + commentsOnPostList.get(numcom - 1).getLikes());
        templikeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                commentsOnPostList.get(numcom - 1).setLikes(commentsOnPostList.get(numcom - 1).getLikes() + 1);
            }
        });
        tempdeleteButton = new JButton("Delete");
        tempdeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                commentsOnPostList.remove(numcom - 1);
                // Wonseok please add the code to remove/makeInvisible the Panel for the comment
            }
        });
        templikeButton = new JButton("Likes " + commentsOnPostList.get(numcom - 1).getLikes());
        templikeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String editedString = (JOptionPane.showInputDialog(null, "Enter the edited String",
                        "Post maker", JOptionPane.QUESTION_MESSAGE));
                // Wonseok please add the code for editing the comment.
            }
        });

        commentLikeButton.add(templikeButton);
        JPanel commentOnPostPanel = new JPanel();
        commentOnPostPanel.add(label);
        commentOnPostPanel.add(commentLikeButton.get(numcom - 1));
        return commentOnPostPanel;
    }

    //Mackenna please advise on how you want the comments and post content to be written to the same file together
    public void writeToFile(ArrayList<comment> commentsOnPostList) {

        try {
            File f = new File(fileName);
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);

            for (int i = 0; i < commentsOnPostList.size(); i++) {
                comment temp = new comment();
                temp = commentsOnPostList.get(i);
//                if(i!=0) {
//                    pw.printf("-");
//                }

                pw.printf("[" + temp.getCommentername());
                pw.printf("," + temp.getComtext() + "," + temp.getLikes() + "," + temp.getTime());
                pw.printf("]\n");
                /*if(i!=commentsOnPostList.size()-1 ){
                    pw.printf("-");
                }*/
            }
            pw.printf("\n");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<comment> readFromFile(User user) throws FileNotFoundException {
        ArrayList<comment> temporaryCom = new ArrayList<>();
        File f = new File(fileName);
        FileReader fr = new FileReader(f);
        //Book temporary = new Book;
        BufferedReader bfr = new BufferedReader(fr);
        String line = "";
        while (true) {
            try {
                line = bfr.readLine();
                if (line == null) {
                    break;
                }
                line = line.replace("[", "");
                line = line.replace("]", "");
                String[] values = line.split(",");
                comment tempcom = new comment(values[1], values[1], Integer.parseInt(values[2]), values[3]);
                temporaryCom.add(tempcom);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return temporaryCom;
    }
}
