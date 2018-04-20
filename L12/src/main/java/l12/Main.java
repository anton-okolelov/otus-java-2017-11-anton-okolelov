package l12;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    private final static int PORT = 8000;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {

        CacheManager cacheManager = CacheManager.getInstance();
        cacheManager.addCache("cache1");
        Cache cache = cacheManager.getCache("cache1");

        cache.put(new Element("1", "Jan"));
        cache.put(new Element("2", "Feb"));
        cache.put(new Element("3", "Mar"));

        cache.get("1");
        cache.get("2");
        cache.get("5"); //miss

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new StatsServlet(cache)), "/stats");
        context.addServlet(LoginServlet.class, "/login");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }


}