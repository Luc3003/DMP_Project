package servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/role/patient")
public class ServletSite extends GenericServlet {

    String role;
    String user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        user = (String) req.getSession().getAttribute("user");
        role = (String) req.getSession().getAttribute("role");
        System.out.println(user+role);
        req.getSession().setAttribute("user",user);
        req.getSession().setAttribute("role",role);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("patient", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("role",role);
        req.getSession().setAttribute("user",user);
    }
}
