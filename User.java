package helper;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;


public class User {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CONTACT = "contact_no";
    public static final String GENDER="gender";

    private String _id;
    private String name;
    private String email;
    private String password;
    private String contact;
    private String gender;

    //for Sign up purpose
    public User(String name, String email, String password, String contact,String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.gender=gender;
    }

    //for Sign in purpose

    //code to be written

    //other constructor
    public User(Document document) {
        _id = document.getObjectId(ID).toString();
        name = document.getString(NAME);
        email = document.getString(EMAIL);
        password = document.getString(PASSWORD);
        contact = document.getString(CONTACT);
        gender=document.getString(GENDER);
    }


    //conversion into a document
    public Document toDocument() {
        Document document = new Document();
        document.append(NAME, name);
        document.append(EMAIL, email);
        document.append(PASSWORD, password);
        document.append(CONTACT, contact);
        document.append(GENDER,gender);
        return document;

    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ID, _id);
        jsonObject.put(NAME, name);
        jsonObject.put(EMAIL, email);
        // jsonObject.put(PASSWORD,password);
        jsonObject.put(CONTACT, contact);
        jsonObject.put(GENDER,gender);
        return jsonObject;

    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public String getGender() {
        return gender;
    }
}
