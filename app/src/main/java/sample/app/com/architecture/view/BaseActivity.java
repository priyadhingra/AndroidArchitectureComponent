package sample.app.com.architecture.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity<T extends BaseViewModal> extends AppCompatActivity{

    protected abstract Class getViewModalClass();

    protected T mViewModal;

    protected abstract BaseView getViewImpl();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModal = (T) ViewModelProviders.of(this , new BaseViewModal.Factory(this)).get(getViewModalClass());
        mViewModal.registerView(getViewImpl());
    }

}
