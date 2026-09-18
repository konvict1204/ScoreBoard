package project.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.dto.MatchesResponseDto;
import project.exceptions.ValidationException;
import project.service.MatchService;
import project.util.ParserUtil;
import java.io.IOException;

@WebServlet(value = "/matches", name = "MatchServlet")
public class MatchServlet extends  HttpServlet {
    MatchService matchService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        this.matchService = (MatchService) config.getServletContext()
                .getAttribute("matchService");

        if(matchService == null) {
            throw new IllegalStateException();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstPlayerName = req.getParameter("firstPlayerName");
        String secondPlayerName = req.getParameter("secondPlayerName");

        if(firstPlayerName == null || secondPlayerName == null ||
                firstPlayerName.isEmpty() || secondPlayerName.isEmpty()
        || firstPlayerName.equals(secondPlayerName)) {
            throw new ValidationException("Ошибка валидации");
        }

        String uuid = matchService.startMatch(firstPlayerName, secondPlayerName);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        req.setAttribute("uuid", uuid);
        req.setAttribute("match", matchService.getMatch(uuid));
        req.getRequestDispatcher(ParserUtil.getPathToJsp("match")).forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNumber = req.getParameter("pageNumber");
        MatchesResponseDto matches;
        matches = pageNumber == null
                ? matchService.getMatches(1)
                : matchService.getMatches(Integer.parseInt(pageNumber));

        req.setAttribute("matches", matches);

        req.getRequestDispatcher(ParserUtil.getPathToJsp("matches")).forward(req, resp);

    }
}
