package pe.magnatech.sftwlnorte.Login;

import android.util.Patterns;

/**
 * Created by fixt on 20/11/16.
 */

public class LoginPresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public boolean validar(String email, String password){
        boolean valid = true;


        if(email.isEmpty()){
            loginView.errMail("Ingresa un email valido");
            valid = false;
        }else{
            loginView.errMail(null);
        }

        if(password.isEmpty()){
            loginView.errPass("Ingrese un valor alfanumerico entre 4 y 10 carácteres");
            valid = false;
        }else{
            loginView.errPass(null);
        }

        return valid;
    }


}