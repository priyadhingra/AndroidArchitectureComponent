package sample.app.com.architecture.view;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import sample.app.com.architecture.data.model.CategoriesDetailEntity;
import sample.app.com.architecture.data.model.CategoriesOuterEntity;
import sample.app.com.architecture.data.model.CategoryInternalEntity;
import sample.app.com.architecture.data.repository.ICategoryRepository;
import sample.app.com.architecture.view.mapper.CategoryEntityToViewModelMapper;
import sample.app.com.architecture.view.model.CategoriesOuterModel;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserFragmentViewModalTest {

    @Mock
    private ICategoryRepository repository;

    @Mock
    private CategoriesFragmentView view;

    private CategoriesFragmentViewModal userFragmentViewModal;

    @Mock
    private CategoryEntityToViewModelMapper categoryEntityToViewModelMapper;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userFragmentViewModal = new CategoriesFragmentViewModal(mockLifecycleOwner());
        userFragmentViewModal.setUserRepository(repository);
        userFragmentViewModal.registerView(view);
        userFragmentViewModal.setCategoryEntityToViewModelMapper(categoryEntityToViewModelMapper);
    }

    private LifecycleOwner mockLifecycleOwner() {
        final LifecycleOwner lifecycleOwner = mock(LifecycleOwner.class);
        LifecycleRegistry registry = new LifecycleRegistry(lifecycleOwner);
        registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        doReturn(registry).when(lifecycleOwner).getLifecycle();
        return lifecycleOwner;
    }

    @Test
    public void shouldShowErrorOnLoadUser(){

        String errorMessage = "some error message";
        when(repository.getCategories()).thenReturn(Observable.error(new Throwable(errorMessage)));
        userFragmentViewModal.handleLoadUserClick();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verify(view).showError(errorMessage);
    }

    @Test
    public void shouldLoadUser() {
        CategoriesOuterEntity categoriesOuterEntity = new CategoriesOuterEntity();
        CategoriesDetailEntity categoriesDetailEntity = new CategoriesDetailEntity();
        categoriesDetailEntity.setId(1);
        categoriesDetailEntity.setName("priya");
        CategoryInternalEntity categoryInternalEntity = new CategoryInternalEntity();
        categoryInternalEntity.setCategories(categoriesDetailEntity);
        List<CategoryInternalEntity> categoryInternalEntityList = new ArrayList<>();
        categoryInternalEntityList.add(categoryInternalEntity);
        categoriesOuterEntity.setCategories(categoryInternalEntityList);
        CategoriesOuterModel categoriesOuterModel = categoryEntityToViewModelMapper.mapCategoryEntityToCategoryViewModel(categoriesOuterEntity);
        doReturn(categoriesOuterModel).when(categoryEntityToViewModelMapper).mapCategoryEntityToCategoryViewModel(categoriesOuterEntity);
        when(repository.getCategories()).thenReturn(Observable.just(categoriesOuterEntity));
        userFragmentViewModal.handleLoadUserClick();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verify(view).updateCategories(categoriesOuterModel);
    }
}
