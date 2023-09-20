package fahim.footwear.admin.Common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;

public class CommonImage {


    private boolean hasImageInImageView(@NonNull ImageView view, String tag) {

        String previousTag = (String) view.getTag();
        return previousTag.equalsIgnoreCase(tag);

    }

    public static Bitmap getHiResBitmap(@NonNull Bitmap image, int maxSize) {
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


    public static Bitmap convertImageViewToBitmap(ImageView imageView) {

        Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        return bm;
    }


    public static String convertBitmapToString(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }


    public static Bitmap convertStringToBitmap(String encodedString) {

        byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;
    }




    @SuppressLint("CommitPrefEdits")
    public  static  void saveStringToPref(Context mContext, String key, String value){

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }



    public  static  String getStringFromPref(Context mContext, String key){

        String prefString = null ;
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = PreferenceManager.getDefaultSharedPreferences(mContext);

        String s =  settings.getString(key, null);

        if(s!=null&&!s.equalsIgnoreCase("null")){
            prefString = s ;
        }

        return  prefString;
    }
    public  static  void setStringFromPref(Context mContext, String key, EditText editText){

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = PreferenceManager.getDefaultSharedPreferences(mContext);

        String s =  settings.getString(key, null);

        if(s!=null&&!s.equalsIgnoreCase("null")){
            editText.setText(s);
        }
    }

    public static String getString(EditText editText) {

        return editText.getText().toString().trim().replace("#", "");
    }

}
