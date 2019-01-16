package com.example.ingenierosergio.platzigram.login.presenter;

import android.app.Activity;

import com.example.ingenierosergio.platzigram.login.interactor.LoginInteractor;
import com.example.ingenierosergio.platzigram.login.interactor.LoginInteractorImpl;
import com.example.ingenierosergio.platzigram.login.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor interactor;


    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.interactor=new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password, Activity activity,FirebaseAuth firebaseAuth) {
           loginView.disableInputs();
           loginView.showProgressBar();
           interactor.signIn(username,password,activity,firebaseAuth);
    }

    @Override
    public void loginSuccess() {
       loginView.goContainer();
       loginView.hideProgressBar();
    }

    @Override
    public void loginError(String error) {
       loginView.enableInputs();
       loginView.hideProgressBar();
       loginView.loginError(error);
    }
}
