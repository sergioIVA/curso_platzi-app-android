package com.example.ingenierosergio.platzigram.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginPresenter {

    void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth); //Interator
    void loginSuccess();
    void loginError(String error);
}
