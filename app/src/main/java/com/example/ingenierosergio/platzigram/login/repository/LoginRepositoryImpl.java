package com.example.ingenierosergio.platzigram.login.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ingenierosergio.platzigram.login.presenter.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginRepositoryImpl implements LoginRepository {
    LoginPresenter presenter;
    private String TAG="LoginRepositoryImpl";



    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String username, String password, final Activity activity, FirebaseAuth firebaseAuth) {

        if(username.isEmpty()||password.isEmpty()){

            presenter.loginError("Por favor Ingrese los valores!");
        }else {


            firebaseAuth.signInWithEmailAndPassword(username, password).
                    addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = task.getResult().getUser();

                                SharedPreferences preferences = activity.getSharedPreferences("USER", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("email", user.getEmail());
                                editor.commit();
                                Log.w(TAG,"Usuario Logeado"+user.getEmail());

                                presenter.loginSuccess();
                            } else {
                                presenter.loginError("Ocurrio un Error");
                            }
                        }
                    });
        }

    }
}
