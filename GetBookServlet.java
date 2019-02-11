import helper.Book;
import helper.DB;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "GetBookServlet",urlPatterns = "/api/getbook")
public class GetBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        // For accepting cross domain requests
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        //System.out.println("going to post");
        String id=request.getParameter("_id");
        if(id==null || id.equals("")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The book doesnt exist");
            return;
        }
        //System.out.println(id);
        ServletContext servletContext=request.getServletContext();
        servletContext.setAttribute("bookid",id);

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext=request.getServletContext();
        String bookid=(String) servletContext.getAttribute("bookid");
      //  System.out.println("in get book id is " +bookid);
        try {
            Book book= DB.getBookById(bookid);
            //System.out.println(book.getName());
            response.getWriter().write(book.toJsonObject().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
