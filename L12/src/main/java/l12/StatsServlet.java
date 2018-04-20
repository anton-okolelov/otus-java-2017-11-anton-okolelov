package l12;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.statistics.StatisticsGateway;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class StatsServlet extends HttpServlet {

    private final Cache cache;

    public StatsServlet(Cache cache) {
        this.cache = cache;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute("login") == null) {
                response.sendRedirect("/login");
                return;
            }

            response.setHeader("Content-Type", "text/html; charset=utf8");
            HashMap<String, Object> vars = new HashMap<>();

            StatisticsGateway stats = cache.getStatistics();
            vars.put("hits", stats.cacheHitCount());
            vars.put("evictions", stats.cacheEvictedCount());
            vars.put("misses", stats.cacheMissCount());
            response.getWriter().println(TemplateProcessor.instance().getPage("stats.html", vars));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
