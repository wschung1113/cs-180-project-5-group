//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.notification.Failure;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.Random;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class PosterRunLocalTest {
//    public static void main(String[] args) {
//        Result result = JUnitCore.runClasses(TestCase.class);
//        if (result.wasSuccessful()) {
//            System.out.println("Excellent - Test ran successfully");
//        } else {
//            for (Failure failure : result.getFailures()) {
//                System.out.println(failure.toString());
//            }
//        }
//    }
//
//
//    public static class TestCase {
//        private final PrintStream originalOutput = System.out;
//        private final InputStream originalSysin = System.in;
//
//        @SuppressWarnings("FieldCanBeLocal")
//        private ByteArrayInputStream testIn;
//
//        @SuppressWarnings("FieldCanBeLocal")
//        private ByteArrayOutputStream testOut;
//
//        @Before
//        public void outputStart() {
//            testOut = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(testOut));
//        }
//
//        @After
//        public void restoreInputAndOutput() {
//            System.setIn(originalSysin);
//            System.setOut(originalOutput);
//        }
//
//        private String getOutput() {
//            return testOut.toString();
//        }
//
//        @SuppressWarnings("SameParameterValue")
//        private void receiveInput(String str) {
//            testIn = new ByteArrayInputStream(str.getBytes());
//            System.setIn(testIn);
//        }
//
//        private static String multiline(String... inputLines) {
//            StringBuilder sb = new StringBuilder();
//
//            for(String line : inputLines) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
//            }
//
//            return sb.toString();
//        }
//
//        @Test(timeout = 1000)
//        public void postClassDeclarationTest() {
//            Class<?> clazz;
//            int modifiers;
//            Class<?> superclass;
//            Class<?>[] superinterfaces;
//            String fileName = "getFileName";
//            String fileNameField = "fileName";
//            String createPost = "createPost";
//            String editPost = "editPost";
//            String read = "readFromFile";
//            String readAll = "readAll";
//
//
//            clazz = Poster.class;
//
//            try {
//                clazz.getDeclaredConstructor(User.class);
//            } catch (NoSuchMethodException e) {
//                Assert.fail("Ensure that `Poster` declares a constructor that is `public` and has one parameter of type `User`!");
//
//                return;
//            } //end try catch
//
//            try {
//                clazz.getDeclaredMethod(fileName);
//            } catch (NoSuchMethodException e) {
//                Assert.fail("Ensure that `Poster` declares a method named `getFileName` that is `public`, has a return type of `String`, and has no parameters!");
//
//                return;
//            } //end try catch
//
//            try {
//                clazz.getDeclaredField("fileName");
//            } catch (NoSuchFieldException e) {
//                Assert.fail("Ensure that `Poster` declares a field named `fileName` that is `private`, and of type `String'!");
//
//                return;
//            } //end try catch
//
//            try {
//                clazz.getDeclaredMethod(createPost);
//            } catch (NoSuchMethodException e) {
//                Assert.fail("Ensure that `Poster` declares a method named `createPostMethod` that is `public`, has a return type of 'Post', and has five parameters!");
//
//                return;
//            } //end try catch
//
//            try {
//                clazz.getDeclaredMethod(editPost);
//            } catch (NoSuchMethodException e) {
//                Assert.fail("Ensure that `Poster` declares a method named `editPostMethod` that is `public`, has a return type of 'Post', and has two parameters!");
//
//                return;
//            } //end try catch
//
//
//            try {
//                clazz.getDeclaredMethod(read);
//            } catch (NoSuchMethodException e) {
//                Assert.fail("Ensure that `Poster` declares a method named `readFromFile` that is `public`, has a return type of 'ArrayList<Post>', and has one parameter!");
//                return;
//            } //end try catch
//
//            try {
//                clazz.getDeclaredMethod("readAll");
//            } catch (NoSuchMethodException e) {
//                Assert.fail("Ensure that 'Poster' declares a method named 'readAll' that is 'public', has a return type of 'ArrayList<Post>', and has no parameters!");
//                return;
//            }
//
//            try {
//                clazz.getDeclaredMethod("writeAll", ArrayList.class);
//            } catch (NoSuchMethodException e) {
//                Assert.fail("Ensure that 'Poster' declares a method named 'writeAll' that is 'public', has a return type of 'void', and has one parameter of type 'ArrayList<Post>'!");
//                return;
//            }
//
//
//            clazz = Poster.class;
//
//            modifiers = clazz.getModifiers();
//
//            superclass = clazz.getSuperclass();
//
//            superinterfaces = clazz.getInterfaces();
//
//            Assert.assertTrue("Ensure that `Poster` is `public`!", Modifier.isPublic(modifiers));
//            Assert.assertFalse("Ensure that `Poster` is NOT `abstract`!", Modifier.isAbstract(modifiers));
//            Assert.assertEquals("Ensure that `Poster` extends `Object`!", Object.class, superclass);
//            Assert.assertEquals("Ensure that `Poster` implements no interfaces!", 0, superinterfaces.length);
//        } //PosterClassDeclarationTest
//
//
//
//
//
//        @Test(timeout = 1000)
//        public void createPostTest() {
//            String input = multiline("create","Hello, my name is Joe");
//            String expected = multiline("Hello, my name is Joe", "Hello, my name is Joe", "Hello, my name is Joe");
//            String message = "Be sure createPost, Post, and read and write from file match results";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        } // createPostTest()
//
//        @Test(timeout = 1000)
//        public void invalidCreatePostTest() {
//            String input = multiline("invalidCreate", null);
//            String expected = null;
//            String message = "Be sure createPost doesn't attempt to create post when user or postString is null";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//        //edit post and delete post methods require working with the GUI so they don't have test cases
//
//        @Test(timeout = 1000)
//            public void readTest() {
//            String input = multiline("read", "this is a test");
//            String expected = "this is a test";
//            String message = "Be sure that readFromFile reads posts in correctly";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//
//        @Test(timeout = 1000)
//        public void invalidReadTest() {
//            String input = multiline("readInvalid", "this is a test");
//            String expected = "Error reading from file. That file does not exist."; //IOException should be caught
//            String message = "Be sure that readFromFile has a catch block for invalid file names";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//        @Test(timeout = 1000)
//        public void writeTest() {
//            String input = multiline("write", "this is a test");
//            String expected = "this is a test ";
//            String message = "Be sure writeToFile correctly writes to file";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//        @Test(timeout = 1000)
//        public void invalidWriteTest() {
//            String input = multiline("writeInvalid");
//            String expected = "Error writing to file. That file does not exist. "; //IOException should be caught
//            String message = "Be sure that writeToFile has a catch block for invalid file names";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//        @Test(timeout = 1000)
//        public void findPostTest() {
//            String input = multiline("findPost", "this is a test");
//            String expected = "0";
//            String message = "Be sure that findPost returns the correct index of a post";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//        @Test(timeout = 1000)
//        public void invalidFindPostTest() {
//            String input = multiline("findPost", "this is a test");
//            String expected = "1"; //size of the array, because post was not found
//            String message = "Be sure the findPost method returns the size of the array when no post is found";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//        @Test(timeout = 1000)
//        public void readAllTest() {
//            String input = multiline("readAll", "this is a test");
//            String expected = "Posts read successfully. "; //throws ArrayOutOfBoundsException if readAll is written wrong
//            String message = "Be sure the findPost method returns the size of the array when no post is found";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//        @Test(timeout = 1000)
//        public void writeAllTest() {
//            String input = multiline("writeAll", "this is a test");
//            String expected = "Posts written successfully."; //throws ArrayOutOfBoundsException if writeAll is written wrong
//            String message = "Be sure the writeAll method correctly writes to a file";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//        @Test(timeout = 1000)
//        public void invalidReadAllTest() {
//            String input = multiline("invalidReadAll", "this is a test");
//            String expected = "No posts to read."; //this method causes null pointer exception if readAll doesn't account for null
//            String message = "Be sure the readAll method returns null when no posts are read.";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//
//        @Test(timeout = 1000)
//        public void invalidWriteAllTest() {
//            String input = multiline("invalidWriteAll", "this is a test");
//            String expected = "Error!No posts to write"; //this method causes null pointer exception if writeAll doesn't account for null
//            String message = "Be sure the writeAll method doesn't attempt to write a null string";
//
//            receiveInput(input);
//            Poster.main(new String[] {});
//
//            assertEquals(message, expected, getOutput());
//        }
//    }
//}