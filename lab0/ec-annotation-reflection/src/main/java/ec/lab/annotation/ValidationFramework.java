package ec.lab.annotation;

import java.lang.reflect.*;


public class ValidationFramework {
	public static String savedEmail = "admin@ec.com";
	public static String savedPassword = "admin";

	public static void validate(Object obj) throws Exception  {
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(MyFieldAnnotation.class)) {
				MyFieldAnnotation annotation = field.getAnnotation(MyFieldAnnotation.class);
				String validationRule = annotation.value();
				String fieldValue = getFieldValue(obj, field);
				if ("email".equals(validationRule)) {
					if (!isValidEmail(fieldValue)) {
						throw new Exception("Invalid email address");
					}
				} else if ("password".equals(validationRule)) {
					if (!isValidPassword(fieldValue)) {
						throw new Exception("Invalid password");
					}
				}
			}
		}
	}

	private static String getFieldValue(Object obj, Field field) {
		String name1 = field.getAnnotation(MyFieldAnnotation.class).value();
		String name2 = "";
		Field privateField;
		try {
			privateField = obj.getClass().getDeclaredField(name1);
			privateField.setAccessible(true);
			name2 = (String) privateField.get(obj);

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return name2;
	}

	private static boolean isValidEmail(String email) {
		return email.equals(savedEmail);
	}

	private static boolean isValidPassword(String password) {
		return password.equals(savedPassword);
	}

	public static void main(String[] args) {

		// test1
		RegistrationForm form = new RegistrationForm();
		form.setEmail("admin@ec.com");
		form.setPassword("admin");
		try {
			ValidationFramework.validate(form);
			System.out.println("Validation successed");
		} catch (Exception e) {
			System.out.println("Validation error: " + e.getMessage());
		}

		// test2
		form.setEmail("guest@ec.com");
		form.setPassword("guest");
		try {
			ValidationFramework.validate(form);
			System.out.println("Validation successed");
		} catch (Exception e) {
			System.out.println("Validation error: " + e.getMessage());
		}

		// test3
		form.setEmail("admin@ec.com");
		form.setPassword("password");
		try {
			ValidationFramework.validate(form);
			System.out.println("Validation successed");
		} catch (Exception e) {
			System.out.println("Validation error: " + e.getMessage());
		}
	}
}
