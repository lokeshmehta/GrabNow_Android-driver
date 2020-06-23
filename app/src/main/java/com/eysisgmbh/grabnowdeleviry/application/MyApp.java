package com.eysisgmbh.grabnowdeleviry.application;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;


import com.eysisgmbh.grabnowdeleviry.R;
import com.eysisgmbh.grabnowdeleviry.custom.CustomActivity;
import com.eysisgmbh.grabnowdeleviry.model.LoginModel;
import com.eysisgmbh.grabnowdeleviry.utils.TypefaceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import javinator9889.localemanager.application.BaseApplication;

public class MyApp extends BaseApplication {

    private static final String KEYSERVERID = "keyserverid";
    public static String SHARED_PREF_NAME = "RS_PREF";
    private static ProgressDialog dialog;
    private static Context ctx;
    private static MyApp myApplication = null;

    long timeStart = 0;

   /* public static void showLanguagePopup(TextView v, Activity activity) {

        PopupMenu popup = new PopupMenu(activity, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_language, popup.getMenu());
        if (MyApp.localeManager.getLanguage().equalsIgnoreCase("en")) {
            popup.getMenu().getItem(0).setVisible(false);
        } else if (MyApp.localeManager.getLanguage().equalsIgnoreCase("da")) {
            popup.getMenu().getItem(1).setVisible(false);
        } else {
            popup.getMenu().getItem(2).setVisible(false);
        }


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.lang_english:
                        localeManager.setNewLocale(activity, LanguagesSupport.Language.ENGLISH);

                        // activity.recreate();
                        activity.startActivity(new Intent(activity, activity.getClass()));
                        CustomActivity.animateFade(activity);
                        activity.finish();
                        return true;
                    case R.id.lang_danish:
                        localeManager.setNewLocale(activity, LanguagesSupport.Language.DANISH);
                        activity.startActivity(new Intent(activity, activity.getClass()));
                        CustomActivity.animateFade(activity);
                        activity.finish();
                        return true;
                    case R.id.lang_german:
                        localeManager.setNewLocale(activity, LanguagesSupport.Language.GERMAN);
                        activity.startActivity(new Intent(activity, activity.getClass()));
                        CustomActivity.animateFade(activity);
                        activity.finish();

                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();

    }*/

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static MyApp getApplication() {
        return myApplication;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static void spinnerStart(Context context, String text) {
        String pleaseWait = text;
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.setTitle(pleaseWait);
                return;
            }
            dialog = ProgressDialog.show(context, pleaseWait, "", true);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);

            dialog.getWindow()
                    .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.progress);
            ImageView img_progress=dialog.findViewById(R.id.img_progress);
            RotateAnimation rotate = new RotateAnimation(
                    0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );

            rotate.setDuration(900);
            rotate.setRepeatCount(Animation.INFINITE);
            img_progress.startAnimation(rotate);
        } catch (Exception e) {
        }

    }

  /*  public static boolean checkLogin(Activity activity) {

        if (MyApp.readUser().getData() == null) {

            new AlertDialog.Builder(activity)
                    .setIcon(android.R.drawable.ic_dialog_alert)

                    .setMessage(activity.getResources().getString(R.string.login_msg))
                    .setPositiveButton(activity.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyApp.writeUserData(new LoginModel());


                            activity.startActivity(new Intent(activity, Login.class));
                            activity.finishAffinity();
                            CustomActivity.animateSwipeRight(activity);
                        }

                    })
                    .setNegativeButton(activity.getResources().getString(R.string.no), null)
                    .show();
            return false;
        }

        return true;
    }*/


    public static void spinnerStop() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                }

            }
        }

    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static void popMessage(String titleMsg, String errorMsg,
                                  Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleMsg).setMessage(Html.fromHtml(errorMsg)).setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        try {
            alert.show();
        } catch (Exception e) {
        }

    }

    public static void popMessageFinish(String titleMsg, String errorMsg,
                                        final Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleMsg).setMessage(Html.fromHtml(errorMsg)).setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        context.finish();
                    }
                });

        AlertDialog alert = builder.create();
        try {
            alert.show();
        } catch (Exception e) {
        }

    }

    public static void popFinishableMessage(String titleMsg, String errorMsg,
                                            final Activity context) {
        // pop error message
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleMsg).setMessage(errorMsg)
                .setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        context.finish();
                        CustomActivity.animateSwipeRight(context);
                    }
                });

        AlertDialog alert = builder.create();
        try {
            alert.show();
        } catch (Exception e) {
        }

    }

    public static void showMassage(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static void writeUserData(LoginModel device) {
        try {
            String path = "/data/data/" + ctx.getPackageName()
                    + "/UserData.ser";
            File f = new File(path);
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(device);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @SuppressLint("SdCardPath")
    public static LoginModel readUser() {
        String path = "/data/data/" + ctx.getPackageName()
                + "/UserData.ser";
        File f = new File(path);
        LoginModel device = new LoginModel();
        if (f.exists()) {
            try {
                System.gc();
                FileInputStream fileIn = new FileInputStream(path);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                device = (LoginModel) in.readObject();
                in.close();
                fileIn.close();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (OptionalDataException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return device;
    }





    public static String getSharedPrefString(String preffConstant) {
        String stringValue = "";
        SharedPreferences sp = myApplication.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        stringValue = sp.getString(preffConstant, "");
        return stringValue;
    }

    public static void setSharedPrefString(String preffConstant,
                                           String stringValue) {
        SharedPreferences sp = myApplication.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(preffConstant, stringValue);
        editor.commit();
    }

    public static double getSharedPrefFloat(String preffConstant) {
        double stringValue = 0;
        SharedPreferences sp = myApplication.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        stringValue = sp.getFloat(preffConstant, 0);
        return stringValue;
    }

    public static void setSharedPrefFloat(String preffConstant,
                                          float stringValue) {
        SharedPreferences sp = myApplication.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(preffConstant, stringValue);
        editor.commit();
    }

    public static int getSharedPrefInteger(String preffConstant) {
        int intValue = 0;
        SharedPreferences sp = myApplication.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        intValue = sp.getInt(preffConstant, 0);
        return intValue;
    }

    public static void setSharedPrefInteger(String preffConstant, int value) {
        SharedPreferences sp = myApplication.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(preffConstant, value);
        editor.commit();
    }

    public static boolean getSharedPrefBoolean(String preffConstant) {
        boolean booleanVal = false;
        SharedPreferences sp = myApplication.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        booleanVal = sp.getBoolean(preffConstant, false);
        return booleanVal;
    }

    public static void setSharedPrefBoolean(String preffConstant, boolean value) {
        SharedPreferences sp = myApplication.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(preffConstant, value);
        editor.commit();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.setDefaultFont(this, "MONOSPACE", "fonts/OpenSans-SemiBold.ttf");
        timeStart = System.currentTimeMillis();
        ctx = getApplicationContext();
        myApplication = this;




    }


    @Nullable
    @Override
    protected SharedPreferences getCustomSharedPreferences(@NonNull Context base) {
        return null;
    }
}