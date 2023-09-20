package fahim.footwear.admin;

import static fahim.footwear.admin.Common.Common.GRAND_TOTAL_TAKA;
import static fahim.footwear.admin.Common.Common.NOT_RECEIVED;
import static fahim.footwear.admin.Common.Common.NOT_SENT;
import static fahim.footwear.admin.Common.Common.ONE_DOZEN;
import static fahim.footwear.admin.Common.Common.PICK_IMAGE_REQUEST;
import static fahim.footwear.admin.Common.Common.PRODUCT_SENT;
import static fahim.footwear.admin.Common.Common.RECEIVED;
import static fahim.footwear.admin.Common.Common.SIX_PAIR;
import static fahim.footwear.admin.Common.Common.chalan_image;
import static fahim.footwear.admin.Common.Common.chalan_number_date;
import static fahim.footwear.admin.Common.Common.customerAddress;
import static fahim.footwear.admin.Common.Common.customerName;
import static fahim.footwear.admin.Common.Common.customerPhone;
import static fahim.footwear.admin.Common.Common.customer_or_shop_image;
import static fahim.footwear.admin.Common.Common.date;
import static fahim.footwear.admin.Common.Common.due;
import static fahim.footwear.admin.Common.Common.main_postId;
import static fahim.footwear.admin.Common.Common.order_id;
import static fahim.footwear.admin.Common.Common.paid_status;
import static fahim.footwear.admin.Common.Common.productImageLink;
import static fahim.footwear.admin.Common.Common.productName;
import static fahim.footwear.admin.Common.Common.product_receiving_status;
import static fahim.footwear.admin.Common.Common.product_sending_status;
import static fahim.footwear.admin.Common.Common.removeStringFromSharedPreference;
import static fahim.footwear.admin.Common.Common.shopAddress;
import static fahim.footwear.admin.Common.Common.shopName;
import static fahim.footwear.admin.Common.Common.totalOrderQuantity;
import static fahim.footwear.admin.Common.Common.totalPrice;
import static fahim.footwear.admin.Common.Common.total_paid;
import static fahim.footwear.admin.Common.Common.unitPrice;
import static fahim.footwear.admin.Common.Common.unitSystem;
import static fahim.footwear.admin.Common.CommonImage.convertBitmapToString;
import static fahim.footwear.admin.Common.CommonImage.convertStringToBitmap;
import static fahim.footwear.admin.Common.CommonImage.getHiResBitmap;
import static fahim.footwear.admin.Common.CommonImage.getStringFromPref;
import static fahim.footwear.admin.Common.CommonImage.saveStringToPref;
import static fahim.footwear.admin.Configuration.KEY_ACTION;
import static fahim.footwear.admin.Configuration.KEY_IMAGE;
import static fahim.footwear.admin.Configuration.KEY_USERS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fahim.footwear.admin.Common.Common;
import fahim.footwear.admin.Common.RequestModel;
import fahim.footwear.admin.Spreadsheet_Connection.Controller;

public class Cart_One_Edit extends AppCompatActivity {


    private Context mContext;

    TextView txtProduct_receiving_status;

    LinearLayout ll_reload, ll_upload_Challan_Image, ll_call_phone;

    private String OriginalPostID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_show_one);


        mContext = this;

        setTitle("RequestOneEdit: ");

        ll_reload = (LinearLayout)findViewById(R.id.ll_reload);
        ll_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loading!=null&&loading.isShowing())
                    loading.dismiss();
                if(loading!=null)
                loading.show();
                try {
                    sendRequestForOneUser(oneOrderId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ll_upload_Challan_Image= (LinearLayout)findViewById(R.id.ll_upload_Challan_Image);
        ll_call_phone= (LinearLayout)findViewById(R.id.ll_call_phone);

        txtProduct_receiving_status = (TextView)findViewById(R.id. txtProduct_receiving_status);
        txtShopName     = (TextView)findViewById(R.id.txtShopName);
        txtShopAddress= (TextView)findViewById(R.id.txtShopAddress);
         iv_customer_or_shop_image = (SpeedyImageView)findViewById(R.id.iv_customer_or_shop_image);

         txtOrderDate                 = (TextView)findViewById(R.id.txtOrderDate);
         txtProductName               = (TextView)findViewById(R.id.txtProductName);
         txtUnitSystem                = (TextView)findViewById(R.id.txtUnitSystem);
         txtUnitPrice                 = (TextView)findViewById(R.id.txtUnitPrice);
         txtTotalOrderQuantity        = (TextView)findViewById(R.id.txtTotalOrderQuantity);
           txtTotalPrice              = (TextView)findViewById(R.id.txtTotalPrice);
           txtCustomerPhone           = (TextView)findViewById(R.id.txtCustomerPhone);
           txtCustomerName            = (TextView)findViewById(R.id.txtCustomerName);
           txtMain_postId             = (TextView)findViewById(R.id.txtMain_postId);
           txtOrder_id                = (TextView)findViewById(R.id.txtOrder_id);
           txtProduct_sending_status  = (TextView)findViewById(R.id.txtProduct_sending_status);
           txtChalan_number_date      = (TextView)findViewById(R.id.txtChalan_number_date);
           txtTotalPaid               = (TextView)findViewById(R.id.txtTotalPaid);
           txtdueMoney = (TextView)findViewById(R.id.txtdue);
           txtPaid_status             = (TextView)findViewById(R.id.txtPaid_status);
           txtChalan_image_status     = (TextView)findViewById(R.id.txtChalan_image_status);
         iv_productImage       = (SpeedyImageView)findViewById(R.id.iv_productImage);
         iv_chalanImage        = (SpeedyImageView)findViewById(R.id.iv_chalanImage);

         tvChalan_imageLink = (TextView)findViewById(R.id.tvPhotoLink);
         txtHeader = (TextView)findViewById(R.id.txtHeader);




        loading = new ProgressDialog(mContext);
        loading.setTitle("Hey wait, Please......");
        loading.setMessage("Wait a moment.........");
        loading.setCancelable(true);
        loading.setIndeterminate(false);

        loading.show();

        if(getIntent()!=null){
            if(getIntent().getStringExtra(order_id)!=null) {
                try {
                    setImage(iv_productImage, "null", getIntent().getStringExtra(main_postId), productImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                OriginalPostID = getIntent().getStringExtra(main_postId);
                Log.i(TAG, "onCreate: OriginalPostID "+OriginalPostID);

                oneOrderId = getIntent().getStringExtra(order_id);

                try {
                    setImage(iv_chalanImage, "null", oneOrderId+"challan", challanImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    setImage(iv_customer_or_shop_image, "null", oneOrderId+"customer", customerImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Log.i(TAG, "One Order Id:  "+ oneOrderId);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendRequestForOneUser(oneOrderId);

                    }
                },50);
            }
        }

        txtChalan_image_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        ll_upload_Challan_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(challanImageString!=null){
                    showLoading();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            uploadChallanImage(challanImageString, oneOrderId);

                        }
                    }, 100);
                }else {
                    Toast.makeText(mContext, "Please Select Image first. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_chalanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = convertImageViewToBitmap(iv_chalanImage);
                showPictureFullScreen(bitmap);
            }
        });

        txtTotalPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareUpdateOneCell(oneOrderId, "20", "Total Paid");
            }
        });





        txtTotalPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareUpdateOneCell(oneOrderId, "25", "Grand Total");
            }
        });




        txtdueMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareUpdateOneCell(oneOrderId, "21", "Due Money");
            }
        });

        txtChalan_number_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareUpdateOneCell(oneOrderId, "18", "Challan Date And Number");
            }
        });

        txtPaid_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFillOptions(paidStatusArray, oneOrderId, "22", "Paid Status");
            }
        });



        txtProduct_sending_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFillOptions(productSentStatusArray, oneOrderId, "16", "Product Sending Status");
            }
        });

        txtProduct_receiving_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFillOptions(productReceivingStatusArray, oneOrderId, "19", "Receiving Status");

            }
        });

        imgThreeDot = (SpeedyImageView) findViewById(R.id.imgThreeDot);
        imgThreeDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeleteOneRequest(oneOrderId);
            }
        });



    }


    String [] productSentStatusArray = {PRODUCT_SENT, NOT_SENT };
    String [] productReceivingStatusArray = {RECEIVED, NOT_RECEIVED};


    private class JsonParser {


        private JSONArray users = null;

        private String json;

        public JsonParser(String json) {
            this.json = json;
        }

        @SuppressLint("SetTextI18n")
        protected void parseJSON() {




            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                Log.i(TAG, "onResponse: jsonObject: " + jsonObject.toString());

                users = jsonObject.getJSONArray(KEY_USERS);

                Log.i(TAG, "onResponse: JsonArrayLength: " + users.length());

                if(users!=null&&users.length()<1){

                    txtEmpty.setVisibility(View.VISIBLE);
                    mainScrollView.setVisibility(View.GONE);
                    return;
                }



                RequestModel p = new RequestModel();

                for (int i = 0; i < users.length(); i++) {




                    JSONObject jo = users.getJSONObject(i);

                    p.setShopName(jo.getString(shopName));
                    p.setShopAddress(jo.getString(shopAddress));
                    p.setDate(jo.getString(date)); // Date + time
                    p.setProductName(jo.getString(productName));
                    p.setUnitPrice(jo.getString(unitPrice));
                    p.setTotalOrderQuantity(jo.getString(totalOrderQuantity));
                    p.setCustomerPhone(jo.getString(customerPhone));
                    p.setCustomerName(jo.getString(customerName));
                    p.setCustomerAddress(jo.getString(customerAddress));
                    p.setProduct_sending_status(jo.getString(product_sending_status));
                    p.setPaid_status(jo.getString(paid_status));


                    p.setUnitSystem(jo.getString(unitSystem));
                    p.setTotalPrice(jo.getString(GRAND_TOTAL_TAKA));
                    p.setGrand_total_taka(jo.getString(GRAND_TOTAL_TAKA));

                    p.setProductImageLink(jo.getString(productImageLink));
                    p.setChalan_image(jo.getString(chalan_image));
                    p.setCustomer_or_shop_image(jo.getString(customer_or_shop_image));


                    p.setMain_postId(jo.getString(main_postId));
                    p.setOrder_id(jo.getString(order_id));

                    p.setChalan_number_date(jo.getString(chalan_number_date));
                    p.setProduct_receiving_status(jo.getString(product_receiving_status));
                    p.setTotal_paid(jo.getString(total_paid));
                    p.setDue(jo.getString(due));

                    Log.i(TAG, "parseJSON:p.getMain_postId() " + p.getOrder_id());

                }




                if(p.getProduct_receiving_status()!=null&&!TextUtils.isEmpty(p.getProduct_receiving_status())){
                    txtProduct_receiving_status.setText(p.getProduct_receiving_status());
                }else {
                    txtProduct_receiving_status.setText(NOT_SENT);
                }

                if(p.getChalan_image()!=null&&!TextUtils.isEmpty(p.getChalan_image())){
                    txtChalan_image_status.setText("Not Submitted-1");
                }else {
                    txtChalan_image_status.setText("Submitted-1");
                }

                if(p.getDue()!=null){
                    txtdueMoney.setText(p.getDue());
                }
                if(p.getTotal_paid()!=null){
                    txtTotalPaid.setText(p.getTotal_paid());
                }
                if(p.getChalan_number_date()!=null){
                    txtChalan_number_date.setText(p.getChalan_number_date());
                }


                if(p.getMain_postId()!=null){
                    txtMain_postId.setText(p.getMain_postId());
                }

                if(p.getShopName()!=null){
                    txtShopName.setText(p.getShopName());
                }


                if(p.getShopAddress()!=null){
                    txtShopAddress.setText(p.getShopAddress());
                }

                if(p.getDate()!=null){
                    txtOrderDate.setText(p.getDate());
                }

                if(p.getProductName()!=null){
                    txtProductName.setText(p.getProductName().replace("###", "\n\n"));
                }

                if(p.getUnitPrice()!=null){
                    txtUnitPrice.setText(p.getUnitPrice());
                }



                if(p.getTotalOrderQuantity()!=null){
                    txtTotalOrderQuantity.setText(p.getTotalOrderQuantity().replace("###", "\n\n"));
                }

                if(p.getGrand_total_taka()!=null){
                    txtTotalPrice.setText(p.getGrand_total_taka());
                }

                if(p.getCustomerPhone()!=null){
                    txtCustomerPhone.setText(p.getCustomerPhone());
                }

                if(p.getCustomerName()!=null){
                    txtCustomerName.setText(p.getCustomerName());
                }

                if(p.getProduct_sending_status()!=null){
                    txtProduct_sending_status.setText(p.getProduct_sending_status());
                }

                if(p.getPaid_status()!=null&&!TextUtils.isEmpty(p.getPaid_status())){
                    txtPaid_status.setText(p.getPaid_status());
                }else {
                    txtPaid_status.setText(FULL_DUE);
                }


                if(p.getUnitSystem()!=null){
                    txtUnitSystem.setText(p.getUnitSystem());
                }

                if(p.getOrder_id()!=null){
                    txtOrder_id.setText(p.getOrder_id());
                }



                Picasso.with(mContext).cancelRequest(iv_productImage);

                if (p.getProductImageLink() != null
                        && !TextUtils.isEmpty(p.getProductImageLink())
                        && !p.getProductImageLink().equalsIgnoreCase("null")) {
                    boolean imageSetSucceed = false;

                    String previousImageString = getStringFromPref(mContext, p.getMain_postId());

                    if (previousImageString != null) {

                        Bitmap bitmap = null;

                        try {
                            bitmap = convertStringToBitmap(previousImageString);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (bitmap != null) {
                            Bitmap hiResBitmap = getHiResBitmap(bitmap, 1000);
                            iv_productImage.setImageBitmap(hiResBitmap);
                            iv_productImage.setVisibility(View.VISIBLE);
                            Log.i(TAG, "onSuccess: From Shared Preference " + " PostId: " + p.getMain_postId() + " Url : " + p.getProductImageLink());
                            imageSetSucceed = true;
                        }
                    }


                    if (!imageSetSucceed) {

                        Log.i(TAG, "onSuccess:  Shared Preference fail  PostId: " + p.getMain_postId() + " Url : " + p.getProductImageLink());

                        Picasso.with(mContext).cancelRequest(iv_productImage);

                        Picasso.with(mContext).load(p.getProductImageLink()).into(iv_productImage, new Callback() {
                            @Override
                            public void onSuccess() {

                                Bitmap bitmap = convertImageViewToBitmap(iv_productImage);
                                Bitmap hiResBitmap = getHiResBitmap(bitmap, 1000);
                                iv_productImage.setImageBitmap(hiResBitmap);
                                iv_productImage.setVisibility(View.VISIBLE);
                                Log.i(TAG, "onSuccess: From WebUrl " + " Position :  " +  p.getMain_postId() + " Url : " + p.getProductImageLink());
                                String bitmapString = convertBitmapToString(bitmap);
                                saveStringToPref(mContext, p.getMain_postId(), bitmapString);
                            }

                            @Override
                            public void onError() {

                                Log.i(TAG, "onSuccess:3_0 Picasso Error: from Net  " + " Image Position Url is not Valid: " + "ImageUrl is :  " + p.getProductImageLink());

                            }
                        });
                    }

                } else {
                    Picasso.with(mContext).cancelRequest(iv_productImage);

                }


                setImage(iv_chalanImage, p.getChalan_image(), p.getOrder_id()+"challan", challanImage);
                setImage(iv_customer_or_shop_image, p.getCustomer_or_shop_image(), p.getOrder_id()+"customer", customerImage);








            } catch (JSONException e) {
                e.printStackTrace();
                Log.i(TAG, "onResponse : JSONException " + e.getMessage());
            }
        }
    }








    SpeedyImageView imgThreeDot;

    private void confirmDeleteOneRequest( final String theOrderId) {
        AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
        ad.setTitle("Confirm Delete");
        ad.setMessage("Do you really want to delete the post ? ");
        ad.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteOneRequest(theOrderId );

            }
        });
        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = ad.create();
        dialog.show();
    }

    private void deleteOneRequest(final String theOrderId) {

        Log.i(TAG, "deleteOneRequest: theOrderId:  " + theOrderId );

        Toast.makeText(mContext, "Post Deleting.....", Toast.LENGTH_SHORT).show();

        showLoading();

        class deleteOnePost extends Thread {


            boolean isDeleted = false ;

            String result = null;

            String url, one_order_id;

            public deleteOnePost(String url, String one_order_id) {
                this.url = url;
                this.one_order_id = one_order_id;

            }

            @Override
            public void run() {
                super.run();

                JSONObject jsonObject = Controller.delete_one_order_request(url, one_order_id, OriginalPostID);//  url,  userUniqueId,  postId

                Log.i(TAG, "doInBackground: one_order_id id 1 : params[1]: " + one_order_id);

                try {

                    if (jsonObject != null) {
                        result = jsonObject.getString("result");
                        Log.i(TAG, "jsonObject 1: " + result);

                        // Your Post Deleted Successfully

                        if(result.contains("Deleted Successfully")){
                            isDeleted=true ;
                        }

                    } else {
                        Log.i(TAG, "jsonObject 2: " + result);

                    }
                } catch (JSONException je) {
                    Log.i(TAG, "jsonObject 3: " + je.getLocalizedMessage());

                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        dismissLoading();

                        if (isDeleted) {

                            Toast.makeText(mContext, ""+result, Toast.LENGTH_SHORT).show();
                            finish();

                        } else if (result != null) {
                            Toast.makeText(mContext, "Error : "+result, Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(mContext, "Something Wrong, Try again Later", Toast.LENGTH_SHORT).show();

                        }


                    }
                });


            }
        }

        new deleteOnePost(Common.ORDER_REQUEST_URL, theOrderId).start();

    }



    String SUBMITTED = "SUBMITTED";
    String NOT_SUBMITTED="NOT_SUBMITTED";

    String FULL_PAID = "FULL_PAID";
    String LOW_DUE = "LOW_DUE";
    String MAJOR_DUE= "MAJOR_DUE";
    String HALF_DUE = "HALF_DUE";
    String FULL_DUE = "FULL_DUE";

    String [] paidStatusArray = {FULL_DUE, MAJOR_DUE, HALF_DUE, LOW_DUE, FULL_PAID};
    String [] unitSystemArray = {SIX_PAIR, ONE_DOZEN};



     void chooseFillOptions(String[] array, String orderId, String cellNumber, String title) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mContext);

        builder.setTitle(title+" : ");
        builder.setItems(array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String newText = array[which].toString();
                startUpdateOneCell (newText,  orderId,   cellNumber );
            }
        });


        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();


    }


    String challanImageString=null;
    Bitmap rbitmap;
    private String oneOrderId = "1";


    private void prepareUpdateOneCell( final String orderId, final String cellNumber, String titel ) {

        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mContext);
        final View cl = getLayoutInflater().inflate(R.layout.update_cell__layout, null);
        builder.setTitle(titel+" : ");

        final TextView edtCmnt = (TextView) cl.findViewById(R.id.edtCmnt);
        ImageView imgInsertCmnt = (ImageView) cl.findViewById(R.id.imgInsertCmnt);
        builder.setView(cl);


        final androidx.appcompat.app.AlertDialog alert = builder.create();

        imgInsertCmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTextValue = edtCmnt.getText().toString();
                newTextValue = newTextValue.replace("#", "").replace("*", "");
                if (TextUtils.isEmpty(newTextValue)) {
                    edtCmnt.setError("Empty");

                    return;
                }else {

                    startUpdateOneCell(newTextValue, orderId,  cellNumber );


                }
                alert.dismiss();
            }
        });

        alert.show();
    }

    void  startUpdateOneCell (String finalNewTextValue, String orderId,  String cellNumber ){
        showLoading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onRowResponse:8888 requestForInsertComment:  start:  "+ finalNewTextValue);

                updateOneCell(finalNewTextValue, orderId,  cellNumber );


            }
        }, 100);
    }

    void updateOneCell (String finalNewTextValue, String orderId, String cellNumber){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Common.ORDER_REQUEST_URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        showJSON(response);
                        Log.i(TAG, "onResponse:8888 sendRequestForOneUser  1 " + response.toString());
                        dismissLoading();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i(TAG, "onResponse:8888 sendRequestForOneUser  2 " + error.toString());
                        Toast.makeText(mContext, "error : "+error.toString(), Toast.LENGTH_SHORT).show();
                        dismissLoading();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_ACTION,"insertOnCellValue");
                    params.put("new_text", finalNewTextValue);

                params.put("order_id", orderId);
                params.put("cell_number", cellNumber);

                Log.i(TAG, "getParams: sendRequestForOneUser orderId :  " + orderId);


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




    void uploadChallanImage (String imageString, String orderId){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Common.ORDER_REQUEST_URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        removeStringFromSharedPreference(oneOrderId+"challan");
                        showJSON(response);
                        Log.i(TAG, "onResponse: sendRequestForOneUser  1 " + response.toString());
                        dismissLoading();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i(TAG, "onResponse: sendRequestForOneUser  2 " + error.toString());
                        Toast.makeText(mContext, "error : "+error.toString(), Toast.LENGTH_SHORT).show();
                        dismissLoading();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_ACTION,"insertChallanImage");
                if(imageString!=null) {
                    params.put(KEY_IMAGE, imageString);
                }else {
                    params.put(KEY_IMAGE, "null");
                }
                params.put("order_id", orderId);

                Log.i(TAG, "getParams: sendRequestForOneUser orderId :  " + orderId);


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




    private void sendRequestForOneUser(final String userUniqueId) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Common.ORDER_REQUEST_URL + "?action=read_one_user_twenty_rows",//URL_FOR_ONE_USER_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       showJSON(response);
                        Log.i(TAG, "onResponse: sendRequestForOneUser  1 " + response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i(TAG, "onResponse: sendRequestForOneUser  2 " + error.toString());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("column_down", String.valueOf(0));
                params.put("down_range", String.valueOf(0));
                params.put("userUniqueId", userUniqueId);

                Log.i(TAG, "getParams: sendRequestForOneUser userUniqueId :  " + userUniqueId);


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

    TextView txtEmpty ;
    ScrollView mainScrollView ;
    private ProgressDialog loading;
    private void showJSON(String json) {
        Log.i("Tests", "run:  14:  ");

        txtEmpty = (TextView)findViewById(R.id.txtEmpty);
        mainScrollView = (ScrollView)findViewById(R.id.mainScrollView);

        JsonParser pj = new JsonParser(json);
        pj.parseJSON();  // here Makes the AdvertiseLists - Model list for Adapter


        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }


    }

    String challanImage = "challanImage";
    String productImage = "productImage";
    String customerImage = "customerImage";

    void  setImage (ImageView imageView, String ImageLink, String id, String whichImage){
        Picasso.with(mContext).cancelRequest(imageView);

        if (ImageLink != null
                && !TextUtils.isEmpty(ImageLink)
                && !ImageLink.equalsIgnoreCase("null")) {

            final boolean[] imageSetSucceed = {false};

            String previousImageString = getStringFromPref(mContext, id);

            if (previousImageString != null) {

                Bitmap bitmap = null;

                try {
                    bitmap = convertStringToBitmap(previousImageString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (bitmap != null) {
                    Bitmap hiResBitmap = getHiResBitmap(bitmap, 1000);
                    imageView.setImageBitmap(hiResBitmap);
                    imageView.setVisibility(View.VISIBLE);
                    Log.i(TAG, "onSuccess: From Shared Preference " + " PostId: " + id + " Url : " + ImageLink);
                    imageSetSucceed[0] = true;
                }
            }


            if (!imageSetSucceed[0]) {

                Log.i(TAG, "onSuccess:  Shared Preference fail  PostId: " + id + " Url : " +ImageLink );

                Picasso.with(mContext).cancelRequest(imageView);

                Picasso.with(mContext).load(ImageLink).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                        Bitmap bitmap = convertImageViewToBitmap(imageView);
                        Bitmap hiResBitmap = getHiResBitmap(bitmap, 1000);
                        imageView.setImageBitmap(hiResBitmap);
                        imageView.setVisibility(View.VISIBLE);
                        Log.i(TAG, "onSuccess: From WebUrl " + " Position :  " +  id + " Url : " + ImageLink);
                        String bitmapString = convertBitmapToString(bitmap);
                        saveStringToPref(mContext, id, bitmapString);
                        imageSetSucceed[0] = true ;
                    }

                    @Override
                    public void onError() {

                        Log.i(TAG, "onSuccess:3_0 Picasso Error: from Net  " + " Image Position Url is not Valid: " + "ImageUrl is :  " + ImageLink);

                    }
                });
            }

            if(whichImage.equalsIgnoreCase(challanImage)) {
                if (!imageSetSucceed[0]) {
                    txtChalan_image_status.setText(NOT_SUBMITTED);
                } else {
                    txtChalan_image_status.setText(SUBMITTED);
                }
            }

        } else {
            Picasso.with(mContext).cancelRequest(imageView);

        }

    }

    private Bitmap convertImageViewToBitmap(ImageView imageView) {

        Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        return bm;
    }
    TextView  txtShopName, txtShopAddress,txtOrderDate                ,
            txtProductName              ,
            txtUnitSystem               ,
            txtUnitPrice                ,
            txtTotalOrderQuantity       ,
            txtTotalPrice             ,
            txtCustomerPhone          ,
            txtCustomerName           ,
            txtMain_postId            ,
            txtOrder_id               ,
            txtProduct_sending_status ,
            txtChalan_number_date     ,
            txtTotalPaid              ,
            txtdueMoney,
            txtPaid_status            ,
            txtChalan_image_status  ,tvChalan_imageLink ,txtHeader
            ;
    SpeedyImageView iv_customer_or_shop_image,iv_productImage,
            iv_chalanImage;

    String TAG = "OneRequest";





    androidx.appcompat.app.AlertDialog.Builder alertDialog;

    androidx.appcompat.app.AlertDialog imageDialog;

    private void showPictureFullScreen(Bitmap bitmap) {

        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        LayoutInflater inflater = this.getLayoutInflater();
        View imageAlert = inflater.inflate(R.layout.fullscreen_picture_alert, null);

        final TextView textView = (TextView) imageAlert.findViewById(R.id.dummyTxtView);
        final TouchImageView alertImageView = (TouchImageView) imageAlert.findViewById(R.id.alertImageView);


        alertImageView.setImageBitmap(bitmap);

        LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        alertImageView.setLayoutParams(params);
        alertImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        alertDialog.setView(imageAlert);


        imageDialog = alertDialog.create();

        imageDialog.show();

//       Window window = imageDialog.getWindow();
//       assert window != null;
//       window.setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.MATCH_PARENT );
//       window.setGravity(Gravity.CENTER);

        try {

            alertImageView.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public boolean onLongClick(View v) {

                    switch (v.getId()) {

                        case R.id.alertImageView:

                            PopupMenu popupMenu = new PopupMenu(mContext, textView);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_image_save, popupMenu.getMenu());
                            popupMenu.setGravity(Gravity.CENTER);

                            popupMenu.show();
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    switch (item.getItemId()) {

                                        case R.id.menu_save:

                                            Bitmap bitmap = convertImageViewToBitmap(alertImageView);
                                            saveImageToExternalStorage(bitmap);

                                            break;

                                        default:
                                            break;

                                    }


                                    return false;
                                }
                            });


                            break;

                        default:
                            break;


                    }


                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            alertImageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    switch (v.getId()) {

                        case R.id.alertImageView:

                            PopupMenu popupMenu = new PopupMenu(mContext, textView);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_image_save, popupMenu.getMenu());
                            // popupMenu.setGravity(Gravity.CENTER);

                            popupMenu.show();
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    switch (item.getItemId()) {

                                        case R.id.menu_save:

                                            Bitmap bitmap = convertImageViewToBitmap(alertImageView);
                                            saveImageToExternalStorage(bitmap);

                                            break;

                                        default:
                                            break;

                                    }


                                    return false;
                                }
                            });


                            break;

                        default:
                            break;


                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void saveImageToExternalStorage(Bitmap bitmap) {

        FileOutputStream outStream = null;

        File outFile;

        try {

            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Camera/posts";
            File dir = new File(root);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filename = String.format("%d.jpg", System.currentTimeMillis());
            outFile = new File(dir, filename);

            outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();


            MediaScannerConnection.scanFile(this, new String[]{outFile.getPath()}, new String[]{"image/jpeg"}, null);

            Toast.makeText(mContext, "Image Saving Completed in Phone", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "File Saving Failed", Toast.LENGTH_SHORT).show();

        }


    }




    void  showLoading(){
        if(loading!=null&&loading.isShowing())
            loading.dismiss();
        loading = new ProgressDialog(mContext);
        loading.setTitle("Hey wait, Please......");
        loading.setMessage("Wait a moment.........");
        loading.setCancelable(true);
        loading.setIndeterminate(false);

        loading.show();

    }





    void dismissLoading (){
        if(loading!=null&&loading.isShowing())
            loading.dismiss();

    }


    ////////////////////////////////////// upload Picture Part ///////////////////
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
                challanImageString = getStringImage(rbitmap);
                iv_chalanImage.setImageBitmap(rbitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }

    ///////////////////////////////////////////////////////////////

}