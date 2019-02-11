import helper.DB;
import helper.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;


@WebServlet(name = "SignUpServlet", urlPatterns = "/api/signupuser")
public class SignUpServlet extends HttpServlet {
    public static final String DATA_DIR = "javax.servlet.context.tempdir";
    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;


    public void init() throws ServletException {

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        File fileDirectory = (File) getServletContext().getAttribute(DATA_DIR);
        diskFileItemFactory.setRepository(fileDirectory);
        this.uploader = new ServletFileUpload(diskFileItemFactory);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        // For accepting cross domain requests
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");


        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not a multipart data/form data");
        }

        String id = null;
        String email = null;
        String name = null;
        String password = null;
        String Contact = null;
        PrintWriter out = response.getWriter();
        File picFile;
        String gender=null;
        String tempFilePath = null;


        //To get the values of the fields

        try {
            List<FileItem> fileItemList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemIterator = fileItemList.iterator();

            while (fileItemIterator.hasNext()) {
                FileItem fileItem = fileItemIterator.next();

                //run the switch case for accpting the values
                switch (fileItem.getFieldName()) {
                    case "name":
                        name = fileItem.getString();
                        break;
                    case "email":
                        email = fileItem.getString();
                        break;
                    case "password":
                        password = fileItem.getString();
                        break;
                    case "contact_no":
                        Contact = fileItem.getString();
                        break;

                    case "gender":
                        gender=fileItem.getString();
                        break;
                    case "image":
                        tempFilePath = request.getServletContext().getAttribute(DATA_DIR) + File.separator + fileItem.getFieldName()+".jpg";
                        System.out.println(tempFilePath);
                        picFile = new File(tempFilePath);
                        System.out.println(picFile);
                        fileItem.write(picFile);
                        break;
                }

            }

        } catch (FileUploadException e) {
            e.printStackTrace();
            out.write("File not uploaded due to file upload exception");

        } catch (Exception e) {
            e.printStackTrace();
            out.write("File not uploaded due to exception");

        }

        try {
            User user = new User(name, email, password, Contact,gender);
            id = DB.signUp(user);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put(User.ID, id);
            response.getWriter().write(jsonResponse.toString());
            boolean v = DB.InsertImage(tempFilePath, id);
            response.getWriter().write(jsonResponse.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
