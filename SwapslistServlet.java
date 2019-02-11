import helper.Book;
import helper.DB;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "SwapslistServlet",urlPatterns = "/api/swapslist")
public class SwapslistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext=request.getServletContext();
        String userid=(String)servletContext.getAttribute("usrid");
        System.out.println(userid);


        try {
            List<Document> swaps = DB.getSwapsbyId(userid);
            JSONObject jsonResponse = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            //List<String>bookids=new ArrayList<>();
            for (Document document : swaps) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("_id", document.getObjectId(Book.ID));
                jsonObject.put("NameUser", document.get("NameUser"));
                jsonObject.put("BookName", document.get("BookName"));
                jsonObject.put("bookid",document.get("bookid"));
                jsonArray.put(jsonObject);
            }
                jsonResponse.put("result", jsonArray);
                response.getWriter().write(jsonResponse.toString());

        }
            catch(JSONException e){
                e.printStackTrace();
            }


        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
