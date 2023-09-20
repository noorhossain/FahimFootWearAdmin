package fahim.footwear.admin.Common;

import static fahim.footwear.admin.R.drawable.download_start;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
//
//import fahim.foot.wear.ExternalDbOpenHelper;
//import fahim.foot.wear.R;
//import fahim.foot.wear.Spreadsheet_Connection.InternetConnection;

import fahim.footwear.admin._MyApplication;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

public class Common {

    public  static     String CUSTOMER_NAME_STRING = "noor hossain";
    public  static     String CUSTOMER_PHONE_STRING = "01879115959";
    public  static     String CUSTOMER_ADDRESS_STRING = "Shibchar, Madaripur";
    public  static    String  SHOP_NAME_STRING  = "shop_name";

    public  static final    String CUSTOMER_NAME = "CUSTOMER_NAME";
    public  static  final   String CUSTOMER_PHONE = "CUSTOMER_PHONE";
    public  static  final   String SHOP_ADDRESS = "CUSTOMER_ADDRESS";
    public  static  final  String  SHOP_NAME = "shop_name";
    public  static  final  String  CUSTOMER_OR_SHOP_IMAGE = "CUSTOMER_OR_SHOP_IMAGE_STRING";
    public  static    String  CUSTOMER_OR_SHOP_IMAGE_STRING = "-------";
    public  static  final String PRODUCT_SENT = "PRODUCT_SENT";
    public  static  final String NOT_SENT  = "NOT_SENT";


    public  static  final  String NOT_RECEIVED = "NOT_RECEIVED";
    public  static  final  String RECEIVED = "RECEIVED";




    public static ArrayList<RequestModel> CART_ARRAY_LIST =null ;
    public  static String ORDER_ID = null;
    public static String ORDER_DATE =null ;

    public  static  final String ACTIVITY_LABEL = "ACTIVITY_LABEL";


    public  static  final   String CUSTOMER_ADDRESS = "CUSTOMER_ADDRESS";
    public  static final String WHICH_SHOES = "which_shoes";

    public static final String      main_postId                          ="main_postId";
    public static final String      order_id                            = "order_id";

    public static final String      date                            ="date";
    public static final String      productName                     ="productName";
    public static final String      unitSystem                      ="unitSystem";
    public static final String      unitPrice                       ="unitPrice";
    public static final String      totalOrderQuantity              ="totalOrderQuantity";
    public static final String      totalPrice                      ="totalPrice";
    public static final String      customerPhone                   ="customerPhone";
    public static final String      customerName                    ="customerName";
    public static final String      customerAddress                 ="customerAddress";
    public static final String      productImageLink                ="productImageLink";
    public static final String      product_sending_status          ="product_sending_status";
    public static final String      chalan_image                    = "chalan_image";
    public static final String      chalan_number_date              = "chalan_number_date";
    public static final String      product_receiving_status        = "product_receiving_status";
    public static final String      total_paid                      = "total_paid";
    public static final String      due                             = "due";
    public static final String      paid_status                     = "paid_status";
    public static final String      sending_status_False            = "sending_status_False";
    public static final String      sending_status_True             = "sending_status_True";
    public static final String      sending_status_False_string     = "এখনও প্রোডাক্ট পাঠানো হয় নাই।";
    public static final String      sending_status_True_string      = "প্রোডাক্ট পাঠানো হয়েছে।";
    public static final String      customer_or_shop_image          = "customer_or_shop_image";
    public static final String      shopName                        = "shopName";
    public static final String      shopAddress                     = "shopAddress";

    public static final String GRAND_TOTAL_PRODUCT_ITEM = "grand_total_product_item" ;
    public static final String GRAND_TOTAL_QNTY = "grand_total_qnty" ;
    public static final String GRAND_TOTAL_TAKA = "grand_total_taka" ;

    public static  String GRAND_TOTAL_PRODUCT_ITEM_STRING = "grand_total_product_item" ;
    public static  String GRAND_TOTAL_QNTY_STRING = "grand_total_qnty" ;
    public static  String GRAND_TOTAL_TAKA_STRING = "grand_total_taka" ;




    public static final String LADIES_PONSE= "LADIES_PONSE" ;
    public static final String JENTS_PONSE = "JENTS_PONES";
    public static final String KIDS_PONSE= "KIDS_PONSE";
    public static final String LADIES_BARMIZE= "LADIES_BARMIZE";
    public static final String JENTS_BARMIZE= "JENTS_BARMIZE";
    public static final String KIDS_BARMIZE= "KIDS_BARMIZE";
    public static final String JENTS_CHOTI= "JENTS_CHOTI";
    public static final String MAILA_LADIES= "MAILA_LADIES";
    public static final String KANGAROO= "KANGAROO";
    public static final String CADES= "CADES";
    public static final String WINTER_COLLECTIONS= "WINTER_COLLECTIONS";
    public static final String EID_COLLECTIONS= "EID_COLLECTIONS";
    public static final String MISCELLINIOUS = "MISCELLINIOUS";
    public static final String ALL_CATEGORY= "ALL_CATEGORY";

    public static final String SIX_PAIR = "সিক্স-পেয়ার";
    public static final String ONE_DOZEN = "১-ডজন";


    public static String APP_SCRIPT_WEB_APP_URL = "https://script.google.com/macros/s/AKfycbxgfK8BG4rG8TagGLGvlQ_Ypzb7an3sldTYne7Mb1_EzrYwGd0SnWEdHULmw6nzlsOouQ/exec";


    public static final   String ORDER_REQUEST_URL = "https://script.google.com/macros/s/AKfycbxDPsjp9XNMM6T89jQOYS7jZp-Hu8LC83pwoUYENJiKLnDztTYTv0jboeof8UA_YHe-Aw/exec" ;

    public static final String LADIES_PONSE_URL= "https://script.google.com/macros/s/AKfycbxMS2wMRaipa_rR4s7C5jSjU08BQAuwVcceLcMcCLQSwWXh4YSOAYW9e5g5faj6cjU/exec" ;
    public static final String JENTS_PONSE_URL = "https://script.google.com/macros/s/AKfycbx-YSmjQYUzix1WWnZMrxAA-lElEB8fx2_cyS88bf3gM0zkbqtwa-KW0p-XT17_yn5JeQ/exec";
    public static final String KIDS_PONSE_URL= "https://script.google.com/macros/s/AKfycbw8P83WFgCFm4bhGvfzpWktqf2fAMulJS_cFNDSGGcosvLK5OHqjxN9S5jIEOp9WxG5gw/exec";
    public static final String LADIES_BARMIZE_URL= "https://script.google.com/macros/s/AKfycbwwE1pyJvesMJZG_Hhj2KwdeG0YjqihY8olW-Th3hhK5LyBGooS7fw4Fs69DP1NtPQhew/exec";
    public static final String JENTS_BARMIZE_URL= "https://script.google.com/macros/s/AKfycbx3rAXl2_bsmblMrvmXF0KPHelUXFd1nfY7q_jDi-x-ZD3WWjh4HVFWr43Q58a_AsItcQ/exec";
    public static final String KIDS_BARMIZE_URL= "https://script.google.com/macros/s/AKfycbx9FevJKgQ62YTB4oumXmL-GI5ooyzPlXcQ_EShzgg1WfqbkQJCD3VqPnwdTyk5MRn6zw/exec";
    public static final String JENTS_CHOTI_URL= "https://script.google.com/macros/s/AKfycbx0tZgalXHZjRB4mpyqPXQHUIc1lB_dtCac-Pzsr0zxT6xoEDDlw--0Buni3kyBLciyxQ/exec";
    public static final String MAILA_LADIES_URL= "https://script.google.com/macros/s/AKfycbzm-aDjZxqPrc1sltyAx1uUWvGCnk9PeEVmenoSxqml393HuVgkRutk-9uIfzFor-gO/exec";
    public static final String KANGAROO_URL= "https://script.google.com/macros/s/AKfycbyDdCDCZ0WZ-h-u-bdtT9GDneuiOImwqNFyyKK06XTTb_wb8xv10lzDwz_pxaU_r7Gtiw/exec";
    public static final String CADES_URL= "https://script.google.com/macros/s/AKfycbyqzhUe6eOUX7ZLQWsD1mPkMZsUBTn46nHVyuQx8oV4GJZpm_oyDok4Ez7sspxeD5Bz6Q/exec";
    public static final String WINTER_COLLECTIONS_URL= "https://script.google.com/macros/s/AKfycbypyrBFlfpHbiJJ5dY5RZ6FvVw3Rexm4ql9jZ_DsCCxBfLjJxQRQhLNQCnINUx-sK5u/exec";
    public static final String EID_COLLECTIONS_URL= "https://script.google.com/macros/s/AKfycbwVLJ0suhwkxMf7NR33Q7nFXLVHdH0rUgdHos27_KucwPXzAVPqMOtgdMDuDFIt77GUTg/exec";
    public static final String MISCELLINIOUS_URL = "https://script.google.com/macros/s/AKfycbzJm-KI1XhfqWi5lzqm7PvM3z4pG739O1smmOddfHBWWzjEyTR-8Um29mrrHrAgTbq8/exec";
    public static final String ALL_CATEGORY_URL= "https://script.google.com/macros/s/AKfycbwCYpQMeH5uzXGKS6i_5dyJtWJRu2R9O0ZuZK8Ye-fiQisyoG7Xd9ccLGMEd0hp2zDnTA/exec";;







    public static final int FAJR_SILENT_ON_CODE = 5012;

    public  static  String ONLINE_ARABIC_DATE ;
    public  static  String ONLINE_ENGLISH_DATE ;




    public static String SHARED_FONT_COLOR = "";
    public static ListAdapter TOKEN_LIST_ADAPTER;



    public static void  saveString(final String value, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_MyApplication.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void  removeStringFromSharedPreference( String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_MyApplication.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static String  loadString(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_MyApplication.getContext());
        return sharedPreferences.getString(key,  "");
    }

    public static String  loadString(String key, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_MyApplication.getContext());
        return sharedPreferences.getString(key,  defaultValue);
    }
    public static void  saveCheckBox(final boolean isChecked, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_MyApplication.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isChecked);
        editor.apply();
    }


    public static boolean loadCheckBox(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_MyApplication.getContext());
        return sharedPreferences.getBoolean(key, false);
    }








    public static String[] getTafsirLinksLineArray(Context mContext) {
        String[] _tafseer_dwonload_links = null;
        String[] whichArray = null;

        whichArray = new String[0];

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(mContext.getAssets().open("tafseers_links.txt")));
            String myLine = reader.readLine();
            ArrayList<String> lineList = new ArrayList<>();
            while (myLine != null) {
                //ttt += myLine + "\n";
                lineList.add(myLine);
                myLine = reader.readLine();
            }
            _tafseer_dwonload_links = new String[lineList.size()];
            _tafseer_dwonload_links = lineList.toArray(_tafseer_dwonload_links);
            whichArray = _tafseer_dwonload_links;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return whichArray;
    }






    public static ProgressDialog publicDialog = null;

    public static void showPublicProgress(Context context) {
        dismissPublicProgress(context);
        publicDialog = new ProgressDialog((Activity) context);
        publicDialog.setMessage("Opening....");
        publicDialog.setProgress(99);
        publicDialog.setIndeterminate(true);
        publicDialog.setCancelable(true);
        publicDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        publicDialog.show();
    }

    public static void dismissPublicProgress(Context context) {
        if (publicDialog != null && publicDialog.isShowing()) {
            try {
                publicDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    if (publicDialog.getContext() == context)
                        publicDialog.dismiss();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

    }





    public static CharSequence[] charArray() {
        ArrayList<String> listArray = new ArrayList<String>();
        for (int i = 0; i < 2001; i++) {

            listArray.add(String.valueOf(i));
        }
        CharSequence[] itemsArray = new CharSequence[listArray.size()];
        itemsArray = listArray.toArray(itemsArray);
        CharSequence[] finalItemsArray = itemsArray;
        return finalItemsArray;
    }

    public static String[] s_books_array = {"তালিকা থেকে সিলেক্ট করুন, অথবা, লিখতে এখানে ক্লিক করুন:", "এসো আলোর পথে", "মুক্তির পয়গাম", "মোদের চলার পথ ইসলাম", "কিশোর মনে ভাবনা জাগে", "নতুন পৃথিবীর স্বপ্ন", "ছোটদের ইসলাম শিক্ষা সিরিজ", "সাহসী মানুষের গল্প (৩য় খণ্ড)", "সাহসী মানুষের গল্প (৪র্থ খণ্ড)", "ইসলামী আচরণ", "নবীদের সংগ্রামী জীবন", "ছোটদের নবী", "আমাদের প্রিয় নবী", "সাহাবীদের গল্প শোন", "ছোটদের হযরত ওমর (রা:)", "আবু বকরের গল্প শোন", "ওমর ফারুকের গল্প শোন", "ওসমান গনির গল্প শোন", "আলী হায়দারের গল্প শোন", "বেহেশতের সুসংবাদ পেলেন যারা", "আদম (আ)", "আমরা সেই সে জাতি (১ম খণ্ড)", "আমরা সেই সে জাতি (২য় খণ্ড)", "সাহসী মানুষের গল্প (১ম খণ্ড)", "সাহসী মানুষের গল্প (২য় খণ্ড)", "মোরা বড় হতে চাই", "তরুণ তোমার জন্য", "ক্যারিয়ার বিকশিত জীবনের দ্বার", "তথ্যপ্রযুক্তির সহজ পাঠ", "শ্রেষ্ঠ মুসলিম মনীষীদের জীবনকথা (১ম খণ্ড)", "সত্যের সেনানী (২য় খণ্ড)", "খলিফা ওমর ইবনে আব্দুল আজিজ", "হযরত ইমাম বুখারী", "মুসলিম মনীষীদের ছেলেবেলা", "মুসলিম বিজ্ঞানীদের জীবন ও কর্ম", "ছোটদের হাসানুল বান্না", "হাজী শরীয়াতুল্লাহ", "সাইয়েদ নিসার আলী তিতুমীর", "ছোটদের মওদূদী", "নাম তার ফররুখ", "ছোটদের নজরুল", "ছোটোদের আসান ফেকাহ", "দৈনন্দিন মাসনুন দোয়া ও মাসআলা মাসায়েল", "মাসিক নতুন কিশোরকণ্ঠ", "ছোটদের ইসলামী সাধারণ জ্ঞান", "কিশোরকণ্ঠ গল্পসমগ্র", "কিশোরকণ্ঠ উপন্যাসসমগ্র", "মানবদেহের অলৌকিক রহস্য", "Youth Wave", "ক্যারিয়ার সম্পর্কে সচেতনতা সৃষ্টি", "ক্যারিয়ার প্ল্যানিং বিষয়ে প্রাথমিক ধারণা প্রদান", "প্রাথমিক পর্যায়ের টেকনিক্যাল বিষয়াদির ধারণা প্রদান", "আধুনিক পৃথিবীর নানা বিষয়ে সাধারণ ধারণা", "Understanding Science Series, BICS", "বিশ্বজয়ের কথা", "আইসিটি অভিধান", "ইসলামের প্রাথমিক পরিচয়", "ঈমানের হাকীকত", "নামাজ", "আমরা কি চাই কেন চাই কেন চাই কিভাবে চাই?", "আল্লাহর দিকে আহবান", "মুক্তির পয়গাম", "মনটাকে কাজ দিন", "আসান ফেকাহ (১ম খণ্ড)", "সংবিধান, বিআইসিএস", "কর্মপদ্ধতি, বিআইসিএস", "চরিত্র গঠনের মৌলিক উপাদান", "আদর্শ কিভাবে প্রচার করতে হবে", "তাওহীদ রেসালাত ও আখেরাত", "ইসলাম পরিচিতি", "মৃত্যু যবনিকার ওপারে", "ইসলামের সহজ পরিচয়", "মজবুত ঈমান", "ইসলামের হাকীকত", "যাকাতের হাকীকত", "ইকামাতে দ্বীন", "আল্লাহর রসূল কিভাবে নামায পড়তেন", "হজ্জের হাকীকত", "ইসলামী রাষ্ট্র ব্যবস্থা", "শিক্ষাব্যবস্থার ইসলামী রূপরেখা", "অর্থনীতিতে রাসূলের (সাঃ) দশ দফা", "ইসলামের স্বর্ণযুগে সামাজিক ন্যায়", "পর্দার আসল রূপ", "শিক্ষা সেমিনার প্রবন্ধ সংকলন", "জাতীয় শিক্ষা স্মারক", "ইসলামের রাজনৈতিক মতবাদ", "ইসলামী অর্থব্যবস্থার মূলনীতি", "শিক্ষা সাহিত্য সংস্কৃতি", "সলামী রাষ্ট্র কিভাবে প্রতিষ্ঠিত হয়", "ইসলামী আন্দোলনের নৈতিক ভিত্তি", "ইসলামী আন্দোলন সাফল্যের শর্তাবলী", "সত্যের সাক্ষ্য", "ইসলামী আন্দোলনের কর্মীদের পারস্পরিক সম্পর্ক", "রসূলুল্লাহর বিপ্লবী জীবন", "যুগে যুগে ইসলামী আন্দোলন", "আসহাবে রাসূলের জীবন কথা (১ম খণ্ড)", "আসহাবে রাসূলের জীবন কথা (২য় খণ্ড)", "কারাগারে রাতদিন", "ইসলামী আন্দোলন ও সংগঠন", "কারাগারের স্মৃতি", "সাহাবীদের আলোকিত জীবন (১ম খণ্ড)", "সাহাবীদের আলোকিত জীবন (২য় খণ্ড)", "স্মৃতি অমলিন (১ম খণ্ড)", "ইসলামী আন্দোলন সমস্যা ও সম্ভাবনা", "আসান ফেকাহ (২য় খণ্ড)", "কবীরা গুনাহ", "কাওয়ায়িদুল কুরআন", "তা’লীমুল কুরআন", "শব্দার্থে আল কুরআনুল মজীদ", "কোরআনের অভিধান", "শব্দে শব্দে আল কুরআন", "কুরআন অধ্যয়ন সহায়িকা", "একমাত্র ধর্ম", "কোরআনের চারটি মৌলিক পরিভাষা", "ভাঙ্গা ও গড়া", "হেদায়াত", "আল্লাহর পরিচয়", "তাযকিয়াতুন নাফস", "আখেরাতের জীবনচিত্র", "ইসলাম ও জাহেলিয়াত", "সীরাতে সরওয়ারে আলম (১ম খণ্ড)", "ইসলামী রেনেসাঁ আন্দোলন", "ইসলামী সমাজ বিপ্লবের ধারা", "ইসলাম ও জাহেলিয়াতের চিরন্তন দ্বন্দ্ব", "একটি আদর্শবাদী দলের পতনের কারণঃ তার থেকে বাঁচার উপায়", "আধুনিক যুগে ইসলামী বিপ্লব", "ইসলামী সংগঠন", "ইসলামী আন্দোলনের কর্মীদের প্রশিক্ষণ গাইড", "সংগঠনে কী পেলাম", "বিভিন্ন দেশে ইসলাম প্রচার", "উপমহাদেশে ইসলামী আন্দোলন", "শাহ ওয়ালিউল্লাহ মুহাদ্দিসে দেহলভী", "শাহ আব্দুল আজিজ", "মুজাহিদ আন্দোলনের উত্থান, সংগঠন ও কার্যক্রম পরিচালনা", "হজরত শাহজালাল ও শাহ মখদুম", "ফরায়েজী আন্দোলন", "তিতুমীরের আন্দোলন", "উপমহাদেশের আজাদি আন্দোলন", "সাইয়েদ আহমদ বেরলভী", "মুজাদ্দিদ.ই আলফে সানি শায়খ আহমেদ সিরহিন্দ", "আর রাহীকুল মাখতুম", "অথবা, সীরাতে ইবনে হিশাম", "অথবা, সীরাতে সরওয়ারে আলম (২য় খণ্ড)", "অথবা, মানবতার বন্ধু মুহাম্মদ রসূলুল্লাহ (স.)", "রাসূলুল্লাহর (সা) মক্কার জীবন", "আসহাবে রাসূলের জীবনকথা", "আসহাবে রাসূলের জীবনকথা (৩য় খণ্ড)", "আসহাবে রাসূলের জীবনকথা (৪র্থ খণ্ড)", "আসহাবে রাসূলের জীবনকথা (৫ম খণ্ড)", "আসহাবে রাসূলের জীবনকথা (৬ষ্ঠ খণ্ড)", "ওহাবী আন্দোলন", "আজাদী আন্দোলনে আলেম সমাজের সংগ্রামী ভূমিকা", "মুহাররমের শিক্ষা", "খিলাফতে রাশেদা", "ওমর ইবনে আব্দুল আজীজ", "উসমানী খিলাফাতের ইতিকথা", "ইমাম ইবনে তাইমিয়ার সংগ্রামী জীবন", "বাংলার মুসলমানদের ইতিহাস", "আমাদের জাতিসত্তার বিকাশধারা", "দার্শনিক শাহ্ ওয়ালিউল্লাহ মুহাদ্দিসে দেহলভী ও তাঁর চিন্তাধারা", "দি ইন্ডিয়ান মুসলমানস", "ইসলামের শক্তির উৎস", "জীবন সায়াহ্নে মানবতার রূপ", "বিশ্বনবীর সাহাবী (১ম খণ্ড)", "বিশ্বনবীর সাহাবী (২য় খণ্ড)", "শাহ্ ওয়ালিউল্লাহ মুহাদ্দিসে দেহলভী ও জামালউদ্দিন আফগানী", "বদিউজ্জামান সাঈদ নুরসী এবং তুরস্ক", "চেতনার বালাকোট", "ইসলামের পুনর্জাগরণে ইখওয়ানুল মুসলিমুনের ভূমিকা", "আধুনিক যুগ, ইসলাম, কৌশল ও কর্মসূচি", "মাওলানা মওদূদী একটি জীবন একটি ইতিহাস", "ইসলামী ঐক্য ইসলামী আন্দোলন", "বাংলাদেশে ইসলামী ঐক্যপ্রচেষ্টার ইতিহাস", "পলাশী থেকে বাংলাদেশ", "মুসলিম মানসে সংকট", "জামায়াতে ইসলামীর ইতিহাস", "জামায়াতে ইসলামীর ঊনত্রিশ বছর", "রাজনীতিতে জামায়াতে ইসলামী", "শহীদ হাসানুল বান্নার ডায়েরি", "ইসলামী পুনর্জাগরণ : সমস্যা ও সম্ভাবনা", "ইসলামী দাওয়াত ও কর্মনীতি", "দা’য়ী ইলাল্লাহ্ দা’ওয়াত ইলাল্লাহ্", "দাওয়াতে দ্বীন ও তার কর্মপন্থা", "অন্যান্য ধর্মাবলম্বীদের প্রতি ইসলামের আহ্বান", "দাওয়াতে দ্বীনের গুরুত্ব ও কৌশল", "দ্বীনে হক", "ইসলামী নেতৃত্বের গুণাবলী", "ইসলামী নেতৃত্ব", "বাইয়াতের হাকিকাত", "ইসলামের সামাজিক বিধান", "পর্দা ও ইসলাম", "আদাবে জিন্দেগী", "মাতা", "সুবহে সাদিক", "ইসলামের বুনিয়াদী শিক্ষা", "ইসলামের দৃষ্টিতে জন্মনিয়ন্ত্রণ", "ইসলাম ও আধুনিক মুসলিম নারী", "পরিবার ও পারিবারিক জীবন", "সুদ ও আধুনিক ব্যাংকিং", "ইসলাম ও অর্থনৈতিক চ্যালেঞ্জ", "ইসলামী অর্থনীতি নির্বাচিত প্রবন্ধ", "আল", "খেলাফত ও রাজতন্ত্র", "ইসলামী রাষ্ট্রে অমুসলিমদের অধিকার", "ইসলামে মানবাধিকার", "শরিয়তী রাষ্ট্রব্যবস্থা", "একটি সত্যনিষ্ঠ দলের প্রয়োজন", "ইসলামী রাষ্ট্রব্যবস্থা : তত্ত্ব ও প্রয়োগ", "মক্কার পথ", "ইসলামী সংস্কৃতির মর্মকথা", "ইসলামী সংস্কৃতি", "সংস্কৃতি ও বিনোদন", "ইসলামী সাহিত্য : মূল্যবোধ ও উপাদান", "ইসলামী উসুলে ফিকাহ", "সুন্নাত ও বিদয়াত", "মতবিরোধপূর্ণ বিষয়ে সঠিক পন্থা অবলম্বনের উপায়", "আল্লাহর রসূল কিভাবে নামাজ পড়তেন", "কবীরা গুনাহ, ইমাম আযযাহাবী", "গীবত, ইমাম গাজ্জালী", "ইসলামে হালাল ও হারামের বিধান", "ফিকহুস সুন্নাহ (৩য় খণ্ড)", "একজন মুসলমানের যা যা করণীয়", "ইসলাম ও অন্যান্য মতবাদ", "ধর্মনিরপেক্ষ মতবাদ", "ইসলাম ও জাতীয়তাবাদ", "রাষ্ট্রবিজ্ঞানের কথা", "দর্শন কোষ",};


    public static String[] hadisBookArray = {
            "তালিকা থেকে সিলেক্ট করুন, অথবা, লিখতে এখানে ক্লিক করুন:",
            "রাহে আমল (১ম খণ্ড)"
            , "রাহে আমল (২য় খণ্ড)"
            , "এন্তেখাবে হাদীস (১ম খণ্ড)"
            , "এন্তেখাবে হাদীস (২য় খণ্ড)"
            , "রিয়াদুস সালেহীন (১ম খণ্ড)"
            , "রিয়াদুস সালেহীন (২য় খণ্ড)"
            , "রিয়াদুস সালেহীন (৩য় খণ্ড)"
            , "রিয়াদুস সালেহীন (৪র্থ খণ্ড)"
            , "রিয়াদুস সালেহীন (৫ম খণ্ড)"
            , "হাদীস শরীফ (১ম খণ্ড)"
            , "হাদীস শরীফ (২য় খণ্ড)"
            , "হাদিসের পরিচয়"
            , "হাদীসের আলোকে মানব জীবন (১ম খণ্ড)"
            , "হাদীসের আলোকে মানব জীবন (২য় খণ্ড)"
            , "হাদীসের আলোকে মানব জীবন (৩য় খণ্ড)"
            , "হাদীসের আলোকে মানব জীবন (৪র্থ খণ্ড)"
            , "যাদে রাহ্"
            , "হাদিসে কুদসী"
            , "আততারগীব ওয়াত তারহীব"
            , "আল-আদাবুল মুফরাদ"
            , " মুয়াত্তা ইমাম মালিক"
            , " বুখারি শরিফ "
            , "মুসলিম শরিফ "
            , " তিরমিজি শরিফ "
            , "আবু দাউদ শরিফ "
            , " ইবনে মাজা শরিফ "
            , "নাসাই শরিফ "
            , " শামাইলুন নাবিয়্যি"
            , " হাদিস নিয়ে বিভ্রান্তি"
            , "সহীহ বুখারী"
            , "সহীহ মুসলিম"
            , "সহীহ মুসলিম"
            , "সুনান আবূ দাউদ"
            , "সূনান আবু দাউদ"
            , "সূনান আত তিরমিজী"
            , "সূনান তিরমিজী"
            , "সহীহ শামায়েলে তিরমিযী"
            , "সূনান নাসাঈ"
            , "রিয়াযুস স্বা-লিহীন"
            , "আল-লুলু ওয়াল মারজান"
            , "সুনানে ইবনে মাজাহ"
            , "সুনান আদ-দারেমী"
            , "বুলুগুল মারাম"
            , "আল-আদাবুল মুফরাদ"
            , "মুসনাদে আহমাদ"
            , "মুয়াত্তা মালিক"
            , "মিশকাতুল মাসাবীহ"
            , "হাদীস সম্ভার"
            , "সুনান আদ-দারাকুতনী"
            , "সহীহ হাদিসে কুদসি"
            , "রমযান বিষয়ে জাল ও দুর্বল হাদিসসমূহ"
            , "আন্‌-নওয়াবীর চল্লিশ হাদীস"
            , "যঈফ ও জাল হাদিস",
            "ঈমান",
            "ইসলাম",
            "ইবাদত",
            "ইলম",
            "পিতা-মাতার প্রতি কর্তব্য",
            "শিষ্টাচার",
            "সালাত",
            "সাওম",
            "ইসলামী আন্দোলন",
            "লক্ষ্য ও উদ্দেশ্য",
            "পাঁচ দফা কর্মসূচি",
            "তাওহিদ",
            "আখিরাত",
            "রিসালাত",
            "আনুগত্য",
            "পর্দা",
            "তাকওয়া",
            "বাইয়াত",
            "মুমিনের গুণাবলি",
            "তাওহিদ",
            "রিসালাত",
            "আখিরাত",
            "তাকওয়া",
            "আল্লাহর পথে ব্যয়",
            "ইসলাম",
            "ইসলামী আন্দোলন",
            "বাইয়াত",
            "ত্যাগ-কুরবানি",
            "মুমিনের গুণাবলি",
            "দায়িত্বশীলের গুণাবলি",
            "সবর ও তাওয়াক্কুল",
            "পর্দা",
            "অর্থব্যবস্থা",
            "রাষ্ট্রব্যবস্থা",
            "হজ্ব",
            "জান্নাত",
            "জাহান্নাম",
            "মুয়ামালাত",
            "ইসলামী আন্দোলন না করার পরিণাম",
            "আত্মশুদ্ধি",
            "মুমিনদের পারস্পরিক সম্পর্ক",
            "সালাত",
            "যাকাত",
            "পরামর্শ",
            "ইহতেসাব",
            "শাহাদাত",
            "লক্ষ্য-উদ্দেশ্য",
            "পাঁচ দফা কর্মসূচি",
            "উপরের একটিও নয়, তাহলে এখানে ক্লিক করুন:"

    };

    public static String[] hadisBishoyArray = {
            "তালিকা থেকে সিলেক্ট করুন, অথবা, লিখতে এখানে ক্লিক করুন:",
            "ঈমান",
            "ইসলাম",
            "ইবাদত",
            "ইলম",
            "পিতা-মাতার প্রতি কর্তব্য",
            "শিষ্টাচার",
            "সালাত",
            "সাওম",
            "ইসলামী আন্দোলন",
            "লক্ষ্য ও উদ্দেশ্য",
            "পাঁচ দফা কর্মসূচি",
            "তাওহিদ",
            "আখিরাত",
            "রিসালাত",
            "আনুগত্য",
            "পর্দা",
            "তাকওয়া",
            "বাইয়াত",
            "মুমিনের গুণাবলি",
            "তাওহিদ",
            "রিসালাত",
            "আখিরাত",
            "তাকওয়া",
            "আল্লাহর পথে ব্যয়",
            "ইসলাম",
            "ইসলামী আন্দোলন",
            "বাইয়াত",
            "ত্যাগ-কুরবানি",
            "মুমিনের গুণাবলি",
            "দায়িত্বশীলের গুণাবলি",
            "সবর ও তাওয়াক্কুল",
            "পর্দা",
            "অর্থব্যবস্থা",
            "রাষ্ট্রব্যবস্থা",
            "হজ্ব",
            "জান্নাত",
            "জাহান্নাম",
            "মুয়ামালাত",
            "ইসলামী আন্দোলন না করার পরিণাম",
            "আত্মশুদ্ধি",
            "মুমিনদের পারস্পরিক সম্পর্ক",
            "সালাত",
            "যাকাত",
            "পরামর্শ",
            "ইহতেসাব",
            "শাহাদাত",
            "লক্ষ্য-উদ্দেশ্য",
            "পাঁচ দফা কর্মসূচি",
            "উপরের একটিও নয়, তাহলে এখানে ক্লিক করুন:"

    };
    public static final String DB_NAME = "tafheemul_quran.db";

    public static String COMMON_SURA_NAME = "";
    public static String COMMON_ARABIC_AYAH = "";
    public static String COMMON_BENGALI_TRANS = "";

        //APP_SCRIPT_WEB_APP_URL
    //https://script.google.com/macros/s/AKfycbwCYpQMeH5uzXGKS6i_5dyJtWJRu2R9O0ZuZK8Ye-fiQisyoG7Xd9ccLGMEd0hp2zDnTA/exec

    public static String APP_SCRIPT_ALL_SHOES = "https://script.google.com/macros/s/AKfycbwCYpQMeH5uzXGKS6i_5dyJtWJRu2R9O0ZuZK8Ye-fiQisyoG7Xd9ccLGMEd0hp2zDnTA/exec";
    public static String APP_SCRIPT_PENDING_POST_URL = "";

    public static String APP_IS_RUNNING = "";

    public static String AZAN_NAME = "";

    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "com.alquran.tafhimul_quran";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    public static final String RESULT_DISTRICT = PACKAGE_NAME +
            ".RESULT_DISTRICT";
    public static final String RESULT_COUNTRY = PACKAGE_NAME +
            ".RESULT_COUNTRY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";


    public static final String ADVERTISE_DATABASE = "advertise_database";

    public static final String LISTVIEW_ADVERTISE_DATABASE = "listview_advertise_database";


    public static final int PICK_IMAGE_REQUEST = 9999;
    public static final int PICK_CARD_AD_IMAGE_REQUEST = 8888;
    public static final int PICK_PUBLISHED_IMAGE_REQUEST = 5555;

    public static final int PICK_LIST_AD_IMAGE_REQUEST = 6666;

    public static String PUBLISH_IMAGE_PASSWORD = "";
    public static String PUBLISH_E_ShopURl_PASSWORD = "";

    public static MediaPlayer mp;
    public static TextToSpeech textToSpeechService;

    public static Location mLastLocation;

    public static String FAJR_TIME_NOTIFICATION = "";
    public static String DHUHR_TIME_NOTIFICATION = "";
    public static String ASR_TIME_NOTIFICATION = "";
    public static String MAGHRIB_TIME_NOTIFICATION = "";
    public static String ISHA_TIME_NOTIFICATION = "";


    public static void stopBanglaReading() {
        isPlayingInTafseerFragment = false;

        if (textToSpeechService != null) {
            try {
                textToSpeechService.stop();
                textToSpeechService.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public static ArrayList<Locale> languages;
    public static String TAG = "CommonClass";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initSupportedLanguagesLollipop() {
        languages = new ArrayList<Locale>();
        Set<Locale> availableLocales = textToSpeechService.getAvailableLanguages();
        for (Locale locale : availableLocales) {
            languages.add(locale);
        }
    }

    private void initSupportedLanguagesLegacy() {

        languages = new ArrayList<Locale>();

        Locale[] allLocales = Locale.getAvailableLocales();
        for (Locale locale : allLocales) {
            try {
                int res = textToSpeechService.isLanguageAvailable(locale);
                locale.getVariant();
                boolean hasVariant = locale.getVariant().length() > 0;
                locale.getCountry();
                boolean hasCountry = locale.getCountry().length() > 0;

                boolean isLocaleSupported =
                        !hasVariant && !hasCountry && res == TextToSpeech.LANG_AVAILABLE ||
                                !hasVariant && hasCountry && res == TextToSpeech.LANG_COUNTRY_AVAILABLE ||
                                res == TextToSpeech.LANG_COUNTRY_VAR_AVAILABLE;

                Log.d(TAG, "Locale : || " + locale + " || Language: " + locale.getLanguage() + " || CountryName " + " [" + locale.getDisplayName() + "] || " + " (supported=" + isLocaleSupported + ",res=" + res + ", country=" + locale.getCountry() + ", variant=" + locale.getVariant() + ")");

                if (isLocaleSupported) {
                    languages.add(locale);
                }
            } catch (Exception ex) {
                Log.e(TAG, "Error checking if language is available for TTS (locale=" + locale + "): " + ex.getClass().getSimpleName() + "-" + ex.getMessage());
            }
        }
    }







    public static void copyText(final Context mContext, final String allTextCopy) {
        Typeface qalam = Typeface.createFromAsset(mContext.getAssets(), "qalam.ttf");
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mContext);
        builder.setTitle("Select To Copy: ");
        final EditText editText = new EditText(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(lp);
        editText.setText(allTextCopy);
        editText.setTypeface(qalam);
        builder.setView(editText);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("Copy Text", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("search.tafheem.noor Text Viewer", allTextCopy);
                cm.setPrimaryClip(clip);
                Toast.makeText(mContext, "Copied", Toast.LENGTH_SHORT).show();
            }
        });
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }


    public  static void copyText (String text){


        ClipboardManager cm = (ClipboardManager) _MyApplication.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("search.tafheem.noor Text Viewer", text);
        cm.setPrimaryClip(clip);
        Toast.makeText(_MyApplication.getContext(), "Copied", Toast.LENGTH_SHORT).show();

    }

    public  static  String numberEToB(String englishNumber){

        String bnNumber = englishNumber.replace("1", "১")
                .replace("2", "২")
                .replace("3", "৩")
                .replace("4", "৪")
                .replace("5", "৫")
                .replace("6", "৬")
                .replace("7", "৭")
                .replace("8", "৮")
                .replace("9", "৯")
                .replace("0", "০");

        return  bnNumber;

    }

    public  static  String numberBToE(String bnNumber){

        String enNumber = bnNumber
                .replace( "১","1")
                .replace( "২","2")
                .replace( "৩","3")
                .replace( "৪","4")
                .replace( "৫","5")
                .replace( "৬","6")
                .replace( "৭","7")
                .replace( "৮","8")
                .replace( "৯","9")
                .replace( "০","0");

        return  enNumber;

    }



    public static boolean isPlayingInTafseerFragment = false;

    private static final String SURAH_ID = "surah_id";

    private static final String VERSE_ID = "verse_id";
    private static final String WORDS_ID = "words_id";
    private static final String WORDS_bN = "translate_bn";
    private static final String WORDS_AR = "words_ar";


    public static long integerToLong(int minute) {
        int delay = minute * 1000;
        long diff = (long) delay;
        return diff;
    }
    @SuppressWarnings("deprecation")
    public static Spanned makeHtmlToHtml(Spanned html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(String.valueOf(html),Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(String.valueOf(html));
        }
        return result;
    }

    public static File checkMoreDbFilePath(Context mContext, String actualDbName, String filenameWithExtension) {

        String folderName = "";
        if (actualDbName.equalsIgnoreCase("tafheem_urdu")) {
            folderName = "UrduTafheem";
        } else if (actualDbName.equalsIgnoreCase("tafheem_english")) {
            folderName = "EnglishTafheem";
        } else if (actualDbName.equalsIgnoreCase("fezilalilquran")) {// bengali
            folderName = "Darsul_Quran";
        } else {
            folderName = "MoreTafseers";

        }

        File dir = commonDir();
        File secondDir = new File(dir + "/" + folderName);
        File file = new File(secondDir, filenameWithExtension);
        if (!file.exists()) {
            file = new File(secondDir, filenameWithExtension.replaceAll("\\..*", ""));
        }

        return file;

    }

    public static File fileNamePathInTilawaat(Context mContext, String folderName, String filenameWithExtension) {
        File dir = commonDir();
        File secondDir = new File(dir + "/" + folderName);
        File fileNamePath = new File(secondDir, filenameWithExtension);
        return fileNamePath;

    }

    public static File secondFolderPath(String folderName) {

        File dir = commonDir();
        File secondDir = new File(dir + "/" + folderName);
        if (!secondDir.exists()) {
            secondDir.mkdirs();
        }
        return secondDir;
    }


    //public  static  File rootTilawaatDir (Context mContext){

    public static File commonDir() {
        File dir = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            dir = new File(_MyApplication.getContext().getExternalFilesDir(null) + "/tilawaat");
            // dir = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+ "/tilawaat" );
        } else {
            dir = new File(Environment.getExternalStorageDirectory() + "/tilawaat");
        }

        try {
            if (!dir.exists()) {
                // Make it, if it doesn't exit
                boolean success= false;
                try {
                    success =dir.mkdirs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(!success) {
                    dir=null;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dir;

    }

    public static FileInputStream fileInputStreamFromUri(Uri uri) {
        FileInputStream fileInputStream = null;
        try {

            ParcelFileDescriptor objects =_MyApplication.getContext().getContentResolver().openFileDescriptor(uri, "r");
            if(objects!=null&&objects.getFileDescriptor()!=null)
            fileInputStream = new FileInputStream(objects.getFileDescriptor());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  fileInputStream;
    }

    public static File commonDocumentDir() {
        File dir = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //  dir = new File (_MyApplication.getContext().getExternalFilesDir(null) + "/tilawaat");
            dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/tilawaat");
        } else {
            dir = new File(Environment.getExternalStorageDirectory() + "/tilawaat");
        }

        try {
            if (!dir.exists())
                dir.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dir;

    }

    public static boolean isApi30() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return true;
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public  static  Uri  saveDbFilesToMediaStore ( String fileName){
        // sqlite : "application/x-sqlite3"
        // text : "text/plain"



        ContentValues values = new ContentValues();

        values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);       //file name
        values.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");        //file extension, will automatically add to file
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, "Download");     //end "/" is not mandatory  [Download, Documents]
            values.put(MediaStore.Video.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Video.Media.IS_PENDING, 1);
        }
        values.put(MediaStore.Video.Media.TITLE, fileName);
        Uri uri = null ;
        try {
           // uri: content://media/external_primary/file/152   --- photo
            //uri: content://media/external/file/191           ..... file
           uri = _MyApplication.getContext().getContentResolver().insert(MediaStore.Files.getContentUri("external"), values); //important!
//            Uri collection =
//                    MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
//
//            uri = _MyApplication.getContext().getContentResolver().insert(Uri.parse("content://media/external/file"), values);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  uri ;

    }
    public  static  void  saveVideoToMediaStore (){
        ContentValues valuesvideos;
        valuesvideos = new ContentValues();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            valuesvideos.put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/" + "YourFolder");
            valuesvideos.put(MediaStore.Video.Media.DATE_TAKEN, System.currentTimeMillis());
            valuesvideos.put(MediaStore.Video.Media.IS_PENDING, 1);
        }
        valuesvideos.put(MediaStore.Video.Media.TITLE, "SomeName");
        valuesvideos.put(MediaStore.Video.Media.DISPLAY_NAME, "SomeName");
        valuesvideos.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        valuesvideos.put(MediaStore.Video.Media.DATE_ADDED, System.currentTimeMillis() / 1000);

        ContentResolver resolver = _MyApplication.getContext().getContentResolver();
        Uri collection = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        }
        assert collection != null;
        Uri uriSavedVideo = resolver.insert(collection, valuesvideos);
    }


    public static Uri getUriFromFilePath(File filePath) {
        Uri fileUri = FileProvider.getUriForFile(_MyApplication.getContext(), "com.alquran.tafhimul_quran.provider", filePath);
        return fileUri;

    }



  public static   Toast  toast ;

    public static   void showPublicToast (String text, int length){

        if(toast!=null)
            toast.cancel();
        toast = Toast.makeText(_MyApplication.getContext(), text, length);
        toast.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                toast.cancel();
            }
        },2000);


    }

////////////////////////////word_by_word ///////////////////////

    public static final int FONT_QALAM_MAJEED = 0;
    public static final int FONT_HAFS = 1;
    public static final int FONT_NOOREHUDA = 2;
    public static final int FONT_ME_QURAN = 3;
    public static final int FONT_MAX = 3;

    public static final String LANG = "lang";
    public static final String LANG_BN = "bn";
    public static final String LANG_EN = "en";
    public static final String LANG_INDO = "indo";
    public static final String SHOW_TRANSLATION = "showTranslation";
    public static final String WORD_BY_WORD = "wordByWord";
    public static final String KEEP_SCREEN_ON = "keepScreenOn";
    public static final String ARABIC_FONT = "arabicFont";
    public static final String FONT_SIZE_ARABIC = "fontSizeArabic";
    public static final String FONT_SIZE_TRANSLATION = "fontSizeTranslation";
    public static final String FIRST_RUN = "firstRun";
    public static final String DATABASE_VERSION = "dbVersion";

   // public static final String defaultLang = "en";
    public static final String defaultLang = "bn";
    public static final boolean defaultShowTranslation = true;
    public static final boolean defaultWordByWord = true;
    public static final boolean defaultKeepScreenOn = true;
    public static final String defaultArabicFont = "PDMS_IslamicFont.ttf";
    public static final String defaultFontSizeArabic = "30";
    public static final String defaultFontSizeTranslation = "14";

    // public String lang;
    public boolean rtl;
    public boolean showTranslation;
    public boolean wordByWord;
    public boolean fullWidth;
    public boolean keepScreenOn;
    public boolean enableAnalytics;
    public String fontArabic;
    public String fontSizeArabic;
    public int fontSizeTranslation;

    public void load(Context context) {
        Log.d("Config", "Load");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            loadDefault();
            fontArabic = sp.getString(Common.ARABIC_FONT, Common.defaultArabicFont);
            fontSizeArabic = sp.getString(Common.FONT_SIZE_ARABIC, Common.defaultFontSizeArabic);
            Log.d("Config", "Loading Custom");

        } catch (Exception e) {
            loadDefault();
            Log.d("Config", "Exception Loading Defaults");
        }
    }

    public void loadDefault() {
        fontArabic = defaultArabicFont;
        fontSizeArabic = defaultFontSizeArabic;
    }


}
