package com.example.ruan.blueboothprint.Moudle.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Moudle.ListViewItemData;
import com.example.ruan.blueboothprint.Moudle.Moudle.ViewHolder;
import com.example.ruan.blueboothprint.Moudle.Util.System.DensityUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/11.
 */
public class ListViewAdapter extends BaseAdapter {

    private ViewHolder viewHolder = null;
    private Context context = null;
    private ArrayList<Object> list = null;
    private String flag = null;

    public ListViewAdapter(Context context, ArrayList<Object> list, String flag) {
        this.context = context;
        this.list = list;
        this.flag = flag;
    }


    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListViewItemData data = (ListViewItemData) list.get(position);
        viewHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.listviewitem, null);
            viewHolder = new ViewHolder();

            viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.itemImage);
            viewHolder.itemText = (TextView) convertView.findViewById(R.id.itemText);
            viewHolder.itemRightImage = (ImageView) convertView.findViewById(R.id.itemRightImage);
            viewHolder.itemCenterImage = (ImageView) convertView.findViewById(R.id.itemCenterImage);
            viewHolder.itemCenterText = (TextView) convertView.findViewById(R.id.itemCenterText);
            viewHolder.itemGridView = (LinearLayout) convertView.findViewById(R.id.itemGridView);
            viewHolder.addressLinear = (RelativeLayout) convertView.findViewById(R.id.addressLinear);
            viewHolder.itemListview = (RelativeLayout) convertView.findViewById(R.id.itemListview);
            viewHolder.itemname = (TextView) convertView.findViewById(R.id.itemname);
            viewHolder.itemphone = (TextView) convertView.findViewById(R.id.itemphone);
            viewHolder.itemaddress = (TextView) convertView.findViewById(R.id.itemaddress);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if ("ListView".equals(flag)) {
            if (data.getItemImage() != null) {
                viewHolder.itemImage.setImageDrawable(data.getItemImage());
                viewHolder.itemImage.setVisibility(View.VISIBLE);
            } else
                viewHolder.itemImage.setVisibility(View.GONE);
            if (data.getItemRightImage() != null) {
                viewHolder.itemRightImage.setImageDrawable(data.getItemRightImage());
                viewHolder.itemRightImage.setVisibility(View.VISIBLE);
            } else
                viewHolder.itemRightImage.setVisibility(View.GONE);
            if (data.getItemText() != null) {
                viewHolder.itemText.setText(data.getItemText());
                viewHolder.itemText.setVisibility(View.VISIBLE);
            } else
                viewHolder.itemText.setVisibility(View.GONE);
            DensityUtil.setFrameHeight(viewHolder.itemListview, data.getItemHeight());
        }
        if ("GridView".equals(flag)) {
            viewHolder.itemCenterImage.setImageDrawable(data.getItemCenterImage());
            viewHolder.itemCenterText.setText(data.getItemCenterText());
            DensityUtil.setFrameHeight(viewHolder.itemGridView, data.getItemHeight());
        }
        if ("Address".equals(flag)) {
            viewHolder.itemaddress.setText(data.getItemAddress());
            viewHolder.itemname.setText(data.getItemName());
            viewHolder.itemphone.setText(data.getItemPhone());
            viewHolder.addressLinear.setVisibility(View.VISIBLE);
        }


        return convertView;
    }

    public void ChangeData(ArrayList<Object> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
}
