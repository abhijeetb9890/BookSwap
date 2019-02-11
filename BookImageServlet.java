import com.mongodb.gridfs.GridFSDBFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "BookImageServlet",urlPatterns = "/book.img")
public class BookImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //      HttpSession httpSession=request.getSession(false);
  //      ServletContext servletContext=request.getServletContext();
    //    String userid=(String) servletContext.getAttribute("usrid");

        // String user_id=(String) httpSession.getAttribute("user_id");
        //System.out.println(user_id);
        //String id=request.getParameter(User.ID);
        String bookid=request.getParameter("_id");
        ServletContext servletContext=request.getServletContext();
        servletContext.setAttribute("bookimgid",bookid);
    //    String temp=(String) servletContext.getAttribute("bookimgid");
       // System.out.println("From post id is"+temp);
        //System.out.println("The book id is"+bookid);

        //HttpSession httpSession=request.getSession();
        //httpSession.setAttribute("bookimgid",bookid);



      /*  if (bookid==null)
        {
            returnDefaultImage(response);
            return;
        }


        GridFSDBFile gridFSDBFile = helper.DB.getImageBook(bookid);

        if (gridFSDBFile!= null) {
            gridFSDBFile.writeTo(response.getOutputStream());
        } else {
            returnDefaultImage(response);
        }

*/

    }

    private void returnDefaultImage(HttpServletResponse response)throws IOException {

        response.sendRedirect("/static/book.jpg");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //ServletContext servletContext=request.getServletContext();
        //String bookid=(String) servletContext.getAttribute("bookimgid");
        //HttpSession httpSession=request.getSession();
        //String bookid=(String) httpSession.getAttribute("bookimgid");
        //System.out.println("Received book id is"+bookid);

        //System.out.println("The bookid received is"+bookid);

        String bookid=request.getParameter("_id");

        System.out.println("Book id is"+bookid);
        if (bookid==null)
        {
            returnDefaultImage(response);
            return;
        }


        GridFSDBFile gridFSDBFile = helper.DB.getImageBook(bookid);

         if (gridFSDBFile!= null) {
            gridFSDBFile.writeTo(response.getOutputStream());
        } else {
            returnDefaultImage(response);
        }


    }
}
