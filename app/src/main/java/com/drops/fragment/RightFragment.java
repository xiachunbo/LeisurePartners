package com.drops.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lovebobo.webview.R;

import java.util.ArrayList;
import java.util.List;


public class RightFragment extends Fragment implements OnClickListener{


	private LayoutInflater mInflater;

	private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
			"Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango" };
	private ListView listView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.main_right_fragment, container,false);
		initView(view);
		listView = (ListView)view.findViewById(R.id.left_drawer);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.left_menu_layout ,data);
        listView.setAdapter(arrayAdapter);
        return view;
    }
    private List<String> getData(){
    List<String> data = new ArrayList<String>();
    for(int i = 0;i < 10;i++) {
       data.add(i+"");
	}
	return data;
    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mInflater = LayoutInflater.from(getActivity());

	}

	private void initView(View view) {
		// title
		view.findViewById(R.id.ivTitleBtnLeft).setVisibility(View.GONE);
		view.findViewById(R.id.ivTitleBtnRigh).setVisibility(View.GONE);
		TextView title = (TextView) view.findViewById(R.id.ivTitleName);
		title.setText("右边");
		setListener();

	}

	private void setListener() {

	}

	@Override
	public void onClick(View v) {

	}

	
}
