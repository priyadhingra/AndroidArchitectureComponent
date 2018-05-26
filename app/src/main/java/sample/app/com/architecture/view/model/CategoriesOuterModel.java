
package sample.app.com.architecture.view.model;

import java.util.List;

import sample.app.com.architecture.view.IDataModal;

public class CategoriesOuterModel implements IDataModal {

    private List<CategoryInternalModel> categories = null;

    public List<CategoryInternalModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryInternalModel> categories) {
        this.categories = categories;
    }

}
