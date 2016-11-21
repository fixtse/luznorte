package pe.magnatech.sftwlnorte.Login;

import android.app.ProgressDialog;

import pe.magnatech.sftwlnorte.MainActivity;
import pe.magnatech.sftwlnorte.R;
import pe.magnatech.sftwlnorte.Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private EditText eteUsuario, etePassword;
    private ProgressDialog dialog;
    private String usuario;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eteUsuario = (EditText) findViewById(R.id.txtUsuario);
        etePassword = (EditText) findViewById(R.id.txtContra);

        Button but = (Button)findViewById(R.id.btnIng);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(LoginActivity.this);
                //dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setMessage("Iniciando Sesión...");
                dialog.setIndeterminate(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();


                usuario = eteUsuario.getText().toString().trim().toLowerCase();
                String password = etePassword.getText().toString();


                setPresenter(new LoginPresenter(LoginActivity.this));

                if (presenter.validar(usuario, password)) {
                    login(usuario, password);
                } else {
                    onLoginFailed();
                }
            }
        });
    }

    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login(String email, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ws-software1.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        service.obtenerLogin(new LoginRequest(email,password)).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                dialog.dismiss();
                if (response.body() == 1){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    eteUsuario.setText(null);
                    etePassword.setText(null);
                    startActivity(intent);
                }else if (response.body()==2){
                    Toast.makeText(LoginActivity.this, "Usuario no autorizado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario/contraseña errado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    @Override
    public void errPass(String err) {
        etePassword.setError(err);
    }

    @Override
    public void errMail(String err) {
        eteUsuario.setError(err);
    }

    @Override
    public void onLoginFailed() {
        dialog.dismiss();

    }


}
