package sample.app.com.architecture.view.mapper;

import java.util.ArrayList;
import java.util.List;

import sample.app.com.architecture.data.model.CategoriesDetailEntity;
import sample.app.com.architecture.data.model.CategoriesOuterEntity;
import sample.app.com.architecture.data.model.CategoryInternalEntity;
import sample.app.com.architecture.view.model.CategoriesDetailModel;
import sample.app.com.architecture.view.model.CategoriesOuterModel;
import sample.app.com.architecture.view.model.CategoryInternalModel;

public class CategoryEntityToViewModelMapper {

   public CategoriesOuterModel mapCategoryEntityToCategoryViewModel(CategoriesOuterEntity categoriesOuterEntity){
        CategoriesOuterModel categoriesOuterModel = new CategoriesOuterModel();
        List<CategoryInternalEntity> categoryInternalEntities = categoriesOuterEntity.getCategories();
        List<CategoryInternalModel> categoryInternalModelList = new ArrayList<>();
        for(CategoryInternalEntity categoryInternalEntity : categoryInternalEntities){
            CategoryInternalModel categoryInternalModel = new CategoryInternalModel();
            CategoriesDetailModel categoriesDetailModel = new CategoriesDetailModel();
            CategoriesDetailEntity categoriesDetailEntity = categoryInternalEntity.getCategories();
            categoriesDetailModel.setId(categoriesDetailEntity.getId());
            categoriesDetailModel.setName(categoriesDetailEntity.getName());
            categoryInternalModel.setCategories(categoriesDetailModel);
            categoryInternalModelList.add(categoryInternalModel);
            //mapping logic
        }
        categoriesOuterModel.setCategories(categoryInternalModelList);
        return categoriesOuterModel;
    }
}
