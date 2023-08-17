package com.example.myapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.forpatient.fragments.PatientAppointmentStatus;
import com.example.myapplication.forpatient.fragments.PatientPending;
import com.example.myapplication.forpatient.fragments.PatientRequest;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new PatientRequest();
            case 1:
                return new PatientPending();
            case 2:
                return new PatientAppointmentStatus();
            default:
                return new PatientRequest();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
