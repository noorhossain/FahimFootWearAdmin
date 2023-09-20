package fahim.footwear.admin;

import static fahim.footwear.admin.Common.Common.ACTIVITY_LABEL;
import static fahim.footwear.admin.Common.Common.CUSTOMER_ADDRESS;
import static fahim.footwear.admin.Common.Common.CUSTOMER_NAME;
import static fahim.footwear.admin.Common.Common.CUSTOMER_PHONE;
import static fahim.footwear.admin.Common.Common.ONE_DOZEN;
import static fahim.footwear.admin.Common.Common.SIX_PAIR;
import static fahim.footwear.admin.Common.Common.WHICH_SHOES;
import static fahim.footwear.admin.Common.Common.commonDir;
import static fahim.footwear.admin.Common.CommonImage.convertBitmapToString;
import static fahim.footwear.admin.Common.CommonImage.convertStringToBitmap;
import static fahim.footwear.admin.Common.CommonImage.getHiResBitmap;
import static fahim.footwear.admin.Common.CommonImage.getStringFromPref;
import static fahim.footwear.admin.Common.CommonImage.saveStringToPref;
import static fahim.footwear.admin.Configuration.KEY_ADTEXT;
import static fahim.footwear.admin.Configuration.KEY_CMNT1;
import static fahim.footwear.admin.Configuration.KEY_CMNT10;
import static fahim.footwear.admin.Configuration.KEY_CMNT2;
import static fahim.footwear.admin.Configuration.KEY_CMNT3;
import static fahim.footwear.admin.Configuration.KEY_CMNT4;
import static fahim.footwear.admin.Configuration.KEY_CMNT5;
import static fahim.footwear.admin.Configuration.KEY_CMNT6;
import static fahim.footwear.admin.Configuration.KEY_CMNT7;
import static fahim.footwear.admin.Configuration.KEY_CMNT8;
import static fahim.footwear.admin.Configuration.KEY_CMNT9;
import static fahim.footwear.admin.Configuration.KEY_EXTRA;
import static fahim.footwear.admin.Configuration.KEY_ID;
import static fahim.footwear.admin.Configuration.KEY_IMAGE;
import static fahim.footwear.admin.Configuration.KEY_NAME;
import static fahim.footwear.admin.Configuration.KEY_PHONE;
import static fahim.footwear.admin.Configuration.KEY_USERS;
import static fahim.footwear.admin.Configuration.KEY_WEBURL;
import static fahim.footwear.admin.Configuration.LIST_USER_URL_BIGPTI_PASS;
import static fahim.footwear.admin.Configuration.POST_ID;
import static fahim.footwear.admin.Configuration.URL_FOR_FIFTY_ROWS;
import static fahim.footwear.admin.Configuration.USER_ID;

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
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.EditText;
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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fahim.footwear.admin.Common.Common;
import fahim.footwear.admin.Common.PostsModel;

import fahim.footwear.admin.Spreadsheet_Connection.Controller;
import fahim.footwear.admin.Spreadsheet_Connection.InternetConnection;

//import static fahim.footwear.admin.Configuration.APP_SCRIPT_WEB_APP_URL;
//import android.support.v7.app.AppCompatActivity;

public class BiggoptiShow extends AppCompatActivity {

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

    private String shoe_catagory = "shoe_catagory";
    private String APP_BAR_LABEL = "SHOES";

    TextView txtHeader;


    @SuppressLint({"CommitPrefEdits", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = PreferenceManager.getDefaultSharedPreferences(BiggoptiShow.this);


        setContentView(R.layout.biggopti_show);

        mContext = this;

        txtHeader = (TextView) findViewById(R.id.txtHeader);

        //  Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this, ReadingSelection.class));

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        if (getIntent() != null) {
            if (getIntent().getStringExtra(WHICH_SHOES) != null) {
                shoe_catagory = getIntent().getStringExtra(WHICH_SHOES);
                Log.i(TAG, "onCreate: ShowBiggopti WHICH_SHOES :  " + shoe_catagory);

                APP_BAR_LABEL = getIntent().getStringExtra(ACTIVITY_LABEL);
                txtHeader.setText(APP_BAR_LABEL);

            }
        }

        switch (shoe_catagory) {


            case Common.LADIES_PONSE:
                Common.APP_SCRIPT_WEB_APP_URL = Common.LADIES_PONSE_URL;
                Log.i(TAG, "onCreate: switch  : LADIES_PONSE " + Common.LADIES_PONSE);

                break;
            case Common.JENTS_PONSE:
                Common.APP_SCRIPT_WEB_APP_URL = Common.JENTS_PONSE_URL;
                break;
            case Common.KIDS_PONSE:
                Common.APP_SCRIPT_WEB_APP_URL = Common.KIDS_PONSE_URL;
                break;
            case Common.LADIES_BARMIZE:
                Common.APP_SCRIPT_WEB_APP_URL = Common.LADIES_BARMIZE_URL;
                break;
            case Common.JENTS_BARMIZE:
                Common.APP_SCRIPT_WEB_APP_URL = Common.JENTS_BARMIZE_URL;
                break;

            case Common.KIDS_BARMIZE:
                Common.APP_SCRIPT_WEB_APP_URL = Common.KIDS_BARMIZE_URL;
                break;

            case Common.JENTS_CHOTI:
                Common.APP_SCRIPT_WEB_APP_URL = Common.JENTS_CHOTI_URL;
                break;

            case Common.MAILA_LADIES:
                Common.APP_SCRIPT_WEB_APP_URL = Common.MAILA_LADIES_URL;
                break;

            case Common.KANGAROO:
                Common.APP_SCRIPT_WEB_APP_URL = Common.KANGAROO_URL;
                break;

            case Common.CADES:
                Common.APP_SCRIPT_WEB_APP_URL = Common.CADES_URL;
                break;

            case Common.WINTER_COLLECTIONS:
                Common.APP_SCRIPT_WEB_APP_URL = Common.WINTER_COLLECTIONS_URL;
                break;

            case Common.EID_COLLECTIONS:
                Common.APP_SCRIPT_WEB_APP_URL = Common.EID_COLLECTIONS_URL;
                break;

            case Common.MISCELLINIOUS:
                Common.APP_SCRIPT_WEB_APP_URL = Common.MISCELLINIOUS_URL;
                break;

            case Common.ALL_CATEGORY:
                Common.APP_SCRIPT_WEB_APP_URL = Common.ALL_CATEGORY_URL;
                break;


        }


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

                listFooterProgressView = new ProgressBar(BiggoptiShow.this);
                footerText = new TextView(BiggoptiShow.this);
                footerText.setText("End of Post.");
                footerText.setTextColor(Color.BLUE);


                loading = new ProgressDialog(mContext);
                loading.setTitle("Hey wait, Please......");
                loading.setMessage("Wait a moment.........");
                loading.setCancelable(true);
                loading.setIndeterminate(false);

                loading.show();
                listView.addFooterView(listFooterProgressView);
                new ReadExternalJsonText(shoe_catagory, fileExists(shoe_catagory)).start();
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


    ArrayList<PostsModel> advertiseList; // = new ArrayList<>();


    ArrayList<String> keyList;




    String TAG = "ShowBiggopti";
    private boolean fromNetWork = false;

    private class BiggoptiLisAdapter extends ArrayAdapter<PostsModel> {

        private int resourceLayout;
        private Context mContext;
        int click = 0;


        public BiggoptiLisAdapter(@NonNull Context context, int resource, @NonNull List<PostsModel> items) {
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
                v = vi.inflate(R.layout.list_row, null);

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

            final PostsModel p = getItem(position);

            if (p != null) {

                final TextView txtStockOut = (TextView) v.findViewById(R.id.txtStockOut);
                TextView tv_date_time = (TextView) v.findViewById(R.id.tv_uid);
                final TextView textViewName = (TextView) v.findViewById(R.id.tv_uname);
                final TextView tv_uPhone = (TextView) v.findViewById(R.id.tv_uPhone);
                final TextView tvPhotoLink = (TextView) v.findViewById(R.id.tvPhotoLink);
                final TextView tv_uAdText = (TextView) v.findViewById(R.id.tv_uAdText);
                final TextView txtShowMore = (TextView) v.findViewById(R.id.txtShowMore);
                final TextView txtHeader = (TextView) v.findViewById(R.id.txtHeader);

                final SpeedyImageView iv = (SpeedyImageView) v.findViewById(R.id.imageView3);
                final ImageView imgLikeWhite = (ImageView) v.findViewById(R.id.imgLikeOn);
                final ImageView imgLikeBlue = (ImageView) v.findViewById(R.id.imgLikeOff);
                final TextView tvShowCmnt = (TextView) v.findViewById(R.id.tvShowCmnt);

                final TextView txt1 = (TextView) v.findViewById(R.id.txt1);
                final TextView txt2 = (TextView) v.findViewById(R.id.txt2);
                final TextView txt3 = (TextView) v.findViewById(R.id.txt3);
                final TextView txt4 = (TextView) v.findViewById(R.id.txt4);
                final TextView txt5 = (TextView) v.findViewById(R.id.txt5);
                final TextView txt6 = (TextView) v.findViewById(R.id.txt6);
                final TextView txt7 = (TextView) v.findViewById(R.id.txt7);
                final TextView txt8 = (TextView) v.findViewById(R.id.txt8);
                final TextView txt9 = (TextView) v.findViewById(R.id.txt9);
                final TextView txt10 = (TextView) v.findViewById(R.id.txt10);
                final LinearLayout cmntLayout = (LinearLayout) v.findViewById(R.id.cmntLayout);
                final TextView tvAdComment = (TextView) v.findViewById(R.id.txtAdComment);

                final TextView tvh1 = (TextView) v.findViewById(R.id.tvh1);
                final TextView tvh2 = (TextView) v.findViewById(R.id.tvh2);
                final TextView tvh3 = (TextView) v.findViewById(R.id.tvh3);
                final TextView tvh4 = (TextView) v.findViewById(R.id.tvh4);
                final TextView tvh5 = (TextView) v.findViewById(R.id.tvh5);
                final TextView tvh6 = (TextView) v.findViewById(R.id.tvh6);
                final TextView tvh7 = (TextView) v.findViewById(R.id.tvh7);
                final TextView tvh8 = (TextView) v.findViewById(R.id.tvh8);
                final TextView tvh9 = (TextView) v.findViewById(R.id.tvh9);
                final TextView tvh10 = (TextView) v.findViewById(R.id.tvh10);

                final LinearLayout ll1 = (LinearLayout) v.findViewById(R.id.ll1);
                final LinearLayout ll2 = (LinearLayout) v.findViewById(R.id.ll2);
                final LinearLayout ll3 = (LinearLayout) v.findViewById(R.id.ll3);
                final LinearLayout ll4 = (LinearLayout) v.findViewById(R.id.ll4);
                final LinearLayout ll5 = (LinearLayout) v.findViewById(R.id.ll5);
                final LinearLayout ll6 = (LinearLayout) v.findViewById(R.id.ll6);
                final LinearLayout ll7 = (LinearLayout) v.findViewById(R.id.ll7);
                final LinearLayout ll8 = (LinearLayout) v.findViewById(R.id.ll8);
                final LinearLayout ll9 = (LinearLayout) v.findViewById(R.id.ll9);
                final LinearLayout ll10 = (LinearLayout) v.findViewById(R.id.ll10);


                TextView fitv = null;
                LinearLayout fll = null;
                int sheetCellNumber = 0;
                String fullComment = "";
                boolean isUserCommented = false;
                Log.i(TAG, "isUserCommented: raw: " + isUserCommented);


                int totalComments = 0;

                if (p.getCmnt10() != null && !p.getCmnt10().equals("")) {
                    txt10.setText(formattedComnt(p.getCmnt10()));
                    tvh10.setText(p.getCmnt10().substring(0, 1));
                    ll10.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt10());
                    Log.i(TAG, "isUserCommented: 10: " + isUserCommented);
                } else {
                    ll10.setVisibility(View.GONE);
                    fitv = txt10;
                    fll = ll10;
                    sheetCellNumber = 20;
                    fullComment = p.getCmnt10();
                }
                if (p.getCmnt9() != null && !p.getCmnt9().equals("")) {
                    txt9.setText(formattedComnt(p.getCmnt9()));
                    tvh9.setText(p.getCmnt9().substring(0, 1));
                    ll9.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt9());
                    Log.i(TAG, "isUserCommented: 9: " + isUserCommented);
                } else {
                    ll9.setVisibility(View.GONE);
                    fitv = txt9;
                    fll = ll9;
                    sheetCellNumber = 19;
                    fullComment = p.getCmnt9();
                }
                if (p.getCmnt8() != null && !p.getCmnt8().equals("")) {
                    txt8.setText(formattedComnt(p.getCmnt8()));
                    tvh8.setText(p.getCmnt8().substring(0, 1));
                    ll8.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt8());
                    Log.i(TAG, "isUserCommented: 8: " + isUserCommented);
                } else {
                    ll8.setVisibility(View.GONE);
                    fitv = txt8;
                    fll = ll8;
                    sheetCellNumber = 18;
                    fullComment = p.getCmnt8();
                }
                if (p.getCmnt7() != null && !p.getCmnt7().equals("")) {
                    txt7.setText(formattedComnt(p.getCmnt7()));
                    tvh7.setText(p.getCmnt7().substring(0, 1));
                    ll7.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt7());
                    Log.i(TAG, "isUserCommented: 7: " + isUserCommented);
                } else {
                    ll7.setVisibility(View.GONE);
                    fitv = txt7;
                    fll = ll7;
                    sheetCellNumber = 17;
                    fullComment = p.getCmnt7();
                }
                if (p.getCmnt6() != null && !p.getCmnt6().equals("")) {
                    txt6.setText(formattedComnt(p.getCmnt6()));
                    tvh6.setText(p.getCmnt6().substring(0, 1));
                    ll6.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt6());
                    Log.i(TAG, "isUserCommented: 6: " + isUserCommented);
                } else {
                    ll6.setVisibility(View.GONE);
                    fitv = txt6;
                    fll = ll6;
                    sheetCellNumber = 16;
                    fullComment = p.getCmnt6();
                }
                if (p.getCmnt5() != null && !p.getCmnt5().equals("")) {
                    txt5.setText(formattedComnt(p.getCmnt5()));
                    tvh5.setText(p.getCmnt5().substring(0, 1));
                    ll5.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt5());
                    Log.i(TAG, "isUserCommented: 5: " + isUserCommented);
                } else {
                    ll5.setVisibility(View.GONE);
                    fitv = txt5;
                    fll = ll5;
                    sheetCellNumber = 15;
                    fullComment = p.getCmnt5();
                }
                if (p.getCmnt4() != null && !p.getCmnt4().equals("")) {
                    txt4.setText(formattedComnt(p.getCmnt4()));
                    tvh4.setText(p.getCmnt4().substring(0, 1));
                    ll4.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt4());
                    Log.i(TAG, "isUserCommented: 4: " + isUserCommented);
                } else {
                    ll4.setVisibility(View.GONE);
                    fitv = txt4;
                    fll = ll4;
                    sheetCellNumber = 14;
                    fullComment = p.getCmnt4();
                }
                if (p.getCmnt3() != null && !p.getCmnt3().equals("")) {
                    txt3.setText(formattedComnt(p.getCmnt3()));
                    tvh3.setText(p.getCmnt3().substring(0, 1));
                    ll3.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt3());
                    Log.i(TAG, "isUserCommented: 3: " + isUserCommented);
                } else {
                    ll3.setVisibility(View.GONE);
                    fitv = txt3;
                    fll = ll3;
                    sheetCellNumber = 13;
                    fullComment = p.getCmnt3();
                }
                if (p.getCmnt2() != null && !p.getCmnt2().equals("")) {
                    txt2.setText(formattedComnt(p.getCmnt2()));
                    tvh2.setText(p.getCmnt2().substring(0, 1));
                    ll2.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt2());
                    Log.i(TAG, "isUserCommented: 2: " + isUserCommented);
                } else {
                    ll2.setVisibility(View.GONE);
                    fitv = txt2;
                    fll = ll2;
                    sheetCellNumber = 12;
                    fullComment = p.getCmnt2();
                }
                if (p.getCmnt1() != null && !p.getCmnt1().equals("")) {
                    txt1.setText(formattedComnt(p.getCmnt1()));
                    tvh1.setText(p.getCmnt1().substring(0, 1));
                    ll1.setVisibility(View.VISIBLE);
                    totalComments = totalComments + 1;
                    isUserCommented = isCommented(p.getCmnt1());
                    Log.i(TAG, "isUserCommented: 1: " + isUserCommented);
                } else {
                    ll1.setVisibility(View.GONE);
                    fitv = txt1;
                    fll = ll1;
                    sheetCellNumber = 11;
                    fullComment = p.getCmnt1();
                }

                if (fll == null) {
                    tvAdComment.setVisibility(View.GONE);
                } else {
                    tvAdComment.setVisibility(View.VISIBLE);
                }

                if (isUserCommented) {
                    tvAdComment.setVisibility(View.GONE);
                } else {
                    tvAdComment.setVisibility(View.VISIBLE);
                }

                final TextView finalFitv = fitv;
                final int finalSheetCellNumber = sheetCellNumber;
                final String finalFullComment = fullComment;
                final int finalTotalComments1 = totalComments;
                final boolean finalIsUserCommented = isUserCommented;
                final LinearLayout finalFll = fll;
                tvAdComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(finalFitv, p.getPost_id(), String.valueOf(finalSheetCellNumber), finalFullComment, fromNetWork, finalTotalComments1, finalIsUserCommented, finalFll, position);

                    }
                });

                if (totalComments != 0) {
                    tvShowCmnt.setText("" + totalComments + " Comments");
                } else {
                    tvShowCmnt.setText("Comment");

                }


                final int finalTotalComments = totalComments;


                txt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt1, p.getPost_id(), "11", p.getCmnt1(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll1, position);
                    }
                });
                txt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt2, p.getPost_id(), "12", p.getCmnt2(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll2, position);
                    }
                });
                txt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt3, p.getPost_id(), "13", p.getCmnt3(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll3, position);
                    }
                });
                txt4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt4, p.getPost_id(), "14", p.getCmnt4(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll4, position);
                    }
                });
                txt5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt5, p.getPost_id(), "15", p.getCmnt5(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll5, position);
                    }
                });
                txt6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt6, p.getPost_id(), "16", p.getCmnt6(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll6, position);
                    }
                });
                txt7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt7, p.getPost_id(), "17", p.getCmnt7(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll7, position);
                    }
                });
                txt8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt8, p.getPost_id(), "18", p.getCmnt8(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll8, position);
                    }
                });
                txt9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt9, p.getPost_id(), "19", p.getCmnt9(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll9, position);
                    }
                });
                txt10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBeforeinsertComment(txt10, p.getPost_id(), "20", p.getCmnt10(), fromNetWork, finalTotalComments1, finalIsUserCommented, ll10, position);
                    }
                });

                LinearLayout ll_show_comment = (LinearLayout) v.findViewById(R.id.ll_show_comment);
                ll_show_comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cmntLayout.getVisibility() == View.GONE) {
                            cmntLayout.setVisibility(View.VISIBLE);
                            tvShowCmnt.setText("Hide Comment");
                        } else {
                            cmntLayout.setVisibility(View.GONE);
                            if (finalTotalComments != 0) {
                                tvShowCmnt.setText("" + finalTotalComments + " Comments");
                            } else {
                                tvShowCmnt.setText("Comment");
                            }
                        }
                    }
                });
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                cmntLayout.setVisibility(View.VISIBLE);


                String productName = "";
                String unitSystemAndPrice = "";
                String imageLink = "";

                if (p.getIds() != null && !p.getIds().equals("")) {
                    unitSystemAndPrice = p.getIds();
                    tv_date_time.setText("Date: " + p.getIds().replace(":", ", M: ")
                            .replace("#", ", মূল্য: ") + " টাকা।");

                }

                boolean isStockOut = p.getWebUrl() != null && !TextUtils.isEmpty(p.getWebUrl()) && p.getWebUrl().equalsIgnoreCase("0");

                if (isStockOut)
                    txtStockOut.setVisibility(View.VISIBLE);
                else
                    txtStockOut.setVisibility(View.GONE);


                if (p.getName() != null && !p.getName().equals("") && !TextUtils.isEmpty(p.getName())) {

                    if (isStockOut) {
                        textViewName.setText(p.getName() + " -(STOCK OUT)");
                        productName = p.getName() + " -(STOCK OUT)";
                        textViewName.setTextColor(Color.LTGRAY);
                    } else {
                        productName = p.getName();
                        textViewName.setText(p.getName());
                        textViewName.setTextColor(Color.BLACK);
                    }

                    try {
                        txtHeader.setText(p.getName().substring(0, 1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                if (p.getPhone() != null && !p.getPhone().equals("") && !TextUtils.isEmpty(p.getPhone())) {
                    Log.i(TAG, "onPostExecute: uPhones + total likes:: 6  : " + p.getPhone());
                    tv_uPhone.setText(format(Long.parseLong(p.getPhone())));
                    tv_uPhone.setVisibility(View.VISIBLE);

                } else {
                    tv_uPhone.setVisibility(View.GONE);
                    Log.i(TAG, "onPostExecute: uPhones likes All null 11  : " + p.getPhone());

                }


                if (likeExists(p.getPost_id())) {
                    imgLikeBlue.setVisibility(View.VISIBLE);
                    imgLikeWhite.setVisibility(View.GONE);

                } else {

                    imgLikeBlue.setVisibility(View.GONE);
                    imgLikeWhite.setVisibility(View.VISIBLE);

                }

                final int[] likeClicked = {1};

                imgLikeWhite.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        if (fromNetWork) {
                            int totalLike = 0;


                            if (p.getPhone() != null && !p.getPhone().equals("") && !TextUtils.isEmpty(p.getPhone())) {
                                totalLike = Integer.parseInt(p.getPhone()) + likeClicked[0];


                            } else {
                                totalLike = totalLike + likeClicked[0];
                            }
                            likeClicked[0]++;

                            tv_uPhone.setText("" + totalLike);
                            tv_uPhone.setVisibility(View.VISIBLE);

                            if (p.getPost_id() != null && !TextUtils.isEmpty(p.getPost_id())) {
                                Log.i(TAG, "getPost_id for Like: " + p.getPost_id());
                                insertLikesToSheet(p.getPost_id(), tv_uPhone);
                            } else {
                                Log.i(TAG, "getPost_id for Like: " + "Post id Not Found Like Not Submitted");

                            }

                            imgLikeWhite.setVisibility(View.GONE);
                            imgLikeBlue.setVisibility(View.VISIBLE);

                        } else {
                            showToast(mContext, "Like And Comment, Require Network And After Loaded Posts from Network", Toast.LENGTH_LONG);

                        }

                    }
                });

                tv_uAdText.setMaxLines(5);

                if (p.getAdText() != null && !p.getAdText().equals("")) {
                    tv_uAdText.setText(p.getAdText());
                }


                if (tv_uAdText.getText().toString().length() > 30) {
                    txtShowMore.setVisibility(View.VISIBLE);
                } else {
                    txtShowMore.setVisibility(View.GONE);
                }
                tv_uAdText.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        if (txtShowMore.getText().toString().contains("See More....")) {
                            tv_uAdText.setMaxLines(Integer.MAX_VALUE);
                            txtShowMore.setText("See less..");

                        } else {
                            tv_uAdText.setMaxLines(5);
                            txtShowMore.setText("See More....");

                        }

                    }
                });


                txtShowMore.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {

                        if (txtShowMore.getText().toString().contains("See More....")) {
                            tv_uAdText.setMaxLines(Integer.MAX_VALUE);
                            txtShowMore.setText("See less..");

                        } else {
                            tv_uAdText.setMaxLines(4);
                            txtShowMore.setText("See More....");

                        }

                    }
                });


                Picasso.with(mContext).cancelRequest(iv);

                if (p.getImageUrl() != null
                        && !TextUtils.isEmpty(p.getImageUrl())
                        && !p.getImageUrl().equalsIgnoreCase("null")) {
                    imageLink = p.getImageUrl();

                    boolean imageSetSucceed = false;

                    String previousImageString = getStringFromPref(mContext, p.getPost_id());

                    if (previousImageString != null) {

                        Bitmap bitmap = null;

                        try {
                            bitmap = convertStringToBitmap(previousImageString);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (bitmap != null) {
                            Bitmap hiResBitmap = getHiResBitmap(bitmap, 1000);
                            iv.setImageBitmap(hiResBitmap);


                            // iv.setImageBitmap(bitmap);
                            iv.setVisibility(View.VISIBLE);
                            //   saveImageAndSavePathToSharedPrefrence(bitmap, p.getPost_id());
                            Log.i(TAG, "onSuccess: From Shared Preference " + " Position :  " + position + " PostId: " + p.getPost_id() + " Url : " + p.getImageUrl());

                            tvPhotoLink.setText("Photo Link");
                            tvPhotoLink.setTextColor(Color.BLUE);
                            tvPhotoLink.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    go_to_website(p.getImageUrl());
                                }
                            });
                            tvPhotoLink.setVisibility(View.VISIBLE);


                            imageSetSucceed = true;
                        }
                    }


                    if (!imageSetSucceed) {

                        Log.i(TAG, "onSuccess:  Shared Preference fail " + " Position :  " + position + " PostId: " + p.getPost_id() + " Url : " + p.getImageUrl());

                        Picasso.with(mContext).cancelRequest(iv);

                        Picasso.with(mContext).load(p.getImageUrl()).into(iv, new Callback() {
                            @Override
                            public void onSuccess() {

                                Bitmap bitmap = convertImageViewToBitmap(iv);
                                Bitmap hiResBitmap = getHiResBitmap(bitmap, 1000);
                                iv.setImageBitmap(hiResBitmap);


                                // iv.setImageBitmap(bitmap);
                                iv.setVisibility(View.VISIBLE);
                                //   saveImageAndSavePathToSharedPrefrence(bitmap, p.getPost_id());
                                Log.i(TAG, "onSuccess: From WebUrl " + " Position :  " + position + " PostId: " + p.getPost_id() + " Url : " + p.getImageUrl());

                                tvPhotoLink.setText("Photo Link");
                                tvPhotoLink.setTextColor(Color.BLUE);
                                tvPhotoLink.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        go_to_website(p.getImageUrl());
                                    }
                                });
                                tvPhotoLink.setVisibility(View.VISIBLE);


                                String bitmapString = convertBitmapToString(bitmap);

                                saveStringToPref(mContext, p.getPost_id(), bitmapString);


                            }

                            @Override
                            public void onError() {

                                Log.i(TAG, "onSuccess:3_0 Picasso Error: from Net  " + " Image Position Url is not Valid: " + "ImageUrl is :  " + p.getImageUrl());

                                tvPhotoLink.setVisibility(View.GONE);
                                iv.setVisibility(View.GONE);

                            }
                        });
                    }

                } else {
                    Picasso.with(mContext).cancelRequest(iv);
                    tvPhotoLink.setVisibility(View.GONE);
                    iv.setVisibility(View.GONE);

                }


                iv.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {


                        Bitmap bitmap = convertImageViewToBitmap(iv);
                        showPictureFullScreen(bitmap);

                        return true;
                    }
                });

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bitmap bitmap = convertImageViewToBitmap(iv);
                        showPictureFullScreen(bitmap);


                    }
                });

                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                iv.setScaleType(ImageView.ScaleType.FIT_XY);


                LinearLayout ll_order = (LinearLayout) v.findViewById(R.id.ll_order_details);
                String finalProductName = productName;
                String finalUnitSystemAndPrice = unitSystemAndPrice;
                String finalImageLink = imageLink;
                ll_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //if(!isStockOut){
                        prepareOrder(finalProductName,
                                finalUnitSystemAndPrice,
                                finalImageLink);
//                        }else {
//                            Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
//                        }
                    }
                });


                final SpeedyImageView imgThreeDot = (SpeedyImageView) v.findViewById(R.id.imgThreeDot);
                final CardView card_view = (CardView) v.findViewById(R.id.card_view);


                try {
                    imgThreeDot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            switch (v.getId()) {

                                case R.id.imgThreeDot:

                                    PopupMenu popupMenu = new PopupMenu(mContext, v);
                                    popupMenu.getMenuInflater().inflate(R.menu.biggopti_menu, popupMenu.getMenu());
                                    popupMenu.show();
                                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                        @Override
                                        public boolean onMenuItemClick(MenuItem item) {

                                            switch (item.getItemId()) {

                                                case R.id.menu_delete_post:

                                                    if (fromNetWork) {
                                                            confirmDeletePost(p.getUser_id(), p.getPost_id(), card_view);
                                                    } else {
                                                        showToast(mContext, "For Deleting Post, Require Network And After Loaded All Posts", Toast.LENGTH_LONG);
                                                    }
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

            return v;
        }

        @Nullable
        @Override
        public PostsModel getItem(int position) {
            return super.getItem(super.getCount() - position - 1);
        }

        @Override
        public boolean isEnabled(int position) {
            //return super.isEnabled(position);
            return false;
        }
    }


    private void prepareOrder(final String productName,
                              final String unitSystemAndPrice,
                              final String imageLink) {
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mContext);


        final View cl = getLayoutInflater().inflate(R.layout.order_request_layout, null);
        final TextView txtProductName = (TextView) cl.findViewById(R.id.txtProductName);
        final TextView txtUnitSystem = (TextView) cl.findViewById(R.id.txtUnitSystem);
        final TextView txtPriceInformation = (TextView) cl.findViewById(R.id.txtPriceInformation);

        final TextView txtWritePairOrDzn = (TextView) cl.findViewById(R.id.txtWritePairOrDzn);

        final TextView txtTotalPrice = (TextView) cl.findViewById(R.id.txtTotalPrice);
        final EditText et_InputUnit = (EditText) cl.findViewById(R.id.et_InputUnit);

       // disableSoftKeyboard(et_InputUnit);
        et_InputUnit.setTextIsSelectable(true);
        et_InputUnit.setFocusable(true);
        ImageView imgSendRequest = (ImageView) cl.findViewById(R.id.imgSendRequest);


        txtProductName.setText("Product Name : " + productName);


        String unitSystem = "";
        String priceInformation = "";
        String perPieceString = "";
        String writePairOrDozen = "";
        String[] array = unitSystemAndPrice.split("#");

        if (array.length > 1)
            perPieceString = array[1];
        else perPieceString = "80";

        int perPieceInteger = 80;
        try {
            perPieceInteger = Integer.parseInt(perPieceString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        boolean isDozen = false;

        if (unitSystemAndPrice.contains(SIX_PAIR)) {
            priceInformation = "প্রতি পেয়ার এর মূল্য : " + perPieceString + " টাকা\n(সর্ব নিম্ন ওয়ার্ডার করতে পারবেন ৬ পেয়ার।)";
            unitSystem = SIX_PAIR;
            writePairOrDozen = "কত পেয়ার লিখুন : ";
        } else {
            priceInformation = "প্রতি পেয়ার এর মূল্য : " + perPieceString + " টাকা\n(সর্ব নিম্ন ওয়ার্ডার করতে পারবেন ১ ডজন।)";
            isDozen = true;
            unitSystem = ONE_DOZEN;
            writePairOrDozen = "কত ডজন লিখুন : ";

        }
        txtPriceInformation.setText(priceInformation);
        txtUnitSystem.setText("ইউনিট সিস্টেম: " + unitSystem);
        txtWritePairOrDzn.setText(writePairOrDozen);


        String finalUnitSystem = unitSystem;
        String finalUnitPrice = perPieceString;
        final int[] totalTaka = {1200};

        int finalPerPieceInteger = perPieceInteger;
        boolean finalIsDozen = isDozen;
        et_InputUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                String s = String.valueOf(charSequence);
                String totalPriceString = "300 Taka";

                if (TextUtils.isEmpty(s)) {
                    txtTotalPrice.setVisibility(View.GONE);

                } else {
                    int qnty = 0;

                    try {
                        qnty = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    if (qnty == 0) {
                        txtTotalPrice.setVisibility(View.GONE);
                    } else {
                        txtTotalPrice.setVisibility(View.VISIBLE);

                        totalTaka[0] = finalPerPieceInteger * qnty;
                        totalPriceString =qnty + " পেয়ার এর মোট  মূল্য " + totalTaka[0] + " টাকা।";



                        if (finalIsDozen) {

                            if (qnty < 12) {

                                String information = "বি:দ্র: ১ ডজন / 12 পেয়ার এর নীচে অর্ডার করা যাবেনা ";
                                txtTotalPrice.setText( totalPriceString+"\n\n"+information);

                            }else {

                                int y = qnty % 12;
                                String information;
                                if (y != 0) {
                                    information = "বি:দ্র: ডজন এর ইউনিট মিলিয়ে অর্ডার করতে হবে। যেমন:12, 24, 36, 48 ইত্যাদি";
                                }else {
                                    information = "বি:দ্র: এখন ডজন হিসেবে অর্ডার করতে পারেন।  ";
                                }
                                txtTotalPrice.setText(totalPriceString + "\n\n" + information);
                            }

                        } else {
                            if (qnty < 6) {
                                String information ="বি:দ্র: ৬ পেয়ার এর নীচে অর্ডার করা যাবেনা। ";
                                txtTotalPrice.setText( totalPriceString+"\n\n"+information);

                            }else {

                                int y = qnty % 6;
                                String information;
                                if (y != 0) {
                                    information = "বি:দ্র: ৬ পেয়ার এর ইউনিট মিলিয়ে অর্ডার করতে হবে। যেমন:6, 12, 18, 24 ইত্যাদি";
                                }else {
                                    information = "বি:দ্র: এখন সিক্স-পেয়ার হিসেবে অর্ডার করতে পারেন। ";
                                }
                                txtTotalPrice.setText(totalPriceString + "\n\n" + information);
                            }
                        }


                    }

                }

                // পোস্ট করার সময় ৬ পেয়ার যেগুলো বিক্রি হবে, সেগুলোতে পেয়ার এর মূল দিতে হতে আর
                // আর যেগুলো ডজন এর নীচে বিক্রয় হবেনা, সেগুলোতে ডজন এর দাম দিতে হবে।

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        builder.setView(cl);
        final androidx.appcompat.app.AlertDialog alert = builder.create();




        imgSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String totalOrderQuantity = et_InputUnit.getText().toString();
                et_InputUnit.setError(null);

                if (TextUtils.isEmpty(totalOrderQuantity)) {
                    et_InputUnit.setError("Empty");
                    return;
                }

                int qnty = 0;

                try {
                    qnty = Integer.parseInt(totalOrderQuantity);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

//                if (qnty == 0) {
//                    Toast.makeText(mContext, "0 কোয়ান্টিটি অর্ডার করা সম্ভব নয়। ", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                if (finalIsDozen) {

                    if (qnty < 12) {
                        Toast toast1 = Toast.makeText(mContext, "১ ডজন এর নীচে অর্ডার করা যাবেনা। ", Toast.LENGTH_SHORT) ;
                        toast1.setGravity(Gravity.CENTER, 0,0);
                        toast1.show();
                        return;
                    }else {

                        int y = qnty % 12;
                        if (y != 0) {
                            Toast toast1 = Toast.makeText(mContext, "ডজন এর ইউনিট মিলিয়ে অর্ডার করতে হবে। যেমন: 24, 36, 48 ইত্যাদি", Toast.LENGTH_LONG);
                            toast1.setGravity(Gravity.CENTER, 0, 0);
                            toast1.show();
                            return;
                        }
                    }

                } else {
                    if (qnty < 6) {
                        Toast toast1 = Toast.makeText(mContext, "৬ পেয়ার এর নীচে অর্ডার করা যাবেনা। ", Toast.LENGTH_SHORT) ;
                        toast1.setGravity(Gravity.CENTER, 0,0);
                        toast1.show();
                        return;
                    }else {

                        int y = qnty % 6;
                        if (y != 0) {
                            Toast toast1 = Toast.makeText(mContext, "৬ পেয়ার এর ইউনিট মিলিয়ে অর্ডার করতে হবে। ", Toast.LENGTH_LONG);
                            toast1.setGravity(Gravity.CENTER, 0, 0);
                            toast1.show();
                            return;
                        }
                    }
                }




                String userPhone = settings.getString(CUSTOMER_PHONE, "01879115953");
                String userName = settings.getString(CUSTOMER_NAME, "NoorHossain");
                String userAddress = settings.getString(CUSTOMER_ADDRESS, "Shibchar, Madaripur");

                try {
                    sendOrderRequests(userPhone, userName, userAddress, productName, finalUnitSystem, totalOrderQuantity, finalUnitPrice, String.valueOf(totalTaka[0]), imageLink, alert);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });



        alert.show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(finalIsDozen)
                    et_InputUnit.setText("12");
                else
                    et_InputUnit.setText("6");

            }
        },100);



    }

    public static void disableSoftKeyboard(@NonNull final EditText v) {
        v.setRawInputType(InputType.TYPE_CLASS_TEXT);
        v.setTextIsSelectable(true);
    }

    private void sendOrderRequests
            (final String userPhone,
             final String userName,
             final String userAddress,
             final String productName,
             final String unitSystem,
             final String totalOrderQuantity,
             final String unitPrice,
             final String totalPrice,
             final String imageLink, androidx.appcompat.app.AlertDialog alert) {

        if (loading != null && loading.isShowing())
            loading.dismiss();

        loading = new ProgressDialog(mContext);
        loading.setTitle("Request Sending......");
        loading.setMessage("Wait a moment.........");
        loading.setCancelable(true);
        loading.setIndeterminate(false);
        loading.show();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy:HH.mm.ss", Locale.US);
        final String date = simpleDateFormat.format(new Date());

        StringRequest stringRequest = null;


        stringRequest = new StringRequest(Request.Method.POST, Common.ORDER_REQUEST_URL + "?action=insert_orders_requests", // URL_FOR_FIFTY_ROWS,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i(TAG, "onResponse:sendRequestToGuardians  " + response.toString());

                        if (loading != null && loading.isShowing())
                            loading.dismiss();

                        if (!response.toLowerCase().contains("Success".toLowerCase())) {
                            Toast.makeText(mContext, "Not Success, Please Try Again Later.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
                        try {
                            alert.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        new AlertDialog.Builder(mContext).setTitle("Alhamdulillah, Success").setMessage("আলহামদুলিল্লাহ, আপনার অর্ডার রিকোয়েষ্টটি আমাদের কাছে পৌছেছে।  " +
                                " প্রোডাক্ট এভেইলেবল সাপেক্ষে শীঘ্রই প্রোডাক্ট আপনার ঠিকানায় পৌছে দেয়া হবে ইনশাআল্লাহ।  সাথে থাকার জন্য ধন্যবাদ।  ")
                                .setPositiveButton("OK", null).create().show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i(TAG, "onResponse: BiggoptiShow 20_rows:  " + error.toString());

                        if (loading != null && loading.isShowing())
                            loading.dismiss();
                        Toast.makeText(mContext, "Not Success, Please Try Again Later.", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("date", date);

                params.put("productName", productName);
                params.put("unitSystem", unitSystem);
                params.put("unitPrice", unitPrice);
                params.put("totalOrderQuantity", totalOrderQuantity);
                params.put("totalPrice", totalPrice);

                params.put("customerPhone", userPhone);
                params.put("customerName", userName);
                params.put("customerAddress", userAddress);
                params.put("productImageLink", imageLink);
                params.put("sending_status", "এখনও প্রোডাক্ট পাঠানো হয় নাই।");

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


    //////////////////////////////////////////////////////////////// insert Comment Block ////////////////////////////////////////////////////////


    private void checkBeforeinsertComment(final TextView tv, final String postId, final String cellNumber, final String previousComment, boolean fromNetwork, int totalPreviousCmnts, boolean isUserCommented, final LinearLayout fll, final int position) {


        Log.i(TAG, "checkBeforeinsertComment: LLLLLLLLLLL " + postId);

        if (!fromNetwork) {
            showToast(mContext, "Like And Comment, Require Network And After Loaded All Posts", Toast.LENGTH_LONG);
            return;
        }

        if (TextUtils.isEmpty(postId)) {
            Toast.makeText(mContext, "This is an Old Post, Whereas Post Id Not Found", Toast.LENGTH_SHORT).show();
            return;
        }


        Log.i(TAG, "checkBeforeinsertComment: 10 : " + isUserCommented);

        Log.i(TAG, "insertComment: userId: " + userID);

        if (!TextUtils.isEmpty(previousComment)) {

            if (previousComment.contains(userID)) {

                Log.i(TAG, "checkBeforeinsertComment: 1 : ");

                CharSequence[] array = {"Edit", "Delete"};
                AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
                ad.setTitle("What Action ?");
                ad.setItems(array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (i == 0) {
                            insertComment(tv, postId, cellNumber, pvcm(previousComment), true, fll, position); //ডিলিট / কপি / এডিট
                        }
                        if (i == 1) {
                            tv.setText(TextUtils.concat(formattedComnt(previousComment), " ...deleting"));
                            tv.setTextColor(Color.BLUE);
                            deleteComment(postId, cellNumber, fll);
                        }
                    }
                });

                AlertDialog dialog = ad.create();
                dialog.show();


            } else if (isUserCommented) {
                Log.i(TAG, "checkBeforeinsertComment: 2 : ");
                //showToast(mContext, "আপনি ইতিপূর্বে এই পোস্ট এ কমেন্ট করেছেন। একটি পোস্ট এ একটিই কমেন্ট করতে পারবেন। তবে \n\nপূর্বেকৃত আপনার কমেন্টের উপর ক্লিক করে কমেন্ট পরিবর্তন করতে পারবেন।", Toast.LENGTH_LONG);
            } else if (totalPreviousCmnts > 9) {
                Log.i(TAG, "checkBeforeinsertComment: 5 : ");

                //showToast(mContext, "একটি পোস্ট এ সর্বমোট ১০টি কমেন্ট করা যাবে, ১০ কমেন্ট পূর্ণ হয়ে গেছে। \n\n তবে আপনার পূর্বকৃত কমেন্ট এর উপর ক্লিক করে কমেন্ট পরিবর্তন করতে পারবেন। ", Toast.LENGTH_LONG);

            }
//            else {//can comment :
//                Log.i(TAG, "checkBeforeinsertComment: 3 : ");
//                showToast(mContext, "কমেন্ট করতে  ‘+’ চিহ্নিত ঘরে ক্লিক করুন।", Toast.LENGTH_SHORT);
//            }
        } else if (isUserCommented) {
            Log.i(TAG, "checkBeforeinsertComment: 4 : " + isUserCommented);
            // showToast(mContext, "আপনি ইতিপূর্বে এই পোস্ট এ কমেন্ট করেছেন। একটি পোস্ট এ একটিই কমেন্ট করতে পারবেন। তবে \n\nপূর্বেকৃত আপনার কমেন্টের উপর ক্লিক করে কমেন্ট পরিবর্তন করতে পারবেন।", Toast.LENGTH_LONG);
        } else if (totalPreviousCmnts > 9) {
            Log.i(TAG, "checkBeforeinsertComment: 5 : ");

            // showToast(mContext, "একটি পোস্ট এ সর্বমোট ১০টি কমেন্ট করা যাবে, ১০ কমেন্ট পূর্ণ হয়ে গেছে। \n\n তবে আপনার পূর্বকৃত কমেন্ট এর উপর ক্লিক করে কমেন্ট পরিবর্তন করতে পারবেন। ", Toast.LENGTH_LONG);

        } else {
            insertComment(tv, postId, cellNumber, pvcm(previousComment), false, fll, position);

        }


    }

    private void deleteComment(final String postID, final String cellNumber, final LinearLayout fll) {

        Log.i("onResponse", "deleteComment: Called  ");


        StringRequest stringRequest = new StringRequest(Common.APP_SCRIPT_WEB_APP_URL + "?action=delete_comment&uId=" + postID + "&cellNumber=" + cellNumber,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        fll.setVisibility(View.GONE);

                        implementOneResponseRow(postID, response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        fll.setVisibility(View.GONE);

                        Log.i(TAG, "onErrorResponse: " + error.getMessage());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //   params.put("postId", postId);
                //   params.put("down_range", String.valueOf(1));

                Log.i(TAG, "getParams: columnDown :  " + columnDown);
                Log.i(TAG, "getParams: downRange:  " + 1);

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


        class deleteOwnComment extends Thread {

            String result = null;

            String url, id, cellNumber;

            public deleteOwnComment(String url, String id, String cellNumber) {
                this.url = url;
                this.id = id;
                this.cellNumber = cellNumber;
            }

            @Override
            public void run() {
                super.run();

                JSONObject jsonObject = Controller.biggoptiShow_delete_comment(url, id, cellNumber);// url, id , cellNumber .

                Log.i(TAG, "doInBackground: uPhones id 1 : params[1]: " + id);

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

                        fll.setVisibility(View.GONE);
                        //  sendRequestForUpdateOneRow(position);
                        if (result != null && result.equalsIgnoreCase("Alhamdulillah, Your comment Deleted Successfully")) {

                            isFromInsertComment = true;
                            // showToast(mContext, "Your Comment Deleted Successfully", Toast.LENGTH_LONG);
                            fll.setVisibility(View.GONE);
                            //sendRequestForadvertises();


                        }


                    }
                });


            }
        }

        //   new  deleteOwnComment(Common.APP_SCRIPT_WEB_APP_URL, postID , cellNumber).start();

    }


    private void insertComment(final TextView tv, final String postId, final String cellNumber, final String previousOnlyCmnt, boolean isEditCmnt, final LinearLayout fll, final int position) {


        final String[] PostUserName = {settings.getString("PostUserName", null)};
        Log.i(TAG, "insertComment: userName: " + PostUserName[0]);


        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mContext);


        final View cl = getLayoutInflater().inflate(R.layout.insert_comment_layout, null);
        final TextView txtChangeName = (TextView) cl.findViewById(R.id.txtChangeName);
        final TextView txtTwo = (TextView) cl.findViewById(R.id.txtTwo);
        final TextView txtName = (TextView) cl.findViewById(R.id.txtName);
        final TextView edtName = (TextView) cl.findViewById(R.id.edtName);
        if (isEditCmnt) {
            builder.setTitle("Edit Comment :");
            txtTwo.setText("কমেন্ট এডিট করুন :");
        } else
            builder.setTitle("Write Comment : ");


        edtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtName.setError(null);
            }
        });

        if (PostUserName[0] != null) {
            edtName.setText(PostUserName[0]);
            txtName.setVisibility(View.GONE);
            edtName.setVisibility(View.GONE);
            txtChangeName.setVisibility(View.VISIBLE);
        } else {
            txtName.setVisibility(View.VISIBLE);
            edtName.setVisibility(View.VISIBLE);
            txtChangeName.setVisibility(View.GONE);
        }

        txtChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtName.setVisibility(View.VISIBLE);
                edtName.setVisibility(View.VISIBLE);
                txtChangeName.setVisibility(View.GONE);

            }
        });


        final TextView edtCmnt = (TextView) cl.findViewById(R.id.edtCmnt);
        if (!TextUtils.isEmpty(previousOnlyCmnt)) {
            edtCmnt.setText(previousOnlyCmnt);
        }
        edtCmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCmnt.setError(null);
            }
        });

        ImageView imgInsertCmnt = (ImageView) cl.findViewById(R.id.imgInsertCmnt);


        builder.setView(cl);

        final androidx.appcompat.app.AlertDialog alert = builder.create();

        imgInsertCmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtName.getVisibility() == View.VISIBLE) {
                    String name = edtName.getText().toString();

                    if (TextUtils.isEmpty(name)) {
                        edtName.setError("Empty");
                        return;
                    } else {
                        PostUserName[0] = name;
                        editor.putString("PostUserName", name);
                        editor.apply();
                    }
                }

                String s = edtCmnt.getText().toString();
                s = s.replace("#", "").replace("*", "");
                if (TextUtils.isEmpty(s)) {
                    edtCmnt.setError("Empty");
                    showToast(mContext, "Comment Something..", Toast.LENGTH_SHORT);
                    return;
                } else if (s.length() > 500) {

                    showToast(mContext, "Comment Should be less than 500 characters length....", Toast.LENGTH_LONG);
                    return;

                }


                if (!TextUtils.isEmpty(s)) {
                    tv.setText(TextUtils.concat(formattedComnt(PostUserName[0] + ".    :  " + s), " ...uploading"));
                    tv.setTextColor(Color.BLUE);
                    tv.setVisibility(View.VISIBLE);
                    fll.setVisibility(View.VISIBLE);

                    requestForInsertComment(tv, fll, Common.APP_SCRIPT_WEB_APP_URL, postId, PostUserName[0] + ".    :  " + s, cellNumber);
                }
                alert.dismiss();


            }
        });

        alert.show();


    }


    private void requestForInsertComment(final TextView tv, final LinearLayout fll, final String url, final String postId, final String comment, final String cellNumber) {

        Log.i(TAG, "onRowResponse: requestForInsertComment:  called");


        StringRequest stringRequest = new StringRequest(url + "?action=insert_comment&uId=" + postId + "&cmnt=" + comment + "*" + userID + "&cellNumber=" + cellNumber,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        tv.setText(formattedComnt(comment));
                        tv.setVisibility(View.VISIBLE);
                        fll.setVisibility(View.VISIBLE);

                        implementOneResponseRow(postId, response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        tv.setText(formattedComnt(comment));
                        tv.setVisibility(View.VISIBLE);
                        fll.setVisibility(View.VISIBLE);

                        Log.i(TAG, "onErrorResponse: " + error.getMessage());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //   params.put("postId", postId);
                //   params.put("down_range", String.valueOf(1));

                Log.i(TAG, "getParams: columnDown :  " + columnDown);
                Log.i(TAG, "getParams: downRange:  " + 1);

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
                        Type type = new TypeToken<ArrayList<PostsModel>>() {
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
                        userListAdapter = new BiggoptiLisAdapter(mContext, R.layout.list_row, advertiseList);//AssignOnly, For Later Use, on Network Call

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

                    if (!jo.getString(KEY_ID).isEmpty()) {


                        keyList.add(jo.getString(KEY_ID));

                        PostsModel p = new PostsModel();

                        p.setIds(jo.getString(KEY_ID)); // Date + time
                        p.setName(jo.getString(KEY_NAME));
                        p.setImageUrl(jo.getString(KEY_IMAGE));
                        p.setPhone(jo.getString(KEY_PHONE));//////////////////////////// this is like
                        p.setWebUrl(jo.getString(KEY_WEBURL));
                        p.setAdText(jo.getString(KEY_ADTEXT));
                        p.setComments(jo.getString(KEY_CMNT1));
                        p.setCmnt1(jo.getString(KEY_CMNT1));
                        p.setCmnt2(jo.getString(KEY_CMNT2));
                        p.setCmnt3(jo.getString(KEY_CMNT3));
                        p.setCmnt4(jo.getString(KEY_CMNT4));
                        p.setCmnt5(jo.getString(KEY_CMNT5));
                        p.setCmnt6(jo.getString(KEY_CMNT6));
                        p.setCmnt7(jo.getString(KEY_CMNT7));
                        p.setCmnt8(jo.getString(KEY_CMNT8));
                        p.setCmnt9(jo.getString(KEY_CMNT9));
                        p.setCmnt10(jo.getString(KEY_CMNT10));
                        p.setPost_id(jo.getString(POST_ID));
                        p.setUser_id(jo.getString(USER_ID));
                        p.setExtra(jo.getString(KEY_EXTRA));
                        //  if(advertiseList!=null)

                        advertiseList.add(0, p);

                        Log.i(TAG, "parseJSON:p.getPost_id() " + p.getPost_id());

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
                new saveFilesToExternal(jsonString, shoe_catagory).start();

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
                runOnUiThread(new Runnable() {

                    public void run() {
                        // save index and top position
                        int index = listView.getFirstVisiblePosition();
                        View v = listView.getChildAt(0);
                        int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());
                        userListAdapter = new BiggoptiLisAdapter(BiggoptiShow.this, R.layout.list_row, advertiseList);
                        userListAdapter.notifyDataSetChanged();
                        listView.setSelectionFromTop(index, top);
                        Log.i("Tests", "run:  15:  ");
                    }
                });

            }
        } else {
            Log.i("Tests", "run:  16:  ");

            userListAdapter = new BiggoptiLisAdapter(this, R.layout.list_row, advertiseList);
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

    ProgressDialog loading;


    boolean isToastShown1 = false;

    private void sendRequest() {

        if (!InternetConnection.checkConnection(mContext)) {

            showToast(mContext, "Check Internet Connections Then Refresh The Screen", Toast.LENGTH_LONG);


            dismissAllProgress();

        } else {

            Log.i(TAG, "sendRequest: 1  " + Common.APP_SCRIPT_WEB_APP_URL);
            if (TextUtils.isEmpty(Common.APP_SCRIPT_WEB_APP_URL)) {
                // showToast(mContext, "Script Url is Empty...", Toast.LENGTH_LONG);

                Log.i(TAG, "sendRequest: 2 APP_SCRIPT_WEB_APP_URL:   " + Common.APP_SCRIPT_WEB_APP_URL);


                sendRequestForScriptUrl();

                if (layoutSwipeRefresh != null) {
                    layoutSwipeRefresh.setRefreshing(false);
                    layoutSwipeRefresh.setRefreshing(true);
                }

                if (!isToastShown1) {
                    isToastShown1 = true;
                    showToast(mContext, "New Post Loading, please wait.....", Toast.LENGTH_LONG);
                }
                dismissAllProgress();

            } else {
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
                        //  showJSON(response);
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


    private void sendRequestForadvertises() {
        //  showToast(BiggoptiShow.this,"LIST_USER_URL: "+LIST_USER_URL,Toast.LENGTH_LONG);


        StringRequest stringRequest = new StringRequest(Common.APP_SCRIPT_WEB_APP_URL + "?action=readAll", //  LIST_USER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Log.e("null", "ser image" + response); // response =  full spreadSheetData
                        showJSON(response);
                        Log.i(TAG, "sendRequest: 2:   " + response);

                        Log.i("Tests", "run:  6:  ");


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToast(BiggoptiShow.this, "REQUEST ERROR:  " + error.getMessage(), Toast.LENGTH_LONG);
                        Log.i(TAG, "sendRequest: 3:   " + error.getMessage());


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


    BiggoptiLisAdapter userListAdapter;


    ArrayList<String> keyList2;

    public String[] uIds;
    public String[] uNames;
    public String[] uImages;

    public String[] uPhones;
    public String[] uWebUrl;
    public String[] uAdText;


    private void sendRequestForScriptUrl() {

        StringRequest stringRequest = new StringRequest(LIST_USER_URL_BIGPTI_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Log.e("null","ser image"+response);
                        // showJSONForScriptUrl(response);

                        JsonParserForScriptUrl pjScript = new JsonParserForScriptUrl(response);
                        pjScript.parseJSON();  // here Makes Scripte Lists Model list


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToast(BiggoptiShow.this, error.getMessage(), Toast.LENGTH_LONG);
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


    ArrayList<PostsModel> scriptList;


    private class JsonParserForScriptUrl {


        private JSONArray users = null;

        private String json;

        public JsonParserForScriptUrl(String json) {
            this.json = json;
        }

        protected void parseJSON() {

            if (scriptList != null)
                scriptList.clear();
            scriptList = new ArrayList<>();


            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                users = jsonObject.getJSONArray(KEY_USERS);


                JSONArray reverseJsonArray = new JSONArray();

                for (int i = users.length() - 1; i >= 0; i--) {
                    try {
                        reverseJsonArray.put(users.getJSONObject(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.i(TAG, "parseJSON: Error: " + e.getMessage());
                    }
                }


                String biggoptiUrl = null;
                String pendingUrl = null;


                for (int i = 0; i < users.length(); i++) {

                    JSONObject jo = users.getJSONObject(i);

                    if (!jo.getString(KEY_ID).isEmpty()) {


                        PostsModel p = new PostsModel();

                        p.setIds(jo.getString(KEY_ID)); // Date + time
                        p.setName(jo.getString(KEY_NAME));
                        p.setImageUrl(jo.getString(KEY_IMAGE));
                        p.setPhone(jo.getString(KEY_PHONE));//////////////////////////// this is like
                        p.setWebUrl(jo.getString(KEY_WEBURL));
                        p.setAdText(jo.getString(KEY_ADTEXT));

                        String checkRunningSeason = jo.getString(KEY_IMAGE);

                        if (checkRunningSeason.contains("Running#MainBiggopti")) {

                            biggoptiUrl = jo.getString(KEY_ID);
                            Log.i(TAG, "ForScript: Running#MainBiggopti: " + biggoptiUrl);

                        }

                        String checkPendingSeason = jo.getString(KEY_WEBURL);
                        if (checkPendingSeason.contains("Running#PendingBiggopti")) {

                            pendingUrl = jo.getString(KEY_NAME);
                            Log.i(TAG, "ForScript: Running#PendingBiggopti: " + pendingUrl);
                        }
                        scriptList.add(0, p);
                    }


//                    else {
//
//                        listView.removeFooterView(listFooterProgressView);
//                        listView.removeFooterView(footerText);
//                        listView.addFooterView(footerText);
//                        userListAdapter.notifyDataSetChanged();
//
//                        isEndOfSpreadSheet = true ;
//
//                    }


                }

                if (pendingUrl != null) {
                    Common.APP_SCRIPT_PENDING_POST_URL = pendingUrl;
                }

                if (biggoptiUrl != null) {

                    Common.APP_SCRIPT_WEB_APP_URL = biggoptiUrl;
                    sendRequestForTwentyRows();

                } else {
                    sendRequestForScriptUrl();
                }


                // Log.e("uImage","ser image"+uImages[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void showJSONForScriptUrl(String json) {
        fahim.footwear.admin.JsonParser pj = new fahim.footwear.admin.JsonParser(json);
        pj.parseJSON();

        if (fahim.footwear.admin.JsonParser.uNames != null && fahim.footwear.admin.JsonParser.uNames[1] != null && !fahim.footwear.admin.JsonParser.uNames[1].equals("")) {
            Common.APP_SCRIPT_PENDING_POST_URL = fahim.footwear.admin.JsonParser.uNames[1];
        }

        if (fahim.footwear.admin.JsonParser.uIds != null && fahim.footwear.admin.JsonParser.uIds[1] != null && !fahim.footwear.admin.JsonParser.uIds[1].equals("")) {
            Common.APP_SCRIPT_WEB_APP_URL = fahim.footwear.admin.JsonParser.uIds[1];

            //sendRequestForadvertises();
            sendRequestForTwentyRows();

            //  showToast(mContext, "Script Url Found, Request For Advertises", Toast.LENGTH_SHORT);
        } else {

            sendRequestForScriptUrl();

            // showToast(mContext, "Script Url not Found, Request For Scrit url Again", Toast.LENGTH_SHORT);


        }


    }


    public String[] reversedArray(String[] originalArray) {

        List<String> list = Arrays.asList(originalArray);
        Collections.reverse(list);
        //originalArray = new String[originalArray.length];
        originalArray = (String[]) list.toArray();

        return originalArray;

    }

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


    ///////////////////////////////////////////////////////////////////////////////////////////


    private void implementOneResponseRow(String postId, String response) {

        Log.i(TAG, "onResponse: One_rows: implementOneResponseRow Called:   " + response);


        // showJSON(response);
        JSONArray users = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            users = jsonObject.getJSONArray(KEY_USERS);

            int postPosition = 0;

            for (int i = 0; i < advertiseList.size(); i++) {  // retrive the postId position of MainList (after response Alhamdulillah Success)

                PostsModel p = advertiseList.get(i);
                String onePostID = p.getPost_id();

                if (onePostID.equals(postId)) {
                    postPosition = i;
                    Log.i(TAG, "onResponse: post Position:  " + i);
                    Log.i(TAG, "onResponse: post Position:  " + i + "  sent PostID: " + postId);

                } else {
                    Log.i(TAG, "onResponse: Not matching Position:  " + i + "  list PostID: " + onePostID);
                    Log.i(TAG, "onResponse: Not matching Position:  " + i + "  sent PostID: " + postId);


                }
            }

            for (int i = 0; i < users.length(); i++) { // Remove and replace that position  One Model data

                JSONObject jo = users.getJSONObject(i);

                PostsModel p = new PostsModel();


                p.setIds(jo.getString(KEY_ID));
                p.setName(jo.getString(KEY_NAME));
                p.setImageUrl(jo.getString(KEY_IMAGE));
                p.setPhone(jo.getString(KEY_PHONE));//////////////////////////// this is like
                p.setWebUrl(jo.getString(KEY_WEBURL));
                p.setAdText(jo.getString(KEY_ADTEXT));
                p.setComments(jo.getString(KEY_CMNT1));
                p.setCmnt1(jo.getString(KEY_CMNT1));
                p.setCmnt2(jo.getString(KEY_CMNT2));
                p.setCmnt3(jo.getString(KEY_CMNT3));
                p.setCmnt4(jo.getString(KEY_CMNT4));
                p.setCmnt5(jo.getString(KEY_CMNT5));
                p.setCmnt6(jo.getString(KEY_CMNT6));
                p.setCmnt7(jo.getString(KEY_CMNT7));
                p.setCmnt8(jo.getString(KEY_CMNT8));
                p.setCmnt9(jo.getString(KEY_CMNT9));
                p.setCmnt10(jo.getString(KEY_CMNT10));
                p.setPost_id(jo.getString(POST_ID));
                p.setUser_id(jo.getString(USER_ID));
                p.setExtra(jo.getString(KEY_EXTRA));
                advertiseList.remove(postPosition);
                advertiseList.add(postPosition, p);
                toNotifyDatasetChanged();

                Gson gson = new Gson();
                String jsonString = gson.toJson(advertiseList);
                new saveFilesToExternal(jsonString, shoe_catagory).start();


                Log.i(TAG, "onResponse: One_rows_request:  " + p.getCmnt1());
            }
            Log.i(TAG, "onResponse: One_rows_request:  " + response.toString());

            runOnUiThread(new Runnable() {

                public void run() {
                    // save index and top position
                    int index = listView.getFirstVisiblePosition();
                    View v = listView.getChildAt(0);
                    int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());
                    userListAdapter = new BiggoptiLisAdapter(BiggoptiShow.this, R.layout.list_row, advertiseList);

                    listView.setSelectionFromTop(index, top);
                    Log.i("onResponse", "run:  35 notifyDataSetChanged:  ");

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    //////////////////////////////////////////////////////////////////////////////////////////////


    private void insertLikesToSheet(final String randomPostId, final TextView tv_uPhone) {

        Log.i(TAG, "insertLikesToSheet: " + randomPostId);


        StringRequest stringRequest = new StringRequest(Common.APP_SCRIPT_WEB_APP_URL + "?action=updatelike&uId=" + randomPostId + "&uPhone=" + "1", // uPhone == likes;
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        implementOneResponseRow(randomPostId, response);
                        saveLike(randomPostId);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i(TAG, "onErrorResponse: " + error.getMessage());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //   params.put("postId", postId);
                //   params.put("down_range", String.valueOf(1));

                Log.i(TAG, "getParams: columnDown :  " + columnDown);
                Log.i(TAG, "getParams: downRange:  " + 1);

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


        class insertLikessToDatabase extends Thread { // url, id , comment, cellNumber .

            String url, id, likes;
            String result = null;

            public insertLikessToDatabase(String url, String id, String likes) {

                this.url = url;
                this.id = id;
                this.likes = likes;

            }

            @Override
            public void run() {
                super.run();

                JSONObject jsonObject = Controller.biggoptiShow_update_like(url, id, likes);// url, id , likes .

                try {

                    if (jsonObject != null) {
                        result = jsonObject.getString("result");
                        Log.i(TAG, "jsonObject 1: " + result);

                    } else {
                        Log.i(TAG, "jsonObject 2: " + result);
                        result = "JsonObject Null";

                    }
                } catch (JSONException je) {
                    Log.i(TAG, "jsonObject 3: " + je.getLocalizedMessage());
                    result = je.getLocalizedMessage().toString();

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (result != null) {
                            //sendRequestForFiftyRows();
                            //sendRequestForUpdateOneRow(position);

                            if (result.contains("#")) { // 0- liked Submitted SuccessFully  #  1- total likes from Sheet
                                String[] results = result.split("#");
                                if (results.length == 2) {
                                    tv_uPhone.setText(results[1]);
                                    tv_uPhone.setVisibility(View.VISIBLE);
                                    tv_uPhone.setTextColor(Color.BLACK);
                                    //  showToast(mContext, results[0], Toast.LENGTH_LONG);

                                    // settings.getString("uPhones" + p.getIds(), "");
                                    editor.putString("uPhones" + id, results[1]); //  1: total likes from sheet
                                    editor.putBoolean("liked" + id, true);
                                    editor.apply();

                                } else {
                                    //  showToast(mContext, "Server Problem, Cannot Submit Like", Toast.LENGTH_SHORT);
                                }

                            } else {
                                // showToast(mContext, "Server Problem, Cannot Submit Like", Toast.LENGTH_SHORT);
                            }
                        } else {
                            // showToast(mContext, "Server Problem, Cannot Submit Like", Toast.LENGTH_SHORT);

                        }

                    }
                });

            }

        }

        // showToast(mContext, "Submitting Like, Please Wait....", Toast.LENGTH_LONG);
        Log.i(TAG, "doInBackground: uPhones id 3 : p.getIds():  " + randomPostId);

        //  new submitnewCounterClick().execute(Common.APP_SCRIPT_WEB_APP_URL, p.getIds(), String.valueOf("1"));
        //  new insertLikessToDatabase(Common.APP_SCRIPT_WEB_APP_URL, randomPostId, "1").start();

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


    private boolean likeExists(String fileName) {


        File dir = commonDir();
        if (!dir.exists())
            dir.mkdir();
        File subDir = new File(dir + "/Posts_Contents");
        if (!subDir.exists())
            subDir.mkdir();
        File trpleDir = new File(subDir + "/Confidence");
        if (!trpleDir.exists())
            trpleDir.mkdir();

        File file = null;
        file = new File(trpleDir, fileName + ".txt");
        if (!file.exists())
            file = new File(trpleDir, fileName);

        if (file.exists())
            return true;
        else return false;

    }

    private void saveLike(String fileName) {


        File dir = commonDir();
        if (!dir.exists())
            dir.mkdir();
        File subDir = new File(dir + "/Posts_Contents");
        if (!subDir.exists())
            subDir.mkdir();
        File trpleDir = new File(subDir + "/Confidence");
        if (!trpleDir.exists())
            trpleDir.mkdir();


        File file = null;
        file = new File(trpleDir, fileName + ".txt");
        if (!file.exists())
            file = new File(trpleDir, fileName);

        file.mkdir();

        ///// Check Exceed/////
        if (trpleDir.exists()) {
            File[] fileListArray = trpleDir.listFiles();
            if (fileListArray != null) {
                int numberOfFiles = fileListArray.length;
                if (numberOfFiles > 200) {
                    if (trpleDir.delete()) {
                        saveLike(fileName);
                    }
                }
            }
        }


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
                                                    showToast(BiggoptiShow.this, "Necessary Permissions required for this app", Toast.LENGTH_LONG);
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
//                PostsModel listViewAdModel = new PostsModel();
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
//            savedAdapter = new BiggoptiLisAdapter(this, R.layout.list_row, advertiseList);
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