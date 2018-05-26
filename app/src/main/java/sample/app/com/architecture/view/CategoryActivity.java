package sample.app.com.architecture.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sample.app.com.architecture.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout, new CategoriesFragment()).addToBackStack(null).commitAllowingStateLoss();

    }
}
