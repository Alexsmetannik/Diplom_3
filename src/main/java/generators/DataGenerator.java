package generators;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class DataGenerator {
    public static Faker faker = new Faker();

    public static String getNewEmail(){
        String newEmail = faker.internet().emailAddress();
        return newEmail;
    }

    public static String getNewPassword(){
        String newPassword = RandomStringUtils.randomAlphabetic(12);
        return newPassword;
    }

    public static String getNewName(){
        String newName = faker.name().firstName();
        return newName;
    }
}
