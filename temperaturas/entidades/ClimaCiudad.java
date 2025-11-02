package entidades;
import java.time.LocalDate;

public class ClimaCiudad {

    private String ciudad;
    private LocalDate fecha;
    private double clima;
    
    public ClimaCiudad(String ciudad, LocalDate fecha, double clima) {
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.clima = clima;
    }

    public String getCiudad() {
        return ciudad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getClima() {
        return clima;
    }


}
