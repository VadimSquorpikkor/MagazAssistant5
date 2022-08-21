package com.squorpikkor.magazassistant5.ui.main.pager;

import static com.squorpikkor.magazassistant5.ui.main.adapter.OrderLocationAdapter.EXTRA_POSITION;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.squorpikkor.magazassistant5.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //TODO !!!!!!!! нужно брать не по позиции, а по ID работника!
        // (соответственно сделать метод выбора работника для пейджера не по позиции....)
        // Тогда при любой сортировке списка сотрудника будет выбираться нужный работник.
        // С другой стороны, всё как-то и так работает, пока не буду трогать
        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, PagerFragment.newInstance(position))
                    .commitNow();
        }
    }
}