package pe.magnatech.sftwlnorte.Login;

/**
 * Created by fixt on 20/11/16.
 */

public interface LoginView {

    public void login(String email, String password);
    public void errPass(String err);
    public void errMail(String err);
    public void onLoginFailed();
}
