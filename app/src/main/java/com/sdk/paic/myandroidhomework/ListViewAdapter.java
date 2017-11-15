package com.sdk.paic.myandroidhomework;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author ex-lisuyang001
 * @date 2017/11/7
 */

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ItemHolder> {


	ArrayList<String> datas = new ArrayList<>();

	public ListViewAdapter(ArrayList<String> args) {
		if (args != null) {
			datas.clear();
			datas.addAll(args);
		}
	}

	@Override
	public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
		ItemHolder itemHolder = new ItemHolder(inflate);

		return itemHolder;
	}

	@Override
	public void onBindViewHolder(ItemHolder holder, int position) {

		TextView view = (TextView) holder.get_view(R.id.tv_show_item);
		view.setText(datas.get(position));

	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	public static class ItemHolder extends RecyclerView.ViewHolder {


		public ItemHolder(View itemView) {
			super(itemView);
		}

		public View get_view(int source) {

			return itemView.findViewById(source);

		}


	}

}
