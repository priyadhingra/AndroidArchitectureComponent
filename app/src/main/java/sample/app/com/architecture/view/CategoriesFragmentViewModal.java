package sample.app.com.architecture.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import sample.app.com.architecture.data.repository.ICategoryRepository;
import sample.app.com.architecture.data.repository.CategoryRepositoryImpl;
import sample.app.com.architecture.view.mapper.CategoryEntityToViewModelMapper;
import sample.app.com.architecture.view.model.CategoriesOuterModel;
import sample.app.com.architecture.view.model.UserDataModal;


public class CategoriesFragmentViewModal extends BaseViewModal<CategoriesFragmentView> {

    private UserDataModal userDataModal = new UserDataModal();
    private ICategoryRepository userRepository;
    private CategoriesOuterModel categoriesOuterModel;
    private CategoryEntityToViewModelMapper categoryEntityToViewModelMapper;
    public CategoriesFragmentViewModal(LifecycleOwner owner) {
        super(owner);
        this.userRepository = new CategoryRepositoryImpl();
        this.categoryEntityToViewModelMapper = new CategoryEntityToViewModelMapper();
    }

    public void setUserRepository(ICategoryRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setCategoryEntityToViewModelMapper(CategoryEntityToViewModelMapper categoryEntityToViewModelMapper) {
        this.categoryEntityToViewModelMapper = categoryEntityToViewModelMapper;
    }

    @Override
    protected void initObservableData() {
        MutableLiveData<IDataModal> categoryDataModalMutableLiveData = new MutableLiveData<>();
        categoryDataModalMutableLiveData.setValue(categoriesOuterModel);
        mObservableData.add(categoryDataModalMutableLiveData);

        MutableLiveData<IDataModal> userDataModalMutableLiveData = new MutableLiveData<>();
        userDataModalMutableLiveData.setValue(userDataModal);
        mObservableData.add(userDataModalMutableLiveData);

    }

    public void manipulate() {
        userDataModal.text1 = "new";
        mObservableData.get(0).setValue(userDataModal);
    }

    @Override
    protected void handleChangedDataModal(IDataModal dataModal) {
        if(dataModal instanceof CategoriesOuterModel){
            mView.updateCategories(((CategoriesOuterModel)dataModal));
        }
        else if(dataModal instanceof UserDataModal){

        }
    }

    public void handleLoadUserClick() {
        userRepository.getCategories()
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoriesOuterEntity -> {
//                            mView.updateCategories(categoryEntityToViewModelMapper.mapCategoryEntityToCategoryViewModel(categoriesOuterEntity));
                            mObservableData.get(0).setValue(categoryEntityToViewModelMapper.mapCategoryEntityToCategoryViewModel(categoriesOuterEntity));
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            mView.showError(throwable.getMessage());
                        });

    }

}
