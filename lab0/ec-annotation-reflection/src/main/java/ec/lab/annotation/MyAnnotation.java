package ec.lab.annotation;

import java.lang.annotation.*;

/*
 * Define annotations
 */

// Annotation targeting for field
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyFieldAnnotation{
	String value() default "int";
}

//Single value annotation targeting for method
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotationSingleValue {
	int value();
}

//Multiple value annotation targeting for method
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotationMultiValue {
	int value1() default 1;  
	String value2() default "";  
	String value3() default "C";  
}

//Multiple value annotation targeting for all element types.
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2 {
    String key() default "default key";
    String value() default "default value";
}
