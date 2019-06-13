import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/show")
public class ServletToShowInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase dataBase = new DataBase();

        ArrayList<Plane> l = null;

        try {
            l = dataBase.selectAll();
        } catch (SQLException |ClassNotFoundException e) {
            resp.sendError(400, "error message");
        }

        PrintWriter pw = resp.getWriter();
        if (l == null)
            pw.println("<h1 align='center'>TYT NI4OrO NEMA</h1>");
        else {
            for (Plane c : l) {
                pw.println(c);
            }
        }

        pw.close();
    }
}

