# Annotations and Reflection
Author: HBF  
Date: 2023-09-07 
Abstract: This project gives examples of Java reflection and annotations

## Concepts of Java Reflection


### What is Java reflection

ChatGPT: What is Java reflection?

Java reflection is a feature that allows you to inspect and manipulate the structure, behavior, and metadata of classes, objects, methods, fields, and other program elements at runtime. It provides a way to access and work with the internal details of Java classes and objects, even if you don't have access to their source code at compile time.

Reflection is primarily accomplished using classes from the java.lang.reflect package, and it enables you to do the following at runtime:

1. **Inspect Class Information:** You can retrieve information about classes, such as their names, modifiers (public, private, etc.), superclasses, implemented interfaces, constructors, methods, and fields.
2. **Create Objects Dynamically:** You can create new instances of classes, including classes that you discover at runtime.
3. **Access and Modify Fields:** You can read or modify the values of fields, even private ones, within objects.
4. **Invoke Methods Dynamically:** You can invoke methods on objects, including private and inaccessible methods, by name.
5. **Access Annotations:** You can retrieve annotations applied to classes, methods, fields, and other program elements.

Reflection is a powerful but advanced feature of Java and is often used in situations where you need to build flexible and extensible code, frameworks, or tools. 


One application of Java reflection is to access Java annotations at runtime. 

### Hand-on reflection

1. Read MyReflection.java as Java to see how to access and manipulate object, classes, fields, methods at runtime.  
2. Run MyReflection.java;  right click -> Run As -> Java Application. 

Check and run MyReflection.java as Java 

Next we will say how reflection is used to access annotation values. 


## Concepts of Java Annotation

### What is Java Annotation

ChatGPT: What is Java annotation?

In Java, an annotation is a form of metadata that can be added to Java source code elements, such as classes, methods, fields, and other program elements, to provide additional information or instructions to the compiler, development tools, or runtime environment. Annotations do not directly affect the functionality of the code but serve as markers or tags that convey information about the code to various tools and frameworks.

Annotations serve various purposes in Java, including:

1. Documentation: Annotations can be used to generate documentation or other types of reports for the code.
2. Code analysis: Annotations help tools and frameworks analyze the code and enforce specific rules or conventions.
3. Runtime behavior: Some annotations can affect the behavior of code at runtime, such as annotations used in Java EE (Enterprise Edition) for configuring servlets or EJBs.
4. Code generation: Annotations are often used in conjunction with code generation tools, such as those in frameworks like Spring or Hibernate, to generate code or configurations automatically.
5. Customization: Developers can create custom annotations to add their own metadata and behaviors to their code, making it more expressive and tailored to their needs.


Annotations are typically defined using the @ symbol followed by an annotation type. They can also include elements with values that you can specify when using the annotation. For example, the annotation @Deprecated in the following 
 is used to indicate that the OldClass is deprecated and should not be used anymore. Many IDEs and development tools will generate warnings or errors when you try to use deprecated code. But the code can still run. 

~~~
@Deprecated
public class OldClass {
    // ...
}
~~~

Java includes several built-in annotations, such as @Override, @SuppressWarnings, and @Deprecated, which have specific meanings and are used for various purposes within the language and its standard libraries. Additionally, many third-party libraries and frameworks define their own custom annotations to provide additional functionality and configuration options.


In summary, annotations provide additional data about a program that is not part of the program itself. Annotations have no direct effect on the operation of the code they annotate. 

Annotation data can be retrieved by or target for:

- compiler, namely, used by the compiler to detect errors or suppress warnings. 
- Compile-time and deployment-time processing — Software tools can process annotation information to generate code, XML files, and so forth.
- Runtime processing — Some annotations are available to be examined at runtime.


### Classifications of annotations

**Build-in annotations**

Java provide some build-in annotations, e.g.,  @Override, @SuppressWarnings, @Deprecated. 

Some build-in annotations are used other annotation: @Target, @Retentio, @Inherited, @Documented. 


**Custom annotations**

Custom annotations are user-defined annotations. The @interface element is used to define custom annotations. 


**Annotations with methods to carry data**

1. Marker Annotation: has no method, e.g. @interface MyAnnotation{}   
2. Single-Value Annotation: has one method, e.g.,  @interface MyAnnotation{int value();}  
3. Multi-Value Annotation: has more than one method, e.g., 
@interface MyAnnotation{int value1() default 1; String value2() default ""; } 


### Using multiple annotations 

The @Target tag is used to specify at which type, the annotation is used. For example, the following custom annoatation MyAnnotation can be applied to class, interface, or enumeration. 

~~~
@Target(ElementType.TYPE)  
@interface MyAnnotation{  
int value1();  
String value2();  
}  
~~~

The ElementType can be TYPE, FIELD, METHOD, .... . @Target is not used, it the annotation can be an eligiable element. 


Note that annotation can only apply to methods that do not have any throws clauses,  return one of the following: primitive data types, String, Class, enum or array of these data types, not have any parameter.


@Retention annotation is used to specify to what level annotation will be available. For example, the following annotation data can be retained at runtime, can be applied to class, field variables, methods.  

~~~
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})  
@interface MyAnnotation{  
int value1();  
String value2();  
}  
~~~


### Applications of Java annotations

ChatGPT: What are the application of Java annotation?

Java annotations have a wide range of applications in software development, as they provide a mechanism to attach metadata and additional information to code elements. Here are some common applications of Java annotations:

1. **Code Documentation:** Annotations can be used to generate documentation or documentation-like comments automatically. For example, the @Deprecated annotation indicates that a class, method, or field should no longer be used, and this information can be included in generated documentation.
2. **Code Organization:** Annotations can help organize and categorize code. For instance, the @SuppressWarnings annotation can be used to suppress compiler warnings for specific code elements, making it easier to manage code quality.
3. **Code Analysis and Validation:** Many static analysis tools and code quality checkers use annotations to detect potential issues in code. Developers can create custom annotations to add rules and constraints to their code, and these tools can then analyze the code based on these annotations.
4. **Dependency Injection:** In frameworks like Spring, annotations are used to configure and define dependencies between components. For example, the @Autowired annotation is used to inject dependencies automatically into a class.
5. **Aspect-Oriented Programming (AOP):** AOP frameworks like AspectJ use annotations to specify cross-cutting concerns, such as logging, security, and transaction management, and then apply these concerns to various parts of the application.
6. **Persistence Mapping:** In Java Persistence API (JPA) and Hibernate, annotations like @Entity, @Table, and @Column are used to map Java objects to database tables and define the database schema.
7. **Servlet Configuration:** In Java Servlets, annotations like @WebServlet are used to configure servlets, making it easier to define URL mappings and other settings.
8. **JUnit Testing:** Annotations like @Test in JUnit are used to mark methods as test cases, allowing the testing framework to identify and execute them as part of test suites.
9. **Custom Metadata:** Developers can create custom annotations to add their own metadata to code elements, enabling custom behaviors or configurations. For example, you can create an annotation to specify additional validation rules for form fields in a web application.
10. **Frameworks and Tools:** Many libraries and frameworks use annotations to simplify configuration and customization. Examples include Spring Boot, Java EE (Jakarta EE), and Android development, which heavily rely on annotations to simplify development.
11. **ORM (Object-Relational Mapping):** In addition to JPA and Hibernate, various other ORM frameworks use annotations to map Java objects to database entities and define relationships.
12. **Security:** Annotations can be used to specify security-related information, such as role-based access control (RBAC) or permissions, to restrict access to certain methods or resources.
13. **Logging and Profiling:** Some logging and profiling frameworks use annotations to mark methods for logging or profiling purposes.


We will see and practice most of these applications in lab and assignment tasks. 


### Hand-on custom annotations. 


1. Read MyAnnotaiton.java for examples of defining custom annotations.
2. Read and run MyAnnotaitonClass.java for examples of using custom annotations in class definition.
3. Read RegistrationForm.java for an application of custom annotations.
4. Read and run ValidationFramework.java for using the RegistrationForm.java in validation. 
5. Run the following Maven command under the project root folder. 

```
mvn clean package
java -jar target/ec-annotation.jar
```


