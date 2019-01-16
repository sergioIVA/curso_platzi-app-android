package com.example.ingenierosergio.platzigram.login.view;

public interface LoginView {

    void goCreateAccount();
    void goContainer();
    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();
    void loginError(String error);

}
