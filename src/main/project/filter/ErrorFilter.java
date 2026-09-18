package project.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.exceptions.MatchDoesntExist;
import project.exceptions.ValidationException;
import project.util.ParserUtil;
import java.io.IOException;


@WebFilter("/*")
public class ErrorFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        try {
            chain.doFilter(request, response);
        } catch (ValidationException e) {
            sendError(req,resp,HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
        } catch (MatchDoesntExist e){
            sendError(req,resp,HttpServletResponse.SC_NOT_FOUND,e.getMessage());
        } catch (Exception e){
            sendError(req,resp,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    private void sendError(HttpServletRequest req, HttpServletResponse resp,  int errorCode, String message) throws IOException, ServletException {
        resp.setStatus(errorCode);
        req.setAttribute("error", message);
        req.getRequestDispatcher(ParserUtil.getPathToJsp("errorPage")).forward(req, resp);
    }
}
