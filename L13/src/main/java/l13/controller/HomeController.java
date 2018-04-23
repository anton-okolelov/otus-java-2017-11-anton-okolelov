package l13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String HomePage(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html; charset=utf-8");
        return "home";
    }
}
