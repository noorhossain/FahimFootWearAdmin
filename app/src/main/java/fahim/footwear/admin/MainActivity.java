package fahim.footwear.admin;

import static fahim.footwear.admin.Common.Common.ACTIVITY_LABEL;
import static fahim.footwear.admin.Common.Common.ALL_CATEGORY;
import static fahim.footwear.admin.Common.Common.WHICH_SHOES;
import static fahim.footwear.admin.Common.Common.dismissPublicProgress;
import static fahim.footwear.admin.Common.Common.showPublicProgress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fahim.footwear.admin.Common.Common;

public class MainActivity extends AppCompatActivity {
    Button btn_LADIES_PONSE, btn_JENTS_PONSE, btn_KIDS_PONSE, btn_LADIES_BARMIZE, btn_JENTS_BARMIZE,
            btn_KIDS_BARMIZE, btn_JENTS_CHOTI, btn_MAILA_LADIES, btn_KANGAROO, btn_CADES, btn_WINTER_COLLECTIONS
            , btn_EID_COLLECTIONS, btn_MISCELLINIOUS, btnAllCategory, btnSeeRequestLists;

    Context mContext;
    Button btnUpload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;


        btnSeeRequestLists = (Button)findViewById(R.id.btnSeeRequestLists);
        btnSeeRequestLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, Carts_Lists.class));
            }
        });


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
        btnAllCategory              = (Button) findViewById(R.id.btnAllCategory);

        btn_LADIES_PONSE           .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToButtonClicks(Common.LADIES_PONSE, "লেডিস পন্স >");

            }
        });
        btn_JENTS_PONSE            .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToButtonClicks(Common.JENTS_PONSE,
                        "জেন্টস পন্স >");

            }
        });
        btn_KIDS_PONSE             .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.KIDS_PONSE,
                        "কিডস/বাচ্চা পন্স >");

            }
        });
        btn_LADIES_BARMIZE         .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.LADIES_BARMIZE,
                        "লেডিস বার্মিজ >");

            }
        });
        btn_JENTS_BARMIZE          .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.JENTS_BARMIZE,
                        "জেন্টস বার্মিজ >");

            }
        });
        btn_KIDS_BARMIZE           .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.KIDS_BARMIZE,
                        "কিডস/বাচ্চা বার্মিজ >");

            }
        });
        btn_JENTS_CHOTI            .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.JENTS_CHOTI,
                        "জেন্টস চটি (পান্ডা/উডল্যান্ড) >");

            }
        });
        btn_MAILA_LADIES           .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.MAILA_LADIES,
                        "মাইলা লেডিস >");

            }
        });
        btn_KANGAROO               .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.KANGAROO,
                        "ক্যাংগারু >");

            }
        });
        btn_CADES                  .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.CADES,
                        "কেডস >");

            }
        });
        btn_WINTER_COLLECTIONS     .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.WINTER_COLLECTIONS,
                        "উইন্টার কালেকশন >");

            }
        });
        btn_EID_COLLECTIONS       .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.EID_COLLECTIONS,"ঈদ কালেকশন >");

            }
        });

        btn_MISCELLINIOUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToButtonClicks(Common.MISCELLINIOUS, "অন্যান্য ক্যাটাগরি >");

            }
        });


        btnAllCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToButtonClicks(ALL_CATEGORY, "সকল প্রকার >");
            }
        });

////////////////////////////////////////




        btnUpload = (Button)findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(0,0);
                Intent i = new Intent(MainActivity.this, BigptiAddPanelForPblc.class);
                startActivity(i);
                overridePendingTransition(0,0);

            }
        });
        beginActivity();

    }

    @Override
    protected void onResume() {
        super.onResume();
        dismissPublicProgress(mContext);
    }

    private void sendToButtonClicks (String which, String activityLabel){

        overridePendingTransition(0,0);
        Intent i = new Intent(MainActivity.this, BiggoptiShow.class);
        i.putExtra(WHICH_SHOES, which);
        i.putExtra(ACTIVITY_LABEL, activityLabel);
        showPublicProgress(mContext);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },50);
        startActivity(i);
        overridePendingTransition(0,0);
    }

    void beginActivity(){
        if(!checkAndRequestPermissions()){
            checkAndRequestPermissions();
        }
    }



    public static final int REQUEST_CALL_PHONE_PERMISSIONS = 111;


    private boolean checkPhoneCallPermission() {

        int permissionCallPhone = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionCallPhone != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), REQUEST_CALL_PHONE_PERMISSIONS);
            return false;
        }
        return true;


    }


    ///////////////Permissions Code ///////////////



    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;


    private boolean checkAndRequestPermissions() {


        int permissionReadExternalStorage;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            permissionReadExternalStorage = ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_MEDIA_IMAGES);
        else
            permissionReadExternalStorage = ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE);


        int permissionWriteExtarnalStorage;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            permissionWriteExtarnalStorage = ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_MEDIA_AUDIO);
        else
            permissionWriteExtarnalStorage = ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE);


        List<String> listPermissionsNeeded = new ArrayList<>();


        if (permissionWriteExtarnalStorage != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                listPermissionsNeeded.add(android.Manifest.permission.READ_MEDIA_AUDIO);
            else
                listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }

        if (permissionReadExternalStorage != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                listPermissionsNeeded.add(android.Manifest.permission.READ_MEDIA_IMAGES);
            else
                listPermissionsNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);

        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            int permissionVideoStorage = ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_MEDIA_VIDEO);

            if (permissionVideoStorage != PackageManager.PERMISSION_GRANTED) {

                listPermissionsNeeded.add(android.Manifest.permission.READ_MEDIA_VIDEO);

            }

            int notificationPermission = ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.POST_NOTIFICATIONS);

            if (notificationPermission != PackageManager.PERMISSION_GRANTED) {

                listPermissionsNeeded.add(android.Manifest.permission.POST_NOTIFICATIONS);

            }

        }



        int cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);

        if(cameraPermission != PackageManager.PERMISSION_GRANTED){
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
        }


        if(listPermissionsNeeded.size()>0){
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }

//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), REQUEST_ID_MULTIPLE_PERMISSIONS);
//            return false;
//        }

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


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    perms.put(android.Manifest.permission.READ_MEDIA_IMAGES, PackageManager.PERMISSION_GRANTED);
                    perms.put(android.Manifest.permission.READ_MEDIA_AUDIO, PackageManager.PERMISSION_GRANTED);
                    perms.put(android.Manifest.permission.READ_MEDIA_VIDEO, PackageManager.PERMISSION_GRANTED);
                    perms.put(android.Manifest.permission.POST_NOTIFICATIONS, PackageManager.PERMISSION_GRANTED);


                } else {
                    perms.put(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                    perms.put(android.Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                }

                perms.put(android.Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);


                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        perms.put(permissions[i], grantResults[i]);
                    }

                    if(grantResults[0]==RESULT_OK){
                        System.out.println("grantsults [0] : " + grantResults[0] );
                    }


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {


                        if (       perms.get(android.Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                                && perms.get(android.Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
                                && perms.get(android.Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED
                                && perms.get(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
                                && perms.get(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

                        ) {
                            // All Permissions Are granted :
                            Toast.makeText(this, "Jajakumullah, For Granting Permission.", Toast.LENGTH_LONG).show();



                            //else any one or all the permissions are not granted

                        } else {
                            if (       ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_MEDIA_IMAGES)
                                    || ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_MEDIA_AUDIO)
                                    || ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_MEDIA_VIDEO)
                                    || ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.POST_NOTIFICATIONS)
                                    || ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)

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
                                                        Toast.makeText(MainActivity.this, "Necessary Permissions required for this app", Toast.LENGTH_LONG).show();
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

                            }
                        }


                    } else {


                        if (perms.get(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                                && perms.get(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                                && perms.get(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

                        ) {
                            Toast.makeText(this, "Jajakumullah, For Granting Permission.", Toast.LENGTH_LONG).show();
                            //else any one or both the permissions are not granted


                        } else {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    || ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
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
                                                        Toast.makeText(MainActivity.this, "Necessary Permissions required for this app", Toast.LENGTH_LONG).show();
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

                            }
                        }


                    }


                }

            }

            break;


        }

    }

    private void permissionSettingScreen() {
        Toast.makeText(this, "Enable All permissions, Click On Permission", Toast.LENGTH_LONG)
                .show();

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





}