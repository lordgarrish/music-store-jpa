package lordgarrish.servlet;

import lordgarrish.business.*;
import lordgarrish.dao.*;
import lordgarrish.data.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/catalog", "/news"})
public class StartPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String url;

        if(requestURI.endsWith("catalog")) {
            if(req.getSession().getAttribute("albums") == null) {
                try {
                    DBUtil.createTestCatalog(); //Creates test catalog of albums for testing
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            url = showCatalog(req);
        }
        else if(requestURI.endsWith("news")){
            url = "/404.jsp"; //News page in development
        } else {
            url = "/404.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    //Shows store catalog on '/catalog' page
    private String showCatalog(HttpServletRequest req) throws ServletException {
        String url = "/catalog.jsp";

        AbstractDao<MusicAlbum, String> albumDao = new MusicAlbumDao();
        List<MusicAlbum> albumsList;
        try {
            albumsList = albumDao.getAll();
        } catch (Exception e) {
            System.err.println("Can't get albums from DB");
            e.printStackTrace();
            throw new ServletException(e);
        }

        HttpSession session = req.getSession();
        session.setAttribute("albums", albumsList);

        return url;
    }
}
