import helper.Book;
import helper.DB;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "BooksServlet", urlPatterns = "/api/findbooks")
public class BooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext=request.getServletContext();
        String user_id=(String) servletContext.getAttribute("usrid");
        try {
           /*List<Book> userBooks = DB.getBooksByUser(user_id);
            for (Book book:userBooks)
            {
                    String bookid=book.get_id();
                System.out.println("Book id is"+bookid);
                    DB.updateKey(bookid);
            }*/

            List<Book> books = DB.getBooks();


            JSONObject jsonResponse = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            //List<String>bookids=new ArrayList<>();
            for (Book book : books) {
                //if (book.getKey()==0) {
                    JSONObject jsonObject = book.toJsonObject();
                   // System.out.println(book.get_id());
                    jsonArray.put(jsonObject);
                }

            //}

            jsonResponse.put("result", jsonArray);

            response.getWriter().write(jsonResponse.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
