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
    /// y java no have structs and uniform init
    public static final class QuestionData
    {
        private QuestionData(String heading, String body, String[] answers)
        { this.heading = heading; this.body = body; this.answers = answers; }

        String heading; String body; String[] answers;
    }

    public static final QuestionData[] QUIZ_DATA =
    {
        new QuestionData ( "Question One", "This is the first question.",
            new String[] { "it", "has", "four", "answers" } ),
        new QuestionData ( "Question Two", "This is the second question.",
            new String[] { "this", "one", "has", "five", "answers" } ),
        new QuestionData ( "Question Three", "This is the third question.",
            new String[] { "this", "one", "blag", "has", "six", "answers" } ),
    };

    /// The argument name for the question number of this fragment.
    private static final String ARG_QUESTION_NUMBER = "question_number";

    /// Construct a new fragment for the given question number.
    public static QuestionFragment newInstance(int questionNumber)
    {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_QUESTION_NUMBER, questionNumber);
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

        QuestionData data = QUIZ_DATA[getArguments().getInt(ARG_QUESTION_NUMBER)];

        heading.setText(data.heading);
        body.setText(data.body);

        for (int index = 0; index < data.answers.length; ++index)
        {
            RadioButton button = new RadioButton(getActivity());
            button.setId(index);
            button.setText(data.answers[index]);
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
