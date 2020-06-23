package com.eysisgmbh.grabnowdeleviry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eysisgmbh.grabnowdeleviry.application.MyApp;
import com.eysisgmbh.grabnowdeleviry.custom.CustomActivity;
import com.eysisgmbh.grabnowdeleviry.model.LoginModel;
import com.eysisgmbh.grabnowdeleviry.networking.GetDataService;
import com.eysisgmbh.grabnowdeleviry.networking.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eysisgmbh.grabnowdeleviry.utils.UiUtils.setTextWatcher;

public class Login extends CustomActivity {

    TextInputLayout txt_mobile, txt_pass;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpUi();
    }

    private void setUpUi() {
        setupActionBar(findViewById(R.id.toolbar), R.string.login);

        setClick(R.id.btn_login);
        txt_mobile = findViewById(R.id.txt_mobile);
        ccp = findViewById(R.id.ccp);
        txt_pass = findViewById(R.id.txt_pass);
        setTextWatcher(txt_mobile, 1, null);
        setTextWatcher(txt_pass, 4, null);
    }

    @Override
    public void onClick(View v) {
         if (v.getId() == R.id.btn_login) {


            if (txt_mobile.getEditText().length() == 0 || txt_pass.getEditText().length() < 4)
                return;

            GetDataService service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
            Call<LoginModel> call = service.login(ccp.getSelectedCountryCode(), txt_mobile.getEditText().getText().toString(), txt_pass.getEditText().getText().toString(),  MyApp.getSharedPrefString("TOKEN"),
                    "android", "de");
            MyApp.spinnerStart(Login.this, "");
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    MyApp.spinnerStop();
                    Log.e("response", response.body().getMessage());
                    if (response.body().getStatus() == 200) {
                        MyApp.writeUserData(response.body());

                        finishAffinity();
                        startActivity(new Intent(Login.this, MainActivity.class));

                        CustomActivity.animateSwipeLeft(Login.this);
                    } else {
                        Snackbar.make(txt_mobile, response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    MyApp.spinnerStop();
                    Toast.makeText(Login.this, getString(R.string.error_msg) ,Toast.LENGTH_SHORT).show();
                }
            });


        }
    }


}