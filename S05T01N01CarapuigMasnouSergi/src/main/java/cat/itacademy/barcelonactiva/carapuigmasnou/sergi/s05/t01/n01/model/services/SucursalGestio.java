package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.services;
import java.util.List;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.dto.SucursalDTO;

public interface SucursalGestio {

    SucursalDTO crearSucursal(SucursalDTO sucursalDTO);

    SucursalDTO actualitzarSucursal(Integer id, SucursalDTO sucursalDTO);

    void eliminarSucursal(Integer id);

    SucursalDTO obtenirSucursalPerId(Integer id);

    List<SucursalDTO> obtenirTotesLesSucursals();
}
