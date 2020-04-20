package com.example.edu.myapplication.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.edu.myapplication.R;
import com.example.edu.myapplication.Utils.AllUtil;
import com.example.edu.myapplication.Utils.ApplicationUtil;
import com.example.edu.myapplication.Utils.SQLiteHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    private SQLiteHelper dbHelper;
    private static final int CHOSSE_PHOTO = 1;

    @BindView(R.id.shangchuan_head)
    ImageView shangchuanHead;
    @BindView(R.id.register_username)
    EditText registerUsername;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.register_repassword)
    EditText registerRepassword;
    @BindView(R.id.phonenum)
    EditText phonenum;
    @BindView(R.id.checkbox_tiaokuan)
    CheckBox checkboxTiaokuan;
    @BindView(R.id.nextbtn)
    Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new SQLiteHelper(this, "UserDB.db", null, 1);

        ButterKnife.bind(this);

        ApplicationUtil.getInstance().addActivity(this);
    }

    @OnClick(R.id.shangchuan_head)
    public void onShangchuanHeadClicked() {
        if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RegisterActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOSSE_PHOTO);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOSSE_PHOTO:
                if (resultCode == -1) {
                    String imgPath = AllUtil.handleImageOnKitKat(RegisterActivity.this, data);
                    setHead(imgPath);
                }
                break;
            default:
                break;
        }
    }

    private void setHead(String imgPath) {
        if (imgPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            Bitmap round = AllUtil.toRoundBitmap(bitmap);
            try {
                String path = getCacheDir().getPath();

                File file = new File(path,"user_head");
                round.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));//压缩图片
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            shangchuanHead.setImageBitmap(round);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.nextbtn)
    public void onNextbtnClicked() {

        if(checkboxTiaokuan.isChecked()){
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String username_str = registerUsername.getText().toString();
            String userpassword_str = registerPassword.getText().toString();
            String repassword_str = registerRepassword.getText().toString();
            String phone = phonenum.getText().toString();
            if("".equals(userpassword_str)||"".equals(username_str))
            {
                Toast.makeText(RegisterActivity.this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
            }
            else {
                if (userpassword_str.equals(repassword_str)) {
                    if(phone.length()==11) {
                        ContentValues values = new ContentValues();
                        //组装数据
                        values.put("name", username_str);
                        values.put("password", userpassword_str);
                        values.put("phonenum", phone);
                        db.insert("User", null, values);

                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this, "请输入11位的电话号码", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        }else {
            Toast.makeText(RegisterActivity.this, "请勾选同意使用条款", Toast.LENGTH_SHORT).show();
        }

    }


    //回调函数，获取图像权限被拒绝时会回到register界面
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
