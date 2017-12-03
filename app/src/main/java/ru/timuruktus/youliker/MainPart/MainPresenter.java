package ru.timuruktus.youliker.MainPart;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.timuruktus.youliker.Utils.ISettings;

@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> implements IMainPresenter{

    public static final String FEED_FRAGMENT_TAG = "feedTag";
    public static final String LOGIN_FRAGMENT_TAG = "loginTag";
    public static final String REGISTER_FRAGMENT_TAG = "registerTag";
    public static final String NEWEST_FEED_FRAGMENT_TAG = "newestFeedTag";
    public static final String NEW_POST_FRAGMENT_TAG = "newPostTag";
    public static final int SNACKBAR_BACKGROUND_COLOR = 0x2c82c9;
    private static String currentFragmentTag;

    private void loadFirstFragment(){
        ISettings settings = MyApp.getSettings();
        if(settings.isUserLoggedIn()){
            MyApp.INSTANCE.getRouter().newRootScreen(FEED_FRAGMENT_TAG);
        }else{
            MyApp.INSTANCE.getRouter().newRootScreen(LOGIN_FRAGMENT_TAG);
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadFirstFragment();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {

    }

}
