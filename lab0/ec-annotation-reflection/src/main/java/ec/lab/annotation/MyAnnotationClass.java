package ec.lab.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * Applying annotations in class definition
 */
@MyAnnotation2
class MyAnnotationClass {
	
	@MyFieldAnnotation
	int id = 1234;
	
	@MyFieldAnnotation("float")
	float score = 1234;
	
	@MyAnnotationSingleValue(value=10)
	public void test1() {
		System.out.println("Method annotated by a single value annotation");
	}
	
	@MyAnnotationMultiValue(value1 = 10, value2="A", value3 = "B")
	public void test2() {
		System.out.println("Method annoted by a multiple value anotation");
	}
	
	// Get annotation values at runtime by reflection
    @MyAnnotation2  // annotated by default values
    public void test3() throws Exception {
        // Use reflection to get the method
        Method method = this.getClass().getMethod("test3");
        MyAnnotation2 annotation = (MyAnnotation2) method.getAnnotation(MyAnnotation2.class);
        print(annotation);
    }
    
    @MyAnnotation2(key="cp630", value="EC") // Annotated by new vakyes
    public void test4() throws Exception {
        //Method method = MyAnnotationClass.class.getMethod("test4", null);
        Method method = this.getClass().getMethod("test4");
        MyAnnotation2 annotation = (MyAnnotation2) method.getAnnotation(MyAnnotation2.class);
        print(annotation);
    }

    public void print(MyAnnotation2 annotation) {
        System.out.println(annotation.key() + " : " + annotation.value());
    }
    
	@Deprecated
	public void sayDeprecated() {
		System.out.println("Deprecated annotation");
	}
	
	public static void main(String args[]) throws Exception {
		MyAnnotationClass obj1 = new MyAnnotationClass();
		obj1.test1();
		
		MyAnnotation2 anno2 = obj1.getClass().getAnnotation(MyAnnotation2.class);
		System.out.println("Class annotaiton: "+anno2.value());			

		Field idField = obj1.getClass().getDeclaredField("id");
		MyFieldAnnotation typeanno = idField.getAnnotation(MyFieldAnnotation.class);
		System.out.println("Get field annotaton value: " + typeanno.value());

		Field scoreField = obj1.getClass().getDeclaredField("score");
		typeanno = scoreField.getAnnotation(MyFieldAnnotation.class);
		System.out.println("Get field annotaton value: " + typeanno.value());

		Method m = obj1.getClass().getMethod("test1");
		MyAnnotationSingleValue manno = m.getAnnotation(MyAnnotationSingleValue.class);
		System.out.println("Get method annotation value: " + manno.value());
		;

		
		
		System.out.println("Get method annotation values: ");
		obj1.test2();
		Method m1 = obj1.getClass().getMethod("test2");
		MyAnnotationMultiValue manno1 = m1.getAnnotation(MyAnnotationMultiValue.class);
		System.out.println("value1 is: " + manno1.value1());
		System.out.println("value2 is: " + manno1.value2());
		System.out.println("value3 is: " + manno1.value3());

		obj1.sayDeprecated();
		
        try {
            obj1.test3();
            obj1.test4();
        } catch( Exception e ) {
            System.err.println("Exception [" + e.getClass().getName() + "] - " + e.getMessage());
            e.printStackTrace(System.err);
        }	
	}
}


