package com.moviles.kiari.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.moviles.kiari.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;



    protected static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);





    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historia, container, false);
        final TextView textTitle = root.findViewById(R.id.title);
        pageViewModel.getTitleText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textTitle.setText(s);
            }
        });

        final TextView textDescription = root.findViewById(R.id.descripcion);
        pageViewModel.getDescriptionText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textDescription.setText(s);
            }
        });
        final TextView textNumeration = root.findViewById(R.id.count);
        pageViewModel.getNumerationText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textNumeration.setText(s);
            }
        });

        return root;
    }
}