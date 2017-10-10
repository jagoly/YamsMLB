package net.yams.mlbquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import java.util.ArrayList;

/// FragmentPagerAdapter that returns a fragment corresponding to one of the questions.
public class QuestionPagerAdapter extends FragmentPagerAdapter
{
    private DatabaseHelper mDatabaseHelper;

    public QuestionPagerAdapter(FragmentManager fm, DatabaseHelper databaseHelper, int quizIndex)
    {
        super(fm);

        mDatabaseHelper = databaseHelper;
        mQuizIndex = quizIndex;

        String query = "select AnswerText from Answers";
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> answers = new ArrayList<>();
        while (cursor.moveToNext()) answers.add(cursor.getString(0));

        mAnswers = new String[answers.size()];
        answers.toArray(mAnswers);

        cursor.close();
        db.close();
    }


    @Override
    public Fragment getItem(int position)
    {
        String query = String.format("SELECT QuestionText FROM Questions WHERE QuizIndex = %d AND QuestionIndex = %d", mQuizIndex, position);
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // todo: assert count is 1
        cursor.moveToNext();
        return QuestionFragment.newInstance(position, cursor.getString(0), mAnswers);

        //return QuestionFragment.newInstance(position, "error", new String[] {"error"});
    }

    @Override
    public int getCount()
    {
        // todo: store mCount in ctor
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return "Question No. " + (position + 1);
    }

    private int mQuizIndex;
    private String[] mAnswers;
}
