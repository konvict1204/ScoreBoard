package project.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import project.repository.MatchRepo;
import project.repository.PlayerRepo;
import project.service.MatchService;
import project.util.PoolManager;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MatchRepo matchRepo = new MatchRepo();
        PlayerRepo playerRepo = new PlayerRepo();
        MatchService matchService = new MatchService(matchRepo, playerRepo);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("matchService", matchService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PoolManager.closeSession();
    }
}
