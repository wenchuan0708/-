package com.yongjian.gdufszhushou.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yongjian.gdufszhushou.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by YONGJIAN on 2016/4/29 0029.
 */
public class CalendarFragment extends Fragment {


    private ImageView imageView;
    private PhotoViewAttacher mAttacher;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_layout,null);

        imageView = (ImageView) view.findViewById(R.id.photoview);
        Drawable bitmap = getResources().getDrawable(R.drawable.calendar);
        imageView.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(imageView);
        mAttacher.setZoomable(true);

        return view;
    }
}
