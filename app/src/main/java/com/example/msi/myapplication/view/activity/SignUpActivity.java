package com.example.msi.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.msi.myapplication.ApiService;
import com.example.msi.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText firstNameEditText = findViewById(R.id.first_name_edit_text);
        final EditText lastNameEditText = findViewById(R.id.last_name_edit_text);
        final NumberPicker ageNumberPicker = findViewById(R.id.numberPicker);
        ageNumberPicker.setMinValue(15);
        ageNumberPicker.setMaxValue(99);
        ageNumberPicker.setValue(18);

        final CheckBox cssCheckBox = findViewById(R.id.css_check_box);
        final CheckBox javaCheckBox = findViewById(R.id.java_check_box);
        final CheckBox htmlCheckBox = findViewById(R.id.html_check_box);

        final RadioButton maleRadio = findViewById(R.id.male_radio_button);
        RadioButton femaleRadio = findViewById(R.id.female_radio_button);

        final SwitchCompat hasJobSwitch = findViewById(R.id.job_switch);

        Button signUpButton = findViewById(R.id.sing_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "SignUp ckicked", Toast.LENGTH_SHORT).show();
                ApiService apiService = new ApiService(SignUpActivity.this);

                JSONObject request = new JSONObject();
                try {
                    request.put("first_name", firstNameEditText.getText().toString());
                    request.put("last_name" , lastNameEditText.getText().toString());
                    request.put("has_job" , hasJobSwitch.isChecked());
                    request.put("age", String.valueOf(ageNumberPicker.getValue()));

                    JSONArray skillJsonArray = new JSONArray();
                    if (cssCheckBox.isChecked()){
                        skillJsonArray.put("Css");
                    }
                    if (htmlCheckBox.isChecked()){
                        skillJsonArray.put("Html");
                    }
                    if (javaCheckBox.isChecked()){
                        skillJsonArray.put("Java");
                    }
                    if (maleRadio.isChecked()){
                        request.put("gender", "male");
                    }else {
                        request.put("gender", "female");
                    }

                    request.put("skills", skillJsonArray);

                    apiService.signUpUser(request, new ApiService.OnSignUpComplete() {
                        @Override
                        public void onSignUp(boolean success) {
                            if (success)
                                Toast.makeText(SignUpActivity.this, "ثبت نام با موفقیت انجام شد :)", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(SignUpActivity.this, "عدم موفقیت در ثبت نام :(", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
