import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add")
public class ServletToAddInfo extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String brand = req.getParameter("brand");
        String captain = req.getParameter("captain");
        String engine = req.getParameter("engine");
        String series = req.getParameter("series");

        if (requestIsValid(req)){
          //  resp.setStatus (HttpServletResponse.SC_NOT_FOUND);
          //  resp.getOutputStream().print("{\"errorMessage 404\":\"Поля не заповнені\"}");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
           // resp.sendError(400, "error message");
            //resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
          //  resp.getOutputStream().print("{\"errorMessage 404\":\"Поля не заповнені\"}");
           // resp.flushBuffer();
        }

        Plane plane = new Plane(brand, captain, engine, series);


        DataBase dataBase = new DataBase();

        try {
            dataBase.insert(plane);
        } catch (SQLException |ClassNotFoundException e) {
           resp.setStatus (HttpServletResponse.SC_NOT_FOUND);
           return;
          //  resp.sendError(HttpServletResponse.SC_NOT_FOUND);
          //  resp.sendError(400, "error message");
         //   resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
           // resp.getOutputStream().print("{\"errorMessage 404\":\"Поля не заповнені\"}");
           // resp.flushBuffer();
        }
        resp.sendRedirect("/Servlet_war_exploded/index1.jsp");

    }

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    private boolean requestIsValid(final HttpServletRequest req) {
        String brand = req.getParameter("brand");
        String captain = req.getParameter("captain");
        String engine = req.getParameter("engine");
        String series = req.getParameter("series");
        return brand != null || brand.length() > 0 ||
                captain != null || captain.length() > 0 ||
                engine != null || engine.length() > 0 ||
                series != null || series.length() > 0 ;
    }


}
