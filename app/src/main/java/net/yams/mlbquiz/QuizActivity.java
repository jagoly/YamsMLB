package net.yams.mlbquiz;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class QuizActivity extends Activity
{
    public static final String ARG_INDEX = "index";

    private SQLiteAssetHelper mDatabaseHelper;
    private QuestionPagerAdapter mQuestionPagerAdapter;

    private ViewPager mQuestionPager;

    //============================================================================//

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        final int quizIndex = getIntent().getExtras().getInt(ARG_INDEX);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mDatabaseHelper = new SQLiteAssetHelper(this, "mlb.db", null, 1);
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        //--------------------------------------------------------//

        final String query = "SELECT QuizName FROM Quizes WHERE QuizIndex = " + quizIndex;
        final Cursor cursor = db.rawQuery(query, null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.quiz_toolbar);

        cursor.moveToNext();
        toolbar.setTitle(cursor.getString(0));

        cursor.close();

        //--------------------------------------------------------//

        mQuestionPagerAdapter = new QuestionPagerAdapter(getFragmentManager(), db, quizIndex);

        mQuestionPager = (ViewPager) findViewById(R.id.quiz_question_pager);
        mQuestionPager.setAdapter(mQuestionPagerAdapter);
    }

    //============================================================================//

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mDatabaseHelper.close();
    }

    //============================================================================//

    public void finishQuiz(View view)
    {
        final int[] choices = mQuestionPagerAdapter.getChoices();

        for (int index = 0; index < choices.length; ++index)
        {
            if (choices[index] == -1)
            {
                mQuestionPager.setCurrentItem(index);
                Toast.makeText(this, "Please answer every question.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        finish();
    }
}
