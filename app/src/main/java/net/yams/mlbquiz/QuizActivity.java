package net.yams.mlbquiz;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class QuizActivity extends AppCompatActivity
{
    private DatabaseHelper mDatabaseHelper;

    private QuestionPagerAdapter mQuestionPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mDatabaseHelper = new DatabaseHelper(this);

        mQuestionPagerAdapter = new QuestionPagerAdapter(getSupportFragmentManager(), mDatabaseHelper, 0);

        mViewPager = (ViewPager) findViewById(R.id.quiz_pager);
        mViewPager.setAdapter(mQuestionPagerAdapter);
    }

}
