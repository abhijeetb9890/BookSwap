import helper.Book;
import helper.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RemoveBookServlet", urlPatterns = "/api/removebook")
public class RemoveBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter(Book.ID);

        if (id == null || id.equals("")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The book does not exist");
            return;
        }

        try {
            boolean r = DB.removeBook(id);
            System.out.println(r);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
