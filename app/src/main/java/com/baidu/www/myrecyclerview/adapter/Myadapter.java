package com.baidu.www.myrecyclerview.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.www.myrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄郑宇 on 2017/8/30.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.Myviewholder> {

    private List<String> list;
    public Myadapter() {
        list=new ArrayList<>();
        for (int i=0;i<100;i++){
            list.add("这是第"+i+"条数据！");
        }
    }
    private OnItemClickLintener item;
    private OnItemLongClickLintener itemlong;

    public void adddata(int position) {
        list.add(position+1,"这是死数据！");
        notifyItemRangeChanged(position+1,getItemCount());
    }

    public void deletedata(int position) {
        list.remove(position);
        notifyItemRangeRemoved(position, getItemCount());
    }

    public void updatedata(int position) {
        list.remove(position);
        list.add(position,"已更新数据");
        notifyItemRangeChanged(position,getItemCount());
    }


    public interface OnItemClickLintener{
        void OnItemClick(View view,int position);
    }
    public interface OnItemLongClickLintener{
        void OnItemLongClick(View view,int position);
    }
    public void setOnItemClickLintener(OnItemClickLintener item){
        this.item=item;
    }
    public void setOnItemLongClickLintener(OnItemLongClickLintener itemlong){
        this.itemlong=itemlong;
    }
    /**
     * 创建布局文件和viewholder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutManager manager = ((RecyclerView) parent).getLayoutManager();
        View view=null;
        if (manager instanceof GridLayoutManager){
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_two,parent,false);
        }else if (manager instanceof LinearLayoutManager){
           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecyclerview_item,parent,false);
        }
        return new Myviewholder(view);
    }
    //绑定完毕以后
    @Override
    public void onBindViewHolder(Myviewholder holder, final int position) {
        holder.textView.setText(list.get(position));
        holder.viewonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.OnItemClick(v,position);
            }
        });
        holder.viewonclick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemlong.OnItemLongClick(v,position);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Myviewholder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView img;
        private View viewonclick;
        public Myviewholder(View itemView) {
            super(itemView);
            viewonclick=itemView;
            textView = (TextView) itemView.findViewById(R.id.my_item_title);
            img = (ImageView) itemView.findViewById(R.id.my_item_img);
        }
    }

}
