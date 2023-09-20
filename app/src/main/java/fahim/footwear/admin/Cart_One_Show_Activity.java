package fahim.footwear.admin;

import static fahim.footwear.admin.Common.Common.CART_ARRAY_LIST;
import static fahim.footwear.admin.Common.Common.CUSTOMER_ADDRESS_STRING;
import static fahim.footwear.admin.Common.Common.CUSTOMER_NAME_STRING;
import static fahim.footwear.admin.Common.Common.CUSTOMER_PHONE_STRING;
import static fahim.footwear.admin.Common.Common.GRAND_TOTAL_PRODUCT_ITEM_STRING;
import static fahim.footwear.admin.Common.Common.GRAND_TOTAL_QNTY_STRING;
import static fahim.footwear.admin.Common.Common.GRAND_TOTAL_TAKA_STRING;
import static fahim.footwear.admin.Common.Common.ORDER_DATE;
import static fahim.footwear.admin.Common.Common.ORDER_ID;
import static fahim.footwear.admin.Common.Common.SHOP_NAME_STRING;
import static fahim.footwear.admin.Common.Common.order_id;
import static fahim.footwear.admin.Common.Common.productImageLink;
import static fahim.footwear.admin.Common.CommonImage.convertImageViewToBitmap;
import static fahim.footwear.admin.Common.CommonImage.getHiResBitmap;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.ortiz.touchview.TouchImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import fahim.footwear.admin.Common.RequestModel;

public class Cart_One_Show_Activity extends AppCompatActivity {

    ListView listView;

    TextView txtShopName, txtShopAddress, txtOrderDate,
            txtProductName,
            txtUnitSystem,
            txtUnitPrice,
            txtTotalOrderQuantity,
            txtTotalPrice,
            txtCustomerPhone,
            txtCustomerName,
            txtMain_postId,
            txtOrder_id,
            txtProduct_sending_status,
            txtChalan_number_date,
            txtTotalPaid,
            txtdueMoney,
            txtPaid_status,
            txtChalan_image_status, tvChalan_imageLink, txtHeader;

    Context mContext;
    private String oneOrderId = "1";


    String productName_string,
            unitPriceString,
            totalPrice_string,
            totalOrderQuantity_string;

    RequestModel requestModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_show_activity);

        mContext = this;

        setTitle("Order Cart:");


        txtCustomerName = (TextView) findViewById(R.id.txtCustomerName);
        txtCustomerPhone = (TextView) findViewById(R.id.txtCustomerPhone);
        txtShopAddress = (TextView) findViewById(R.id.txtShopAddress);
        txtOrderDate = (TextView) findViewById(R.id.txtOrderDate);
        txtOrder_id = (TextView) findViewById(R.id.txtOrder_id);
        txtShopName = (TextView) findViewById(R.id.txtShopName);


        listView = (ListView) findViewById(R.id.list_view);
        //  listView.setStackFromBottom(true);
        listView.setDividerHeight(0);


        if (getIntent() != null) {
            if (getIntent().getStringExtra(order_id) != null) {


                oneOrderId = getIntent().getStringExtra(order_id);
                Log.i(TAG, "oneOrderId: " + oneOrderId);

                productName_string = getIntent().getStringExtra("productName");
                totalPrice_string = getIntent().getStringExtra("totalPrice");
                totalOrderQuantity_string = getIntent().getStringExtra("totalOrderQuantity");
                unitPriceString = getIntent().getStringExtra("unitPrice");

                String product_image_string = getIntent().getStringExtra(productImageLink);


                Log.i(TAG, "SHOP_NAME_STRING: " + SHOP_NAME_STRING);
                Log.i(TAG, "CUSTOMER_NAME_STRING: " + CUSTOMER_NAME_STRING);


                String[] productNameArray = productName_string.split("###");
                String[] totalPriceArray = totalPrice_string.split("###");
                String[] totalOrderQuantityArray = totalOrderQuantity_string.split("###");
                String[] unitPriceArray = unitPriceString.split("###");
                String [] productImageLinkArray = product_image_string.split("###");

                if (CART_ARRAY_LIST != null) {
                    CART_ARRAY_LIST.clear();
                }

                CART_ARRAY_LIST = new ArrayList<>();

                for (int i = 0; i < productNameArray.length; i++) {
                    RequestModel requestModel = new RequestModel();
                    requestModel.setProductName(productNameArray[i]);
                    Log.i(TAG, "productNameArray: "+productNameArray[i] );
                    requestModel.setTotalPrice(totalPriceArray[i]);
                    requestModel.setTotalOrderQuantity(totalOrderQuantityArray[i]);
                    requestModel.setUnitPrice(unitPriceArray[i]);
                    requestModel.setProductImageLink(productImageLinkArray[i]);
                    CART_ARRAY_LIST.add(0, requestModel);
                }


            }
        }


        if (CART_ARRAY_LIST.size() > 0) {
            cartAdapter = new BiggoptiLisAdapter(mContext, R.layout.cart_list_row, CART_ARRAY_LIST);
            listView.setAdapter(cartAdapter);
            setTitle("Cart : " + CART_ARRAY_LIST.size() + " Item(s)");

            txtCustomerName.setText(CUSTOMER_NAME_STRING);
            txtCustomerPhone.setText(CUSTOMER_PHONE_STRING);

            txtShopAddress.setText(CUSTOMER_ADDRESS_STRING);
            txtOrderDate.setText("Order Date: " + ORDER_DATE);
            txtOrder_id.setText("Order ID: " + ORDER_ID);
            txtShopName.setText(SHOP_NAME_STRING);
            try {
                if (getLayoutInflater() != null) {
                    LayoutInflater inflater = getLayoutInflater();
                    // ViewGroup headerView = (ViewGroup) inflater.inflate(R.layout.list_header, listView, false);
                    ViewGroup footerView = (ViewGroup) inflater.inflate(R.layout.cart_footer_layout, listView, false);

                    TextView txtTotalUnit = (TextView) footerView.findViewById(R.id.txtTotalUnit);
                    TextView txtTotalOrderQuantity = (TextView) footerView.findViewById(R.id.txtTotalOrderQuantity);
                    TextView txtTotalPrice = (TextView) footerView.findViewById(R.id.txtTotalPrice);

                    txtTotalUnit.setText("" + CART_ARRAY_LIST.size() + " Item");

                    int totalQnt = 0;
                    int totalTaka = 0;

                    for (int i = 0; i < CART_ARRAY_LIST.size(); i++) {

                        RequestModel r = CART_ARRAY_LIST.get(i);

                        String qnt = r.getTotalOrderQuantity();
                        try {
                            int t = Integer.parseInt(qnt);
                            totalQnt = totalQnt + t;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }

                        String taka = r.getTotalPrice();

                        try {
                            int s = Integer.parseInt(taka);
                            totalTaka = totalTaka + s;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }

                    }

                    txtTotalOrderQuantity.setText("" + totalQnt + " Pair");
                    txtTotalPrice.setText("" + totalTaka + "/=");

                    GRAND_TOTAL_PRODUCT_ITEM_STRING = "" + CART_ARRAY_LIST.size();
                    GRAND_TOTAL_QNTY_STRING = "" + totalQnt;
                    GRAND_TOTAL_TAKA_STRING = "" + totalTaka;




                    // listView.addHeaderView(headerView);
                    listView.addFooterView(footerView);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(mContext, "There is no Item in Cart", Toast.LENGTH_SHORT).show();
            setTitle("Cart : No Item");
        }


        loading = new ProgressDialog(mContext);

    }


    BiggoptiLisAdapter cartAdapter;


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
                v = vi.inflate(R.layout.cart_list_row, null);

            }


            final RequestModel p = getItem(position);
            if (p != null) {

                final TextView txtProductName = (TextView) v.findViewById(R.id.txtProductName);
                final TextView txtUnitPrice = (TextView) v.findViewById(R.id.txtUnitPrice);
                final TextView txtTotalOrderQuantity = (TextView) v.findViewById(R.id.txtTotalOrderQuantity);
                final TextView txtTotalPrice = (TextView) v.findViewById(R.id.txtTotalPrice);
                LinearLayout ll_cart_row = (LinearLayout) v.findViewById(R.id.ll_cart_row);

                String productNameString = "";
                if (p.getProductName() != null) {
                    txtProductName.setText(p.getProductName());
                    productNameString = p.getProductName();
                }
                String finalProductNameString = productNameString;
                ll_cart_row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(p.getProductImageLink()!=null&&!TextUtils.isEmpty(p.getProductImageLink())) {

                            showPictureFullScreen(p.getProductImageLink(), finalProductNameString);
                        }
                    }
                });

                if (p.getUnitPrice() != null) {
                    txtUnitPrice.setText(p.getUnitPrice());
                }

                if (p.getTotalOrderQuantity() != null) {
                    txtTotalOrderQuantity.setText(p.getTotalOrderQuantity());
                }

                if (p.getTotalPrice() != null) {
                    txtTotalPrice.setText(p.getTotalPrice());
                }

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

    androidx.appcompat.app.AlertDialog.Builder alertDialog;

    androidx.appcompat.app.AlertDialog imageDialog;

    private void showPictureFullScreen(String imageUrl, String productName) {

        showLoading();

        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        LayoutInflater inflater = this.getLayoutInflater();
        View imageAlert = inflater.inflate(R.layout.fullscreen_picture_alert, null);

        final TextView textView = (TextView) imageAlert.findViewById(R.id.dummyTxtView);
        final TouchImageView iv = (TouchImageView) imageAlert.findViewById(R.id.alertImageView);



        LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        iv.setLayoutParams(params);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        alertDialog.setView(imageAlert);


        imageDialog = alertDialog.create();

        Picasso.with(mContext).cancelRequest(iv);



        Picasso.with(mContext).load(imageUrl).into(iv, new Callback() {
            @Override
            public void onSuccess() {

                dismissLoading();
                Bitmap bitmap = convertImageViewToBitmap(iv);
                Bitmap hiResBitmap = getHiResBitmap(bitmap, 1000);
                iv.setImageBitmap(hiResBitmap);

                imageDialog.show();
                Toast.makeText(mContext, productName, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onError() {
                dismissLoading();


                Toast.makeText(mContext, "Something Wrong Please Try Later", Toast.LENGTH_SHORT).show();
            }
        });



        try {

            iv.setOnLongClickListener(new View.OnLongClickListener() {
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

                                            Bitmap bitmap = convertImageViewToBitmap(iv);
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

            iv.setOnClickListener(new View.OnClickListener() {

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

                                            Bitmap bitmap = convertImageViewToBitmap(iv);
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


    String TAG = "sendRequest";

    ProgressDialog loading;

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

}