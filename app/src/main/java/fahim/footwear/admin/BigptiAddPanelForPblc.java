package fahim.footwear.admin;

import static fahim.footwear.admin.Configuration.KEY_ACTION;
import static fahim.footwear.admin.Configuration.KEY_ADTEXT;
import static fahim.footwear.admin.Configuration.KEY_ID;
import static fahim.footwear.admin.Configuration.KEY_IMAGE;
import static fahim.footwear.admin.Configuration.KEY_NAME;
import static fahim.footwear.admin.Configuration.KEY_PHONE;
import static fahim.footwear.admin.Configuration.LIST_USER_URL_BIGPTI_PASS;
import static fahim.footwear.admin.Configuration.POST_ID;
import static fahim.footwear.admin.Configuration.USER_ID;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fahim.footwear.admin.Common.Common;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

//import android.support.v7.app.AppCompatActivity;

public class BigptiAddPanelForPblc extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
    private EditText editTextUserName, et_uPhone, et_uWebUrl, et_uAdText, et_price, et_unit ;
    private EditText editTextUserId;
    private ImageView imageViewUserImage;
    private Button buttonAddImage;
    String userImage, phonNumber, webUrl, adText;

    private int PICK_IMAGE_REQUEST = 1;

    Bitmap rbitmap;

    String pWordFromServer;
    String pWordFromTyped;

    TextView txtSuccess, txtAdRules, txtAdDisclosure;

    String postUserName ;
    SharedPreferences settings ;
    SharedPreferences.Editor editor ;
    Context mContext;

    public static final String SIX_PAIR = "সিক্স-পেয়ার";
    public static final String ONE_DOZEN = "১-ডজন";

    Button btn_LADIES_PONSE, btn_JENTS_PONSE, btn_KIDS_PONSE, btn_LADIES_BARMIZE, btn_JENTS_BARMIZE,
            btn_KIDS_BARMIZE, btn_JENTS_CHOTI, btn_MAILA_LADIES, btn_KANGAROO, btn_CADES, btn_WINTER_COLLECTIONS
            , btn_EID_COLLECTIONS, btn_MISCELLINIOUS, btnAllCategory;

    String userUniqeID;
    int night;
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.biggopti_add_frm_public);

      //  Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this, ReadingSelection.class));

        mContext = this;
        settings = PreferenceManager.getDefaultSharedPreferences(mContext);

        editor = settings.edit();


        btn_LADIES_PONSE            =  (Button) findViewById(R.id.btn_LADIES_PONSE);
        btn_JENTS_PONSE             =  (Button) findViewById(R.id.btn_JENTS_PONSE);
        btn_KIDS_PONSE              =  (Button) findViewById(R.id.btn_KIDS_PONSE);
        btn_LADIES_BARMIZE          =  (Button) findViewById(R.id.btn_LADIES_BARMIZE);
        btn_JENTS_BARMIZE           =  (Button) findViewById(R.id.btn_JENTS_BARMIZE);
        btn_KIDS_BARMIZE            =  (Button) findViewById(R.id.btn_KIDS_BARMIZE);
        btn_JENTS_CHOTI             =  (Button) findViewById(R.id.btn_JENTS_CHOTI);
        btn_MAILA_LADIES            =  (Button) findViewById(R.id.btn_MAILA_LADIES);
        btn_KANGAROO                =  (Button) findViewById(R.id.btn_KANGAROO);
        btn_CADES                   =  (Button) findViewById(R.id.btn_CADES);
        btn_WINTER_COLLECTIONS      =  (Button) findViewById(R.id.btn_WINTER_COLLECTIONS);
        btn_EID_COLLECTIONS         =  (Button) findViewById(R.id.btn_EID_COLLECTIONS);
        btn_MISCELLINIOUS           =  (Button) findViewById(R.id.btn_MISCELLINIOUS);
        btnAllCategory              = (Button) findViewById(R.id.btn_all_category);


        btn_LADIES_PONSE           .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToButtonClicks(Common.LADIES_PONSE_URL);

            }
        });
        btn_JENTS_PONSE            .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToButtonClicks(Common.JENTS_PONSE_URL);

            }
        });
        btn_KIDS_PONSE             .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.KIDS_PONSE_URL);

            }
        });
        btn_LADIES_BARMIZE         .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.LADIES_BARMIZE_URL);

            }
        });
        btn_JENTS_BARMIZE          .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.JENTS_BARMIZE_URL);

            }
        });
        btn_KIDS_BARMIZE           .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.KIDS_BARMIZE_URL);

            }
        });
        btn_JENTS_CHOTI            .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.JENTS_CHOTI_URL);

            }
        });
        btn_MAILA_LADIES           .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.MAILA_LADIES_URL);

            }
        });
        btn_KANGAROO               .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.KANGAROO_URL);

            }
        });
        btn_CADES                  .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.CADES_URL);

            }
        });
        btn_WINTER_COLLECTIONS     .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.WINTER_COLLECTIONS_URL);

            }
        });
        btn_EID_COLLECTIONS       .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.EID_COLLECTIONS_URL);

            }
        });

        btn_MISCELLINIOUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.MISCELLINIOUS_URL);

            }
        });



        et_unit =  (EditText) findViewById(R.id.et_unit);
        et_price =  (EditText) findViewById(R.id.et_price);
        editTextUserId = (EditText) findViewById(R.id.et_uid);
        editTextUserName = (EditText) findViewById(R.id.et_uname);
        et_uPhone = (EditText)findViewById(R.id.et_uPhone);
        et_uWebUrl = (EditText)findViewById(R.id.et_uWebUrl);
        et_uAdText = (EditText)findViewById(R.id.et_uAdText);

        txtAdRules = (TextView)findViewById(R.id.txtAdRules);
        txtAdDisclosure = (TextView)findViewById(R.id.txtAdDisclosure);


        imageViewUserImage=(ImageView)findViewById(R.id.iv_uphoto);


        txtSuccess = (TextView)findViewById(R.id.txtSuccess);
        buttonAddImage = (Button) findViewById(R.id.btn_image);

        buttonAddImage.setOnClickListener(this);
        btnAllCategory.setOnClickListener(this);
        imageViewUserImage.setOnClickListener(this);


        final  String[] unitArray = {SIX_PAIR, ONE_DOZEN};

        AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
        ad.setTitle("Choose Country Pronunciation: ");

        ad.setItems(unitArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                et_unit.setText(unitArray[i]);
            }
        });

        ad.setPositiveButton("Ok", null);
        AlertDialog dialog = ad.create();


        et_unit.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP) {

                    dialog.show();


                }
                return false;
            }
        });


        et_uAdText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_uAdText.setError(null);
                txtSuccess.setText("");
            }
        });
        editTextUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_uAdText.setError(null);
                txtSuccess.setText("");

            }
        });

        postUserName = settings.getString("PostUserName", null);

        if(postUserName!=null&&!TextUtils.isEmpty(postUserName)){
            editTextUserName.setText(postUserName);
        }

        userUniqeID = settings.getString("UserUniqueID", null);
        if(userUniqeID==null){

            userUniqeID  = UUID.randomUUID().toString();
            editor.putString("UserUniqueID", userUniqeID);
            editor.apply();
        }



        // Retrieved Password :
        txtSuccess.setText("Picture here..........");


        btnAllCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToButtonClicks(Common.ALL_CATEGORY_URL);
            }
        });


    }

    private void sendToButtonClicks (String url ){
        txtSuccess.setText("");

        if(TextUtils.isEmpty(editTextUserName.getText().toString().trim())){
            editTextUserName.setError("empty");
            return;
        }
        if(TextUtils.isEmpty(et_uAdText.getText().toString().trim())){
            et_uAdText.setError("empty");
            return;
        }

        if(TextUtils.isEmpty(et_price.getText().toString().trim())){
            et_price.setError("empty");
            return;
        }

        if(TextUtils.isEmpty(et_unit.getText().toString().trim())){
            et_unit.setError("empty");
            return;
        }

        editTextUserName.setError(null);
        et_uAdText.setError(null);
        txtSuccess.setText("");
        btnAllCategory.setEnabled(false);

        addToFinalPost(url);
    }


    private void addToFinalPost(final String scriptUrl){

        adText = et_uAdText.getText().toString().trim();
        if(adText.length()>63610){

            Toast.makeText(mContext, "Your Post is Very Big length, Please Shorten....", Toast.LENGTH_LONG).show();
            btnAllCategory.setEnabled(true);

            return;

        }
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);

        txtSuccess.setText("Sending.... Please Wait.");

        //get current date and time from system :
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy:HH.mm.ss", Locale.US);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        String price = et_price.getText().toString();
        String unit = et_unit.getText().toString();

        final String registration_date = simpleDateFormat.format(new Date());

        final String userId = registration_date+":"+unit+"#"+price;
        final String userName = editTextUserName.getText().toString().trim();
        final  String postId =  UUID.randomUUID().toString(); ;

        // USER_ID
        //       USER_NAME
        // POST_ID

        editor.putString("PostUserName", userName);
        editor.apply();

        phonNumber = et_uPhone.getText().toString().trim();
        webUrl = et_uWebUrl.getText().toString().trim();


        //Bitmap  rbitmap = getResizedBitmap(bitmap,500);

        Log.e("null","values"+userImage);


        StringRequest stringRequest = new StringRequest(Request.Method.POST,scriptUrl, // ADD_USER_URL,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        if(loading!=null&&loading.isShowing())
                            loading.dismiss();
                        if(response.equalsIgnoreCase("success"))
                            response="Alhamdulillah, Post submitted";
                        Toast.makeText(BigptiAddPanelForPblc.this,response,Toast.LENGTH_LONG).show();

                        //  txtSuccess.setText("Alhamdulillah, Your Post Submitted Successfully. Your Ad-Code is :   "+registration_date);
                        txtSuccess.setText("Alhamdulillah, Your Post Submitted Successfully. Check Home Posts");

                        editTextUserId.setText("");
                        //editTextUserName.setText("");
                        et_uPhone.setText("");
                        et_uWebUrl.setText("");
                      //  et_uAdText.setText("");
                       // imageViewUserImage.setImageBitmap(null);

                        btnAllCategory.setEnabled(true);

//                        Intent intent = new Intent("BG_SHOW_BROADCAST");
//                        intent.putExtra("BROADCAST", "finishBgShowActivity");
//                        LocalBroadcastManager.getInstance(mContext)
//                                .sendBroadcast(intent);
//
//                        startActivity(new Intent(BigptiAddPanelForPblc.this,BiggoptiShow.class));
//                        finish();



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BigptiAddPanelForPblc.this,error.toString(),Toast.LENGTH_LONG).show();
                        if(loading!=null&&loading.isShowing())
                            loading.dismiss();

                        txtSuccess.setText("Failed Sending Post, Check and Wait for Stable Internet and Retry.");
                        btnAllCategory.setEnabled(true);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_ACTION,"insert");
                params.put(KEY_ID,userId); // Date, Price, Unit
                params.put(POST_ID, postId);
                params.put(USER_ID,  userUniqeID);
                params.put(KEY_NAME,userName);
                params.put(KEY_PHONE, "10");// this is like
                if(userImage!=null) {
                    params.put(KEY_IMAGE, userImage);
                }else {
                    params.put(KEY_IMAGE, "null");
                }
                // params.put(KEY_PHONE, phonNumber);
                // params.put(KEY_WEBURL, webUrl);
                params.put(KEY_ADTEXT,adText );

                return params;
            }

        };

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }



    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);

    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }








    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
              //  rbitmap = getResizedBitmap(bitmap,250);//Setting the Bitmap to ImageView
                rbitmap = bitmap;
                userImage = getStringImage(rbitmap);
                imageViewUserImage.setImageBitmap(rbitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    public void onClick(View v) {

        if(v == buttonAddImage){
            showFileChooser();
            txtSuccess.setText("");
        }

        if(v==imageViewUserImage){
            showFileChooser();
            txtSuccess.setText("");
        }

    }



    ///////////////////////////////////////Retrieve Password //////////////////////////////
    ProgressDialog loading;



    private void sendRequestScriptUrl(){
       loading = ProgressDialog.show(this,"Hey Wait Please.....","Connecting To Database.......",false,true);

        StringRequest stringRequest = new StringRequest(LIST_USER_URL_BIGPTI_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Log.e("null","ser image"+response);
                        showJSON(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BigptiAddPanelForPblc.this,error.getMessage(),Toast.LENGTH_LONG).show();
                        txtSuccess.setText("Connection Error..., Check Internet, And Restart App.");
                    }
                });

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        JsonParser pj = new JsonParser(json);
        pj.parseJSON();
        //Log.e("uImage","ser image"+JsonParser.uImages[1]);
//        BiggoptiListAdapter userListAdapter = new BiggoptiListAdapter(this, JsonParser.uIds,JsonParser.uNames,JsonParser.uImages
//                ,JsonParser.uPhones,JsonParser.uWebUrl,JsonParser.uAdText);

        if(fahim.footwear.admin.JsonParser.uNames!=null&&fahim.footwear.admin.JsonParser.uNames[1]!=null&&!fahim.footwear.admin.JsonParser.uNames[1].equals("")) {
            Common.APP_SCRIPT_PENDING_POST_URL = fahim.footwear.admin.JsonParser.uNames[1];
        }

        if(JsonParser.uIds!=null && JsonParser.uIds[1]!=null&&!JsonParser.uIds[1].equals("")) {
            Common.APP_SCRIPT_ALL_SHOES = fahim.footwear.admin.JsonParser.uIds[1];
        }

        if(JsonParser.uIds!=null && JsonParser.uIds[0]!=null&&!JsonParser.uIds[10].equals("")) {
            pWordFromServer = JsonParser.uIds[0].trim();
        }



        Toast.makeText(BigptiAddPanelForPblc.this,"Alhamdulillah, Connection Successfull.....Now Send Your Post.",Toast.LENGTH_LONG).show();

        txtSuccess.setText("Alhamdulillah, Connection Successfull....Now Send Your Post.");

       // Toast.makeText(BigptiAddPanelForPblc.this,"PassWord: "+pWordFromServer,Toast.LENGTH_LONG).show();


        txtAdRules.setText(Html.fromHtml(JsonParser.uAdText[0]));
        txtAdDisclosure.setText(Html.fromHtml(JsonParser.uWebUrl[0]));
        txtAdRules.setMovementMethod(LinkMovementMethod.getInstance());
        txtAdDisclosure.setMovementMethod(LinkMovementMethod.getInstance());

        if(loading!=null&&loading.isShowing())
        loading.dismiss();


      //  Toast.makeText(BigptiAddPanelForPblc.this,"Password: " + pWordFromServer,Toast.LENGTH_LONG).show();


    }







}
