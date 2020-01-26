package com.applicationslab.ayurvedictreatment.widget;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applicationslab.ayurvedictreatment.R;


/**
 * Created by masum on 22/06/2015.
 */
public class CustomToast {

    public CustomToast(Activity context, String title, String subTitle, boolean isSuccess) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) context.findViewById(R.id.custom_toast_layout_id));

        TextView textViewTitle = (TextView) layout.findViewById(R.id.textViewTitle);
        TextView textViewSubTitle = (TextView) layout.findViewById(R.id.textViewSubTitle);
        ImageView imageView = (ImageView) layout.findViewById(R.id.imageView1);
        textViewTitle.setText(title);
        textViewSubTitle.setText(subTitle);

        if (isSuccess) {
            imageView.setBackgroundResource(R.drawable.tick_mark);
        } else {
            imageView.setBackgroundResource(R.drawable.warning);
        }

        // Create Custom Toast
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
