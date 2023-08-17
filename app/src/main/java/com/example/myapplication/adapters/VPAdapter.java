package com.example.myapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.forhealthprofessional.mainappfragments.AppointmentStatus;
import com.example.myapplication.forhealthprofessional.mainappfragments.Pending;
import com.example.myapplication.forhealthprofessional.mainappfragments.Upcoming;

public class VPAdapter extends FragmentStateAdapter {
    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new Upcoming();
            case 1:
                return new Pending();
            case 2:
                return new AppointmentStatus();
            default:
                return new Upcoming();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
