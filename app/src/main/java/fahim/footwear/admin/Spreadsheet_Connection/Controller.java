package fahim.footwear.admin.Spreadsheet_Connection;

/**
 * Created by user on 4/15/2018.
 */

import android.util.Log;

import androidx.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Controller {







   // https://script.google.com/macros/s/AKfycbzVuuT0B1CxYFujFv8r5xpppTCksthaYxnCx1ENT-U9D1INHg4/exec
    //https://script.google.com/macros/s/AKfycbzVuuT0B1CxYFujFv8r5xpppTCksthaYxnCx1ENT-U9D1INHg4/exec
  // public static final String WAURL="https://script.google.com/macros/s/AKfycbzVuuT0B1CxYFujFv8r5xpppTCksthaYxnCx1ENT-U9D1INHg4/exec?";
                                     //https://script.google.com/macros/s/AKfycbzVuuT0B1CxYFujFv8r5xpppTCksthaYxnCx1ENT-U9D1INHg4/exec



   // this macro is mine for inserting, deleting and updating data :
  //  public static final String WAURL="https://script.google.com/macros/s/AKfycbzVuuT0B1CxYFujFv8r5xpppTCksthaYxnCx1ENT-U9D1INHg4/exec?";
    public static final String WAURL="https://script.google.com/macros/s/AKfycbz0LWQfOSKhbh2bd4aHuT1vLqxXTI4cGxKkUneCkVMwytsendw/exec?";


//for registration of customer : this is my script macro: spreadsheet name: Registration:

    public static final String RAGISTRATIONURL="https://script.google.com/macros/s/AKfycbx7yPYdrkg_ZnplhQOCxRX7_SFmFr9IQZI1G8oxbn41VLpgGlw/exec?";
                                                // https://script.google.com/macros/s/AKfycbx7yPYdrkg_ZnplhQOCxRX7_SFmFr9IQZI1G8oxbn41VLpgGlw/exec
                                                //https://script.google.com/macros/s/AKfycbx7yPYdrkg_ZnplhQOCxRX7_SFmFr9IQZI1G8oxbn41VLpgGlw/exec
    // EG : https://script.google.com/macros/s/AKfycbwXXXXXXXXXXXXXXXXX/exec?
//Make Sure '?' Mark is present at the end of URL





//**********************1. affileate : *************************** :
    private static final String MAIN_URL = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1YF0mz0eoXYr-95mdSuozPZ9oKBYCYBMpPAzuYEO4N-w&sheet=Sheet1";

  //  private static final String MAIN_URL = "https://script.google.com/macros/s/AKfycbzVuuT0B1CxYFujFv8r5xpppTCksthaYxnCx1ENT-U9D1INHg4/exec?id=1VZh9F7HzJE21YiOtDSW6SDwSBA3XK3_DZtj519UWj80&sheet=Sheet1?";


//this is ok but "another dev builtin macro" for reading data : built in macro for reading data : from Password and Rate spreadsheet url and id :
    private static final String PASSWORD_RATE_URL = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1DQM_Bzh9pjxq7H52IzEg4hqUWtOokjnV11SzXw2ckl8&sheet=Sheet1";

//this is ok but "another dev builtin macro" for reading data : built in macro for reading data : from Registration spreadsheet url and id :




//**********3. (insert) Sheet name : Ragistration  (Blood Bangladesh and tafheem search***********) :
    private static final String RGISTRATION_URL ="https://script.google.com/macros/s/AKfycbzpH05thl-jdAMS-0JeZzw9YS2mcUg9IJOedUBqWkoGdwPtNwc/exec?";

   // https://script.google.com/macros/s/AKfycby-DObY2GbKJ959geHkIY2006K3dNYYmuTx7ktNFqS1OtBsYA8/exec  //BloodDonorRegistration - noorhossain888
//https://script.google.com/macros/s/AKfycby-DObY2GbKJ959geHkIY2006K3dNYYmuTx7ktNFqS1OtBsYA8/exec?

//ok: (for Classic sign )
   // private static final String RGISTRATION_URL_READ = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1jJm4G3o78I7E3wq7HrYUCsG5axDmVVu2TReoZibrsEM&sheet=Sheet1";

    //ok: (for Blood Bangladesh:
    private static final String RGISTRATION_URL_READ = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=18-ikDMHxR74uiA91SJoWrSTcVNjNg2NpwIOJk9VRwok&sheet=Sheet1";

    private static final String BIBEK_MONTH_READ = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=12tDeyq6pn540-gtE_PZ37AQh5a0oXgIm0tyFoFd8PuA&sheet=Sheet1";
//**************2. (read) Sheet name : ISLAMI SAHITTO ( For tafheem search)************************
    private static final String ISLAMI_SAHITTO = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1I71kmb7IPvlQjrfFdjGcm-_i8KXPoPWr_6DIpY34Lkg&sheet=Sheet1";
// *************4. (insert)  sheet name : wordbywordpassword:(For tafheem search - coments and zogazog / Answer And Question ***************)
    private static final String ZOGAZOG_URL ="https://script.google.com/macros/s/AKfycbxmsVcMiQmMdmyAb896hedKS2yBw4hGm-W1-RN-yADlMPrAS9Mw/exec?";

    // *************4. (insert)  sheet name : StackTraceTafheemScript :(***************)
    private static final String StackTraceTafheemScript_URL ="https://script.google.com/macros/s/AKfycbwLuCJf9ky5lB6yLT63hsNgnkVR2KKtaObTSDk5YiYhqJ8aRxY/exec?";


private  static final  String biggopti_insert_view_url = "https://script.google.com/macros/s/AKfycbwQelcMh2InyeuscMTS-30MM-_vIoBKR4Pw1QRABcFLkckIHg/exec?";

//    private static final String ZOGAZOG_URL ="https://script.google.com/macros/s/AKfycbyllAz-cb7GeP3qC9cb3GZMm7BdDNxhNm9pvvU4WWSDXa74gQfc/exec?";

   // https://script.google.com/macros/s/AKfycbyllAz-cb7GeP3qC9cb3GZMm7BdDNxhNm9pvvU4WWSDXa74gQfc/exec
//https://script.google.com/macros/s/AKfycbwjz-e6JmIe9u514j6tWLXQQ6w6Mwxygzya9ym8bhRttZKnsSs/exec

    //https://docs.google.com/spreadsheets/d/12tDeyq6pn540-gtE_PZ37AQh5a0oXgIm0tyFoFd8PuA/edit?usp=sharing
//https://docs.google.com/spreadsheets/d/1I71kmb7IPvlQjrfFdjGcm-_i8KXPoPWr_6DIpY34Lkg/edit?usp=sharing

    //   private static final String RGISTRATION_URL_READ = "https://script.google.com/macros/s/AKfycbyPdFvvxCouMKftkPJ5mfPyqzgJ6UQ1yjLzzprc27YKXQaqg-QM/exec?id=1jJm4G3o78I7E3wq7HrYUCsG5axDmVVu2TReoZibrsEM&sheet=Sheet1";



    //  private static final String RGISTRATION_URL_READ = "https://script.google.com/macros/s/AKfycbyPdFvvxCouMKftkPJ5mfPyqzgJ6UQ1yjLzzprc27YKXQaqg-QM/exec?id=15Vg1wxoZawuGZjTD9_RV4g15rvhKL3edVZuf6Hg0IfQ&sheet=Sheet1";


// 1. Affiliate marketing (read only) : https://docs.google.com/spreadsheets/d/1jttiRfxfjAAN5AAqt0oANoyPuv8ccUQY43wp7jIsmos/edit?usp=sharing
    // 2. Cpa Marketing (read only) : https://docs.google.com/spreadsheets/d/1bi6GaqYD-LMDaRkX9wMXX4DWa9ZX7dmA1pP7z2aguVX/edit?usp=sharing
    //3. Tafheem App ZogaZog (for insert data) : https://script.google.com/macros/s/AKfycbyllAz-cb7GeP3qC9cb3GZMm7BdDNxhNm9pvvU4WWSDXa74gQfc/exec


//for updating rate and password : this is my script macro: spreadsheet name: RateAndPassword:

    private static final String RATEANDPASSWORD_URL = "https://script.google.com/macros/s/AKfycbzB_WcEUrezH9KlQvkYZBa9NygDUMZ_6BMCgJWNEzPVHV1oj4s/exec?";

    public static final String TAG = "TAG";
    private static final String KEY_USER_ID = "user_id";

    private static Response response;



    public static JSONObject Biggopti_Insert_View(String id, String name) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //.url(RAGISTRATIONURL+"action=insert&id="+id+"&name="+name)
                    .url(biggopti_insert_view_url+"action=insert&id="+id+"&name="+name)

                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }


    public static JSONObject read_biggopti_previous_data(String id) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(biggopti_insert_view_url+"action=read&id="+id)
                    .build();
            response = client.newCall(request).execute();
            // Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject biggopti_update_new_data(String id, String name) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(biggopti_insert_view_url+"action=update&id="+id+"&name="+name)
                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }


    public static JSONObject biggoptiShow_update_like(String url, String id, String like) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url+"?action=updatelike&uId="+id+"&uPhone="+like)
                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject biggoptiShow_insert_comment(String url, String id, String comment, String cellNumber) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url+"?action=insert_comment&uId="+id+"&cmnt="+comment+"&cellNumber="+cellNumber)
                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject biggoptiShow_delete_comment(String url, String id, String cellNumber) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url+"?action=delete_comment&uId="+id+"&cellNumber="+cellNumber)
                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }



    public static JSONObject biggoptiShow_delete_post(String url, String userUniqueId, String postId) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url+"?action=delete_post&uId="+userUniqueId+"&postId="+postId)
                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject delete_one_order_request(String url, String orderId, String mainPostId) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url+"?action=delete_one_order&ORIGINAL_POST_ID="+mainPostId+"&ORDER_REQUEST_ID="+orderId)
                    //.url(url+"?action=delete_one_order&orderId="+orderId)
                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }




    public static JSONObject readIslamiSahitto() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(ISLAMI_SAHITTO)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject bishoy_ovidhan() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(PASSWORD_RATE_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }


    public static JSONObject affiliate() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
   // StackTraceTafheemScript_URL

    public static JSONObject SendStackTraceTafheemScript(String id, String name) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //.url(RAGISTRATIONURL+"action=insert&id="+id+"&name="+name)
                    .url(StackTraceTafheemScript_URL+"action=insert&id="+id+"&name="+name)

                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject send_questions(String id, String name, String email, String received) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //.url(RAGISTRATIONURL+"action=insert&id="+id+"&name="+name)
                    .url(ZOGAZOG_URL+"action=insert&id="+id+"&name="+name+"&email="+email+"&received="+received)

                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject insertRegistration(String id, String name) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //.url(RAGISTRATIONURL+"action=insert&id="+id+"&name="+name)
                     .url(RGISTRATION_URL+"action=insert&id="+id+"&name="+name)

                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }




    public static JSONObject readRegistrationData() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(RGISTRATION_URL_READ+"action=readRegistrationData")
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }




    public static JSONObject readAllData() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(RGISTRATION_URL_READ)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }


    public static JSONObject readBibekMonth() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(BIBEK_MONTH_READ)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }




    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }



    public static JSONObject getPasswordAndRateFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(PASSWORD_RATE_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;

    }



    public static JSONObject readAllRegistration() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(RGISTRATION_URL_READ)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }





    public static JSONObject insertData(String id, String name) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(WAURL+"action=insert&id="+id+"&name="+name)
                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }



    public static JSONObject updateData(String id, String name) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(WAURL+"action=update&id="+id+"&name="+name)
                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }


    public static JSONObject updateRateAndPassword(String id, String name) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(RATEANDPASSWORD_URL+"action=update&id="+id+"&name="+name)
                    .build();
            response = client.newCall(request).execute();
            //    Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject readData(String id) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(WAURL+"action=read&id="+id)
                    .build();
            response = client.newCall(request).execute();
            // Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject deleteData(String id) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(WAURL+"action=delete&id="+id)
                    .build();
            response = client.newCall(request).execute();
            // Log.e(TAG,"response from gs"+response.body().string());
            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "recieving null " + e.getLocalizedMessage());
        }
        return null;
    }


}