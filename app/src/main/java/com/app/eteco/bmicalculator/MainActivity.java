package com.app.eteco.bmicalculator;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText et_cm;
    private EditText et_peso;
    private RadioGroup rg_height;
    private EditText et_ft;
    private EditText et_in;
    private Button btn_calc;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_cm = (EditText)findViewById(R.id.et_cm);
        et_cm.addTextChangedListener(textwatcher);

        et_ft = (EditText)findViewById(R.id.et_ft);

        et_in = (EditText)findViewById(R.id.et_in);

        et_peso = (EditText)findViewById(R.id.et_peso);
        et_peso.addTextChangedListener(textwatcher);
        tv_result = (TextView) findViewById(R.id.tv_result);

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
            if(s.hashCode()== et_cm.getEditableText().hashCode()){
                et  = et_cm;
            }
            else if(s.hashCode() == et_peso.getEditableText().hashCode()){
                et = et_peso;
            }
            if(et != null && s.toString().contains(".")){
                et.setKeyListener(DigitsKeyListener.getInstance(getResources().getString(R.string.digits)));
            }
            else if(et != null){
                et.setKeyListener(DigitsKeyListener.getInstance(getResources().getString(R.string.digits_dot)));
            }
        }
    };
}
