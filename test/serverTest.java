import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Summer 2020</p>
 *
 * @author Purdue CS
 * @version June 15, 2020
 */
public class serverTest {
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
     * @author Purdue CS
     * @version June 15, 2020
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

            for(String line : inputLines) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            return sb.toString();
        }

        @Test(timeout = 1000)
        public void postClassDeclarationTest() {
            Class<?> clazz;
            String socket = "socket";

            clazz = SocialPostingServer.class;

            try {
                clazz.getDeclaredConstructor(Socket.class, int.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `SocialPostingServer` declares a constructor " +
                        "that is `public` and has one parameter of type `Socket`!");
                return;
            }

            try {
                clazz.getDeclaredMethod("validateUser", ArrayList.class, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `SocialPostingServer` declares a method " +
                        "named `validateUser` that is `public`, has a return type of `Boolean`, and has three parameters!");
                return;
            }

            try {
                clazz.getDeclaredMethod("validateRegister", ArrayList.class, String.class, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `SocialPostingServer` declares a method " +
                        "named `validateRegister` that is `public`, has a return type of `String`, and has three parameters!");
                return;
            }

            try {
                clazz.getDeclaredField(socket);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Client` declares an attribute named `socket` that is `public`, has a return type of `String`, and has no parameters!");
                return;
            }

            try {
                clazz.getDeclaredField("id");
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Client` declares an attribute named `message` that is `public`, has a return type of `String`, and has no parameters!");
                return;
            }

        }
    }

}