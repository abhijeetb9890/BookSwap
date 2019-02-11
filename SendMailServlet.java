import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import helper.Book;
import helper.DB;
import helper.User;
import helper.mail;
import org.bson.Document;

/**
 * Created by hp on 19-09-2016.
 */
@WebServlet(name = "SendMailServlet",urlPatterns = "/api/mail")
public class SendMailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext=request.getServletContext();
        String email=(String) servletContext.getAttribute("emailowner");
        System.out.println(email);

        String userid=(String)servletContext.getAttribute("usrid");

        String id=request.getParameter("_id");
        if (id==null || id.equals("") )
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"The book doesnt exist");
            return;
        }

        ServletContext servletContext1=getServletContext();
        servletContext1.setAttribute("mailowner",id);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext1=request.getServletContext();
        String userid=(String)servletContext1.getAttribute("usrid");
       ServletContext servletContext=request.getServletContext();
        String id=(String) servletContext.getAttribute("mailowner");
        Book book= DB.getBookById(id);
        String bookName=book.getName();
        String boookid=book.getOwnerId();
        User user=DB.getUserById(userid);
        String owner=user.getName();
        List<Book>userbooks=DB.getBooksByUser(userid);
        String email=user.getEmail();
        mail mail1=new mail();
        mail1.sendMail(email);
    }
}
