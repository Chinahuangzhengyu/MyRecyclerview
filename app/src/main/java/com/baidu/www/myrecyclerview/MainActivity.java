package com.baidu.www.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.baidu.www.myrecyclerview.adapter.Myadapter;
import com.baidu.www.myrecyclerview.view.MyDecoration;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myrecyclerview;
    private Myadapter myadapter;
    private LinearLayoutManager lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myrecyclerview = (RecyclerView) findViewById(R.id.myrecyclerview);
        lin = new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        myrecyclerview.setLayoutManager(lin);
        /*lin.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position%15==0){
                    return 20;
                }else if(position%15<3){
                    return 20/2;
                }else if (position%15<6){
                    return 20/3;
                }else if (position%15<10){
                    return 20/4;
                }else if (position%15<15){
                    return 20/5;
                }
                return 0;
                switch (position%6){
                    case 0:
                        index= 5;
                        break;
                    case 1:
                        index= 3;
                        break;
                    case 2:
                        index=3;
                        break;
                    case 3:
                        index=2;
                        break;
                    case 4:
                        index=2;
                        break;
                    case 5:
                        index=2;
                        break;
                }
                return index;
            }
        });*/
        myadapter = new Myadapter();
        myrecyclerview.setAdapter(myadapter);
        myrecyclerview.addItemDecoration(new MyDecoration(this,LinearLayoutManager.VERTICAL));
        myadapter.setOnItemClickLintener(new Myadapter.OnItemClickLintener() {
            @Override
            public void OnItemClick(View view, int position) {
                myadapter.adddata(position);
            }
        });
        myadapter.setOnItemLongClickLintener(new Myadapter.OnItemLongClickLintener() {
            @Override
            public void OnItemLongClick(View view, int position) {
                //myadapter.deletedata(position);
                myadapter.updatedata(position);
            }
        });
    }
    public void btnchange(View view){
        RecyclerView.LayoutManager manager = myrecyclerview.getLayoutManager();
        if (manager==null){
            return;
        }
        if (manager instanceof GridLayoutManager){
            myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        }else if (manager instanceof LinearLayoutManager){
            myrecyclerview.setLayoutManager(new GridLayoutManager(this,5));
        }
    }
}
