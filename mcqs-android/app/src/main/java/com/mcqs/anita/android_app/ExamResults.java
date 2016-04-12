package com.mcqs.anita.android_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class ExamResults extends AppCompatActivity implements OnChartValueSelectedListener {
    private TextView actionBarTitle;
    private int examQuestionTotal;
    private int examQuestionCorrect;
    private ImageView downloadIcon;
    private PieChart mChart;
    private Typeface tf;
    private String[] mParties;
    private Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_results);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
        actionBarTitle.setText(R.string.title_activity_exam_results);

        ColorDrawable cd = new ColorDrawable(0xFF2662a6);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(cd);

        downloadIcon = (ImageView) findViewById(R.id.imageViewDownloadIcon);
        downloadIcon.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        examQuestionTotal = extras.getInt("noQuestions");
        examQuestionCorrect = extras.getInt("noQuestionsCorrect");
        mainMenu = (Button) findViewById(R.id.button9);

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startQuiz = new Intent(ExamResults.this, MainActivity.class);
                startActivity(startQuiz);
            }
        });

        mParties = getResources().getStringArray(R.array.result_options);
        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        mChart.setCenterText(generateCenterSpannableText());
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        mChart.setOnChartValueSelectedListener(this);

        setData(1, examQuestionTotal);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exam_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString(getResources().getString(R.string.MCQS_results));
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 12, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 12, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 12, s.length(), 0);
        return s;
    }

    private void setData(int count, float range) {
        float mult = range;
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        yVals1.add(new Entry(examQuestionCorrect, 0));
        yVals1.add(new Entry(examQuestionTotal - examQuestionCorrect, 1));
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, getResources().getString(R.string.title_activity_exam_results));
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#4caf50"));
        colors.add(Color.parseColor("#F44336"));
        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(tf);
        mChart.setData(data);

        mChart.highlightValues(null);
        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
            return;
    }

    @Override
    public void onNothingSelected() {
    }
}
