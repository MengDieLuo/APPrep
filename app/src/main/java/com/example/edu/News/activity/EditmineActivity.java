package com.example.edu.News.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edu.News.R;
import com.example.edu.News.Utils.ApplicationUtil;
import com.example.edu.News.Utils.SQLiteHelper;
import com.example.edu.News.Utils.SharedPre;

import androidx.appcompat.app.AppCompatActivity;

public class EditmineActivity extends AppCompatActivity {

    private EditText update_username, update_password, update_repassword;

    private TextView update_user;

    private SQLiteHelper dbHelper;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmine);
        initView();

        dbHelper = new SQLiteHelper(this, "UserDB.db", null, 1);

        username = (String) SharedPre.getParam(EditmineActivity.this, SharedPre.LOGIN_DATA, "");
        update_username.setText(username);


        update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
                //finish();
            }
        });
        ApplicationUtil.getInstance().addActivity(this);

    }

    private void initView() {
        update_user =(TextView) findViewById(R.id.update_user);
        update_username = (EditText) findViewById(R.id.update_username);
        update_password = (EditText) findViewById(R.id.update_password);
        update_repassword = (EditText) findViewById(R.id.update_repassword);

    }

    private void updateUser() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String update_username_str = update_username.getText().toString();
        String update_password_str = update_password.getText().toString();
        String update_repassword_str = update_repassword.getText().toString();

        if (update_password_str.equals(update_repassword_str)) {
            db.execSQL("update User set name = ?,password = ? where name = ?",
                    new String[]{update_username_str, update_password_str, username});
            Toast.makeText(EditmineActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(EditmineActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
        }

    }

}
