package name.yuris.hellouser;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 * @author Yuri Nevenchenov on 6/17/2017.
 */

public class Utils {
    private static final String IS_LOGGED_IN = "logged_in";
    private static final String USER_LOGIN = "user_login";
    private static final String USER_PASSWORD = "user_password";
    private static BasicPasswordEncryptor mEncryptor;


    public static void hideKeyBoard(@NonNull View view, Context context) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void writeIsAlreadyLoggedIn(Activity activity, boolean loggedIn) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGGED_IN, loggedIn);
        editor.apply();
    }

    public static boolean readIsAlreadyLoggedIn(Activity activity) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        boolean defaultValue = false;
        return sharedPreferences.getBoolean(IS_LOGGED_IN, defaultValue);
    }

    public static void writeUserLogin(Activity activity, @NonNull String userLogin) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_LOGIN, userLogin);
        editor.apply();
    }

    public static String readUserLogin(Activity activity) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "world";
        return " " + sharedPreferences.getString(USER_LOGIN, defaultValue);
    }

    public static void writePassword(Activity activity, @NonNull String password) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_PASSWORD, makeEncryptedPassword(password));
        editor.apply();
    }

    public static boolean isPasswordMatching(Activity activity, @NonNull String newPassword){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String defValue = "rt8sj0d23nMq#2";
        String savedPasswordEncrypted = sharedPreferences.getString(USER_PASSWORD, defValue);
        return getEncryptor().checkPassword(newPassword, savedPasswordEncrypted);
    }

    private static String makeEncryptedPassword(String password) {
       return getEncryptor().encryptPassword(password);
    }

    private static BasicPasswordEncryptor getEncryptor() {
        if(mEncryptor == null){
            mEncryptor = new BasicPasswordEncryptor();
        }
        return mEncryptor;
    }


}