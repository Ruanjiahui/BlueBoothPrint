package com.example.ruan.blueboothprint.Moudle.Moudle;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/4/14.
 */
public class AddressMoudle implements Parcelable {

    private int id = 0;
    private String name = null;
    private String phone = null;
    private String address = null;
    private String convider = null;
    private String city = null;

    public AddressMoudle(){}

    protected AddressMoudle(Parcel in) {
        id = in.readInt();
        name = in.readString();
        phone = in.readString();
        address = in.readString();
        convider = in.readString();
        city = in.readString();
    }

    public static final Creator<AddressMoudle> CREATOR = new Creator<AddressMoudle>() {
        @Override
        public AddressMoudle createFromParcel(Parcel in) {
            return new AddressMoudle(in);
        }

        @Override
        public AddressMoudle[] newArray(int size) {
            return new AddressMoudle[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConvider() {
        return convider;
    }

    public void setConvider(String convider) {
        this.convider = convider;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put("names", name);
        contentValues.put("addresss", address);
        contentValues.put("phones", phone);
        contentValues.put("convider", convider);
        contentValues.put("city", city);

        return contentValues;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(convider);
        dest.writeString(city);
    }
}
