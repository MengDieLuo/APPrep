package com.example.edu.News.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.edu.News.R;
import com.example.edu.News.Utils.AllUtil;
import com.example.edu.News.Utils.ApplicationUtil;
import com.example.edu.News.Utils.Count;
import com.example.edu.News.Utils.SQLiteHelper;
import com.example.edu.News.Utils.SharedPre;

import java.io.File;
import java.io.FileInputStream;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_head)
    ImageView loginHead;
    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.check_user)
    Button checkUser;
    @BindView(R.id.login_password)
    EditText loginPassword;

    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new SQLiteHelper(this, "UserDB.db", null, 1);

        try {
            String path = getCacheDir().getPath();

            String fileName = "user_head";
            File file = new File(path,fileName);
            if (file.exists()==true) {

                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                Bitmap round = AllUtil.toRoundBitmap(bitmap);
                loginHead.setImageBitmap(round);
            } else {
                loginHead.setImageBitmap(BitmapFactory.decodeResource(getResources(),
                        R.drawable.head));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ButterKnife.bind(this);

        ApplicationUtil.getInstance().addActivity(this);
    }

    @OnClick(R.id.check_user)
    public void onViewClicked() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String username_str = loginUsername.getText().toString();
        String userpassword_str = loginPassword.getText().toString();

        Cursor cursor = db.rawQuery("select * from User where name=?", new String[]{username_str});
        if (cursor.getCount() == 0) {
            Toast.makeText(LoginActivity.this, "用户名不存在！", Toast.LENGTH_SHORT).show();
        } else {
            if (cursor.moveToFirst()) {
                String userpassword_db = cursor.getString(cursor.getColumnIndex("password"));

                if (userpassword_str.equals(userpassword_db)) {
                    SharedPre.setParam(LoginActivity.this, SharedPre.IS_LOGIN, true);
                    SharedPre.setParam(LoginActivity.this, SharedPre.LOGIN_DATA, username_str);
                    //user.setUsername(username_str);
                    //user.setPassword(userpassword_str);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Count.getInstance().setTime(System.currentTimeMillis()); //设定时间
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "密码错误，请重新登录", Toast.LENGTH_SHORT).show();
                }
            }
        }
        cursor.close();
        db.close();
    }


}
