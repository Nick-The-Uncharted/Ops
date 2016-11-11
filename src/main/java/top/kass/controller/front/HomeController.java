package top.kass.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value="/project/{id}", method= RequestMethod.GET)
    public ModelAndView project(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("project");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

}
