package framework.annotation;

import java.lang.reflect.Method;

public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = UserController.class; // Ta classe à tester

        // Annotation sur la classe
        if (clazz.isAnnotationPresent(UrlMapping.class)) {
            UrlMapping classAnnotation = clazz.getAnnotation(UrlMapping.class);
            System.out.println("URL de la classe : " + classAnnotation.url());
        }

        // Annotation sur les méthodes
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(UrlMapping.class)) {
                UrlMapping methodAnnotation = method.getAnnotation(UrlMapping.class);
                System.out.println("Méthode " + method.getName() + " → URL : " + methodAnnotation.url());
            }
        }
    }
}
