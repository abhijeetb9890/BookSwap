import com.mongodb.MapReduceCommand;
import helper.Book;
import helper.DB;
import helper.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "MySwapsServlet",urlPatterns = "/api/swap")
public class MySwapsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





        String id=request.getParameter("_id");
        if(id==null || id.equals("")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The book doesnt exist");
            return;
        }
        System.out.println(id);
        ServletContext servletContext=request.getServletContext();
        servletContext.setAttribute("bookid",id);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ServletContext servletContext=request.getServletContext();
        String userid=(String) servletContext.getAttribute("usrid");

        ServletContext servletContext1=request.getServletContext();
        String bookid=(String) servletContext1.getAttribute("bookid");


        Book book=DB.getBookById(bookid);
        String bookName=book.getName();
        String ownerId=book.getOwnerId();
        User user1=DB.getUserById(ownerId);
        String ownerName=user1.getName();

        try
        {
            String myswaps_id=DB.insertSwaps(ownerName,bookName,ownerId,userid,bookid);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put(Book.ID,myswaps_id);

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }
}
