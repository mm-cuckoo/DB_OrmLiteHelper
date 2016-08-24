package com.cfox.db_ormlitehelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cfox.db_ormlitehelper.bean.UserInfo;
import com.cfox.db_ormlitehelper.dao.MyDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button mBtnAdd,mBtnGet,mBtnDel,mBtnUpd;

    private MyDao dao;

    private TextView mContentText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();

    }



    private void initView() {
        mContentText = (TextView) findViewById(R.id.show_text);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnGet = (Button) findViewById(R.id.btn_get);
        mBtnDel = (Button) findViewById(R.id.btn_del);
        mBtnUpd = (Button) findViewById(R.id.btn_upd);
    }

    private void initEvent() {

        //插入
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List users = new ArrayList<>();
                for(int i = 1 ; i < 100 ; i ++){
                    UserInfo info = new UserInfo("张三" + i,"beijing");
                    users.add(info);
                }

                try {
                    dao = new MyDao(UserInfo.class);
                    long startTime = System.currentTimeMillis();
                    int insertNumber = dao.insert(users);
                    long useTime = System.currentTimeMillis() - startTime;

                    Toast.makeText(MainActivity.this,"inser number :" +
                            insertNumber +
                            "user time:" +  useTime,Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        //查找
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContentText.setText("");
                try {
                    dao = new MyDao(UserInfo.class);
                    Map<String,String> map1 = new HashMap<String, String>();
                    map1.put("name","张三update");
                    List infos =  dao.query(dao.queryOrderBy("user_id",false));

                    for(int i = 0; i < infos.size(); i++){
                        mContentText.append("\n" + (i + 1) + infos.get(i) + "\n");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        //删除
        mBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dao = new MyDao(UserInfo.class);

                    List users = dao.queryAll();

                    long startTime = System.currentTimeMillis();
                    int delNumber = dao.delete(users);
                    long useTime = System.currentTimeMillis() - startTime;

                    Toast.makeText(MainActivity.this,"del number :" +
                            delNumber +
                            " user time:" +  useTime,Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        //修改
        mBtnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    dao = new MyDao(UserInfo.class);

//                    Map<String,String> map = new HashMap<String, String>();
//                    map.put("name","张三1");

                    Map<String,String> map1 = new HashMap<String, String>();
                    map1.put("name","张三update");

                    long startTime = System.currentTimeMillis();
                    int updNumber = dao.update(null,map1);
                    long useTime = System.currentTimeMillis() - startTime;

                    Toast.makeText(MainActivity.this,"update number :" +
                            updNumber +
                            " user time:" +  useTime,Toast.LENGTH_SHORT).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
