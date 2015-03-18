package vn.heallife.duchv.ouralarm.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import vn.heallife.duchv.ouralarm.R;
import vn.heallife.duchv.ouralarm.background.model.Alarm;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlarmEdit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AlarmEdit extends Fragment {

    private OnFragmentInteractionListener mListener;

    private String TAG = "ALARMEDIT";

    private EditText hour;
    private EditText minus;
    private EditText message;
    private ImageButton cancelButton;
    private ImageButton saveButton;


    public String getTAG() {
        return TAG;
    }

    public static AlarmEdit newInstance(){
        AlarmEdit alarmEdit = new AlarmEdit();
        return  alarmEdit;
    }
    public AlarmEdit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_edit, container, false);
        hour =(EditText) view.findViewById(R.id.alarm_hour);
        minus = (EditText) view.findViewById(R.id.alarm_minus);
        message = (EditText) view.findViewById(R.id.alarm_message);
        cancelButton = (ImageButton) view.findViewById(R.id.add_cancel);
        saveButton = (ImageButton) view.findViewById(R.id.add_done);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToAlarmList();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Alarm alarm = new Alarm(hour.getText().toString(),minus.getText().toString(),message.getText().toString());
                AlarmView.arrAlarm.add(alarm);
                AlarmView.adapter.notifyDataSetChanged();
                backToAlarmList();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void backToAlarmList(){
        AlarmView alarmView = AlarmView.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, alarmView).commit();
    }

}
