package sample.app.com.architecture.data.repository;

import io.reactivex.Observable;
import sample.app.com.architecture.data.model.CategoriesOuterEntity;


public interface ICategoryRepository {

    Observable<CategoriesOuterEntity> getCategories() ;
}
