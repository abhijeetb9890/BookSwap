package helper;


import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

public class Book {

    public static final String ID = "_id";
    public static final String OWNER_ID = "owner_id";
    public static final String NAME = "name";
    public static final String AUTHOR="author";
    public static final String CATEGORY = "category";
    public static final String SYNOPSIS = "synopsis";
    public static final String RATING = "rating";
    public static final String COST = "cost";
    public static final String RENT = "rent";
    public  static  final String KEY="key";

    private String _id;
    private String category;
    private String name;
    private String synopsis;
    private Double rating;
    private String OwnerId;
    private String Cost;
    private String Rent;
    private String Author;
    private Double key;


    public Book(String category, String name, String synopsis, Double rating, String cost,String Author, String user_id) {

        this.category = category;
        this.name = name;
        this.synopsis = synopsis;
        this.rating = rating;
        this.Cost = cost;
        //this.Rent = rent;
        this.Author=Author;
        this.OwnerId = user_id;
       // this.key= Double.valueOf(0);
    }


    public Book(Document document) {
        _id = document.getObjectId(ID).toString();
        category = document.getString(CATEGORY);
        name = document.getString(NAME);
        synopsis = document.getString(SYNOPSIS);
        //rating = document.getDouble(RATING);
        OwnerId = document.getString(OWNER_ID);
        Cost = document.getString(COST);
        Rent = document.getString(RENT);
        Author=document.getString(AUTHOR);
 //       key=document.getDouble(KEY);

    }

    public Document toDocument() {
        Document document = new Document();
        document.append(CATEGORY, category).
                append(NAME, name).
                append(SYNOPSIS, synopsis).
                append(RATING, rating).
                append(COST, Cost).
                append(AUTHOR,Author).
                append(OWNER_ID, OwnerId).
                append(KEY,key);
        return document;
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ID, _id);
        jsonObject.put(CATEGORY, category);
        jsonObject.put(NAME, name);
        jsonObject.put(SYNOPSIS, synopsis);
        jsonObject.put(RATING, rating);
        jsonObject.put(COST, Cost);
        //jsonObject.put(RENT, Rent);
        jsonObject.put(AUTHOR,Author);
        jsonObject.put(KEY,key);
        return jsonObject;
    }

    public String get_id() {
        return _id;
    }

    public String getCategory() {
        return category;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

    public String getOwnerId() {
        return OwnerId;
    }


    public String getCost() {
        return Cost;
    }

    public String getRent() {
        return Rent;
    }

    public String getAuthor() {
        return Author;
    }

    public Double getKey() {
        return key;
    }

    public void setKey(Double key) {
        System.out.println("Set key is"+ key);
        this.key = key;
    }
}


