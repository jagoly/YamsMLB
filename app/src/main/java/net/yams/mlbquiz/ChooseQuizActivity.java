package net.yams.mlbquiz;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class ChooseQuizActivity extends AppCompatActivity
{
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);

        mDatabaseHelper = new DatabaseHelper(this);

        String query = "SELECT QuizName, Image FROM Quizes";
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Button btnA = (Button) findViewById(R.id.main_button_quiz_a);
        Button btnB = (Button) findViewById(R.id.main_button_quiz_b);
        Button btnC = (Button) findViewById(R.id.main_button_quiz_c);
        Button btnD = (Button) findViewById(R.id.main_button_quiz_d);

        cursor.moveToNext(); btnA.setText(cursor.getString(0));
        cursor.moveToNext(); btnB.setText(cursor.getString(0));
        cursor.moveToNext(); btnC.setText(cursor.getString(0));
        cursor.moveToNext(); btnD.setText(cursor.getString(0));
    }

    // todo: this rubbish should use a recycler view or something

    public void doStartQuizA(View view)
    {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.ARG_INDEX, 0);
        startActivityForResult(intent, 1);
    }

    public void doStartQuizB(View view)
    {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.ARG_INDEX, 1);
        startActivityForResult(intent, 1);
    }

    public void doStartQuizC(View view)
    {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.ARG_INDEX, 2);
        startActivityForResult(intent, 1);
    }
    public void doStartQuizD(View view)
    {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.ARG_INDEX, 3);
        startActivityForResult(intent, 1);
    }

    private void handleQuizResult(int[] results)
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                this.handleQuizResult(data.getIntArrayExtra("results"));
            }
            else if (resultCode == Activity.RESULT_CANCELED)
            {

            }
            else { /* error stuff */ }
        }
    }

}
