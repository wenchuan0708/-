package com.yongjian.gdufszhushou.Widge;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by YONGJIAN on 2016/3/17 0017.
 */
public class ProgressDialogHelper {
    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context, String message) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public static void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
