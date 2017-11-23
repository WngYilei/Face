package com.xl.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class Toa {
    public void toast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();

    }
}
