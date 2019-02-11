import com.mongodb.DBObject;
import helper.DB;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "getHistoryServlet",urlPatterns = "/api/gethistory")
public class getHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        try {
            DBObject dbObject= (DBObject) DB.sumSwapped();
            String bookName=(String) dbObject.get("_id");
            //System.out.println(bookName);
            Integer number=(Integer) dbObject.get("count");

            Document document=DB.getBookByName(bookName);
            String id=document.getString("bookid");

            JSONObject jsonObject=new JSONObject();
            //String bookname=dbObject.get("");
            jsonObject.put("bookname",bookName);
            jsonObject.put("count",number);
            jsonObject.put("_id",id);
            response.getWriter().write(jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
