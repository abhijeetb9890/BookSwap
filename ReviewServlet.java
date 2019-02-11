import helper.Book;
import helper.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hp on 05-10-2016.
 */
@WebServlet(name = "ReviewServlet",urlPatterns = "/api/review")
public class ReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String review=request.getParameter("review");

        String id=request.getParameter("_id");
        if (id==null || id.equals(""))
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"The book doesn't exist");
            return;
        }


        Book book=DB.getBookById(id);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
