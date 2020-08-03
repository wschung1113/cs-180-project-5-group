import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.Assert.assertEquals;
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
            Constructor<?> constructor;
            String message = "message";
            Field mes;
            Method connect;

            clazz = Client.class;

            try {
                constructor = clazz.getDeclaredConstructor(String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Client` declares a constructor that is `public` and has one parameter of type `String`!");
                return;
            }

            try {
                connect = clazz.getDeclaredMethod("connect");
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `Client` declares a method named `connect` that is `public`, has a return type of `String`, and has no parameters!");
                return;
            }

            try {
                mes = clazz.getDeclaredField(message);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `Client` declares an attribute named `message` that is `public`, has a return type of `String`, and has no parameters!");
                return;
            }

        }

        @Test(timeout = 1000)
        public void clientRegistrationTest() throws IOException, ClassNotFoundException {
            String msg = "123,123,123";
            boolean ret;

            Client client = new Client(msg);
            ret = client.connect();
            Assert.assertEquals(ret, true);
        } // registrationTest


        @Test(timeout = 1000)
        public void duplicateRegistrationTest() throws IOException, ClassNotFoundException {
            String[] msg = {"123,123,123","123,123,123"};
            boolean[] ret = new boolean[2];
            boolean[] ret1 = {true, false};

            for (int i = 0; i < msg.length; i++) {
                Client client = new Client(msg[i]);
                ret[i] = client.connect();
                Assert.assertEquals(ret[i], ret1[i]);
            }
        } // duplicateTest

    }

}