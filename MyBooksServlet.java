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
import javax.servlet.http.HttpSession;
import java.util.List;


@WebServlet(name = "MyBooksServlet", urlPatterns = "/api/mybooks")
public class MyBooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user_id=null;
        HttpSession httpSession=request.getSession(false);
        ServletContext servletContext=request.getServletContext();
        String userid=(String) servletContext.getAttribute("usrid");

       // System.out.println(userid);

       // System.out.println(httpSession.toString());
        if (httpSession!=null) {
             user_id = (String) httpSession.getAttribute("user_id");
         //   System.out.println(user_id);
        }
        response.setContentType("application/json");
        // For accepting cross domain requests
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");



        try {
            List<Book> userBooks = DB.getBooksByUser(userid);
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


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
