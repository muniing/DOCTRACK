package com.example.myapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.forpatient.fragments.PatientCompletedMed;
import com.example.myapplication.forpatient.fragments.PatientOngoingMed;


public class MedViewPagerAdapter extends FragmentStateAdapter {
    public MedViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new PatientOngoingMed();
            case 1:
                return new PatientCompletedMed();
            default:
                return new PatientOngoingMed();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
