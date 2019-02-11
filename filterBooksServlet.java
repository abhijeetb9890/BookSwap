import helper.Book;
import helper.DB;
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
import java.util.List;


@WebServlet(name = "filterBooksServlet",urlPatterns = "/api/filter")
public class filterBooksServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String category=request.getParameter(Book.CATEGORY);
        System.out.println(category);
        ServletContext servletContext=request.getServletContext();
        servletContext.setAttribute("category",category);
        if(category==null || category.equals(""))
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"bad request");
            return;
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext=request.getServletContext();
        String category=(String) servletContext.getAttribute("category");

        //String category=request.getParameter("category");
        //if (category==null || category.equals(""))
        //{
          //  response.sendError(HttpServletResponse.SC_BAD_REQUEST,"No books in such category");
           // return;
        //}
        //System.out.println(category);


        try {

            List<Book> books = DB.getBooksByCategory(category);
            JSONObject jsonResponse = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            for (Book book : books) {
                JSONObject jsonObject= book.toJsonObject();
                jsonArray.put(jsonObject);

            }

            jsonResponse.put("result",jsonArray);
            System.out.println(jsonResponse);
            response.getWriter().write(jsonResponse.toString());
        }

        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
