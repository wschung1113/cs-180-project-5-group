import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;

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


//     * Local Test Cases
//     *
//     * <p>Purdue University -- CS18000 -- Summer 2020</p>
//     *
//     * @author Purdue CS
//     * @version August 02, 2020

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
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            String comtextField = "comtext";
            String likesField = "likes";
            String commentIDField = "commentID";
            String getComtext = "getComtext";
            String getCommentID = "getCommentID";
            String getLikes = "getLikes";
            String getTime = "getTime";

            clazz = Comment.class;

            try {
                clazz.getDeclaredConstructor(String.class, String.class, int.class, String.class, int.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Comment' declares a constructor that is `public` and has five parameters!");
                return;
            }
            try {
                clazz.getDeclaredMethod(getComtext);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Comment` declares a method named `getComtext` that is `public`, has a return type of `String`, and has no parameters!");

                return;
            }
            try {
                clazz.getDeclaredMethod("getCommentername");
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'getCommenterName' that is public, has a return type of 'String', and has no parameters!");
                return;
            }
            try {
                clazz.getDeclaredMethod(getCommentID);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'getCommentID' that is public, has a return type of 'String', and has no parameters!");
                return;
            } //end try catch
            try {
                clazz.getDeclaredMethod(getLikes);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'getLikes' that is public, has a return type of 'String', and has no parameters!");
                return;
            }
            try {
                clazz.getDeclaredMethod(getTime);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'getTime' that is public, has a return type of 'String', and has no parameters!");
                return;
            }
            try {
                clazz.getDeclaredMethod("setComtext", String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setComtext' that is public, has a return type of void, and has one parameter of type 'String'!");
                return;
            }
            try {
                clazz.getDeclaredMethod("setCommentername", String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setCommentername' that is public, has a return type of void, and has one parameter of type 'String'!");
                return;
            }
            try {
                clazz.getDeclaredMethod("setCommentID", int.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setCommeID' that is public, has a return type of void, and has one parameter of type 'String'!");
                return;
            }
            try {
                clazz.getDeclaredMethod("setLikes", int.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setLikes' that is public, has a return type of void, and has one parameter of type 'int'!");
                return;
            }
            try {
                clazz.getDeclaredMethod("setTime", String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that 'Comment' declares a method named 'setTime' that is public, has a return type of void, and has one parameter of type 'String'!");
                return;
            }
            try {
                clazz.getDeclaredField("commentername");
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Comment` declares a field named `commentername` that is `private`, and of type `String'!");
                return;
            }
            try {
                clazz.getDeclaredField(comtextField);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Comment` declares a field named `comtext` that is `private`, and of type `String'!");
                return;
            }
            try {
                clazz.getDeclaredField(likesField);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Comment` declares a field named `likes` that is `private`, and of type `int'!");
                return;
            }
            try {
                clazz.getDeclaredField("time");
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Comment` declares a field named `Time` that is `private`, and of type `String'!");
                return;
            }
            try {
                clazz.getDeclaredField(commentIDField);
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
