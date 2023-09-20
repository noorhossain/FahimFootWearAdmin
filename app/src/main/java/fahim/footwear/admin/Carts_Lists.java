package fahim.footwear.admin;

import static fahim.footwear.admin.Common.Common.CUSTOMER_ADDRESS_STRING;
import static fahim.footwear.admin.Common.Common.CUSTOMER_NAME_STRING;
import static fahim.footwear.admin.Common.Common.CUSTOMER_PHONE_STRING;
import static fahim.footwear.admin.Common.Common.GRAND_TOTAL_PRODUCT_ITEM;
import static fahim.footwear.admin.Common.Common.GRAND_TOTAL_QNTY;
import static fahim.footwear.admin.Common.Common.GRAND_TOTAL_TAKA;
import static fahim.footwear.admin.Common.Common.ORDER_DATE;
import static fahim.footwear.admin.Common.Common.ORDER_ID;
import static fahim.footwear.admin.Common.Common.SHOP_NAME_STRING;
import static fahim.footwear.admin.Common.Common.chalan_image;
import static fahim.footwear.admin.Common.Common.chalan_number_date;
import static fahim.footwear.admin.Common.Common.commonDir;
import static fahim.footwear.admin.Common.Common.customerAddress;
import static fahim.footwear.admin.Common.Common.customerName;
import static fahim.footwear.admin.Common.Common.customerPhone;
import static fahim.footwear.admin.Common.Common.date;
import static fahim.footwear.admin.Common.Common.due;
import static fahim.footwear.admin.Common.Common.main_postId;
import static fahim.footwear.admin.Common.Common.order_id;
import static fahim.footwear.admin.Common.Common.paid_status;
import static fahim.footwear.admin.Common.Common.productImageLink;
import static fahim.footwear.admin.Common.Common.productName;
import static fahim.footwear.admin.Common.Common.product_receiving_status;
import static fahim.footwear.admin.Common.Common.product_sending_status;
import static fahim.footwear.admin.Common.Common.shopAddress;
import static fahim.footwear.admin.Common.Common.shopName;
import static fahim.footwear.admin.Common.Common.totalOrderQuantity;
import static fahim.footwear.admin.Common.Common.totalPrice;
import static fahim.footwear.admin.Common.Common.total_paid;
import static fahim.footwear.admin.Common.Common.unitPrice;
import static fahim.footwear.admin.Common.Common.unitSystem;

import static fahim.footwear.admin.Configuration.KEY_USERS;
import static fahim.footwear.admin.Configuration.URL_FOR_FIFTY_ROWS;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ortiz.touchview.TouchImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fahim.footwear.admin.Common.Common;

import fahim.footwear.admin.Common.RequestModel;
import fahim.footwear.admin.Spreadsheet_Connection.Controller;
import fahim.footwear.admin.Spreadsheet_Connection.InternetConnection;



public class Carts_Lists extends AppCompatActivity {

    SharedPreferences settings;

    SharedPreferences.Editor editor;

    Context mContext;
    SwipeRefreshLayout layoutSwipeRefresh;


    private ListView listView;

    String userID = null;

    ImageView imgOptionsSettings;

    ProgressBar listFooterProgressView;
    TextView footerText;




    private void optionSettings() {

    }


    int arPfntcolor;


    BroadcastReceiver bgshowBroacast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String extra = intent.getStringExtra("BROADCAST");
            if (extra != null) {
                if (extra.equalsIgnoreCase("finishBgShowActivity")) {

                    finish();
                    Log.i(TAG, "onReceive: Bg_show_BroadCast receive from bg_send class ");
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        LocalBroadcastManager.getInstance(mContext).registerReceiver(bgshowBroacast, new IntentFilter("BG_SHOW_BROADCAST"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(bgshowBroacast);
    }

    ImageView ImageMenu, btnSendBiggopti, btnPendingPosts, btnProfilePosts;

    ProgressBar progressInLayout;
    int night;

    private String savedfileName = "all_requests";
    private String APP_BAR_LABEL = "SHOES";

    TextView txtHeader;


    @SuppressLint({"CommitPrefEdits", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = PreferenceManager.getDefaultSharedPreferences(Carts_Lists.this);


        setContentView(R.layout.request_list_activity);

        mContext = this;

        txtHeader = (TextView) findViewById(R.id.txtHeader);

        //  Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this, ReadingSelection.class));

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());



        Common.APP_SCRIPT_WEB_APP_URL = Common.ORDER_REQUEST_URL;



        editor = settings.edit();

        arPfntcolor = settings.getInt("arPfntcolor", 0);


        progressInLayout = (ProgressBar) findViewById(R.id.inViewProrgress);
        progressInLayout.setVisibility(View.VISIBLE);

        progressInLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressInLayout.setVisibility(View.GONE);

            }
        });


        imgOptionsSettings = (ImageView) findViewById(R.id.imgOptionsSettings);
        imgOptionsSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                optionSettings();
            }
        });


        userID = settings.getString("UserUniqueID", "01879115953");

//        if (userID == null) {
//            Log.i(TAG, "UseridFromThread: userIdNull  1");
//            new UserIdFromExternalText("UserUniqueID", fileExists("UserUniqueID")).start();
//
//        } else {
//            Log.i(TAG, "UseridFromThread: userIdNotNull  2");
//
//            new saveFilesToExternal(userID, "UserUniqueID").start();
//        }

        listView = (ListView) findViewById(R.id.list_view);
        listView.setStackFromBottom(true);
        listView.setDividerHeight(0);

        listView.setOnTouchListener(new View.OnTouchListener() {
            float height;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float height = event.getY();
                if (action == MotionEvent.ACTION_DOWN) {
                    this.height = height;
                } else if (action == MotionEvent.ACTION_UP) {
                    if (this.height < height) {
                        Log.v(TAG, "Scrolled up");
                        isListScrollDown = false;

                        //  Toast.makeText(BiggoptiShow.this, "Scroll Up", Toast.LENGTH_SHORT).show();
                    } else if (this.height > height) {
                        Log.v(TAG, "Scrolled down");
                        isListScrollDown = true;
                        // Toast.makeText(BiggoptiShow.this, "Scroll Down", Toast.LENGTH_SHORT).show();

                    }
                }
                return false;
            }
        });


        layoutSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.layoutSwipeRefresh);
        layoutSwipeRefresh.setColorSchemeResources(R.color.dark_green, R.color.colorBlue, R.color.black);
        layoutSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                columnDown = 2;
                rowsRight = 1;
                downRange = 20;
                rightRange = 26;

                listView.removeFooterView(footerText);
                listView.removeFooterView(footerText);
                listView.removeFooterView(footerText);
                listView.removeFooterView(listFooterProgressView);
                listView.addFooterView(listFooterProgressView);

                isEndOfSpreadSheet = false;

                isFromInsertComment = false;
                // sendRequestForTwentyRows();
                sendRequest();


            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                listFooterProgressView = new ProgressBar(Carts_Lists.this);
                footerText = new TextView(Carts_Lists.this);
                footerText.setText("End of Post.");
                footerText.setTextColor(Color.BLUE);


                loading = new ProgressDialog(mContext);
                loading.setTitle("Hey wait, Please......");
                loading.setMessage("Wait a moment.........");
                loading.setCancelable(true);
                loading.setIndeterminate(false);

                loading.show();
                listView.addFooterView(listFooterProgressView);
                new ReadExternalJsonText(savedfileName, fileExists(savedfileName)).start();
                layoutSwipeRefresh.setRefreshing(true);


            }
        }, 100);


    }


    boolean isListScrollDown = false;

    int columnDown = 2;
    int rowsRight = 1;
    int downRange = 20;
    int rightRange = 26;

    int loadAtATime = 5;

    int loadOccurance = 1;


    ArrayList<RequestModel> advertiseList; // = new ArrayList<>();


    ArrayList<String> keyList;

    public static final String SIX_PAIR = "সিক্স-পেয়ার";
    public static final String ONE_DOZEN = "১-ডজন";


    private class JsonParser {


        private JSONArray users = null;

        private String json;

        public JsonParser(String json) {
            this.json = json;
        }

        protected void parseJSON() {

            if (isFromInsertComment) {

                if (keyList != null)
                    keyList.clear();
                keyList = new ArrayList<>();
//                if (advertiseList != null)
//                    advertiseList.clear();

                Log.i("Tests", "run:  9:  ");

            } else {

                Log.i("Tests", "run:  36:  ");
                if (keyList != null)
                    keyList.clear();
                keyList = new ArrayList<>();

                if (advertiseList != null)
                    advertiseList.clear();
                advertiseList = new ArrayList<>();
            }


            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                Log.i(TAG, "onResponse: jsonObject: " + jsonObject.toString());

                users = jsonObject.getJSONArray(KEY_USERS);

                Log.i(TAG, "onResponse: JsonArrayLength: " + users.length());


                JSONArray reverseJsonArray = new JSONArray();

                for (int i = users.length() - 1; i >= 0; i--) {
                    try {
                        reverseJsonArray.put(users.getJSONObject(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.i(TAG, "parseJSON: Error: " + e.getMessage());
                    }
                }

//                if(users.length()<20){
//                    listView.removeFooterView(listFooterProgressView);
//                    TextView footerText = new TextView(BiggoptiShow.this);
//                    footerText.setText("Post End.");
//                    listView.addFooterView(footerText);
//                }

                if (getIntent().getStringExtra("which_date") != null) {
                    if (users.length() < 20) {
                        listView.removeFooterView(listFooterProgressView);
                        listView.removeFooterView(listFooterProgressView);
                        listView.removeFooterView(footerText);
                        listView.removeFooterView(footerText);
                        isEndOfSpreadSheet = true;
                    }
                }


                int footerCount = listView.getFooterViewsCount();

                for (int i = 0; i < users.length(); i++) {

                    //     for (int i = users.length()-1; i >= 0; i--) {


                    JSONObject jo = users.getJSONObject(i);

                    if (!jo.getString(order_id).isEmpty()) {


                        keyList.add(jo.getString(date));

                        RequestModel p = new RequestModel();




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
                        p.setTotalPrice(jo.getString(totalPrice));

                        p.setProductImageLink(jo.getString(productImageLink));

                        p.setMain_postId(jo.getString(main_postId));
                        p.setOrder_id(jo.getString(order_id));
                        p.setProduct_sending_status(jo.getString(product_sending_status));
                        p.setChalan_image(jo.getString(chalan_image));
                        p.setChalan_number_date(jo.getString(chalan_number_date));
                        p.setProduct_receiving_status(jo.getString(product_receiving_status));
                        p.setTotal_paid(jo.getString(total_paid));
                        p.setDue(jo.getString(due));


                        p.setGrand_total_qnty(jo.getString(GRAND_TOTAL_QNTY));
                        p.setGrand_total_product_item(jo.getString(GRAND_TOTAL_PRODUCT_ITEM));
                        p.setGrand_total_taka(jo.getString(GRAND_TOTAL_TAKA));

                        //  if(advertiseList!=null)

                        advertiseList.add(0, p);

                        Log.i(TAG, "parseJSON:p.getPost_id() " + p.getOrder_id());

                    } else {

                        listView.removeFooterView(listFooterProgressView);
                        listView.removeFooterView(footerText);
                        listView.addFooterView(footerText);

                        if (userListAdapter != null) {
                            try {
                                userListAdapter.notifyDataSetChanged();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        isEndOfSpreadSheet = true;

                    }


                }


                Gson gson = new Gson();
                String jsonString = gson.toJson(advertiseList);
                new saveFilesToExternal(jsonString, savedfileName).start();

                keyListToSharedPreference(keyList);

                if (foundOldDataThenFirstNetworkCall) {
                    foundOldDataThenFirstNetworkCall = false;
                    Toast.makeText(mContext, "Alhamdulillah, Post Loaded From Networdk", Toast.LENGTH_SHORT).show();
                }

                // Log.e("uImage","ser image"+uImages[0]);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i(TAG, "onResponse : JSONException " + e.getMessage());
            }
        }
    }


    String TAG = "ShowBiggopti";
    private boolean fromNetWork = false;

    private class BiggoptiLisAdapter extends ArrayAdapter<RequestModel> {

        private int resourceLayout;
        private Context mContext;
        int click = 0;


        public BiggoptiLisAdapter(@NonNull Context context, int resource, @NonNull List<RequestModel> items) {
            super(context, resource, items);
            this.resourceLayout = resource;
            this.mContext = context;

        }


        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            View v = convertView;
            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(mContext);
                v = vi.inflate(R.layout.request_list_row, null);

            }


            if (fromNetWork) {
                if (!isEndOfSpreadSheet) {

                    if (isListScrollDown) {
                        if (position == (userListAdapter.getCount()) - 10) {

                            columnDown = userListAdapter.getCount() + 2;

                            rowsRight = 1;
                            downRange = 20;
                            rightRange = 26;
                            //  Toast.makeText(mContext, "called Network from Getview: Position =  " + position + "  userListAdapter.getCount():  " + userListAdapter.getCount(), Toast.LENGTH_SHORT).show();

                            Log.i(TAG, "getView: sendRequestForFiftyRows :  " + "called Network from Getview: Position =  " + position + "  advertiseList.size():  " + userListAdapter.getCount());

                            isFromInsertComment = true; // this is very important: because the mainlist must not clear all data, // then the download data  join at the bottom of list :
                            sendRequestForTwentyRows();
                        }
                    }
                }
            }

            final RequestModel p = getItem(position);

            if (p != null) {

                TextView txtHeader = (TextView)v.findViewById(R.id.txtHeader);
                txtHeader.setText(""+(position+1));

                final TextView   txtShopName     = (TextView)v.findViewById(R.id.txtShopName);
                final TextView   txtShopAddress= (TextView)v.findViewById(R.id.txtShopAddress);
                final TextView   txtOrderDate = (TextView) v.findViewById(R.id.txtOrderDate);
                final TextView   txtGrand_total_product_item = (TextView) v.findViewById(R.id.txtGrand_total_product_item);
                final TextView   txtTotalOrderQuantity = (TextView) v.findViewById(R.id.txtTotalOrderQuantity);
                final TextView   txtTotalPrice = (TextView)v.findViewById(R.id.txtTotalPrice);
                final TextView   txtCustomerPhone= (TextView)v.findViewById(R.id.txtCustomerPhone);
                final TextView   txtCustomerName= (TextView)v.findViewById(R.id.txtCustomerName);
                final TextView   txtProduct_sending_status= (TextView)v.findViewById(R.id.txtProduct_sending_status);
                final TextView   txtPaid_status= (TextView)v.findViewById(R.id.txtPaid_status);
                final  TextView txtProduct_receiving_status = (TextView)v.findViewById(R.id.txtProduct_receiving_status);
                final  TextView txtChalan_number_date = (TextView)v.findViewById(R.id.txtChalan_number_date);

                TextView txtTotalPaid = (TextView) v.findViewById(R.id.txtTotalPaid);
                TextView txtdue = (TextView) v.findViewById(R.id.txtdue);
                TextView txtTotalReturn = (TextView) v.findViewById(R.id.txtTotalReturn);



                boolean isDueZero = false;
                int totalGrandPriceInt = 0;
                int totalPaidInt = 0 ;


                if(p.getDue()!=null&&!TextUtils.isEmpty(p.getDue())) {
                    txtdue.setText(p.getDue());

                    String due = p.getDue() ;
                    if(due.equalsIgnoreCase("0")){
                        isDueZero = true;
                    }
                }

                if(p.getGrand_total_taka()!=null&&!TextUtils.isEmpty(p.getGrand_total_taka())){

                    String s = p.getGrand_total_taka();
                    txtTotalPrice.setText(s);
                    try {
                        totalGrandPriceInt = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                if(p.getTotal_paid()!=null&&!TextUtils.isEmpty(p.getTotal_paid())) {
                    String s = p.getTotal_paid();
                    txtTotalPaid.setText(s);
                    try {
                        totalPaidInt = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                }
                // টোটাল গ্রান্ড প্রাইস থেকে টোটাল পেইড বিয়োগ হয়ে বিয়োগফল কাষ্টমারকে তখনই রিটার্ট দেখানো হবে, যখন রিটার্ন দেওয়া হবে, এবং
                 // কোন ডিউ থাকবে না। সুতরাং, যখন একমাত্র ডিউ ম্যানুয়ালী জিরো করা হবে, তখনই বিয়োগফল রিটার্ন দেখানো হবে ।
                if(isDueZero){
                    int returnInt = totalGrandPriceInt-totalPaidInt ;
                    txtTotalReturn.setText(""+returnInt);
                }

                if(p.getChalan_number_date()!=null&&!TextUtils.isEmpty(p.getChalan_number_date())){
                    txtChalan_number_date.setText(p.getChalan_number_date());
                }

                if(p.getProduct_receiving_status()!=null&&!TextUtils.isEmpty(p.getProduct_receiving_status())){
                    txtProduct_receiving_status.setText(p.getProduct_receiving_status());
                }

                if(p.getShopName()!=null&&!TextUtils.isEmpty(p.getShopName())){
                    txtShopName.setText(p.getShopName());
                }


                if(p.getShopAddress()!=null&&!TextUtils.isEmpty(p.getShopAddress())){
                    txtShopAddress.setText(p.getShopAddress());
                }

                if(p.getDate()!=null&&!TextUtils.isEmpty(p.getDate())){
                    txtOrderDate.setText(p.getDate());
                }




                // this Product GrandTotalUnit Items :
                if(p.getGrand_total_product_item()!=null&&!TextUtils.isEmpty(p.getGrand_total_product_item())){
                    txtGrand_total_product_item.setText(p.getGrand_total_product_item());
                }

//                p.setGrand_total_qnty(jo.getString(GRAND_TOTAL_QNTY));
//                p.setGrand_total_product_item(jo.getString(GRAND_TOTAL_PRODUCT_ITEM));
//                p.setGrand_total_taka(jo.getString(GRAND_TOTAL_TAKA));

                if(p.getGrand_total_qnty()!=null&&!TextUtils.isEmpty(p.getGrand_total_qnty())){
                    txtTotalOrderQuantity.setText(p.getGrand_total_qnty());
                }

                if(p.getGrand_total_taka()!=null&&!TextUtils.isEmpty(p.getGrand_total_taka())){
                    txtTotalPrice.setText(p.getGrand_total_taka());
                }

                if(p.getCustomerPhone()!=null&&!TextUtils.isEmpty(p.getCustomerPhone())){
                    txtCustomerPhone.setText(p.getCustomerPhone());
                }

                if(p.getCustomerName()!=null&&!TextUtils.isEmpty(p.getCustomerName())){
                    txtCustomerName.setText(p.getCustomerName());
                }

                if(p.getProduct_sending_status()!=null&&!TextUtils.isEmpty(p.getProduct_sending_status())){
                    txtProduct_sending_status.setText(p.getProduct_sending_status());
                }

                if(p.getPaid_status()!=null&&!TextUtils.isEmpty(p.getPaid_status())){
                    txtPaid_status.setText(p.getPaid_status());
                }


                LinearLayout ll_order_details = (LinearLayout) v.findViewById(R.id.ll_order_details);

                ll_order_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(mContext, Cart_One_Edit.class );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(order_id, p.getOrder_id());
                        intent.putExtra(main_postId, p.getMain_postId());
                        CUSTOMER_NAME_STRING = p.getCustomerName();
                        Log.i(TAG, "mmmm CUSTOMER_NAME_STRING: "+CUSTOMER_NAME_STRING);
                        CUSTOMER_PHONE_STRING= p.getCustomerPhone();
                        CUSTOMER_ADDRESS_STRING=p.getShopAddress();
                        ORDER_DATE = p.getDate();
                        ORDER_ID = p.getOrder_id();

                        SHOP_NAME_STRING = p.getShopName();

                        intent.putExtra("productName", p.getProductName());
                        Log.i(TAG, "mmmm p.getProductName(): "+p.getProductName());

                        intent.putExtra("unitPrice", p.getUnitPrice());
                        intent.putExtra("totalPrice", p.getTotalPrice());
                        intent.putExtra("totalOrderQuantity", p.getTotalOrderQuantity());

                        mContext.startActivity(intent);
                    }
                });


                LinearLayout ll_order_challan = (LinearLayout) v.findViewById(R.id.ll_order_challan);

                ll_order_challan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(mContext, Cart_One_Show_Activity.class );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(order_id, p.getOrder_id());
                        intent.putExtra(main_postId, p.getMain_postId());

                        CUSTOMER_NAME_STRING = p.getCustomerName();
                        Log.i(TAG, "mmmm CUSTOMER_NAME_STRING: "+CUSTOMER_NAME_STRING);
                        CUSTOMER_PHONE_STRING= p.getCustomerPhone();
                        CUSTOMER_ADDRESS_STRING=p.getShopAddress();
                        ORDER_DATE = p.getDate();
                        ORDER_ID = p.getOrder_id();

                        SHOP_NAME_STRING = p.getShopName();

                        intent.putExtra("productName", p.getProductName());
                        intent.putExtra("unitPrice", p.getUnitPrice());
                        intent.putExtra("totalPrice", p.getTotalPrice());
                        intent.putExtra("totalOrderQuantity", p.getTotalOrderQuantity());
                        intent.putExtra(productImageLink, p.getProductImageLink());

                        mContext.startActivity(intent);
                    }
                });

            }

            return v;
        }

        @Nullable
        @Override
        public RequestModel getItem(int position) {
            return super.getItem(super.getCount() - position - 1);
        }

        @Override
        public boolean isEnabled(int position) {
            //return super.isEnabled(position);
            return false;
        }
    }




///////////////////////////////////////////////////////////////////////////////////////////


    class ReadExternalJsonText extends Thread {

        File fileAlreadyFound;
        String filename;

        public ReadExternalJsonText(String filename, File file) {

            this.fileAlreadyFound = file;
            this.filename = filename;

        }

        @Override
        public void run() {
            super.run();

            if (advertiseList != null)
                advertiseList.clear();
            advertiseList = new ArrayList<>();

            List<String> lineList = new ArrayList<>();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileAlreadyFound));

                String myLine;

                while (true) {


                    if ((myLine = reader.readLine()) != null) {
                        lineList.add(myLine);


                        // here:  loop "While" is finished :
                    } else {
                        String jsonToString = TextUtils.join("", lineList);

                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<RequestModel>>() {
                        }.getType();
                        advertiseList = gson.fromJson(jsonToString, type);

                        break;
                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (advertiseList != null && advertiseList.size() > 0) {
                        fromNetWork = false;
                        userListAdapter = new BiggoptiLisAdapter(mContext, R.layout.request_list_row, advertiseList);//AssignOnly, For Later Use, on Network Call

                        listView.setAdapter(userListAdapter);
                        listView.setSelection(0); // because we feed the Main list in reverse array, in parseJason,   Mainlist.add(0, p) ; So, the list jumps back to bottom ;

                        userListAdapter.notifyDataSetChanged();

                        if (loading != null && loading.isShowing()) {
                            loading.dismiss();
                        }
                        Log.i("Tests", "run:  1:  ");
                        // Now Load  From The Server :
                        foundOldDataThenFirstNetworkCall = true;

                    } else {

                        if (loading != null && !loading.isShowing())
                            loading.show();
                        Log.i("Tests", "run:  2:  ");

                    }
                    sendRequest();


                }
            });

        }

    }

    boolean foundOldDataThenFirstNetworkCall = false;

    boolean isEndOfSpreadSheet = false;


    private void showJSON(String json) {
        Log.i("Tests", "run:  14:  ");
        fromNetWork = true;

        JsonParser pj = new JsonParser(json);
        pj.parseJSON();  // here Makes the AdvertiseLists - Model list for Adapter

        progressInLayout.setVisibility(View.GONE);
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
        if (layoutSwipeRefresh != null) {
            layoutSwipeRefresh.setRefreshing(false);
        }


        if (isFromInsertComment) {
            if (userListAdapter != null) {
                // this is important : because after adding new data the main list remain in the same position
                runOnUiThread(new Runnable() {

                    public void run() {
                        // save index and top position
                        int index = listView.getFirstVisiblePosition();
                        View v = listView.getChildAt(0);
                        int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());
                        userListAdapter = new BiggoptiLisAdapter(Carts_Lists.this, R.layout.request_list_row, advertiseList);
                        userListAdapter.notifyDataSetChanged();
                        listView.setSelectionFromTop(index, top);
                        Log.i("Tests", "run:  15:  ");
                    }
                });

            }
        } else {
            Log.i("Tests", "run:  16:  ");

            userListAdapter = new BiggoptiLisAdapter(this, R.layout.request_list_row, advertiseList);
            listView.setAdapter(null);
            listView.setAdapter(userListAdapter);
            userListAdapter.notifyDataSetChanged();
            listView.setSelection(0); // because we feed the Main list in reverse array, in parseJason,   Mainlist.add(0, p) ; So, the list jumps back to bottom ;
        }

    }



    class saveFilesToExternal extends Thread {
        String jsonString;
        String fileName;

        saveFilesToExternal(String jsonString, String fileName) {
            this.jsonString = jsonString;
            this.fileName = fileName;
        }

        @Override
        public void run() {
            super.run();
            writeJsonFileToExternal(jsonString, fileName);
        }
    }

    private void writeJsonFileToExternal(String jsonContents, String fileName) {


        File dir = commonDir();
        if (!dir.exists())
            dir.mkdir();
        File subDir = new File(dir + "/Posts_Contents");
        if (!subDir.exists())
            subDir.mkdir();
        File trpleDir = new File(subDir + "/PostsContens");
        if (!trpleDir.exists())
            trpleDir.mkdir();

        File file = new File(trpleDir, fileName + ".txt");
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            os.write(jsonContents.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    class UserIdFromExternalText extends Thread {

        File fileAlreadyFound;
        String filename;

        public UserIdFromExternalText(String filename, File file) {

            this.fileAlreadyFound = file;
            this.filename = filename;

        }

        @Override
        public void run() {
            super.run();


            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileAlreadyFound));

                String myLine;

                while (true) {


                    if ((myLine = reader.readLine()) != null) {
                        userID = myLine;
                        Log.i(TAG, "UseridFromThread: myline:  " + userID);

                    } else {
                        break;
                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Log.i(TAG, "UseridFromThread: 0 " + userID);

                    if (userID == null) {
                        userID = UUID.randomUUID().toString();
                        editor.putString("UserUniqueID", userID);
                        editor.apply();
                        new saveFilesToExternal(userID, "UserUniqueID").start();
                        Log.i(TAG, "UseridFromThread: 1 " + userID);
                    } else {
                        editor.putString("UserUniqueID", userID);
                        editor.apply();
                        new saveFilesToExternal(userID, "UserUniqueID").start();
                        Log.i(TAG, "UseridFromThread: 2 " + userID);

                    }

                }
            });

        }

    }


    private File fileExists(String fileName) {


        File dir = commonDir();
        if (!dir.exists())
            dir.mkdir();
        File subDir = new File(dir + "/Posts_Contents");
        if (!subDir.exists())
            subDir.mkdir();
        File trpleDir = new File(subDir + "/PostsContens");
        if (!trpleDir.exists())
            trpleDir.mkdir();

        File file = null;
        file = new File(trpleDir, fileName + ".txt");
        if (!file.exists())
            file = new File(trpleDir, fileName);

        if (file.exists())
            return file;
        else return new File("noFile");

    }


    BiggoptiLisAdapter savedAdapter;

    private void keyListToSharedPreference(ArrayList<String> keyList) {
        // showToast(mContext, "keyListToSharedPreference", Toast.LENGTH_SHORT);
        Set<String> set = new HashSet<String>();
        set.addAll(keyList);
        editor.putStringSet("idKeyList", set);
        editor.apply();

        //  showToast(mContext, "keyList: "+ keyList.toString(), Toast.LENGTH_SHORT);

    }

   private   ProgressDialog loading;


    boolean isToastShown1 = false;

    private void sendRequest() {

        if (!InternetConnection.checkConnection(mContext)) {

            showToast(mContext, "Check Internet Connections Then Refresh The Screen", Toast.LENGTH_LONG);


            dismissAllProgress();

        } else {

            Log.i(TAG, "sendRequest: 1  " + Common.APP_SCRIPT_WEB_APP_URL);

                // sendRequestForadvertises();
                sendRequestForTwentyRows();

                Log.i(TAG, "sendRequest: 3 APP_SCRIPT_WEB_APP_URL:   " + Common.APP_SCRIPT_WEB_APP_URL);


                if (layoutSwipeRefresh != null) {
                    layoutSwipeRefresh.setRefreshing(false);
                    layoutSwipeRefresh.setRefreshing(true);
                }
                if (!isToastShown1) {
                    isToastShown1 = true;
                    showToast(mContext, "New Post Loading, please wait.....", Toast.LENGTH_LONG);
                }
                Log.i("Tests", "run:  5:  ");
                dismissAllProgress();


        }


    }

    private void dismissAllProgress() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (layoutSwipeRefresh != null)
                    layoutSwipeRefresh.setRefreshing(false);

                progressInLayout.setVisibility(View.GONE);

            }
        }, 10000);
    }


    private void sendRequestForTwentyRows() {


        Log.i(TAG, "onClick:  sendRequestForTwentyRows: 1" + Common.APP_SCRIPT_WEB_APP_URL);


        StringRequest stringRequest = null;

        String requestForOnedate = null;

        if (getIntent() != null) {
            if (getIntent().getStringExtra("which_date") != null) {


                requestForOnedate = getIntent().getStringExtra("which_date");
                Log.i(TAG, "onResponse: ShowBiggopti WhichDate :  " + requestForOnedate);

            }
        }


        /////////sendRequestForTwentyRows : /////////////////////
        Log.i(TAG, "onResponse: StringRequest 2  :  ");

        Log.i(TAG, "onClick:  sendRequestForTwentyRows: 3 " + Common.APP_SCRIPT_WEB_APP_URL + " ::: URL_FOR_FIFTY_ROWS:  " + URL_FOR_FIFTY_ROWS);

        stringRequest = new StringRequest(Request.Method.POST, Common.APP_SCRIPT_WEB_APP_URL + "?action=read_fifty_rows", // URL_FOR_FIFTY_ROWS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                        Log.i(TAG, "onResponse: BiggoptiShow 20_rows:  " + response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i(TAG, "onResponse: BiggoptiShow 20_rows:  " + error.toString());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("column_down", String.valueOf(columnDown));
                params.put("down_range", String.valueOf(downRange));

                Log.i(TAG, "getParams: columnDown :  " + columnDown);
                Log.i(TAG, "getParams: downRange:  " + downRange);

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


    private void sendRequestForOneUser(final String userUniqueId, final int columnDown, final int downRange) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Common.APP_SCRIPT_WEB_APP_URL + "?action=read_one_user_twenty_rows",//URL_FOR_ONE_USER_DATA,
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
                params.put("column_down", String.valueOf(columnDown));
                params.put("down_range", String.valueOf(downRange));
                params.put("userUniqueId", userUniqueId);

                Log.i(TAG, "getParams: sendRequestForOneUser userUniqueId :  " + userUniqueId);
                Log.i(TAG, "getParams: sendRequestForOneUser columnDown :  " + columnDown);
                Log.i(TAG, "getParams: sendRequestForOneUser downRange:  " + downRange);

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


    BiggoptiLisAdapter userListAdapter;


    ArrayList<String> keyList2;

    public String[] uIds;
    public String[] uNames;
    public String[] uImages;

    public String[] uPhones;
    public String[] uWebUrl;
    public String[] uAdText;




    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();

    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }


    private String pvcm(String text) {

        Log.i(TAG, "pvcm: 1 text: " + text);

        String onlyComment = "";

        if (text.contains("*")) {
            String[] ar = text.split("\\*");
            if (ar[0].contains(".    :  ")) {
                String[] br = ar[0].split("\\. {4}: {2}");
                if (br.length > 1) {
                    onlyComment = br[1];
                    Log.i(TAG, "pvcm: 2 onlyComment: " + onlyComment);
                    Log.i(TAG, "pvcm: 5 ar[0]: " + ar[0]);
                }

            } else {
                Log.i(TAG, "pvcm: 5 ar[0]: " + ar[0]);

            }
        } else {
            onlyComment = text;
            Log.i(TAG, "pvcm: 3 onlyComment: " + onlyComment);

        }
        Log.i(TAG, "pvcm: 4 onlyComment: " + onlyComment);

        return onlyComment;
    }

    private SpannableString changeIdColorSpan(String text) {

        SpannableString hashText = new SpannableString(text);
        //  Matcher matcher = Pattern.compile("#.*").matcher(hashText);
        Pattern pattern = Pattern.compile("\\*.*");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            hashText.setSpan(new ForegroundColorSpan(Color.WHITE), matcher.start(), matcher.end(), 0);
        }

        return hashText;

    }

    private String rmv(String text) {

        String onlyComment = "";

        if (text.contains("*")) {
            String[] ar = text.split("\\*");
            onlyComment = ar[0];
        } else {
            onlyComment = text;
        }
        return onlyComment;
    }

    private CharSequence formattedComnt(String commentAndName) {

        String fullCmnt = rmv(commentAndName);
        String userName = fullCmnt.replaceAll("\\. {4}: {2}.*", "");
        String onlyComment = fullCmnt.replaceAll(".*?\\. {4}: {2}", "");

        SpannableString sName = new SpannableString(userName);
        SpannableString sCmnt = new SpannableString(onlyComment);
        sName.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, userName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sName.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.black)), 0, userName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sCmnt.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.black)), 0, onlyComment.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        CharSequence formattedComnt = TextUtils.concat(sName, "\n", sCmnt);

        return formattedComnt;

    }

    private SpannableString changeNameColorSpan(String text) {

        SpannableString hashText = new SpannableString(text);
        //  Matcher matcher = Pattern.compile("#.*").matcher(hashText);
        Pattern pattern = Pattern.compile(".*?\\. {4}: {2}");//.replaceAll("\\. {4}: {2}", "\n")
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            hashText.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            hashText.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.black)), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        return hashText;

    }

    private Spannable useNameColor(String text) {

        Spannable name = new SpannableString(text);

        name.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.dark_purple2))
                , 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return name;

    }

    private boolean isComentFound = false;

    private boolean isCommented(String commentText) {

        if (commentText.contains(userID))
            return true;
        else
            return false;
    }

    boolean isFromInsertComment = false;

    Toast toast;

    private void showToast(Context context, String text, int length) {

        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(context, text, length);
        toast.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                toast.cancel();
            }
        }, 2000);


    }



    private void confirmDeletePost(final String userUniqueId, final String postId, final CardView cardView) {
        AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
        ad.setTitle("Confirm Delete");
        ad.setMessage("Do you really want to delete the post ? ");
        ad.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deletePost(userUniqueId, postId, cardView);

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

    private void deletePost(final String userUniqueId, final String postId, final CardView cardView) {

        Log.i(TAG, "deletePost: postId:  " + postId + " userUniqueId: " + userUniqueId);

        Toast.makeText(mContext, "Post Deleting.....", Toast.LENGTH_SHORT).show();

        class deleteOnePost extends Thread {

            String result = null;

            String url, userUniqueId, postId;

            public deleteOnePost(String url, String userUniqueId, String postId) {
                this.url = url;
                this.userUniqueId = userUniqueId;
                this.postId = postId;
            }

            @Override
            public void run() {
                super.run();

                JSONObject jsonObject = Controller.biggoptiShow_delete_post(url, userUniqueId, postId);//  url,  userUniqueId,  postId

                Log.i(TAG, "doInBackground: uPhones id 1 : params[1]: " + userUniqueId);

                try {

                    if (jsonObject != null) {
                        result = jsonObject.getString("result");
                        Log.i(TAG, "jsonObject 1: " + result);

                    } else {
                        Log.i(TAG, "jsonObject 2: " + result);

                    }
                } catch (JSONException je) {
                    Log.i(TAG, "jsonObject 3: " + je.getLocalizedMessage());

                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        cardView.setVisibility(View.GONE);
                        if (result != null && result.equalsIgnoreCase("Your Post Deleted Successfully")) {

                            isFromInsertComment = true;
                            showToast(mContext, "Your Post Deleted Successfully", Toast.LENGTH_LONG);
                            cardView.setVisibility(View.GONE);
                            //sendRequestForadvertises();
                            sendRequestForTwentyRows();


                        } else if (result != null) {
                            //  showToast(mContext, result, Toast.LENGTH_SHORT);
                        } else {
                            // showToast(mContext, "Cannot Delete Post", Toast.LENGTH_SHORT);

                        }


                    }
                });


            }
        }

        new deleteOnePost(Common.APP_SCRIPT_WEB_APP_URL, userUniqueId, postId).start();

    }


    void toNotifyDatasetChanged() {

        if (userListAdapter != null) {
            ((BaseAdapter) userListAdapter).notifyDataSetChanged();
        }

    }


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

            showToast(mContext, "Image Saving Completed in Phone", Toast.LENGTH_LONG);


        } catch (Exception e) {
            e.printStackTrace();

            showToast(mContext, "File Saving Failed", Toast.LENGTH_LONG);


        }


    }

    public static String convertUTCtoJavaTime(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat destFormat = new SimpleDateFormat("'Date: ' dd.MM.yyyy  'Time: ' HH:mm:ss");
        sourceFormat.setTimeZone(utc);
        Date convertedDate = sourceFormat.parse(dateStr);
        assert convertedDate != null;
        return destFormat.format(convertedDate);
    }

    public Bitmap getResizedBitmap(Bitmap bitmap, int newWidth, int newHeight) {

        Bitmap resizedBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        float scaleX = newWidth / (float) bitmap.getWidth();
        float scaleY = newHeight / (float) bitmap.getHeight();
        float pivotX = 0;
        float pivotY = 0;
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);
        Canvas canvas = new Canvas(resizedBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));
        return resizedBitmap;

    }

    private Bitmap convertImageViewToBitmap(ImageView imageView) {

        Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        return bm;
    }

    private void listView_bitmapToStringToSharedPreference(final Bitmap bitmap, final String shreKey) {


        @SuppressLint("StaticFieldLeak")
        class saveBitmapToBackGround extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... urls) {

                String encodedImage = null;


                //   Bitmap realImage = convertImageViewToBitmap(imgView);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();

                encodedImage = Base64.encodeToString(b, Base64.DEFAULT);


                return encodedImage;
            }

            @Override
            protected void onPostExecute(String encodedImageString) {
                super.onPostExecute(encodedImageString);

                editor.putString(shreKey, encodedImageString);
                editor.apply();
            }
        }

        new saveBitmapToBackGround().execute();
    }


    private void saveImageAndSavePathToSharedPrefrence(final Bitmap bitmap, final String imageName) {


        @SuppressLint("StaticFieldLeak")
        class saveBitmapToBackGround extends AsyncTask<String, Void, String> {
            FileOutputStream outStream = null;

            File outFile;
            String outfilePathString = "";

            @Override
            protected String doInBackground(String... urls) {

                try {
                    String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Posts";
                    File dir = new File(root);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    String filename = imageName + ".jpg";
                    outFile = new File(dir, filename);

                    outStream = new FileOutputStream(outFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                    outfilePathString = outFile.getAbsolutePath();

                    MediaScannerConnection.scanFile(mContext.getApplicationContext(), new String[]{outFile.getPath()}, new String[]{"image/jpeg"}, null);

                    outfilePathString = outFile.getAbsolutePath();

                    return outfilePathString;


                } catch (Exception e) {
                    e.printStackTrace();
                    return outfilePathString = null;
                }


            }

            @Override
            protected void onPostExecute(String filePathString) {

                super.onPostExecute(filePathString);

                if (filePathString == null) {
                    showToast(mContext, "File Saving Completed in Directory: " + filePathString, Toast.LENGTH_LONG);
                    Log.i(TAG, "onSuccess: " + "File Saving Completed in Directory: " + filePathString);
                }

            }
        }

        new saveBitmapToBackGround().execute();
    }


    private String saveImageToExternalStorageAndGetPathString(Context mContext, Bitmap bitmap) {

        FileOutputStream outStream = null;

        File outFile;
        String outfilePathString = "";
        try {

            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Camera/ads";
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


            MediaScannerConnection.scanFile(mContext.getApplicationContext(), new String[]{outFile.getPath()}, new String[]{"image/jpeg"}, null);

            showToast(mContext, "File Saving Completed in Directory", Toast.LENGTH_LONG);

            outfilePathString = outFile.getAbsolutePath();


        } catch (Exception e) {
            e.printStackTrace();

            showToast(mContext, "File Saving Failed", Toast.LENGTH_LONG);


        }


        if (!outfilePathString.equals("")) {
            return outfilePathString;
        } else {
            showToast(mContext, "Image PathString in NULL", Toast.LENGTH_LONG);
            return "null";
        }


    }

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    private boolean checkAndRequestPermissions() {

        int permissionWriteExtarnalStorage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionReadExternalStorage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionWriteExtarnalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionReadExternalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }


        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    @SuppressWarnings("ConstantConditions")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);

                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions

                    if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

                    ) {
                        showToast(this, "Jajakumullah For Granting Permissions", Toast.LENGTH_SHORT);


                        //else any one or both the permissions are not granted
                    } else {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)


                        ) {
                            showDialogOK("Necessary Permissions required for this app",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions();
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // proceed with logic by disabling the related features or quit the app.
                                                    showToast(Carts_Lists.this, "Necessary Permissions required for this app", Toast.LENGTH_LONG);
                                                    // permissionSettingScreen ( );
                                                    //  finish();
                                                    break;
                                            }
                                        }
                                    });
                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {

                            permissionSettingScreen();

                            // checkAndRequestPermissions();
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }

            }
        }

    }

    private void permissionSettingScreen() {
        showToast(this, "Enable All permissions, Click On Permission", Toast.LENGTH_LONG);

        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
        // finishAffinity();
        finish();

    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    public void go_to_website(String urlString) {

        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.setClassName("com.google.android.googlequicksearchbox", "com.google.android.googlequicksearchbox.SearchActivity");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
            intent.putExtra("query", urlString);
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
            intent.putExtra(SearchManager.QUERY, urlString);
            mContext.startActivity(intent);
        }
    }


}


//    private void keyListFromSharedPrefernce() {
//        //  showToast(mContext, "keyListFromSharedPrefernce", Toast.LENGTH_SHORT);
//        if (advertiseList != null)
//            advertiseList.clear();
//        advertiseList = new ArrayList<>();
//
//        ArrayList<String> keyListShre = new ArrayList<String>();
//
//        Set<String> set = settings.getStringSet("idKeyList", null);
//        if (set != null) {
//            keyListShre.addAll(set);
//
//            Collections.reverse(keyListShre);
//
//            for (int i = 0; i < keyListShre.size(); i++) {
//
//                // retriveObjectFromShre(keyListShre.get(i));
//
//                RequestModel listViewAdModel = new RequestModel();
//                String previousImagePath = settings.getString("uImages" + keyListShre.get(i), "");
//                String uid_ = settings.getString("uId" + keyListShre.get(i), "");
//                String uNames_ = settings.getString("uNames" + keyListShre.get(i), "");
//                String uPhones_ = settings.getString("uPhones" + keyListShre.get(i), "");
//                String adText_ = settings.getString("uAdText" + keyListShre.get(i), "");
//                String webUrl_ = settings.getString("uWebUrl" + keyListShre.get(i), "");
//
//
//                listViewAdModel.setIds(keyListShre.get(i));
//                listViewAdModel.setImageExternalPath(previousImagePath);
//                listViewAdModel.setName(uNames_);
//                listViewAdModel.setPhone(uPhones_);
//                listViewAdModel.setAdText(adText_);
//                listViewAdModel.setWebUrl(webUrl_);
//
//
//                advertiseList.add(listViewAdModel);
//
//                // showToast(mContext, "keyListShre PPP:"+keyListShre.get(i), Toast.LENGTH_SHORT);
//            }
//
//
//            String[] idArray = new String[keyListShre.size()];
//            idArray = keyListShre.toArray(idArray);
//
//            fromNetWork = false;
//            savedAdapter = new BiggoptiLisAdapter(this, R.layout.request_list_row, advertiseList);
//            listView.setAdapter(savedAdapter);
//            savedAdapter.notifyDataSetChanged();
//
//            // Now Load  From The Server :
//            sendRequest();
//
//        } else {
//            if(loading!=null&&!loading.isShowing())
//                loading.show();
//            sendRequest();
//
//        }
//
//
//    }