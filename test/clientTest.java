import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.util.ArrayList;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Summer 2020</p>
 *
 * @author Purdue CS
 * @version June 15, 2020
 */

public class clientTest {
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

//     * A set of public test cases.
//     *
//     * <p>Purdue University -- CS18000 -- Summer 2020</p>
//     *
//     * @author Purdue CS
//     * @version June 15, 2020

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
        public void clientClassDeclarationTest() {
            Class<?> clazz;
            String message = "message";

            clazz = Client.class;

            try {
                clazz.getDeclaredConstructor(String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Client` declares a constructor that is `public` and has one parameter of type `String`!");
                return;
            }

            try {
                clazz.getDeclaredMethod("connect", int.class, ArrayList.class, Client.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Client` declares a method named `connect` that is `public`, has a return type of `String`, and has 3 parameters!");
                return;
            }

            try {
                clazz.getDeclaredField(message);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Client` declares an attribute named `message` that is `public`, has a return type of `String`, and has no parameters!");
                return;
            }

        }

        @Test(timeout = 1000)
        public void clientRegistrationTest() throws IOException, ClassNotFoundException {
            String msg = "123,123,123";
            boolean ret;
            ArrayList<User> users = new ArrayList<>();
            users.add(new User("123", "123", "123"));

            Client client = new Client(msg);
            ret = client.connect(0, users , client);
            Assert.assertEquals(ret, false);
        } // registrationTest


        @Test(timeout = 1000)
        public void duplicateRegistrationTest() throws IOException, ClassNotFoundException {
            String[] msg = {"123,123,123","123,123,123"};
            ArrayList<User> users = new ArrayList<>();
            users.add(new User("123", "123", "123"));
            users.add(new User("123", "123", "123"));
            boolean[] ret = new boolean[2];
            boolean[] ret1 = {true, true};

            for (int i = 0; i < msg.length; i++) {
                Client client = new Client(msg[i]);
                ret[i] = client.connect(1,users, client);
                Assert.assertEquals(ret[i], ret1[i]);
            }
        } // duplicateTest

    }

}