import com.mongodb.gridfs.GridFSDBFile;
import helper.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "ImageServlet",urlPatterns = "/profile.img")
public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    //      HttpSession httpSession=request.getSession(false);
    ServletContext servletContext=request.getServletContext();
    String userid=(String) servletContext.getAttribute("usrid");

    // String user_id=(String) httpSession.getAttribute("user_id");
    //System.out.println(user_id);
    //String id=request.getParameter(User.ID);

    if (userid==null)
    {
        returnDefaultImage(response);
        return;
    }

    GridFSDBFile gridFSDBFile = helper.DB.getImage(userid);
    System.out.println(gridFSDBFile);
    if (gridFSDBFile != null) {
        gridFSDBFile.writeTo(response.getOutputStream());
    } else {
        returnDefaultImage(response);
    }



}

    private void returnDefaultImage(HttpServletResponse response)throws IOException {

        response.sendRedirect("/static/profile.img");
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
