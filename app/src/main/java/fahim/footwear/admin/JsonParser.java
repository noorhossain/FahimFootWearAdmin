package fahim.footwear.admin;

import static fahim.footwear.admin.Configuration.KEY_ADTEXT;
import static fahim.footwear.admin.Configuration.KEY_ID;
import static fahim.footwear.admin.Configuration.KEY_IMAGE;
import static fahim.footwear.admin.Configuration.KEY_NAME;
import static fahim.footwear.admin.Configuration.KEY_PHONE;
import static fahim.footwear.admin.Configuration.KEY_USERS;
import static fahim.footwear.admin.Configuration.KEY_WEBURL;
import static fahim.footwear.admin.Configuration.POST_ID;
import static fahim.footwear.admin.Configuration.USER_ID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonParser {
    public static String[] uIds;
    public static String[] uNames;
    public static String[] uImages;

    public static   String []   uPhones;
    public static String []     uWebUrl;
    public static String[]      uAdText;

    public static String[] userId;
    public static String[] postId;


    private JSONArray users = null;

    private String json;


    public JsonParser(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(KEY_USERS);

            uIds = new String[users.length()];
            uNames = new String[users.length()];
            uImages = new String[users.length()];
            uPhones= new String[users.length()];
            uWebUrl= new String[users.length()];
            uAdText= new String[users.length()];
            userId = new String[users.length()];
            postId = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                uIds[i] = jo.getString(KEY_ID);
                uNames[i] = jo.getString(KEY_NAME);
                uImages[i] = jo.getString(KEY_IMAGE);

                uPhones [i] = jo.getString(KEY_PHONE);
                uWebUrl[i] = jo.getString(KEY_WEBURL);
                uAdText[i] = jo.getString(KEY_ADTEXT);
                userId[i]  = jo.getString(USER_ID);
                postId [i] = jo.getString(POST_ID);

            }

           // Log.e("uImage","ser image"+uImages[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
