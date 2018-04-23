package l13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginHome(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        response.setContentType("text/html");

        if (request.getParameter("incorrect") != null) {
            model.addAttribute("error_message", "Credentials are incorrect");
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.equals("admin") && password.equals("12345")) {
            request.getSession().setAttribute("login", login);
            return "redirect:/stats";
        } else {
            return "redirect:/login?incorrect=1";
        }
    }

}
