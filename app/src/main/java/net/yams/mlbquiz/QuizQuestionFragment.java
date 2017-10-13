package net.yams.mlbquiz;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizQuestionFragment extends Fragment
{
    private static final String ARG_QUESTION = "question";
    private static final String ARG_ANSWERS = "answers";

    private RadioGroup mRadioGroup;

    //============================================================================//

    public static QuizQuestionFragment newInstance(String question, String[] answers)
    {
        QuizQuestionFragment fragment = new QuizQuestionFragment();

        Bundle args = new Bundle();
        args.putString(ARG_QUESTION, question);
        args.putStringArray(ARG_ANSWERS, answers);
        fragment.setArguments(args);

        return fragment;
    }

    //============================================================================//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final String question = getArguments().getString(ARG_QUESTION);
        final String[] answers = getArguments().getStringArray(ARG_ANSWERS);

        final View view = inflater.inflate(R.layout.fragment_quiz_question, container, false);

        final TextView questionText = (TextView) view.findViewById(R.id.question_text);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.question_group);

        questionText.setText(question);

        for (int i = 0; i < answers.length; ++i)
        {
            RadioButton answerButton = new RadioButton(getActivity());

            answerButton.setId(i);
            answerButton.setText(answers[i]);

            mRadioGroup.addView(answerButton);
        }

        return view;
    }

    //============================================================================//

    public int getChoice()
    {
        return mRadioGroup.getCheckedRadioButtonId();
    }
}
