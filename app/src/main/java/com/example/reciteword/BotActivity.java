package com.example.reciteword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BotActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        initMsgs();
        inputText = (EditText)findViewById(R.id.input_txet);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView)findViewById(R.id.msg_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();

                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息，刷新RecyclerVeiw的显示
                    adapter.notifyItemInserted(msgList.size() - 1);
                    //将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    //清空输入框内容
                    inputText.setText("");
                    if ("人工服务".equals(content)){
                        Msg msgrot=new Msg("欢迎拨打电话1585951290",Msg.TYPE_RECEIVED);
                        msgList.add(msgrot);
                    }
                    else if ("mall的翻译".equals(content)){
                        Msg msgrot=new Msg("mall的翻译为n. 林荫大道，商业街",Msg.TYPE_RECEIVED);
                        msgList.add(msgrot);
                    }else if ("商业街的英文".equals(content)){
                        Msg msgrot=new Msg("商业街的英文为mall",Msg.TYPE_RECEIVED);
                        msgList.add(msgrot);
                    }else if ("fancy的翻译".equals(content)){
                        Msg msgrot=new Msg("fancy的翻译为n. 想像力，幻想;adj. 想像的",Msg.TYPE_RECEIVED);
                        msgList.add(msgrot);
                    }
                    else if ("想像力的英文".equals(content)){
                        Msg msgrot=new Msg("想像力的英文为fancy",Msg.TYPE_RECEIVED);
                        msgList.add(msgrot);
                    }
                    else{
                        Msg msgunknown = new Msg("小机器人听不懂你的话哦", Msg.TYPE_RECEIVED);
                        msgList.add(msgunknown);
                    }

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(BotActivity.this,InterfaceActivity.class);
        startActivity(intent);
    }

    private void initMsgs(){
        Msg msg1 = new Msg("你好，请问有什么问题吗？",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hi",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("你好",
                Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
