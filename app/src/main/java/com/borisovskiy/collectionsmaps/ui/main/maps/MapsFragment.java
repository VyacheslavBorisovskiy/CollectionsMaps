package com.borisovskiy.collectionsmaps.ui.main.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borisovskiy.collectionsmaps.R;
import com.borisovskiy.collectionsmaps.adapters.RecyclerViewAdapterCollections;
import com.borisovskiy.collectionsmaps.adapters.RecyclerViewAdapterMaps;
import com.borisovskiy.collectionsmaps.dto.CalculationDTO;
import com.borisovskiy.collectionsmaps.ui.base.BaseMvpFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MapsFragment extends BaseMvpFragment<MapsFragmentContract.IPresenter, MapsFragmentContract.IHost>
        implements MapsFragmentContract.IView {

    @BindView(R.id.operations_label)
    TextView operationsLabelTextView;
    @BindView(R.id.threads_label)
    TextView threadsLabelTextView;
    @BindView(R.id.operations_et)
    EditText operationsEditText;
    @BindView(R.id.threads_et)
    EditText threadsEditText;
    @BindView(R.id.button_calculation)
    ToggleButton buttonCalculation;
    @BindView(R.id.list_collections)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private RecyclerViewAdapterMaps recyclerViewAdapter;

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = MapsFragmentPresenterInjection.getPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        unbinder = ButterKnife.bind(this, view);
        initialiseRecyclerView();

        buttonCalculation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final String operations = operationsEditText.getText().toString();
                    buttonCalculation.setTextOn(getString(R.string.calculation_button_name_stop));
                    hideKeyboard(getActivity());
                    presenter.startCalculation(operations, threadsEditText.getText().toString());
                } else {
                    presenter.stopCalculation();
                }
            }
        });

        return view;
    }

    private void hideKeyboard(Context context) {
        if (context != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(((Activity) context).getWindow().getDecorView().getRootView().getWindowToken(), 0);
            }
        }
    }


    private void initialiseRecyclerView() {
        recyclerViewAdapter = new RecyclerViewAdapterMaps();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void setData(List<CalculationDTO> items) {
        recyclerViewAdapter.setItems(items);
    }

    @Override
    public void updateItem(String tag, long time) {
        recyclerViewAdapter.updateItem(tag, time);
    }

    @Override
    public void setStateOfButton(boolean isStateChanged) {
        if (buttonCalculation.isChecked() == isStateChanged) return;
        buttonCalculation.setChecked(isStateChanged);
    }

    @Override
    public void showProgressBar() {
        recyclerViewAdapter.showProgressBar();
    }

    @Override
    public void hideProgressBar(String tag) {
        recyclerViewAdapter.hideProgressBar(tag);
    }

    @Override
    public void hideProgressBar() {
        recyclerViewAdapter.hideProgressBar();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOperationsValid(boolean isValid) {
        if(isValid) operationsEditText.setError(null);
        else {
            operationsEditText.setError("Must be a number");
            operationsEditText.requestFocus();
            buttonCalculation.setChecked(false);
        }
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}