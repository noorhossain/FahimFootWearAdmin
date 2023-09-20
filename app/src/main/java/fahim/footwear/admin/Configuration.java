package fahim.footwear.admin;

import fahim.footwear.admin.Common.Common;

public class Configuration {

    //"https://script.google.com/macros/s/AKfycbyfHidhIzdDunSAfRy5pJ2oGTVieVkgHbKLIRbYRVMOgMp_wQ/exec";

    /// 1. ShowPost Url :

    public static final String ADD_USER_URL = Common.APP_SCRIPT_ALL_SHOES;
    public static final String LIST_USER_URL = Common.APP_SCRIPT_ALL_SHOES +"?action=readAll";
    public static final String URL_FOR_FIFTY_ROWS = Common.APP_SCRIPT_ALL_SHOES +"?action=read_fifty_rows";
    public static final String URL_FOR_ONE_USER_DATA = Common.APP_SCRIPT_ALL_SHOES +"?action=read_one_user_twenty_rows";
    public static final String URL_FOR_DATE_DATA = Common.APP_SCRIPT_ALL_SHOES +"?action=read_by_date";
    public static final String URL_FOR_ONE_ROW = Common.APP_SCRIPT_ALL_SHOES +"?action=read_one_row";
    /// 2. PendingPost Url :
        // pending_url: 1-12-2020:  "https://script.google.com/macros/s/AKfycbzaIn21zcm18QlhROYOLSonQv0-13WJo2XC12MOPHCJ3kZZyRpW/exec"
    public static final String APP_SCRIPT_URL_BIGPTI_PENDING = Common.APP_SCRIPT_PENDING_POST_URL;
    public static final String APP_SCRIPT_URL_BIGPTI_PENDING_READALL =APP_SCRIPT_URL_BIGPTI_PENDING+"?action=readAll";


    /// PassWordPost Url :
    public static final String APP_SCRIPT_WEB_APP_URL_BIGPTI_PASS = "https://script.google.com/macros/s/AKfycbyGy-WEaFeXTouD8i9Gjxls71u-XTesVCZsbJ1UHSLT0wORKMw/exec"; // Biggopti PassWord Sheet
    public static final String ADD_USER_URL_BIGPTI_PASS = APP_SCRIPT_WEB_APP_URL_BIGPTI_PASS;
    public static final String LIST_USER_URL_BIGPTI_PASS = APP_SCRIPT_WEB_APP_URL_BIGPTI_PASS+"?action=readAll";

    public static final String BIGPTI_PASS_ADD_BANNER_URL = APP_SCRIPT_WEB_APP_URL_BIGPTI_PASS+"?action=insert_banner_ad";
    public static final String BIGPTI_PASS_CHANGE_PASSWORD = APP_SCRIPT_WEB_APP_URL_BIGPTI_PASS+"?action=insert_password";


    public static final String USER_ID   = "userID";
    public static final String USER_NAME = "userNAME";
    public static final String POST_ID   = "postID";

    public static final String KEY_ID = "uId"; // post id : date+time
    public static final String KEY_NAME = "uName";

    public static final String KEY_PHONE = "uPhone";
    public static final String KEY_WEBURL = "uWebUrl";
    public static final String KEY_ADTEXT = "uAdText";
    public static final String KEY_CMNT1 ="cmnt1";
    public static final String KEY_CMNT2 ="cmnt2";
    public static final String KEY_CMNT3 ="cmnt3";
    public static final String KEY_CMNT4 ="cmnt4";
    public static final String KEY_CMNT5 ="cmnt5";
    public static final String KEY_CMNT6 ="cmnt6";
    public static final String KEY_CMNT7 ="cmnt7";
    public static final String KEY_CMNT8 ="cmnt8";
    public static final String KEY_CMNT9 ="cmnt9";
    public static final String KEY_CMNT10="cmnt10";
    public static final String KEY_EXTRA= "extra";
    public static final String KEY_EMAIL="Email";


    public static final String KEY_IMAGE = "uImage";





    public  static final String KEY_ACTION = "action";

    public static final String KEY_USERS = "records";
}
