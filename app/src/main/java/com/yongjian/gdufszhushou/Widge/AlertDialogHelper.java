package com.yongjian.gdufszhushou.Widge;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by YONGJIAN on 2016/3/17 0017.
 */
public class AlertDialogHelper {
    public static void showAlertDialog(Context context, String title, String message){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setPositiveButton("确定", null)
                .setMessage(message)
                .show();
    }
}
