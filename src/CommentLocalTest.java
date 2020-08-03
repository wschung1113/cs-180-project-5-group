import junit.framework.TestCase;
import org.junit.Test;
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

public class CommentLocalTest {
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
     * Local Test Cases
     *
     * <p>Purdue University -- CS18000 -- Summer 2020</p>
     *
     * @author Purdue CS
     * @version August 02, 2020
     */
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

            for (String line : inputLines) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            return sb.toString();
        }

        @Test(timeout = 1000)
        public void commentClassDeclarationTest() {
            Class<?> clazz;
            Constructor<?> constructor;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            Field commentername;
            String commenternameField = "commentername";
            Field comtext;
            String comtextField = "comtext";
            Field likes;
            String likesField = "likes";
            Field Time;
            String TimeField = "Time";
            Field commentID;
            String commentIDField = "commentID";

            Method getComtextMethod;
            String getComtext = "getComtext";
            Method getCommenternameMethod;
            String getCommentername = "getCommenter";
            Method getCommentIDMethod;
            String getCommentID = "getCommentID";
            Method getLikesMethod;
            String getLikes = "getLikes";
            Method getTimeMethod;
            String getTime = "getTime";
            Method setComtextMethod;
            String setComtext = "setComtext";
            Method setCommenternameMethod;
            String setCommentername = "setCommentername";
            Method setCommentIDMethod;
            String setCommentID = "setCommentID";
            Method setLikesMethod;
            String setLikes = "setLikes";
            Method setTimeMethod;
            String setTime = "setTime";

            boolean set;
            Random random;

            clazz = Comment.class;

            try {
                constructor = clazz.getDeclaredConstructor(Comment.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Comment' declares a constructor that is `public` and has five parameters!");
                return;
            }
            try {
                getComtextMethod = clazz.getDeclaredMethod(getComtext);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Comment` declares a method named `getComtext` that is `public`, has a return type of `String`, and has no parameters!");

                return;
            }
            try {
                getCommenternameMethod = clazz.getDeclaredMethod(getCommentername);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'getCommenterName' that is public, has a return type of 'String', and has no parameters!");
                return;
            }
            try {
                getCommentIDMethod = clazz.getDeclaredMethod(getCommentID);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'getCommentID' that is public, has a return type of 'String', and has no parameters!");
                return;
            } //end try catch
            try {
                getLikesMethod = clazz.getDeclaredMethod(getLikes);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'getLikes' that is public, has a return type of 'String', and has no parameters!");
                return;
            }
            try {
                getTimeMethod = clazz.getDeclaredMethod(getTime);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'getTime' that is public, has a return type of 'String', and has no parameters!");
                return;
            }
            try {
                setComtextMethod = clazz.getDeclaredMethod(setComtext);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setComtext' that is public, has a return type of void, and has one parameter of type 'String'!");
                return;
            }
            try {
                setCommenternameMethod = clazz.getDeclaredMethod(setCommentername);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setCommentername' that is public, has a return type of void, and has one parameter of type 'String'!");
                return;
            }
            try {
                setCommentIDMethod = clazz.getDeclaredMethod(setCommentID);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setCommeID' that is public, has a return type of void, and has one parameter of type 'String'!");
                return;
            }
            try {
                setLikesMethod = clazz.getDeclaredMethod(setLikes);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setLikes' that is public, has a return type of void, and has one parameter of type 'int'!");
                return;
            }
            try {
                setTimeMethod = clazz.getDeclaredMethod(setTime);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setTime' that is public, has a return type of void, and has one parameter of type 'String'!");
                return;
            }
            try {
                commentername = clazz.getDeclaredField(commenternameField);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Comment` declares a field named `commentername` that is `private`, and of type `String'!");
                return;
            }
            try {
                comtext = clazz.getDeclaredField(comtextField);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Comment` declares a field named `comtext` that is `private`, and of type `String'!");
                return;
            }
            try {
                likes = clazz.getDeclaredField(likesField);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Comment` declares a field named `likes` that is `private`, and of type `int'!");
                return;
            }
            try {
                Time = clazz.getDeclaredField(TimeField);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Comment` declares a field named `Time` that is `private`, and of type `String'!");
                return;
            }
            try {
                commentID = clazz.getDeclaredField(commentIDField);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Comment` declares a field named `commentername` that is `private`, and of type `String'!");
                return;
            }
            clazz = Comment.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `Comment` is `public`!", Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `Comment` is NOT `abstract`!", Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `Comment` extends `Object`!", Object.class, superclass);
            Assert.assertEquals("Ensure that `Comment` implements no interfaces!", 0, superinterfaces.length);
        } //PostClassDeclarationTest


    }
}


