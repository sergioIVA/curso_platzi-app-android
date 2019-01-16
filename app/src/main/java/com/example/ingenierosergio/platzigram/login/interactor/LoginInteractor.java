package com.example.ingenierosergio.platzigram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginInteractor {

    void signIn(String usernamem, String password, Activity activity,FirebaseAuth firebaseAuth);
}
