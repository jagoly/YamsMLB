package net.yams.mlbquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionFragment extends Fragment
{
    private static final String ARG_INDEX = "index";
    private static final String ARG_QUESTION = "question";
    private static final String ARG_ANSWERS = "answers";

    /// Construct a new fragment for the given quiz and question indices.
    public static QuestionFragment newInstance(int index, String question, String[] answers)
    {
        QuestionFragment fragment = new QuestionFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        args.putString(ARG_QUESTION, question);
        args.putStringArray(ARG_ANSWERS, answers);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_question, container, false);

        TextView heading = (TextView) rootView.findViewById(R.id.question_heading);
        TextView body = (TextView) rootView.findViewById(R.id.question_body);
        RadioGroup group = (RadioGroup) rootView.findViewById(R.id.question_group);

        int index = getArguments().getInt(ARG_INDEX);
        String question = getArguments().getString(ARG_QUESTION);
        String[] answers = getArguments().getStringArray(ARG_ANSWERS);

        heading.setText(question);
        body.setText(question);

        for (int i = 0; i < answers.length; ++i)
        {
            RadioButton button = new RadioButton(getActivity());
            button.setId(i);
            button.setText(answers[i]);
            group.addView(button);
        }

        return rootView;
    }

    public int getChoice()
    {
        RadioGroup group = (RadioGroup) getView().findViewById(R.id.question_group);
        return group.getCheckedRadioButtonId();
    }
}
