package com.sogecra.launcher;

import java.util.ArrayList;
import java.util.List;

import com.example.sogecra.R;
import com.github.mikephil.charting.utils.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener {
	
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.activity_main);

	        // initialize the utilities
	        Utils.init(getResources());

	        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();

	        objects.add(new ContentItem("Exemple 1", "A simple demonstration of the linechart."));
	        objects.add(new ContentItem("Exalmpe 2", "A simple demonstration of the bar chart."));

	        MyAdapter adapter = new MyAdapter(this, objects);

	        ListView lv = (ListView) findViewById(R.id.listView1);
	        lv.setAdapter(adapter);

	        lv.setOnItemClickListener(this);
	    }
	
	@Override
	 public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {

        Intent i;

        switch (pos) {
            case 0:
                i = new Intent(this, com.sogecra.charts.LineChartActivity.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(this, com.sogecra.charts.ListViewMultiChartActivity.class);
                startActivity(i);
                break;
           
        }

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }
	
	
	private class ContentItem {
        String name;
        String desc;

        public ContentItem(String n, String d) {
            name = n;
            desc = d;
        }
    }

    private class MyAdapter extends ArrayAdapter<ContentItem> {

        public MyAdapter(Context context, List<ContentItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ContentItem c = getItem(position);

            ViewHolder holder = null;

            if (convertView == null) {

                holder = new ViewHolder();

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
                holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                holder.tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvName.setText(c.name);
            holder.tvDesc.setText(c.desc);

            return convertView;
        }

        private class ViewHolder {

            TextView tvName, tvDesc;
        }
    }

}
