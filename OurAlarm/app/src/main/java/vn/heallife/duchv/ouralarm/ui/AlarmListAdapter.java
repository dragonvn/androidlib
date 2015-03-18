package vn.heallife.duchv.ouralarm.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.heallife.duchv.ouralarm.R;
import vn.heallife.duchv.ouralarm.background.model.Alarm;

/**
 * Created by duchv on 2/13/15.
 */
public class AlarmListAdapter extends ArrayAdapter<Alarm> {

    private Context context;
    private int layoutId;
    private ArrayList<Alarm> alarms;

    public AlarmListAdapter(Context context, int layoutId, ArrayList<Alarm> alarms) {
        super(context, layoutId, alarms);
        this.context = context;
        this.alarms = alarms;
        this.layoutId = layoutId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * không làm:
         * if(convertView==null)
         * {
         * LayoutInflater inflater=
         * context.getLayoutInflater();
         * convertView=inflater.inflate(layoutId, null);
         * }
         * Lý do là ta phải xử lý xóa phần tử Checked, nếu dùng If thì
         * nó lại checked cho các phần tử khác sau khi xóa vì convertView
         * lưu lại trạng thái trước đó
         */
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(R.layout.alarm_list_item,null);
        convertView = inflater.inflate(layoutId,null);
        if(alarms.size()> 0&&position>=0){

        }
        final TextView timeText = (TextView)convertView.findViewById(R.id.alarm_item_time);
        final TextView message = (TextView)convertView.findViewById(R.id.alarm_item_message);
        final Alarm alarm = alarms.get(position);
        message.setText(alarm.getText());
        timeText.setText(alarm.getHour()+ ":"+alarm.getMinus());
        return convertView;

    }
}
