
package sample.app.com.architecture.view.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import sample.app.com.architecture.view.IDataModal;

public class CategoriesDetailModel  {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
