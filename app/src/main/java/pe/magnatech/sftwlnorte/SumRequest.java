package pe.magnatech.sftwlnorte;

/**
 * Created by sarp2 on 21/11/2016.
 */

public class SumRequest {

    private int num, consumo;
    private String fecha;

    public SumRequest(int num, int consumo, String fecha) {
        this.num = num;
        this.consumo = consumo;
        this.fecha = fecha;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
