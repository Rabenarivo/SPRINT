package framework.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URL;

public class FrontServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String urlPath = req.getRequestURI();
        String contextPath = req.getContextPath();
        String relativePath = urlPath.substring(contextPath.length());

        System.out.println("[DEBUG] FrontServlet - URL: " + urlPath);
        System.out.println("[DEBUG] FrontServlet - Context: " + contextPath);
        System.out.println("[DEBUG] FrontServlet - Relative: " + relativePath);

        ServletContext context = getServletContext();
        
        // Gestion spéciale pour index.jsp
        if (relativePath.equals("/") || relativePath.equals("/index.jsp")) {
            RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        URL resource = context.getResource(relativePath);
        System.out.println("[DEBUG] FrontServlet - Resource: " + resource);

        if (resource != null) {
            // ✅ Si la ressource existe (JSP, HTML, image, etc.)
            // On laisse Tomcat la gérer
            RequestDispatcher dispatcher = context.getRequestDispatcher(relativePath);
            dispatcher.forward(req, resp);
        } else {
            // ❌ Redirection vers la page 404 personnalisée
            req.setAttribute("urlPath", urlPath);
            RequestDispatcher dispatcher = context.getRequestDispatcher("/404.jsp");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            dispatcher.forward(req, resp);
        }
    }
}
