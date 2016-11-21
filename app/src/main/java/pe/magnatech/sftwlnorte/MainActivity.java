package pe.magnatech.sftwlnorte;

import android.app.ProgressDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.magnatech.sftwlnorte.Login.LoginActivity;
import pe.magnatech.sftwlnorte.Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText sum, consumo,fec;
    Button btnE;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sum = (EditText)findViewById(R.id.txtSuministro);
        consumo = (EditText)findViewById(R.id.txtConsumo);
        fec = (EditText)findViewById(R.id.fecnac);
        btnE = (Button)findViewById(R.id.btnIng);
        final EditText fecN = (EditText)findViewById(R.id.fecnac);
        fecN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new datePicker();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MainActivity.this);
                //dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setMessage("Verificando Suministro...");
                dialog.setIndeterminate(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://ws-software1.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Service service = retrofit.create(Service.class);
                service.verificarSuministro(new SumRequest(Integer.valueOf(sum.getText().toString()),Integer.valueOf(consumo.getText().toString()),fec.getText().toString())).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        dialog.dismiss();
                        if (response.body()==1){
                            sum.setText(null);
                            consumo.setText(null);
                            fec.setText(null);
                            Toast.makeText(MainActivity.this, "Consumo registrado", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Suministro erroneo", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    Log.i("TAG", "You have forgotten to specify the parentActivityName in the AndroidManifest!");
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
