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

/**
 * Created by hp on 28-09-2016.
 */
@WebServlet(name = "RequestListServlet",urlPatterns = "/api/ownerbooks")
public class RequestListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ServletContext servletContext1=request.getServletContext();
        //String ownerid=(String)servletContext1.getAttribute("owner");

        String id=request.getParameter("_id");
        if(id==null || id.equals(""))
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"The user doesnt exist");
            return;
        }
        System.out.println("At post user id is"+id);
        ServletContext servletContext=request.getServletContext();
        servletContext.setAttribute("ownerid",id);
      //  System.out.println("The owner id is "+ownerid);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       ServletContext servletContext=request.getServletContext();
        String ownerid=(String)servletContext.getAttribute("ownerid");

        System.out.println("Received owner id "+ownerid);
        try {
            List<Book> userBooks = DB.getBooksByUser(ownerid);
            JSONObject jsonResponse = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            for (Book book : userBooks) {

                JSONObject jsonObject = book.toJsonObject();
                jsonArray.put(jsonObject);

            }


            jsonResponse.put("result", jsonArray);
            response.getWriter().write(jsonResponse.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
