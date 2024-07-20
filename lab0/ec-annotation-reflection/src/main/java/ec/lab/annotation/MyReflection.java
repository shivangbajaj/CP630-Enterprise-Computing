package ec.lab.annotation;

import java.lang.reflect.*;

public class MyReflection {
	private String name = "EC";
	private int id = 0;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void print() {
		System.out.println("name:"+this.name +"; id:"+ this.id);
	}
	
	public static void main(String[] args) throws Exception {

		MyReflection obj = new MyReflection();
		System.out.println("Field names of MyReflection class:");
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			System.out.println("  "+ field.getName());
		}
		
		System.out.println("Method names of MyReflection class:");
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method method : methods) {
			System.out.println("  "+ method.getName());
		}
		
		System.out.println("Change field value");
		System.out.println("value of field name before: "+obj.getName());
		Field nameField = obj.getClass().getDeclaredField("name");
		nameField.setAccessible(true);
		nameField.set(obj, "ME");

		System.out.println(nameField.getName());
		Object fieldValue = nameField.get(obj);
		if (fieldValue instanceof String) {
			String stringValue = (String) fieldValue;
			System.out.println("value of field name after: " + stringValue);
		}
		System.out.println("value of field name after: " +obj.getName());
		
		
		System.out.println("reflection of filed id");
		System.out.println("value of field id before: "+obj.getId());
		Field idField = obj.getClass().getDeclaredField("id");
		idField.setAccessible(true);
		idField.setInt(obj, 1);
		System.out.println("value of field id after: "+ obj.getId());
		
		
		System.out.println("reflection of method");		
		Method method = obj.getClass().getDeclaredMethod("print");
		method.invoke(obj);

		
		System.out.println("reflection of the String class");
		Class<?> cls = String.class;
		System.out.println("Class Name: " + cls.getName());

		System.out.println("Field names:");
		fields = cls.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("  "+field.getName());
		}
		
		System.out.println("Method names:");
		methods = cls.getDeclaredMethods();
		System.out.println("Declared Methods:");
		for (Method m : methods) {
			System.out.println("  "+m.getName());
		}
		
		// Create a new instance of the class
		String instance = (String) cls.newInstance();
		// Access and modify a field
		Field field = instance.getClass().getDeclaredField("value");
		field.setAccessible(true);
		System.out.println(field.getType());
		char[] s = {'a', 'b'};
		field.set(instance, s);
		System.out.println("New instance value: " + instance);
		
		// Invoke a method
		method = cls.getDeclaredMethod("toUpperCase");
		String result = (String) method.invoke(instance);
		System.out.println("Method Invocation Result: " + result);
	}
}