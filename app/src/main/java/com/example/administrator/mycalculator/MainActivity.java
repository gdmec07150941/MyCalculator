package com.example.administrator.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 体重输入框
    private EditText weightEditText;
    // 性别选择框
    private RadioButton manRadioButton, womanRioButton;
    // 计算按钮
    private Button calcutorButton;
    // 显示结果
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取UI控件实例
        weightEditText = (EditText) findViewById(R.id.weight);
        manRadioButton = (RadioButton) findViewById(R.id.man);
        womanRioButton = (RadioButton) findViewById(R.id.woman);
        calcutorButton = (Button) findViewById(R.id.calcutor);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    // 注册按钮点击事件
    private void registerEvent(){
        calcutorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weightEditText.getText().toString().trim().equals("")){
                    if(manRadioButton.isChecked() || womanRioButton.isChecked()){
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("-----评估结果-----");
                        if(manRadioButton.isChecked()){
                            stringBuffer.append("男性标准身高");
                            double result = evaluatoHeight(weight,"男");
                            stringBuffer .append((int)result+"厘米");
                        }else{
                            stringBuffer.append("女性标准身高：");
                            double result = evaluatoHeight(weight,"女");
                            stringBuffer.append((int)result+"厘米");
                        }
                        resultTextView.setText(stringBuffer.toString());

                    }else{
                        showMessage("请选择你的性别！");
                    }
                }else {
                    showMessage("请输入体重！");
                }
            }
        });
    }

    // 消息提示对话框
    private void showMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("系统提示");
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    // 计算标准身高
    private double evaluatoHeight(double weight,String sex){
        double height;
        if(sex == "男"){
            height = 170 - ( 62 - weight ) / 0.6;
        }else{
            height = 158 - ( 52 - weight ) / 0.5;
        }
        return height;
    }

    // 创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    // 菜单项被选中之后的事件处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
}
