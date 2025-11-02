package servicios;

import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import entidades.ClimaCiudad;

public class CambioClimaServico {
     // no puede tener variable por fuera
    public static List<ClimaCiudad> getDatos(String nombreArchivo){
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d/M/yyyy");
        try{
        Stream<String> lineas = Files.lines(Paths.get(nombreArchivo));

        return lineas.skip(1)
                .map(linea -> linea.split(","))
                .map(textos -> new ClimaCiudad(textos[0],
                    LocalDate.parse(textos[1], formatoFecha),
                    Double.parseDouble(textos[2])))
                .collect(Collectors.toList());

        } catch (Exception ex) {
            System.out.println(ex);
            return Collections.emptyList();
        }
        
    }
    public static List<String> getCiudad(List<ClimaCiudad> temperaturas) {
        return temperaturas.stream()
                .map(ClimaCiudad::getCiudad)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

}
