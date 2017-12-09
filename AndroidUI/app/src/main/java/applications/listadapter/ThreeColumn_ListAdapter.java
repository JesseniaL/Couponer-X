package applications.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ThreeColumn_ListAdapter extends ArrayAdapter<Information> {

    private LayoutInflater mInflater;
    private ArrayList<Information> couponList;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<Information> couponList) {

        super(context, textViewResourceId, couponList);
        this.couponList = couponList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(mViewResourceId, null);
        Information info = couponList.get(position);
        // Places information from database into the adapter
        if (info != null) {
            TextView store = (TextView) convertView.findViewById(R.id.textStores);
            TextView expiration = (TextView) convertView.findViewById(R.id.textDate);
            TextView  coupon = (TextView) convertView.findViewById(R.id.textCoupon);
            if (store != null) {
                store.setText(info.getStoreName());
            }
            if (expiration != null) {
                expiration.setText((info.getExpirationDate()));
            }
            if (coupon != null) {
                coupon.setText((info.getCouponNumber()));
            }
        }
        return convertView;
    }
}