package ru.timuruktus.youliker.MainPart;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.timuruktus.youliker.LoginPart.LoginFragment;
import ru.timuruktus.youliker.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static ru.timuruktus.youliker.MainPart.MainPresenter.FEED_FRAGMENT_TAG;
import static ru.timuruktus.youliker.MainPart.MainPresenter.LOGIN_FRAGMENT_TAG;
import static ru.timuruktus.youliker.MainPart.MainPresenter.NEWEST_FEED_FRAGMENT_TAG;
import static ru.timuruktus.youliker.MainPart.MainPresenter.NEW_POST_FRAGMENT_TAG;
import static ru.timuruktus.youliker.MainPart.MainPresenter.REGISTER_FRAGMENT_TAG;

public class MainActivity extends MvpAppCompatActivity implements IMainView{

    @InjectPresenter
    MainPresenter mainPresenter;
    public static final String DEFAULT_TAG = "DefaultTag";
    public static final String TESTING_TAG = "TestingTag";

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(),
            R.id.container){
        @Override
        protected Fragment createFragment(String fragmentTag, Object data){
            switch(fragmentTag){
                case FEED_FRAGMENT_TAG:
                case LOGIN_FRAGMENT_TAG:
                    return LoginFragment.getInstance();
                default:
                    throw new RuntimeException("Unknown screen key!");
            }
        }

        @Override
        protected void setupFragmentTransactionAnimation(Command command, Fragment currentFragment, Fragment nextFragment, FragmentTransaction fragmentTransaction){
            super.setupFragmentTransactionAnimation(command, currentFragment, nextFragment, fragmentTransaction);
//            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }

        @Override
        protected void showSystemMessage(String message){
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit(){
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume(){
        super.onResume();
        MyApp.INSTANCE.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onPause(){
        super.onPause();
        MyApp.INSTANCE.getNavigatorHolder().removeNavigator();
    }

    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy(){
        mainPresenter.onDestroy();
        super.onDestroy();

    }
}
