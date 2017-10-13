package net.yams.mlbquiz;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v13.app.FragmentPagerAdapter;

/// FragmentPagerAdapter that returns a fragment corresponding to one of the questions.
public class QuestionPagerAdapter extends FragmentPagerAdapter
{
    private Fragment[] mFragments;

    //============================================================================//

    public QuestionPagerAdapter(FragmentManager fm, SQLiteDatabase db, int quizIndex)
    {
        super(fm);

        String query; Cursor cursor;

        //--------------------------------------------------------//

        query = "SELECT AnswerText FROM Answers";
        cursor = db.rawQuery(query, null);

        final String[] answers = new String[cursor.getCount()];

        for (int index = 0; index < answers.length; ++index)
        {
            cursor.moveToNext();
            answers[index] = cursor.getString(0);
        }

        cursor.close();

        //--------------------------------------------------------//

        query = "SELECT QuestionText FROM Questions WHERE QuizIndex = " + quizIndex;
        cursor = db.rawQuery(query, null);

        final int questionCount = cursor.getCount();

        mFragments = new Fragment[questionCount + 1];

        for (int index = 0; index < questionCount; ++index)
        {
            cursor.moveToNext();
            mFragments[index] = QuizQuestionFragment.newInstance(cursor.getString(0), answers);
        }

        mFragments[questionCount] = new QuizSubmitFragment();

        cursor.close();
    }

    //============================================================================//

    public int[] getChoices()
    {
        final int questionCount = mFragments.length - 1;

        int[] result = new int[questionCount];

        for (int index = 0; index < questionCount; ++index)
        {
            QuizQuestionFragment fragment = (QuizQuestionFragment) mFragments[index];
            result[index] = fragment.getChoice();
        }

        return result;
    }

    //============================================================================//

    @Override
    public Fragment getItem(int position)
    {
        return mFragments[position];
    }

    @Override
    public int getCount()
    {
        return mFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        if (++position == mFragments.length) return "End of Quiz";
        return "Question No. " + position;
    }
}
