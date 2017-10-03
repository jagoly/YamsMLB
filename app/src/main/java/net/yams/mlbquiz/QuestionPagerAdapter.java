package net.yams.mlbquiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/// FragmentPagerAdapter that returns a fragment corresponding to one of the questions.
public class QuestionPagerAdapter extends FragmentPagerAdapter
{
    public QuestionPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        // getItem is called to instantiate the fragment for the given page.
        return QuestionFragment.newInstance(position);
    }

    @Override
    public int getCount()
    {
        return QuestionFragment.QUIZ_DATA.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return "Question No. " + (position + 1);
    }
}
