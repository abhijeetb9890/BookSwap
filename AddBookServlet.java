import helper.Book;
import helper.DB;
import helper.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
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


@WebServlet(name = "AddBookServlet", urlPatterns = "/api/addbook")
public class AddBookServlet extends HttpServlet {



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


        ServletContext servletContext=request.getServletContext();
        String userid=(String) servletContext.getAttribute("usrid");


        response.setContentType("application/json");
        // For accepting cross domain requests
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");


        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not a multipart data/form data");
        }

        String id = null;
        String synopsis = null;
        String name = null;
        String category = null;
        String cost = null;
        PrintWriter out = response.getWriter();
        File picFile;
        String rent=null;
        String Author=null;
        Double rating=null;
        String tempFilePath = null;


        //To get the values of the fields

        try {
            List<FileItem> fileItemList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemIterator = fileItemList.iterator();

            while (fileItemIterator.hasNext()) {
                FileItem fileItem = fileItemIterator.next();

                //run the switch case for accpting the values
                switch (fileItem.getFieldName()) {
                    case Book.NAME:
                        name = fileItem.getString();
                        break;
                    case Book.AUTHOR:
                        Author=fileItem.getString();
                        break;
                    case Book.CATEGORY:
                        category = fileItem.getString();
                        break;
                    case Book.COST:
                        cost = fileItem.getString();
                        break;
                    case Book.SYNOPSIS:
                        synopsis = fileItem.getString();
                        break;

                    case Book.RENT:
                        rent=fileItem.getString();
                        break;
                    case "imagebook":
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
            Book book = new Book(category, name, synopsis, rating,  cost, Author,userid);
            id = DB.insertBook(book);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put(Book.ID, id);
            response.getWriter().write(jsonResponse.toString());
            boolean v = DB.InsertImageBook(tempFilePath, id);
           // System.out.println(v);
            response.getWriter().write(jsonResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Book cannot be inserted into the collection");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
