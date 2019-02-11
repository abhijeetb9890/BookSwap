import helper.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hp on 08-09-2016.
 */
@WebServlet(name = "UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String category=request.getParameter(Book.CATEGORY);




    }

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
}
