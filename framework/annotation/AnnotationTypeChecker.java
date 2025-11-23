package framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTypeChecker {
    
    public static void checkAnnotationReturnTypes(Class<?>... annotationClasses) {
        for (Class<?> annotationClass : annotationClasses) {
            System.out.println("\nChecking annotation: " + annotationClass.getSimpleName());
            
            if (!annotationClass.isAnnotation()) {
                System.out.println("  - Not an annotation type!");
                continue;
            }
            
            Method[] methods = annotationClass.getDeclaredMethods();
            for (Method method : methods) {
                Class<?> returnType = method.getReturnType();
                String typeName = returnType.getSimpleName();
                
                System.out.print("  - " + method.getName() + "() returns: " + typeName);
                
                if (returnType == String.class) {
                    System.out.println(" (String)");
                } else if (returnType == int.class || returnType == Integer.class) {
                    System.out.println(" - ERROR: INTEGER not allowed");
                } else {
                    System.out.println();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // Example usage
        checkAnnotationReturnTypes(UrlMapping.class);
    }
}
