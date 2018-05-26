
package sample.app.com.architecture.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesOuterEntity {

    @SerializedName("categories")
    @Expose
    private List<CategoryInternalEntity> categories = null;

    public List<CategoryInternalEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryInternalEntity> categories) {
        this.categories = categories;
    }

}
