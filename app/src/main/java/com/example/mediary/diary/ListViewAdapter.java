package com.example.mediary.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mediary.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<String> { //extends BaseAdapter {
//    private Context mContext;
//    private LayoutInflater mInflater;
//    String[] values = new String[] { "A",
//            "B",
//            "C"
//    };
//    public ListViewAdapter(Context context, ArrayList<String> lists) {
//        super(context, 0, lists);
//    }
//
//
//        @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public String getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
////        final View v = mInflater.inflate(R.layout.content_main2, null);
//        // Inflate the layout for this fragment
////        View view = mInflater.inflate(R.layout.content_main2, parent, false);
////        String[] values = new String[] { "Android List View",
////                "Adapter implementation",
////                "Simple List View In Android",
////                "Create List View Android",
////                "Android Example",
////                "List View Source Code",
////                "List View Array Adapter",
////                "Android Example List View"
////        };
////
////        ListView listView = (ListView)view.findViewById(R.id.data_list);
//        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
//        //listView.setAdapter(adapter);
////        v.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//////                Intent i = new Intent(new Intent(mContext, DialogListFragment.class));
//////                i.putExtra("position", position);
//////
////////                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, v, "transition");
////////                mContext.startActivity(i, options.toBundle());
//////                mContext.startActivity(i);
////
////        });
//        String user = getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_main2, parent, false);
//        }
//        // Lookup view for data population
//        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
////        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
//        // Populate the data into the template view using the data object
//        tvName.setText("xxxxx");
////        tvHome.setText(user.hometown);
//        // Return the completed view to render on screen
//        return convertView;
//
////        return v;
//    }
    ListViewAdapter(Context context, ArrayList<String> users) {

        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_main2, parent, false);
        }

        // Get the data item for this position
//        User user = getItem(position);

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHometown);
        // Populate the data into the template view using the data object
        tvName.setText(getItem(position));
//        tvName.setText(user.getName());
//        tvHome.setText(user.getHometown());
        // Return the completed view to render on screen
        return convertView;
    }
}
