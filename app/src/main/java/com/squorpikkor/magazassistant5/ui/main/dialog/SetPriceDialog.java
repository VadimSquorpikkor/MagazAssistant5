package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.utils.Utils;

public class SetPriceDialog extends BaseDialog {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeWithVM(R.layout.dialog_set_price);

        EditText juiceBig = view.findViewById(R.id.big_juice);
        EditText juiceSmall = view.findViewById(R.id.small_juice);
        EditText kefirBig = view.findViewById(R.id.big_kefir);
        EditText kefirSmall = view.findViewById(R.id.small_kefir);

        juiceBig.setText(Utils.integerToMoneyString(mViewModel.getJuicePrice().getValue()));
        juiceSmall.setText(Utils.integerToMoneyString(mViewModel.getJuiceSmallPrice().getValue()));
        kefirBig.setText(Utils.integerToMoneyString(mViewModel.getKefirPrice().getValue()));
        kefirSmall.setText(Utils.integerToMoneyString(mViewModel.getKefirSmallPrice().getValue()));

        view.findViewById(R.id.save).setOnClickListener(v->save(
                Utils.stringMoneyToInteger(juiceBig.getText().toString()),
                Utils.stringMoneyToInteger(juiceSmall.getText().toString()),
                Utils.stringMoneyToInteger(kefirBig.getText().toString()),
                Utils.stringMoneyToInteger(kefirSmall.getText().toString())
        ));

        return dialog;
    }

    private void save(int bigJ, int smlJ, int bigK, int smlK) {
        mViewModel.saveNewPrices(bigJ, smlJ, bigK, smlK);
        dismiss();
    }

}
