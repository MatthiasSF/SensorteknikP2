package com.example.matth.project2.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.matth.project2.R;

public class ApiValuesFragment extends Fragment {
    private View view;
    private TextView tempValue;
    private TextView humValue;
    private TextView pressureValue;
    private TextView timestamp;
    private TextView source;
    private String[] values;
    private String type;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_api_values, container, false);
        initialize();
        return view;
    }

    public void initialize() {
        tempValue = view.findViewById(R.id.apiValues_tempValue);
        humValue = view.findViewById(R.id.apiValues_humValue);
        pressureValue = view.findViewById(R.id.apiValues_airValue);
        timestamp = view.findViewById(R.id.apiValues_timeStamp);
        source = view.findViewById(R.id.apiValues__source);
        imageView = view.findViewById(R.id.imageView);
        Bundle bundle = this.getArguments();
        values = bundle.getStringArray("values");
        type = bundle.getString("type");
        if(values != null && type != null) {
            setText(values, type);
            setImageView(values[4]);
        }
    }
    public void setImageView(String text){
        if (text.equals("01d") || text.equals("01n")){
            imageView.setImageResource(R.drawable.i01d);
        }
        if (text.equals("02d") || text.equals("02n")){
            imageView.setImageResource(R.drawable.i02d);
        }
        if (text.equals("03d") || text.equals("03n")){
            imageView.setImageResource(R.drawable.i03d);
        }
        if (text.equals("04d") || text.equals("04n")){
            imageView.setImageResource(R.drawable.i04d);
        }
        if (text.equals("09d") || text.equals("09n")){
            imageView.setImageResource(R.drawable.i09d);
        }
        if (text.equals("10d") || text.equals("10n")){
            imageView.setImageResource(R.drawable.i10d);
        }
        if (text.equals("11d") || text.equals("11n")){
            imageView.setImageResource(R.drawable.i11d);
        }
        if (text.equals("13d") || text.equals("13n")){
            imageView.setImageResource(R.drawable.i13d);
        }
        if (text.equals("50d") || text.equals("50n")){
            imageView.setImageResource(R.drawable.i50d);
        }
    }
    public void setText(String[] values, String source){
        tempValue.setText(values[0] + " c");
        pressureValue.setText(values[1] + "%");
        humValue.setText(values[2] + " hPa");
        timestamp.setText(values[3]);
        this.source.setText(source);
    }
}
