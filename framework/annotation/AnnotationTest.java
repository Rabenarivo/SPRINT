package framework.annotation;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        String packageName = "framework.annotation";

        List<Class<?>> classes = getClasses(packageName);

        System.out.println("=== Classes annotées avec @UrlMapping ===");
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(UrlMapping.class)) {
                UrlMapping classAnnotation = clazz.getAnnotation(UrlMapping.class);
                System.out.println("\nClasse : " + clazz.getName() + " → URL : " + classAnnotation.url());
            }

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(UrlMapping.class)) {
                    UrlMapping methodAnnotation = method.getAnnotation(UrlMapping.class);
                    System.out.println("  Méthode : " + method.getName() + " → URL : " + methodAnnotation.url());
                }
            }
        }
    }

    private static List<Class<?>> getClasses(String packageName) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("Package non trouvé : " + packageName);
        }

        File directory = new File(resource.getFile());
        if (!directory.exists()) {
            throw new IllegalArgumentException("Dossier inexistant : " + directory);
        }

        for (String file : directory.list()) {
            if (file.endsWith(".class")) {
                String className = packageName + '.' + file.substring(0, file.length() - 6);
                classes.add(Class.forName(className));
            }
        }

        return classes;
    }
}
