package l12;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(200);
        response.setContentType("text/html");

        HashMap<String, Object> vars = new HashMap<>();
        if (request.getParameter("incorrect") != null) {
            vars.put("error_message", "Credentials are incorrect");
        }
        response.setHeader("Content-Type", "text/html; charset=utf8");
        response.getWriter().println(TemplateProcessor.instance().getPage("login.html", vars));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.equals("admin") && password.equals("12345")) {
            request.getSession().setAttribute("login", login);
        } else {
            response.sendRedirect("/login?incorrect=1");
        }

        response.sendRedirect("/stats");
    }
}
