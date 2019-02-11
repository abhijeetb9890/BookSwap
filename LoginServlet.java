import helper.DB;
import helper.User;
import org.json.JSONException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by hp on 08-09-2016.
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/api/loginuser")
public class LoginServlet extends HttpServlet {

    public HttpSession httpSession;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username=request.getParameter(User.EMAIL);
        String password=request.getParameter(User.PASSWORD);

        if(username==null || username.equals(""))
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid username");
            return;
        }

        if (password==null || username.equals("")){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid password");
            return;
        }


        User loggedInUser= DB.login(username,password);

        if (loggedInUser != null) {
            try {
                ServletContext servletContext=request.getServletContext();
                servletContext.setAttribute("usrid",loggedInUser.get_id());
                servletContext.setAttribute("emailowner",loggedInUser.getEmail());
                //System.out.println(servletContext.getAttribute(""));
                 httpSession=request.getSession(true);
                if (httpSession!=null) {
                    String id = httpSession.getId();
                    System.out.println(id);
                }



                //System.out.println(loggedInUser.get_id());
                //httpSession.setAttribute("user_id",loggedInUser.get_id());
                //System.out.println(loggedInUser.get_id());
                //String user_id=(String) httpSession.getAttribute("user_id");
                //System.out.println(user_id);

                Cookie cookie=new Cookie("user_id",loggedInUser.get_id());
                response.addCookie(cookie);


                response.getWriter().write(loggedInUser.toJsonObject().toString());
            } catch (JSONException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No user found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong credentials");
            return;
        }
    }

}
