package test.base;

import com.github.javafaker.Faker;

public class FakeData {

    public static String getFakeLogin() {
        Faker faker = new Faker();
        return faker.name().username();
    }

    public static String getFakePassword() {
        Faker faker = new Faker();
        return faker.internet().password();
    }

    public static String getFakeFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

}
