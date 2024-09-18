package practicum.api.common;

import io.qameta.allure.Step;

public class AssertWithStep {

    @Step("Проверить {description}")
    public static void assertEquals(String description, String expected, String actual) {
        org.junit.Assert.assertEquals(description, expected, actual);
    }

    @Step("Проверить {description}")
    public static void assertTrue(String description, boolean actual) {
        org.junit.Assert.assertTrue(description, actual);
    }

    @Step("Проверить {description}")
    public static void assertEquals(String description, int expected, int actual) {
        org.junit.Assert.assertEquals(description, expected, actual);
    }

    @Step("Проверить {description}")
    public static void assertNotNull(String description, Object actual) {
        org.junit.Assert.assertNotNull(description, actual);
    }
}
