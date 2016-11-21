package Remote;

import pe.magnatech.sftwlnorte.LoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fixt on 11/05/16.
 */

    public interface PichangersService {
        @POST("usuario")
        Call<Integer> obtenerLogin(@Body LoginRequest req);


    }


