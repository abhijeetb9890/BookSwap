package helper;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class DB {


    private static final String DB_HOST = "0.0.0.0";
    private static final int DB_PORT = 27017;
    private static final String DB_NAME = "bookswapcommunity";
    private static final String USER_COLLECTION = "users";
    private static final String BOOK_COLLECTION = "books";
    private static final String SWAP_COLLECTION="Swaps";
    private static final String IMAGE_COLLECTION = "image";
    private static final String HISTORY_COLLECTION="History";
    private static final String BOOK_IMAGE_COLLECTION="bookImage";
    private static com.mongodb.DB gridFsDb;
    private static MongoDatabase mongoDatabase;

    static {
        MongoClient mongoClient = new MongoClient(DB_HOST, DB_PORT);
        mongoDatabase = mongoClient.getDatabase(DB_NAME);
        gridFsDb = mongoClient.getDB(DB_NAME);
    }


    public static String signUp(User user) throws IOException {
        Document document = user.toDocument();
        mongoDatabase.getCollection(USER_COLLECTION).insertOne(document);
        String id = document.getObjectId(User.ID).toString();

        if (id == null) {
            throw new IOException("Document not inserted in collection");
        }
        return id;

    }


    public static User login(String email, String password) {
        User user = null;
        FindIterable<Document> loggedUser = mongoDatabase.getCollection(USER_COLLECTION).find(new BasicDBObject(User.EMAIL, email).append(User.PASSWORD, password));

        if (loggedUser != null && loggedUser.first() != null) {
            user = new User(loggedUser.first());
        }
        return user;
    }


    public static boolean removeBook(String id) throws IOException {
        mongoDatabase.getCollection(BOOK_COLLECTION).findOneAndDelete(new BasicDBObject(Book.ID, new ObjectId(id)));
        return true;
    }


    public static User getUserById(String id) {
        User user = null;
        FindIterable<Document> cursor = mongoDatabase.getCollection(USER_COLLECTION).find(new BasicDBObject(User.ID,new ObjectId(id)));

        if (cursor != null || cursor.first() != null)
            user = new User(cursor.first());

        return user;
    }

    public static Document getBookByowner(String id) {

        FindIterable<Document> cursor = mongoDatabase.getCollection(SWAP_COLLECTION).find(new BasicDBObject("ownerId", id));

        if (cursor != null || cursor.first() != null)
            return cursor.first();

        return null;
    }


    public static Book getBookById(String id) {
        Book book = null;
        FindIterable<Document> cursor = mongoDatabase.getCollection(BOOK_COLLECTION).find(new BasicDBObject(Book.ID, new ObjectId(id)));

        if (cursor != null || cursor.first() != null)
            book = new Book(cursor.first());

        return book;
    }

    public static String insertSwaps(String name1,String book1,String ownerId,String userid,String bookid)
    {
        System.out.println("Going to insert");
        Document document=new Document();
        document.append("NameUser",name1);
        document.append("BookName",book1);
        document.append("ownerId",ownerId);
        document.append("userid",userid);
        document.append("bookid",bookid);
        mongoDatabase.getCollection(SWAP_COLLECTION).insertOne(document);
        String id=document.getObjectId(Book.ID).toString();
        return id;
    }

    public static String insertHistory(String name1, String book1, String name2, String book2, Date date)
    {
        Document document=new Document();
        document.append("User1",name1);
        document.append("Book1",book1);
        document.append("User2",name2);
        document.append("Book2",book2);
        document.append("date",date);
        //document.append("",bookid);
        mongoDatabase.getCollection(HISTORY_COLLECTION).insertOne(document);
        String id=document.getObjectId(Book.ID).toString();
        return id;
    }



    public static String insertBook(Book book) throws IOException {
        String id = book.getOwnerId();
        System.out.println(id);
        User user = getUserById(id);

        if (user == null)
            throw new IOException("User not there");

        Document document = book.toDocument();
        mongoDatabase.getCollection(BOOK_COLLECTION).insertOne(document);

        String _id = document.getObjectId(Book.ID).toString();
        return _id;
    }





    public static boolean InsertImage(String tempFilePath, String userId) {
        try {
            File imageFile = new File(tempFilePath);

            //create a photo namespace
            GridFS gridFs = new GridFS(gridFsDb,IMAGE_COLLECTION);
            GridFSInputFile gridFSInputFile = gridFs.createFile(imageFile);
            gridFSInputFile.setFilename(userId);
            gridFSInputFile.save();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean InsertImageBook(String tempFilePath, String bookId) {
        try {
            File imageFile = new File(tempFilePath);

            //create a photo namespace
            GridFS gridFs = new GridFS(gridFsDb, BOOK_IMAGE_COLLECTION);
            GridFSInputFile gridFSInputFile = gridFs.createFile(imageFile);
            gridFSInputFile.setFilename(bookId);
            gridFSInputFile.save();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static GridFSDBFile getImage(String userId) {
        GridFS gfspic = new GridFS(gridFsDb, IMAGE_COLLECTION);
        return gfspic.findOne(userId);
    }


    public static GridFSDBFile getImageBook(String bookId) {
        GridFS gfspic = new GridFS(gridFsDb, BOOK_IMAGE_COLLECTION);
        return gfspic.findOne(bookId);
    }


    /*public static List<GridFSDBFile>  getImagesOfBooks() {
        List<GridFSDBFile> gridFSlist = new ArrayList<>();
        GridFS gfspic = new GridFS(gridFsDb, IMAGE_COLLECTION);

        DBCursor gridFSDBFiles =gfspic.getFileList();
        //while (gridFSDBFiles.hasNext())



        return gridFSlist;
    }*/



    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        FindIterable<Document> iterable = mongoDatabase.getCollection(BOOK_COLLECTION).find();
        for (Document document : iterable) {
            Book book = new Book(document);
            books.add(book);
        }
        return books;
    }

    public static void  updateKey(String id)
    {
        DBCollection collection=gridFsDb.getCollection("books");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("key", 1);
        BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(id));
        collection.update(searchQuery, newDocument);

    }

    public static void  updateKeyZero(String id)
    {
        DBCollection collection=gridFsDb.getCollection("books");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("key", 0);
        BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(id));
        collection.update(searchQuery, newDocument);

    }

    public static List<Document> getHistory() {
        List<Document> books = new ArrayList<>();
        FindIterable<Document> iterable = mongoDatabase.getCollection(HISTORY_COLLECTION).find();
        for (Document document : iterable) {

            books.add(document);
        }
        return books;
    }


    public static Document getBookByName(String name)
    {
        //Document document=gridFsDb.getCollection("Swaps").findOne("");
        FindIterable<Document>cursor=mongoDatabase.getCollection("Swaps").find(new BasicDBObject("BookName",name));

        return cursor.first();

    }



    public static DBObject sumSwapped() {
        DBObject groupFields = new BasicDBObject("_id", "$BookName");

        // we use the $sum operator to increment the "count"
        // for each unique dolaznaStr
        groupFields.put("count", new BasicDBObject("$sum", 1));
        DBObject group = new BasicDBObject("$group", groupFields);
        // You can add a sort to order by count descending
        DBObject sortFields = new BasicDBObject("count", -1);
        DBObject sort = new BasicDBObject("$sort", sortFields);
        DBCollection dbCollection = gridFsDb.getCollection(SWAP_COLLECTION);
        AggregationOutput output = dbCollection.aggregate(group, sort);
        // dbObject=output.results();
        Iterator<DBObject>results=output.results().iterator();

        return results.next();

    }


    public static DBObject sumBooksofUser(String username) {

        DBObject groupFields = new BasicDBObject("_id", "$owner_id");
        groupFields.put("count", new BasicDBObject("$sum", 1));
        DBObject group = new BasicDBObject("$group", groupFields);
        DBObject sortFields = new BasicDBObject("count", -1);
        DBObject sort = new BasicDBObject("$sort", sortFields);
        DBCollection dbCollection = gridFsDb.getCollection(BOOK_COLLECTION);
        AggregationOutput output = dbCollection.aggregate(group, sort);

        for (DBObject dbObject:output.results())
        {
            //System.out.println(dbObject.toString());
            DBObject user =dbObject;
            if (user.get("_id").equals("57e3e796823721b4d405f2ba")) {
                return user;
            }
        }
                return null;
    }




    public static List<Document> getSwapsbyId(String id) {
        List<Document> Swaps = new ArrayList<>();
        FindIterable<Document> iterable = mongoDatabase.getCollection(SWAP_COLLECTION).find(new BasicDBObject("userid",id));
        for (Document document : iterable) {

            Swaps.add(document);
        }
        return Swaps;
    }


    public static List<Book> getBooksByCategory(String category) {
        List<Book> books = new ArrayList<>();
        FindIterable<Document> iterable = mongoDatabase.getCollection(BOOK_COLLECTION).find(new BasicDBObject(Book.CATEGORY,category));
        for (Document document : iterable) {
            Book book = new Book(document);
            books.add(book);
            System.out.println(book);
        }
        return books;
    }

    public static List<Book> getBooksByUser(String id) {

        List<Book> books = new ArrayList<>();
        FindIterable<Document> iterable = mongoDatabase.getCollection(BOOK_COLLECTION).find(new BasicDBObject(Book.OWNER_ID, id));

        //System.out.println(iterable);
        for (Document document : iterable) {
            Book book = new Book(document);
            System.out.println(book);
            books.add(book);
        }
        return books;

    }

}


