package com.app.eteco.bmicalculator;


import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText et_cm;
    private EditText et_kg;
    private EditText et_lb;
    private RadioGroup rg_height;
    private RadioGroup rg_weight;
    private EditText et_ft;
    private EditText et_in;
    private RadioButton rb_kg;
    private RadioButton rb_lb;
    private RadioButton rb_cm;
    private RadioButton rb_ft_in;
    private Button btn_calc;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_ft = (EditText)findViewById(R.id.et_ft);

        et_in = (EditText)findViewById(R.id.et_in);
        rb_kg = (RadioButton)findViewById(R.id.rb_kg);
        rb_lb = (RadioButton)findViewById(R.id.rb_lb);
        rb_cm = (RadioButton)findViewById(R.id.rb_cm);
        rb_ft_in = (RadioButton)findViewById(R.id.rb_ft_in);

        et_cm = (EditText)findViewById(R.id.et_cm);
        et_cm.addTextChangedListener(textwatcher);

        et_kg = (EditText)findViewById(R.id.et_kg);
        et_kg.addTextChangedListener(textwatcher);

        et_lb = (EditText)findViewById(R.id.et_lb);
        et_lb.addTextChangedListener(textwatcher);

        tv_result = (TextView)findViewById(R.id.tv_result);

        btn_calc = (Button)findViewById(R.id.btn_calc);
        btn_calc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(rb_kg.isChecked() && rb_cm.isChecked()) {
                    String str1 = et_kg.getText().toString();
                    String str2 = et_cm.getText().toString();

                    if(TextUtils.isEmpty(str1)){
                        et_kg.setError("Please enter your weight");
                        et_kg.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(str2)){
                        et_cm.setError("Please enter your height");
                        et_cm.requestFocus();
                        return;
                    }

                    float kg = Float.parseFloat(str1);
                    float cm = Float.parseFloat(str2)/100;

                    float bmiValue = metricBMI(kg, cm);

                    String bmiInterpretation = interpretBMI(bmiValue);

                    tv_result.setText(String.valueOf(bmiValue + "-" + bmiInterpretation));
                }

                else if(rb_kg.isChecked() && rb_ft_in.isChecked()) {
                    String str1 = et_kg.getText().toString();
                    String str2 = et_ft.getText().toString();
                    String str3 = et_in.getText().toString();

                    if(TextUtils.isEmpty(str1)){
                        et_kg.setError("Please enter your weight");
                        et_kg.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(str2)){
                        et_ft.setError("Please enter your feets");
                        et_ft.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(str3)){
                        et_in.setError("Please enter your inches");
                        et_in.requestFocus();
                        return;
                    }

                    float kg = Float.parseFloat(str1);
                    float ft = Float.parseFloat(str2);
                    float in = Float.parseFloat(str3);

                    float bmiValue = kg_ftBMI(kg, ft, in);

                    String bmiInterpretation = interpretBMI(bmiValue);

                    tv_result.setText(String.valueOf(bmiValue + "-" + bmiInterpretation));
                }

                if(rb_lb.isChecked() && rb_cm.isChecked()) {
                    String str1 = et_lb.getText().toString();
                    String str2 = et_cm.getText().toString();

                    if(TextUtils.isEmpty(str1)){
                        et_lb.setError("Please enter your weight");
                        et_lb.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(str2)){
                        et_cm.setError("Please enter your height");
                        et_cm.requestFocus();
                        return;
                    }

                    float lb = Float.parseFloat(str1);
                    float cm = Float.parseFloat(str2)/100;

                    float bmiValue = lb_cmBMI(lb, cm);

                    String bmiInterpretation = interpretBMI(bmiValue);

                    tv_result.setText(String.valueOf(bmiValue + "-" + bmiInterpretation));
                }

                else if(rb_lb.isChecked() && rb_ft_in.isChecked()) {
                    String str1 = et_lb.getText().toString();
                    String str2 = et_ft.getText().toString();
                    String str3 = et_in.getText().toString();

                    if(TextUtils.isEmpty(str1)){
                        et_lb.setError("Please enter your weight");
                        et_lb.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(str2)){
                        et_ft.setError("Please enter your feets");
                        et_ft.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(str3)){
                        et_in.setError("Please enter your inches");
                        et_in.requestFocus();
                        return;
                    }

                    float lb = Float.parseFloat(str1);
                    float ft = Float.parseFloat(str2);
                    float in = Float.parseFloat(str3);

                    float bmiValue = imperialBMI(lb, ft, in);

                    String bmiInterpretation = interpretBMI(bmiValue);

                    tv_result.setText(String.valueOf(bmiValue + "-" + bmiInterpretation));
                }
            }
        });

        rg_height = (RadioGroup)findViewById(R.id.rg_height);
        rg_height.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_cm:
                        et_cm.setVisibility(View.VISIBLE);
                        et_cm.requestFocus();
                        et_ft.setVisibility(View.GONE);
                        et_ft.setText("");
                        et_in.setVisibility(View.GONE);
                        et_in.setText("");
                        break;
                    case R.id.rb_ft_in:
                        et_cm.setVisibility(View.GONE);
                        et_cm.setText("");
                        et_ft.setVisibility(View.VISIBLE);
                        et_ft.requestFocus();
                        et_in.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        rg_weight = (RadioGroup)findViewById(R.id.rg_weight);
        rg_weight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_kg:
                        et_kg.setVisibility(View.VISIBLE);
                        et_kg.requestFocus();
                        et_lb.setVisibility(View.GONE);
                        et_lb.setText("");
                        break;
                    case R.id.rb_lb:
                        et_kg.setVisibility(View.GONE);
                        et_kg.setText("");
                        et_lb.setVisibility(View.VISIBLE);
                        et_lb.requestFocus();
                        break;
                }
            }
        });
    }

    private float metricBMI (float kg, float cm) {
        return (kg / (cm * cm));
    }

    private float imperialBMI (float lb, float ft, float in) {
        float kg = lb*(float)0.453592;
        in = in + (ft*12);
        float cm = in*(float)0.0254;
        return (kg / (cm * cm));
    }

    private float kg_ftBMI (float kg, float ft, float in){
        in = in + (ft*12);
        float cm = in*(float)0.0254;
        return (kg / (cm * cm));
    }

    private float lb_cmBMI (float lb, float cm){
        float kg = lb*(float)0.453592;
        return (kg / (cm * cm));
    }

    private String interpretBMI(float bmiValue) {

        if (bmiValue < 16) {
            return "Severely underweight";
        } else if (bmiValue < 18.5) {

            return "Underweight";
        } else if (bmiValue < 25) {

            return "Normal";
        } else if (bmiValue < 30) {

            return "Overweight";
        } else {
            return "Obese";
        }
    }

    TextWatcher textwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        @Override
        public void afterTextChanged(Editable s) {
            EditText et = null;
            if(s.hashCode() == et_cm.getEditableText().hashCode()){
                et  = et_cm;
            }
            else if(s.hashCode() == et_kg.getEditableText().hashCode()){
                et = et_kg;
            }
            else if(s.hashCode() == et_lb.getEditableText().hashCode()){
                et = et_lb;
            }
            if(et != null && s.toString().contains(".")){
                et.setKeyListener(DigitsKeyListener.getInstance(getResources().getString(R.string.digits)));
            }
            else if(et != null && s.toString().isEmpty() && !s.toString().contains(".")){
                et.setKeyListener(DigitsKeyListener.getInstance(getResources().getString(R.string.digits)));
            }
            else if(et != null){
                et.setKeyListener(DigitsKeyListener.getInstance(getResources().getString(R.string.digits_dot)));
            }
        }
    };
}
