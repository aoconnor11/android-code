package com.mcqs.anita.android_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SelectQuiz extends AppCompatActivity {

    private TextView actionBarTitle;
    private Button startQuiz;
    private RadioGroup radioGroup;
    private RadioButton radio10, radio20, radio40, radio60, radioCustom;
    private EditText customValue;
    private ImageView downloadIcon;
    private int customInt;
    private String customString;
    private int noQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
        actionBarTitle.setText(getResources().getString(R.string.title_activity_main));

        ColorDrawable cd = new ColorDrawable(0xFF2662a6);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(cd);

        startQuiz = (Button) findViewById(R.id.button7);
        downloadIcon = (ImageView) findViewById(R.id.imageViewDownloadIcon);
        downloadIcon.setVisibility(View.INVISIBLE);

        radioGroup = (RadioGroup) findViewById(R.id.group_radio);
        radio10 = (RadioButton) findViewById(R.id.radio1);
        radio20 = (RadioButton) findViewById(R.id.radio2);
        radio40 = (RadioButton) findViewById(R.id.radio3);
        radio60 = (RadioButton) findViewById(R.id.radio4);
        radioCustom = (RadioButton) findViewById(R.id.radio5);

        customValue = (EditText) findViewById(R.id.custom_value_edit_text);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    customValue.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.radio2) {
                    customValue.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.radio3) {
                    customValue.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.radio4) {
                    customValue.setVisibility(View.INVISIBLE);
                } else {
                    customValue.setVisibility(View.VISIBLE);
                }
            }
        });

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == radio10.getId()) {
                    noQuestions = 10;
                } else if (selectedId == radio20.getId()) {
                    noQuestions = 20;
                } else if (selectedId == radio40.getId()) {
                    noQuestions = 40;
                } else if (selectedId == radio60.getId()) {
                    noQuestions = 60;
                } else if (selectedId == -1) {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            SelectQuiz.this).create();
                    alertDialog.setTitle(getResources().getString(R.string.error));
                    alertDialog.setMessage("Please select a number of questions");
                    alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                } else {
                    customString = customValue.getText().toString();
                    if (customString.equals("") | customString.equals(" ") | customString.equals("0")) {
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                SelectQuiz.this).create();
                        alertDialog.setTitle(getResources().getString(R.string.error));
                        alertDialog.setMessage(getResources().getString(R.string.custom_value_error));
                        alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialog.show();
                    } else {
                        noQuestions = Integer.parseInt(customString);
                    }
                }
                if (selectedId != -1) {
                    Intent startQuiz = new Intent(SelectQuiz.this, ViewExam.class);
                    startQuiz.putExtra("mode", "exam");
                    startQuiz.putExtra("noQuestions", noQuestions);
                    startActivity(startQuiz);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
