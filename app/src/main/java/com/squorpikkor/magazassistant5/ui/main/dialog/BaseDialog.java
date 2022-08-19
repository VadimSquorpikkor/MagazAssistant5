package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.squorpikkor.magazassistant5.MainActivity;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;

import org.jetbrains.annotations.NotNull;

/**Базовый класс для диалогов. Есть варианты с ViewModel и без*/
class BaseDialog extends DialogFragment {

    Context mContext;
    MainViewModel mViewModel;
    View view;
    AlertDialog dialog;

    @Override
    public void onAttach(@NotNull Context context) {
        mContext = context;
        super.onAttach(context);
    }

    /**Добавляет диалогу лэйаут и задает параметры*/
    public void initialize(int layout) {
        dialog = new AlertDialog.Builder(mContext).create();
        view = requireActivity().getLayoutInflater().inflate(layout, null);
        dialog.setView(view, 0, 0, 0, 0);
    }

    /**Дать диалогу лэйаут и задать параметры. Добавление ViewModel*/
    public void initializeWithVM(int layout) {
        dialog = new AlertDialog.Builder(mContext).create();
        view = requireActivity().getLayoutInflater().inflate(layout, null);
        dialog.setView(view, 0, 0, 0, 0);
        mViewModel = new ViewModelProvider((MainActivity) mContext).get(MainViewModel.class);
    }

    public void decreaseIfCorrect(TextView view, int min) {
        int i = Integer.parseInt(view.getText().toString());
        if (i > min) view.setText(String.valueOf(i - 1));
    }

    public void increaseIfCorrect(TextView view, int max) {
        int i = Integer.parseInt(view.getText().toString());
        if (i < max) view.setText(String.valueOf(i + 1));
    }

    public void decreaseIfCorrect(EditText view, int min) {
        int i = Integer.parseInt(view.getText().toString());
        if (i > min) view.setText(String.valueOf(i - 1));
    }

    public void increaseIfCorrect(EditText view, int max) {
        int i = Integer.parseInt(view.getText().toString());
        if (i < max) view.setText(String.valueOf(i + 1));
    }

    public void decreaseIfCorrect(TextView view, float min) {
        float i = Float.parseFloat(view.getText().toString());
        if (i > min) {
            int ii = (int)(i*100);
            view.setText(String.valueOf((float)(ii - 1) /100));
        }
    }

    public void increaseIfCorrect(TextView view, float max) {
        float i = Float.parseFloat(view.getText().toString());
        if (i < max) {
            int ii = (int)(i*100);
            view.setText(String.valueOf((float)(ii + 1) /100));
        }
    }

    public void decreaseIfCorrect(EditText view, float min) {
        float i = Float.parseFloat(view.getText().toString());
        if (i > min) {
            int ii = (int)(i*100);
            view.setText(String.valueOf((float)(ii - 1) /100));
        }
    }

    public void increaseIfCorrect(EditText view, float max) {
        float i = Float.parseFloat(view.getText().toString());
        if (i < max) {
            int ii = (int)(i*100);
            view.setText(String.valueOf((float)(ii + 1) /100));
        }
    }
}
