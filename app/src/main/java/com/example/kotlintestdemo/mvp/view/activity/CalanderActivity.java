package com.example.kotlintestdemo.mvp.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.bean.JRBean.CalanderBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalanderActivity extends AppCompatActivity {

    private static final String TAG = "CalanderActivity";
    int year, month, day;
    private Calendar mCalendar;
    private RecyclerView rvCalendars;
    private List<CalanderBean> calanderBeanList = new ArrayList<>();
    private mAdapter mAdapter;
    private TextView tvCalender;
    private EditText edCalender;
    private TextView tvSure;

    private int firstDay;
    private int secondDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);
        mCalendar = Calendar.getInstance(Locale.CHINA);
        edCalender = findViewById(R.id.tv_date_select);
        tvSure = findViewById(R.id.tv_date_sure);
        tvCalender = findViewById(R.id.tv_date_total);
        tvCalender.setText(new StringBuilder().append(mCalendar.get(Calendar.YEAR)).append(" ").append(mCalendar.get(Calendar.MONTH) + 1).toString());
        rvCalendars = findViewById(R.id.rv_date);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar.set(Calendar.MONTH, Integer.parseInt(edCalender.getText().toString()) - 1);
                tvCalender.setText(String.format("%d %d", mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH) + 1));
                getData(mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
        });
        rvCalendars.setLayoutManager(gridLayoutManager);
        mAdapter = new mAdapter(calanderBeanList);
        rvCalendars.setAdapter(mAdapter);
        getData(mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getData(int day) {
        Log.e(TAG, "true day: " + day);
        calanderBeanList.clear();
        for (int i1 = 1; i1 <= day; i1++) {
            calanderBeanList.add(new CalanderBean(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), i1));
        }
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR));
        tempCalendar.set(Calendar.MONTH, mCalendar.get(Calendar.MONTH));
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int week = tempCalendar.get(Calendar.DAY_OF_WEEK);
        Log.e(TAG, "getData:week " + week);
        for (int i1 = 1; i1 < week; i1++) {
            calanderBeanList.add(0, new CalanderBean(0, 0, 0));
        }
        Log.e(TAG, "getData: " + calanderBeanList.size());
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Log.e(TAG, "onItemChildClick: " + position);
                // TODO: 22-6-29 多次点击 ，second小于first的问题为解决
                TextView textView = view.findViewById(R.id.tv_item_calander_day);
                if (firstDay != calanderBeanList.get(position).getDay()) {
                    if (firstDay == 0) {
                        firstDay = calanderBeanList.get(position).getDay();
                        textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    } else {
                        secondDay = calanderBeanList.get(position).getDay();
                        textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                        for (CalanderBean calanderBean : calanderBeanList) {
                            if (calanderBean.getDay()>=firstDay&&calanderBean.getDay()<=secondDay) {
                                calanderBean.setSelect(true);
                            }
                        }
                        if (firstDay < secondDay) {
                            Toast.makeText(CalanderActivity.this, "期间" + firstDay + "  " + secondDay, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CalanderActivity.this, "期间" + secondDay + "  " + firstDay, Toast.LENGTH_SHORT).show();
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    firstDay = 0;
                    textView.setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }
        });
    }

    class mAdapter extends BaseQuickAdapter<CalanderBean, BaseViewHolder> {

        public mAdapter(@Nullable List<CalanderBean> data) {
            super(R.layout.item_calander_select, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder baseViewHolder, CalanderBean calanderBean) {
            TextView tvitem=baseViewHolder.getView(R.id.tv_item_calander_day);
            if (calanderBean.getDay() == 0) {
                tvitem.setText( "  ");
            } else {
                tvitem.setText( String.valueOf(calanderBean.getDay()));
            }
            if (calanderBean.isSelect()) {
                tvitem.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
            }
        }
    }
}