package net.yams.mlbquiz;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class QuizActivity extends AppCompatActivity
{
    public static final String ARG_INDEX = "index";

    private DatabaseHelper mDatabaseHelper;
    private QuestionPagerAdapter mQuestionPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mDatabaseHelper = new DatabaseHelper(this);

        int index = getIntent().getExtras().getInt(ARG_INDEX);

        mQuestionPagerAdapter = new QuestionPagerAdapter(getSupportFragmentManager(), mDatabaseHelper, index);

        mViewPager = (ViewPager) findViewById(R.id.quiz_pager);
        mViewPager.setAdapter(mQuestionPagerAdapter);
    }

}
