
/**import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.math.BigInteger;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;


/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Summer 2020</p>
 *
 * @author Purdue CS
 * @version June 15, 2020

public class PostRunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Summer 2020</p>
     *
     * @author Mackenna Hawes
     * @version August 2, 2020

    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        private static String multiline(String... inputLines) {
            StringBuilder sb = new StringBuilder();

            for(String line : inputLines) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            return sb.toString();
        }

        @Test(timeout = 1000)
        public void postClassDeclarationTest() {
            Class<?> clazz;
            Constructor<?> constructor;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            Field name;
            String nameField = "name";
            Field postString;
            String postStringField = "postString";
            Field time0;
            String time0Field = "time0";
            Field time;
            String timeField = "time";
            Field panelLoc;
            String panelLocField = "panelLoc";
            Field SIZE;
            String SIZEField = "SIZE";
            Field commentsField;
            String comments = "allComments";
            Field userField;
            String user = "user";

            Method getNameMethod;
            String getName = "getName";
            Method getPostStringMethod = "getPostString";
            Method getTime0Method;
            String getTime0 = "getTime0";
            Method getTimeMethod;
            String getTime = "getTime";
            Method setPostStringMethod;
            String setPostString = "setPostString";
            Method getPanelLocMethod;
            String getPanelLoc = "getPanelLoc";
            Method setPanelLocMethod;
            String setPanelLoc = "setPanelLoc";
            Method formatPostMethod;
            String formatPost = "formatPost";
            Method sortPostsMethod;
            sortPosts = "sortPosts";

            boolean set;
            Random random;


            clazz = Poster.class;

            try {
                constructor = clazz.getDeclaredConstructor(User.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Post` declares a constructor that is `public` and has five parameters!");

                return;
            } //end try catch

            try {
                getNameMethod = clazz.getDeclaredMethod(getName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Post` declares a method named `getName` that is `public`, has a return type of `String`, and has no parameters!");

                return;
            } //end try catch

            try {
                getPostStringMethod = clazz.getDeclaredMethod(getPostString);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Post' declares a method named 'getPostString' that is public, has a return type of 'String', and has no parameters!");
                return;
            } //end try catch

            try {
                getTime0Method = clazz.getDeclaredMethod(getTime0);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Post' declares a method named 'getTime0' that is public, has a return type of 'LocalDateTime', and has no parameters!");
                return;
            } //end try catch

            try {
                getTimeMethod = clazz.getDeclaredMethod(getTime);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Post' declares a method named 'getTime' that is public, has a return type of 'String', and has no parameters!");
                return;
            } //end try catch

            try {
                setPostStringMethod = clazz.getDeclaredMethod(setPostString);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Post' declares a method named 'setPostString' that is public, has a return type of void, and has one parameter!");
                return;
            } //end try catch

            try {
                getPanelLocMethod = clazz.getDeclaredMethod(getPanelLoc);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Post' declares a method named 'getPanelLoc' that is public, has a return type of 'int', and has no parameters!");
                return;
            } //end try catch

            try {
                setPanelLocMethod = clazz.getDeclaredMethod(setPanelLoc);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Post' declares a method named 'setPanelLoc' that is public, has a return type void, and has one parameter of type 'int'!");
                return;
            } //end try catch

            try {
                formatPostMethod = clazz.getDeclaredMethod(formatPost);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Post' declares a method named 'formatPost' that is public, has a return type of 'String', and has one parameter of type 'String'!");
                return;
            } //end try catch

            try {
                sortPostMethod = clazz.getDeclaredMethod(sortPosts);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Post' declares a method named 'sortPosts' that is public, has a return type of 'ArrayList<Post>', and has one parameter of type 'ArrayList<Post>'");
                return;
            } //end try catch

            try {
                nameField = clazz.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Post` declares a field named `name` that is `private`, and of type `String'!");
                return;
            } //end try catch

            try {
                postStringField = clazz.getDeclaredField(postString);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Post` declares a field named `postString` that is `private`, and of type `String'!");
                return;
            } //end try catch

            try {
                time0Field = clazz.getDeclaredField(time0);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Post` declares a field named `time0` that is `private`, and of type `LocalDateTime'!");
                return;
            } //end try catch

            try {
                timeField = clazz.getDeclaredField(time);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Post` declares a field named `time` that is `private`, and of type `String'!");
                return;
            } //end try catch

            try {
                panelLocField = clazz.getDeclaredField(panelLoc);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Post` declares a field named `panelLoc` that is `private`, and of type `int'!");
                return;
            } //end try catch

            try {
                SIZEField = clazz.getDeclaredField(SIZE);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Post` declares a field named `SIZE` that is `private`, and of type `int'!");
                return;
            } //end try catch

            try {
                commentsField = clazz.getDeclaredField(comments);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that 'Post' declares a field named 'allComments' that is 'private' and of type 'ArrayList<Comment>'");
            }

            try {
                userField = clazz.getDeclaredField(user);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that 'Post' declares a field name 'user' that is 'private' and of type 'User'!");
            }

            clazz = Post.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `Post` is `public`!", Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `Post` is NOT `abstract`!", Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `Post` extends `Object`!", Object.class, superclass);
            Assert.assertEquals("Ensure that `Post` implements no interfaces!", 0, superinterfaces.length);
        } //PostClassDeclarationTest

        @Test(timeout = 1000) {
            public void formatPostTest() {
                String input = multiline("format","This is a very very very very very very long post...");
                String expected = "This is a very very very very \nvery very very long post");;
                String message = "Be sure that formatPost method correctly formats length of lines.";

                receiveInput(input);
                Post.main(new String[] {});

                assertEquals(message, expected, getOutput());
            }
        }

        @Test(timeout = 1000) {
            public void invalidFormatPostTest() {
                String input = multiline("invalidFormat");
                String expected = "Error!  Cannot format a null String.";
                String message = "Be sure that the formatPost method doesn't attempt to format a null String.";

                receiveInput(input);
                Post.main(new String[] {});

                assertEquals(message, expected, getOutput());
            }
        }



    @Test(timeout = 1000) {
            public void sortPostsTest() {
                String input = multiline( "sort", "This is the first post", "This is the second post");
                String expected = "This is the first post";
                String message = "Be sure that sortPosts method correctly sorts posts by time";

                receiveInput(input);
                Post.main(new String[] {});

                assertEquals(message, expected, getOutput());
            }
        }

        @Test(timeout = 1000) {
            public void invalidSortPostsTest() {
                String input = "sortInvalid";
                String expected = "Error!  Cannot sort empty list";
                String message = "Be sure that sortPosts method doesn't attempt to sort an empty arrayList";

                receiveInput(input);
                Post.main(new String[] {});

                assertEquals(message, expected, getOutput());
            }
        }


    }

}
*/