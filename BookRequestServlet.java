import helper.Book;
import helper.DB;
import helper.User;
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
import java.util.List;


@WebServlet(name = "BookRequestServlet",urlPatterns = "/api/bookrequest")
public class BookRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        String userid = (String) servletContext.getAttribute("usrid");

        Document document = DB.getBookByowner(userid);
        String BookName = document.getString("BookName");
        String bookId = document.getString("bookid");
        ServletContext servletContext1 = request.getServletContext();
        servletContext1.setAttribute("bookidrequest", bookId);

        //String userid1=(String)document.get("userid");
        String requestid = document.getString("userid");
        System.out.println(requestid);
        //ServletContext servletContext1=request.getServletContext();
        //servletContext1.setAttribute("owner",requestid);
        // System.out.println("Request is"+requestid);

        JSONArray jsonArray2 = new JSONArray();
        List<Book> books = DB.getBooksByUser(requestid);
        try {
            for (Book book : books) {
                System.out.println("book is" + book.toDocument());
                JSONObject jsonObject = new JSONObject();

                jsonObject = book.toJsonObject();
                jsonArray2.put(jsonObject);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }


        //System.out.println(jsonArray2);

        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject1=new JSONObject();
        try {
            jsonObject.put("BookName",BookName);
            jsonObject.put("_id",requestid);
            jsonObject.put("bookid",bookId);
            System.out.println("The json format is"+jsonObject.toString());
            jsonArray.put(jsonObject);
            jsonObject1.put("result",jsonArray);
            response.getWriter().write(jsonObject1.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
