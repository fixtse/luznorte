package pe.magnatech.sftwlnorte.Remote;

import pe.magnatech.sftwlnorte.Login.LoginRequest;
import pe.magnatech.sftwlnorte.SumRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fixt on 11/05/16.
 */

    public interface Service {
        @POST("usuario")
        Call<Integer> obtenerLogin(@Body LoginRequest req);

        @POST("verSum")
        Call<Integer> verificarSuministro(@Body SumRequest req);


    }


