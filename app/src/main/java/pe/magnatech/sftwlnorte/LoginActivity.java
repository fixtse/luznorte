package pe.magnatech.sftwlnorte;

import android.app.ProgressDialog;
import pe.magnatech.*;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Cargando... Por favor espere");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        String usuario = eteUsuario.getText().toString().trim();
        password = etePassword.getText().toString();
        LoginRequest loginRequest = New LoginRequest();
        setPresenter(new LoginPresenterImp(this));
        lPresenter.obtenerLogin(user);
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
