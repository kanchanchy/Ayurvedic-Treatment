package com.applicationslab.ayurvedictreatment.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;

public class SpinAdapter extends ArrayAdapter<String>{
	
	Context context;
	ArrayList<String>options = new ArrayList<String>();

	Typeface tfRegular;
	
	LayoutInflater inflater;

	public SpinAdapter(Context context, ArrayList<String>options) {
		super(context, R.layout.row_spinner_dropdown, options);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.options = options;
		tfRegular=Typeface.createFromAsset(context.getAssets(), "fonts/OpenSansRegular.ttf");
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null)
		{
			convertView=inflater.inflate(R.layout.row_spinner_dropdown, null);
		    holder= new ViewHolder();
		    holder.txtItem=(TextView)convertView.findViewById(R.id.txtItem);
		    convertView.setTag(holder);
		}
		else
		{   
			    holder=(ViewHolder)convertView.getTag();
		}
		
		holder.txtItem.setTypeface(tfRegular);
		holder.txtItem.setText(options.get(position));
		
		return convertView;
	}  
	
	@Override
	public View getView(int position, View cnvtView, ViewGroup parent) {
		View mySpinner = inflater.inflate(R.layout.spinner_view, parent,false);
		TextView txtItem = (TextView) mySpinner.findViewById(R.id.txtItem);
		txtItem.setTypeface(tfRegular);
		txtItem.setText(options.get(position));

		return mySpinner;
	}
	
	
	public static class ViewHolder {
	    public TextView txtItem;
	}
	

}
