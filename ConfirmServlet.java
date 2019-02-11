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
import java.util.Date;


@WebServlet(name = "ConfirmServlet",urlPatterns = "/api/confirm")
public class ConfirmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext1=request.getServletContext();
        String book1id=(String)servletContext1.getAttribute("bookidrequest");

        System.out.println("Id of BOOK1"+book1id);

        String book2id=request.getParameter("_id");
        if (book2id==null || book2id.equals(""))
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Book doesnt exist");
            return;
        }
        System.out.println("Id of BOOK2"+book2id);
        ServletContext servletContext=request.getServletContext();
        servletContext.setAttribute("book2id",book2id);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext1=request.getServletContext();
        String book1id=(String)servletContext1.getAttribute("bookidrequest");

        String book2id=(String)servletContext1.getAttribute("book2id");
        System.out.println("At get book2id is "+book2id);
        Book book= DB.getBookById(book1id);
        String bookName1=book.getName();
        String ownerId1=book.getOwnerId();
        User user=DB.getUserById(ownerId1);
        String Name1=user.getName();

        Book book1= DB.getBookById(book2id);
        String bookName2=book1.getName();
        String ownerId2=book1.getOwnerId();
        User user2=DB.getUserById(ownerId2);
        String Name2=user2.getName();


        Date date=new Date();

        try {
            String id=DB.insertHistory(Name1,bookName1,Name2,bookName2,date);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("_id",id);
            response.getWriter().write(jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
