package testdata;

import com.github.javafaker.Faker;

public class TestData {
    private static Faker faker = new Faker();

    public static String getTestUserEmail(){
        return "user_" + System.currentTimeMillis() + "@test.ru";
    }

    public static String getTestUserPassword(){
        return getTestUserPassword(8);
    }

    public static String getTestUserPassword(int charCount){
        return faker.regexify(String.format("[a-z]{%d}", charCount));
    }

    public static String getTestUserName(){
        return faker.name().firstName();
    }
}
