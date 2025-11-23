package framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RouteInfoExtractor {
    
    /**
     * Extracts route information from all classes in the specified package
     * that are annotated with @UrlMapping
     */
    public static List<RouteInfo> extractRouteInfo(String packageName) throws Exception {
        List<RouteInfo> routes = new ArrayList<>();
        
        // Get all classes in the package (simplified example - in real implementation
        // you would need to scan the classpath for classes in the package)
        
        // For demonstration, we'll use the ProductController and UserController
        Class<?>[] controllers = {
            Class.forName("framework.annotation.ProductController"),
            Class.forName("framework.annotation.UserController")
        };
        
        for (Class<?> controllerClass : controllers) {
            // Check for class-level @UrlMapping
            if (controllerClass.isAnnotationPresent(UrlMapping.class)) {
                UrlMapping classMapping = controllerClass.getAnnotation(UrlMapping.class);
                String baseUrl = classMapping.url();
                
                // Process methods with @UrlMapping
                for (Method method : controllerClass.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(UrlMapping.class)) {
                        UrlMapping methodMapping = method.getAnnotation(UrlMapping.class);
                        String fullUrl = baseUrl + methodMapping.url();
                        String httpMethod = getHttpMethod(method);
                        
                        // Get return type of the method
                        String returnType = method.getReturnType().getSimpleName();
                        
                        RouteInfo routeInfo = new RouteInfo(
                            controllerClass.getSimpleName(),
                            method.getName(),
                            fullUrl,
                            httpMethod,
                            returnType
                        );
                        
                        routes.add(routeInfo);
                    }
                }
            }
        }
        
        return routes;
    }
    
    /**
     * Determines the HTTP method based on method name conventions
     */
    private static String getHttpMethod(Method method) {
        String methodName = method.getName().toLowerCase();
        
        if (methodName.startsWith("get")) return "GET";
        if (methodName.startsWith("post")) return "POST";
        if (methodName.startsWith("put")) return "PUT";
        if (methodName.startsWith("delete")) return "DELETE";
        
        return "GET"; // Default to GET
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("Extracting route information...");
            List<RouteInfo> routes = extractRouteInfo("framework.annotation");
            
            System.out.println("\nFound " + routes.size() + " routes:");
            for (RouteInfo route : routes) {
                System.out.println(route);
            }
            
            System.out.println("\nChecking annotation return types:");
            AnnotationTypeChecker.checkAnnotationReturnTypes(UrlMapping.class);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
