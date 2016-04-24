package com.example.ruan.blueboothprint.Controller;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Adapter.ListViewAdapter;
import com.example.ruan.blueboothprint.Moudle.Database.CheckDatabase;
import com.example.ruan.blueboothprint.Moudle.Database.GetDatabaseData;
import com.example.ruan.blueboothprint.Moudle.Moudle.AddressMoudle;
import com.example.ruan.blueboothprint.Moudle.Util.AdapterData;
import com.example.ruan.blueboothprint.Moudle.Util.CommonIntent;
import com.example.ruan.blueboothprint.Moudle.Util.PATH;
import com.example.ruan.blueboothprint.boothprint.Applications;
import com.example.ruan.blueboothprint.boothprint.BaseActivity;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/13.
 */
public class Address extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView addressListView = null;

    private View view = null;
    private GetDatabaseData getDatabaseData = null;
    private ArrayList<Map<String, String>> map = null;
    private TextView Addaddress = null;
    private AddressMoudle address = null;
    private ListViewAdapter adapter = null;
    /**
     * 这个是程序的开始
     */
    @Override
    public void inti() {
        setTitle("管理地址");
        setTopTitleColor(R.color.ColorWhite);
        setLeftTitleColor(R.color.ColorWhite);
        setRightTitleVisiable(false);
        setTopColor(R.color.ColorBlue);

        view = LayoutInflater.from(context).inflate(R.layout.address, null);

        address = new AddressMoudle();

        id();

        intance();

        setContent(view);

    }

    private void id() {
        addressListView = (ListView) view.findViewById(R.id.addressListView);
        Addaddress = (TextView) view.findViewById(R.id.Addaddress);

        addressListView.setOnItemClickListener(this);
        addressListView.setOnItemLongClickListener(this);
        Addaddress.setOnClickListener(this);
    }

    @Override
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.Addaddress:
                address.setId(-1);
                CommonIntent.IntentActivity(context, AddorUpdataAdress.class, address);
                break;
        }
    }

    private void intance() {
        CheckDatabase.AddressCheckDatabase(context);
        getDatabaseData = new GetDatabaseData();

        adapter = new ListViewAdapter(context, getData(), "Address");

        addressListView.setAdapter(adapter);
    }

    private ArrayList<Object> getData() {
        map = getDatabaseData.QueryArray(context, "BlueBooth", "Address", null, "", null, "", "", "", "");

        ArrayList<Object> list = new ArrayList<>();

        if (map != null)
            for (int i = 0; i < map.size(); i++)
                list.add(AdapterData.getMap(map.get(i).get("names"), map.get(i).get("phones"), map.get(i).get("convider") + map.get(i).get("city") + map.get(i).get("addresss")));

        return list;
    }


    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PATH.addressMoudle = getAddressData(position);
        Applications.getInstance().removeOneActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        address.setId(-1);
        adapter.ChangeData(getData());
    }


    /**
     * Callback method to be invoked when an item in this view has been
     * clicked and held.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need to access
     * the data associated with the selected item.
     *
     * @param parent   The AbsListView where the click happened
     * @param view     The view within the AbsListView that was clicked
     * @param position The position of the view in the list
     * @param id       The row id of the item that was clicked
     * @return true if the callback consumed the long click, false otherwise
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        CommonIntent.IntentActivity(context, AddorUpdataAdress.class, getAddressData(position));
        return true;
    }

    private AddressMoudle getAddressData(int position) {
        address.setId(Integer.parseInt(map.get(position).get("ids")));
        address.setName(map.get(position).get("names"));
        address.setAddress(map.get(position).get("addresss"));
        address.setPhone(map.get(position).get("phones"));
        address.setConvider(map.get(position).get("convider"));
        address.setCity(map.get(position).get("city"));
        return address;
    }
}
