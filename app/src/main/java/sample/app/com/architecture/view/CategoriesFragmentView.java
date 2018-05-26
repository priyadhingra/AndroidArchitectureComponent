package sample.app.com.architecture.view;

import sample.app.com.architecture.view.model.CategoriesOuterModel;


public interface CategoriesFragmentView extends BaseView {

    void updateCategories(CategoriesOuterModel text);

    void showError(String text);
}
