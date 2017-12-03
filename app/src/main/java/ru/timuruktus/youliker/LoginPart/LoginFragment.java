package ru.timuruktus.youliker.LoginPart;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class LoginFragment extends MvpAppCompatFragment implements ILoginView{

    @InjectPresenter
    public LoginPresenter loginPresenter;

    public static LoginFragment getInstance(){
        return new LoginFragment();
    }


}
