package practicum.api.common;

import io.qameta.allure.Step;

public class Assert {

    @Step("Проверить {description}")
    public static void assertEquals(String actual, String expected, String description) {
        org.junit.Assert.assertEquals(description, expected, actual);
    }

    @Step("Проверить {description}")
    public static void assertTrue(boolean actual, String description) {
        org.junit.Assert.assertTrue(description, actual);
    }

    @Step("Проверить {description}")
    public static void assertEquals(int actual, int expected, String description) {
        org.junit.Assert.assertEquals(description, expected, actual);
    }

    @Step("Проверить {description}")
    public static void assertNotNull(Object actual, String description) {
        org.junit.Assert.assertNotNull(description, actual);
    }
}
