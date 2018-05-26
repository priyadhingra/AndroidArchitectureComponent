package sample.app.com.architecture.data.repository;

import io.reactivex.Observable;
import sample.app.com.architecture.data.NetworkClient;
import sample.app.com.architecture.data.model.CategoriesOuterEntity;
import sample.app.com.architecture.data.networking.SearchCategoryApi;

public class CategoryRepositoryImpl implements ICategoryRepository {

    private final NetworkClient mNetworkClient;

    public CategoryRepositoryImpl() {
        mNetworkClient = NetworkClient.getInstance();
    }


    @Override
    public Observable<CategoriesOuterEntity> getCategories() {
        return mNetworkClient.create(SearchCategoryApi.class)
                .getCategoriesList();
    }



}
