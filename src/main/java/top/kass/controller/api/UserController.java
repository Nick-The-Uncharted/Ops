package top.kass.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kass.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/getMyInfo", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMyInfo(HttpSession session) {
        return userService.getMyInfo((int)session.getAttribute("id"));
    }

    @RequestMapping(value="/password", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> password(@RequestBody Map reqMap, HttpSession session) {
        return userService.password(reqMap, (int)session.getAttribute("id"));
    }

    @RequestMapping(value="/getAllUsers", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllUsers() {
        return userService.getAllUsers();
    }

}
