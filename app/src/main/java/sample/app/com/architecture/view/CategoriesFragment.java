package sample.app.com.architecture.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.widget.TextView;

import sample.app.com.architecture.R;
import sample.app.com.architecture.view.model.CategoriesOuterModel;

public class CategoriesFragment extends BaseFragment<CategoriesFragmentViewModal> implements CategoriesFragmentView,View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.bt_load_user).setOnClickListener(this);
        mViewModal.manipulate();
    }

    @Override
    protected Class getViewModalClass() {
        return CategoriesFragmentViewModal.class;
    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }

    @Override
    public void updateCategories(CategoriesOuterModel categoriesOuterModel) {
        if(categoriesOuterModel != null){
            ((TextView) getView().findViewById(R.id.text1)).setText("size of list : "+String.valueOf(categoriesOuterModel.getCategories().size()));
        }
    }

    @Override
    public void showError(String text) {
        if(!TextUtils.isEmpty(text)){
            ((TextView) getView().findViewById(R.id.text2)).setText(text);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_load_user:
                mViewModal.handleLoadUserClick();
                break;
        }
    }
}
