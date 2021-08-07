package jp.ac.jec.ws.p3_splashtutorial;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class TutorialViewPagerAdapter extends FragmentPagerAdapter {

    public static final int PAGE_NUM = 3;

    public TutorialViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){

            case 0:
                fragment = new TutorialOneFragment();
                break;
            case 1:
                fragment = new TutorialTwoFragment();
                break;
            case 2:
                fragment = new TutorialThreeFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }
}
