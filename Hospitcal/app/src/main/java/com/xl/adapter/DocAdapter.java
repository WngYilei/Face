package com.xl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xl.entity.Doctor;
import com.xl.thread.BitmapThread;

import java.util.List;

import car.xl.com.hospitcal.R;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class DocAdapter extends BaseAdapter {
    Context context;
    List<Doctor> list;

    public DocAdapter(Context context, List<Doctor> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Vh vh;
        if (view == null) {
            vh = new Vh();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.listview, null);
            vh.id = view.findViewById(R.id.doc_id);
            vh.naem = view.findViewById(R.id.doc_name);
            vh.room = view.findViewById(R.id.doc_room);
            vh.title = view.findViewById(R.id.doc_title);
            vh.dicon = view.findViewById(R.id.doc_icn);
            view.setTag(vh);
        } else {
            vh = (Vh) view.getTag();
        }
        Doctor d = list.get(position);
        vh.id.setText(d.getDid() + "");
        vh.naem.setText(d.getDname());
        vh.title.setText(d.getDtitle());
        vh.room.setText(d.getDroom());
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bitmap bitmap = (Bitmap) msg.obj;
                vh.dicon.setImageBitmap(bitmap);
            }
        };
        new BitmapThread(handler, d.getImg()).start();
        return view;
    }

    class Vh {
        TextView id;
        TextView naem;
        TextView title;
        TextView room;
        ImageView dicon;
    }
}


