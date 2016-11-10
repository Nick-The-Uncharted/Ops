package top.kass.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.kass.service.RiskService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/api/risk")
public class RiskController {

    @Autowired
    private RiskService riskService;

    // 参数：flag（所有、我创建的、我跟踪的）
    @RequestMapping(value="/getRisksByPid", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRisksByPid(@RequestParam int id, @RequestParam int flag,
         HttpSession session) {
        return riskService.getRisksByPid(id, flag, (int)session.getAttribute("id"));
    }

    @RequestMapping(value="/getById", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getById(@RequestParam int id, HttpSession session) {
        return riskService.getById(id, (int)session.getAttribute("id"));
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestBody Map reqMap, HttpSession session) {
        return riskService.add(reqMap, (int)session.getAttribute("id"));
    }

    @RequestMapping(value="/modify", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modify(@RequestBody Map reqMap, HttpSession session) {
        return riskService.modify(reqMap, (int)session.getAttribute("id"));
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam int id, HttpSession session) {
        return riskService.delete(id, (int)session.getAttribute("id"));
    }

}
