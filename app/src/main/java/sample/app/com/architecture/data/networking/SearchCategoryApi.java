/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package sample.app.com.architecture.data.networking;

import io.reactivex.Observable;
import sample.app.com.architecture.data.model.CategoriesOuterEntity;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface SearchCategoryApi {
    @Headers("user-key: dab40b37d6a30e8a5b1f0a67344c545b")
    @GET("categories")
    Observable<CategoriesOuterEntity> getCategoriesList();

}
