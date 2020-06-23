package com.eysisgmbh.grabnowdeleviry.custom;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.eysisgmbh.grabnowdeleviry.R;

import org.json.JSONArray;
import org.json.JSONObject;

import javinator9889.localemanager.activity.BaseAppCompatActivity;


/**
 * This is a common activity that all other activities of the app can extend to
 * inherit the common behaviors like setting a Theme to activity.
 */
public class CustomActivity extends BaseAppCompatActivity implements
        OnClickListener {

    private static ResponseCallback responseCallbackCustom;
    /**
     * Apply this Constant as touch listener for views to provide alpha touch
     * effect. The view must have a Non-Transparent background.
     */

    protected OnBackPressedListener onBackPressedListener;


    public static void animateSwipeRight(Activity context) {
        context.overridePendingTransition(R.anim.stay, R.anim.slid_right);
    }

    public static void animateSwipeLeft(Activity context) {
        context.overridePendingTransition(R.anim.slid_left, R.anim.stay);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        // setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow()
                    .addFlags(
                            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // getWindow().setStatusBarColor(getResources().getColor(R.color.main_color_dk));
        }
        // MyApp.localeManager.setNewLocale(this, LanguagesSupport.Language.ENGLISH);

    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    /* (non-Jav-adoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.stay, R.anim.slid_right);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method will setup the top title bar (Action bar) content and display
     * values. It will also setup the customx background theme for ActionBar. You
     * can override this method to change the behavior of ActionBar for
     * particular Activity
     */
    protected void setupActionBar(Toolbar toolbar, int strId) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle("");
        toolbar_title.setText(strId);


    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }


    /**
     * Sets the click listener for a view.
     *
     * @param id the id of View
     * @return the view
     */
    public View setClick(int id) {

        View v = findViewById(id);
        v.setOnClickListener(this);
        return v;
    }

    public View setClick(View v) {
        v.setOnClickListener(this);
        return v;
    }

    public void setOnBackPressedListener(
            OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.doBack();
        else
            super.onBackPressed();

        overridePendingTransition(R.anim.stay, R.anim.slid_right);
    }

    @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean handleReturn = super.dispatchTouchEvent(ev);

        View view = getCurrentFocus();

        int x = (int) ev.getX();
        int y = (int) ev.getY();

        if (view instanceof EditText) {
            View innerView = getCurrentFocus();

            if (ev.getAction() == MotionEvent.ACTION_UP &&
                    !getLocationOnScreen((EditText) innerView).contains(x, y)) {

                InputMethodManager input = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(getWindow().getCurrentFocus()
                        .getWindowToken(), 0);
            }
        }

        return handleReturn;
    }


   /* public void postCall(final Activity context, String url, RequestParams params, String message, final int callNum) {
        if (!MyApp.isConnectingToInternet(context)) {
            Toast.makeText(context, getResources().getString(R.string.internet_connection_error), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isEmpty(message))
            MyApp.spinnerStart(context, message);
        // Log.e("URL", url);
        // MyApp.hideKeyboard(context);
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(300000);
        client.post(context, url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONObject response) {
                MyApp.spinnerStop();
                Log.e("Call No-" + callNum + "Response:", response.toString());
                if (responseCallbackCustom!=null)
                responseCallbackCustom.onJsonObjectResponseReceived(response, callNum);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                MyApp.spinnerStop();
                Log.e("Response:", response.toString());
                if (responseCallbackCustom!=null)
                responseCallbackCustom.onJsonArrayResponseReceived(response, callNum);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                MyApp.spinnerStop();
                Log.e("error message:", throwable.getMessage());

              //  Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                try {

                    responseCallbackCustom.onErrorReceived(errorResponse.toString(), callNum);
                } catch (Exception e) {
                    //   Toast.makeText(context, "No data found  ! \nPlease try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                MyApp.spinnerStop();
                Log.d("error message:", throwable.getMessage());
               // Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                try {
                    responseCallbackCustom.onErrorReceived(responseString, callNum);
                } catch (Exception e) {
                    //   Toast.makeText(context, "No data found  ! \nPlease try again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }*/

    protected Rect getLocationOnScreen(EditText mEditText) {
        Rect mRect = new Rect();
        int[] location = new int[2];

        mEditText.getLocationOnScreen(location);

        mRect.left = location[0];
        mRect.top = location[1];
        mRect.right = location[0] + mEditText.getWidth();
        mRect.bottom = location[1] + mEditText.getHeight();

        return mRect;
    }

    public interface OnBackPressedListener {
        void doBack();
    }

    public interface ResponseCallback {
        void onJsonObjectResponseReceived(JSONObject response, int callNumber);

        void onJsonArrayResponseReceived(JSONArray response, int callNumber);

        void onErrorReceived(String error, int callNumber);

    }


}
