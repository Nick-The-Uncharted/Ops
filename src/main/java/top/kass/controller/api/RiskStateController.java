package top.kass.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.kass.service.RiskStateService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/api/riskState")
public class RiskStateController {

    @Autowired
    private RiskStateService riskStateService;

    @RequestMapping(value="/getRiskStatesByRid", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRiskStatesByRid(@RequestParam int id, HttpSession session) {
        return riskStateService.getRiskStatesByRid(id, (int)session.getAttribute("id"));
    }

    @RequestMapping(value="/getById", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getById(@RequestParam int id, HttpSession session) {
        return riskStateService.getById(id, (int)session.getAttribute("id"));
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestBody Map reqMap, HttpSession session) {
        return riskStateService.add(reqMap, (int)session.getAttribute("id"));
    }

    @RequestMapping(value="/modify", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modify(@RequestBody Map reqMap, HttpSession session) {
        return riskStateService.modify(reqMap, (int)session.getAttribute("id"));
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam int id, HttpSession session) {
        return riskStateService.delete(id, (int)session.getAttribute("id"));
    }

}
