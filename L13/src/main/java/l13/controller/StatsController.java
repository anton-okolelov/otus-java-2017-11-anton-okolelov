package l13.controller;

import net.sf.ehcache.Cache;
import net.sf.ehcache.statistics.StatisticsGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StatsController {

    private Cache cache;

    public StatsController(Cache cache) {
        this.cache = cache;
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String statsHome(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("login") == null) {
            return "redirect:/login";
        }
        response.addHeader("Content-Type", "text/html; charset=utf-8");
        StatisticsGateway stats = cache.getStatistics();
        model.addAttribute("hits", stats.cacheHitCount());
        model.addAttribute("evictions", stats.cacheEvictedCount());
        model.addAttribute("misses", stats.cacheMissCount());
        return "stats";
    }
}
