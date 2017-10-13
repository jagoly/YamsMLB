package net.yams.mlbquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ChooseQuizActivity extends Activity
{
    private SQLiteAssetHelper mDatabaseHelper;

    //============================================================================//

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);

        mDatabaseHelper = new SQLiteAssetHelper(this, "mlb.db", null, 1);
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        final String query = "SELECT QuizIndex as _id, QuizName, Image FROM Quizes";
        final Cursor cursor = db.rawQuery(query, null);

        ResourceCursorAdapter adapter = new ResourceCursorAdapter(this, R.layout.view_choose_quiz_choice, cursor, 0)
        {
            @Override
            public void bindView(View view, Context context, Cursor cursor)
            {
                final int quizIndex = cursor.getInt(0);

                TextView nameView = (TextView) view.findViewById(R.id.choose_quiz_choice_name);
                nameView.setText(cursor.getString(1));

                ImageView imageView = (ImageView) view.findViewById(R.id.choose_quiz_choice_image);
                String imagePath = "quiz_" + cursor.getString(2);
                int imageID = getResources().getIdentifier(imagePath, "drawable", getPackageName());
                imageView.setImageResource(imageID);

                // java 8 has lambdas, but then we'd need to use android studio 3.0
                view.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) { startQuizActivity(quizIndex); }
                });
            }
        };

        ListView listView = (ListView) findViewById(R.id.choose_quiz_list);
        listView.setAdapter(adapter);
    }

    //============================================================================//

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mDatabaseHelper.close();
    }

    //============================================================================//

    private void startQuizActivity(int quizIndex)
    {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.ARG_INDEX, quizIndex);
        startActivityForResult(intent, 1);
    }

    private void handleQuizResult(int[] choices)
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                this.handleQuizResult(data.getIntArrayExtra("choices"));
            }
            else if (resultCode == Activity.RESULT_CANCELED)
            {

            }
            else { /* error stuff */ }
        }
    }

}
