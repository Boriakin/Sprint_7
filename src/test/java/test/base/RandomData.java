package test.base;

import java.util.Random;

public class RandomData {

    public static String getRandomLogin() {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static String getRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@!&()=$#";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static String getRandomFirstName() {
        return getRandomLogin();
    }
}
