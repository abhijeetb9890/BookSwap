import helper.Book;
import helper.DB;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 29-09-2016.
 */
@WebServlet(name = "HistoryServlet",urlPatterns = "/api/history")
public class HistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Document> books = DB.getHistory();

            JSONObject jsonResponse = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<String>bookids=new ArrayList<>();
            for (Document document : books) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("User1",document.getString("User1"));
                jsonObject.put("Book1",document.getString("Book1"));
                jsonObject.put("User2",document.getString("User2"));
                jsonObject.put("Book2",document.getString("Book2"));
                jsonObject.put("date" ,document.getDate("date"));
                System.out.println(jsonObject.toString());
                jsonArray.put(jsonObject);
            }

            jsonResponse.put("result", jsonArray);
            response.getWriter().write(jsonResponse.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
