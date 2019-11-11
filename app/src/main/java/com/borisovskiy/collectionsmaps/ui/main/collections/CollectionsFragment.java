package com.borisovskiy.collectionsmaps.ui.main.collections;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.borisovskiy.collectionsmaps.dto.CalculationDTO;
import com.borisovskiy.collectionsmaps.ui.base.BaseMvpFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CollectionsFragment extends BaseMvpFragment<CollectionsFragmentContract.IPresenter, CollectionsFragmentContract.IHost>
        implements CollectionsFragmentContract.IView {

    private final RecyclerViewAdapterCollections recyclerViewAdapter = new RecyclerViewAdapterCollections();
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


    public static CollectionsFragment newInstance() {
        return new CollectionsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = CollectionFragmentPresenterInjection.getPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);
        unbinder = ButterKnife.bind(this, view);
        initialiseRecyclerView();

        buttonCalculation.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (TextUtils.isEmpty(operationsEditText.getText())) {
                    operationsEditText.setError("Must be a number");
                    operationsEditText.requestFocus();
                    buttonCalculation.setChecked(false);
                    return;
                }
                if (TextUtils.isEmpty(threadsEditText.getText())) {
                    threadsEditText.setError("Must be a number");
                    threadsEditText.requestFocus();
                    buttonCalculation.setChecked(false);
                    return;
                }
//                presenter.showError();
                buttonCalculation.setTextOn(getString(R.string.calculation_button_name_stop));
                hideKeyboard(getActivity());
                presenter.startCalculation(operationsEditText.getText().toString(), threadsEditText.getText().toString());
            } else {
                presenter.stopCalculation(true);
                Log.d("CollectionsFragment", "interrupt-stopCalculation(true)");
            }
        });
        return view;
    }

    private void hideKeyboard(Context context) {
        if (context == null) return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(((Activity) context).getWindow().getDecorView().getRootView().getWindowToken(), 0);
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
    public void showError() {
        if (TextUtils.isEmpty(operationsEditText.getText())) {
            operationsEditText.setError("Must be a number");
            operationsEditText.requestFocus();
            buttonCalculation.setChecked(false);
            return;
        }
        if (TextUtils.isEmpty(threadsEditText.getText())) {
            threadsEditText.setError("Must be a number");
            threadsEditText.requestFocus();
            buttonCalculation.setChecked(false);
            return;
        }
    }

    private void initialiseRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public boolean hasData() {
        return recyclerViewAdapter.getItemCount() > 0;
    }

    @Override
    public void setData(List<CalculationDTO> items) {
        recyclerViewAdapter.setItems(items);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}