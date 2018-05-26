package sample.app.com.architecture.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment<T extends BaseViewModal> extends Fragment{

    protected abstract Class getViewModalClass();

    protected T mViewModal;

    protected abstract BaseView getViewImpl();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModal = (T) ViewModelProviders.of(getActivity() , new BaseViewModal.Factory(getActivity())).get(getViewModalClass());
        mViewModal.registerView(getViewImpl());
    }
}
