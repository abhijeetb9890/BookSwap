import com.mongodb.DBObject;
import helper.Book;
import helper.DB;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UserAnalysisServlet",urlPatterns = "/api/useranalysis")
public class UserAnalysisServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ServletContext servletContext=request.getServletContext();
        String userid= (String) servletContext.getAttribute("usrid");

        String noOfBooks;
        String noOfRequestedBooks;
        String noOfSwaps;


        DBObject docuser= DB.sumBooksofUser(userid);

        Integer count1=(Integer)docuser.get("count");



        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("noofbooks", count1);
            //jsonObject.put("noofrequested");
            //jsonObject.put("noOfSwap");
            response.getWriter().write(jsonObject.toString());

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
