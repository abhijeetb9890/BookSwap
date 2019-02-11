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
import java.util.List;


@WebServlet(name = "UserDataServlet",urlPatterns = "/api/user")
public class UserDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ServletContext servletContext=request.getServletContext();
        String userid=(String) servletContext.getAttribute("usrid");

        try {
            User user = DB.getUserById(userid);
            response.getWriter().write(user.toJsonObject().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
