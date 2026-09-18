package project.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.dto.MatchDto;
import project.service.MatchService;
import project.util.ParserUtil;
import java.io.IOException;

import static project.util.ParserUtil.*;

@WebServlet(value = "/matches/*", name = "PointServlet")
public class PointServlet extends HttpServlet {
    MatchService matchService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        this.matchService = (MatchService) config.getServletContext().getAttribute("matchService");

        if(matchService == null) {
            throw new IllegalStateException();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = parsePathToUIID(req.getPathInfo());
        String playerName = req.getParameter("name");

        MatchDto match = matchService.pointWinsBy(uuid, playerName);
        String winnerName = match.winnerName();
        if(winnerName != null) {
            req.setAttribute("winnerName", winnerName);
            req.getRequestDispatcher(ParserUtil.getPathToJsp("win")).forward(req, resp);
        } else {
            req.setAttribute("uuid", uuid);
            req.setAttribute("match", match);
            req.getRequestDispatcher(ParserUtil.getPathToJsp("match")).forward(req, resp);
        }


    }

}
