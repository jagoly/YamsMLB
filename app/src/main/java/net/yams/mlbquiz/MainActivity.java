package net.yams.mlbquiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doStartQuiz(View view)
    {
        Intent intent = new Intent(this, QuizActivity.class);
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
