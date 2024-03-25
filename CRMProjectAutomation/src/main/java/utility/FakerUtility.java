package utility;

import com.github.javafaker.Faker;

public class FakerUtility {

	public static String phoneNumber() {
		Faker faker = new Faker();
		return faker.numerify("##########");
	}

	public static String emailID() {
		Faker faker = new Faker();
		return faker.bothify("????###@gmail.com");
	}

	public static String firstName() {
		Faker faker = new Faker();
		return faker.name().firstName();
	}

	public static String lastName() {
		Faker faker = new Faker();
		return faker.name().lastName();
	}
}
