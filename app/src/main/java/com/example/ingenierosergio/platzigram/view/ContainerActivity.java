package com.example.ingenierosergio.platzigram.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ingenierosergio.platzigram.R;
import com.example.ingenierosergio.platzigram.login.view.CreateAccountActivity;
import com.example.ingenierosergio.platzigram.login.view.LoginActivity;
import com.example.ingenierosergio.platzigram.post.view.HomeFragment;
import com.example.ingenierosergio.platzigram.view.fragment.ProfileFragment;
import com.example.ingenierosergio.platzigram.view.fragment.SearchFragment;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ContainerActivity extends AppCompatActivity {

    private static final String TAG = "ContainerActivity";
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_container);
        fireBaseinitialize();

        final Fragment homeFragment = new HomeFragment();
        final Fragment profileFragment=new ProfileFragment();
        final Fragment searchFragment=new SearchFragment();


        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, homeFragment).commit();
        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottombar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fragmentManager = getSupportFragmentManager();

                if (item.getItemId() == R.id.home) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();
                }
                else if(item.getItemId() == R.id.profile){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, profileFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();

                }
                else if(item.getItemId() == R.id.search){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, searchFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();
                }
                return true;
            }
        });

    }


    private void fireBaseinitialize(){
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser !=null){
                    Log.w(TAG,"Usuario logeado"+firebaseUser.getEmail());
                }else{
                    Log.w(TAG,"Usuario No logeado");
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.mSignOut:
                    firebaseAuth.signOut();

                    if(LoginManager.getInstance()!=null){
                        LoginManager.getInstance().logOut();
                    }

                        Toast.makeText(this, "Se cerro la session", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(ContainerActivity.this, LoginActivity.class);
                        startActivity(i);
                        break;

                    case R.id.mAbout:
                        Toast.makeText(this, "Platzigram by Platzi", Toast.LENGTH_SHORT).show();
                        break;
                }
        return super.onOptionsItemSelected(item);
    }
}
