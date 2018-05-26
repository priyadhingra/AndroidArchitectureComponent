
package sample.app.com.architecture.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryInternalEntity {

    @SerializedName("categories")
    @Expose
    private CategoriesDetailEntity categories;

    public CategoriesDetailEntity getCategories() {
        return categories;
    }

    public void setCategories(CategoriesDetailEntity categories) {
        this.categories = categories;
    }

}
