package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.repository.SucursalRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SucursalServei implements SucursalGestio {

    private final SucursalRepository sucursalRepository;

    @Autowired
    public SucursalServei(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = convertirASucursal(sucursalDTO);                
        sucursal = sucursalRepository.save(sucursal);
        sucursalDTO.setPk_SucursalID(sucursal.getPk_SucursalID());
        return sucursalDTO;
    }

    @Override
    public SucursalDTO actualitzarSucursal(Integer id, SucursalDTO sucursalDTO) {
        Optional<Sucursal> sucursalOptional = sucursalRepository.findById(id);
        if (sucursalOptional.isPresent()) {
            Sucursal sucursal = sucursalOptional.get();
            sucursal.setNomSucursal(sucursalDTO.getNomSucursal());
            sucursal.setPaisSucursal(sucursalDTO.getPaisSucursal());
            sucursal = sucursalRepository.save(sucursal);
            sucursalDTO.setPk_SucursalID(sucursal.getPk_SucursalID());
            return sucursalDTO;
        }
        return null;
    }

    @Override
    public void eliminarSucursal(Integer id) {
        sucursalRepository.deleteById(id);
    }

    @Override
    public SucursalDTO obtenirSucursalPerId(Integer id) {
        Optional<Sucursal> sucursalOptional = sucursalRepository.findById(id);
        return sucursalOptional.map(sucursal -> {
            SucursalDTO sucursalDTO = convertirASucursalDTO(sucursal);
            sucursalDTO.setPk_SucursalID(sucursal.getPk_SucursalID());
            return sucursalDTO;
        }).orElse(null);
    }

    @Override
    public List<SucursalDTO> obtenirTotesLesSucursals() {
        List<Sucursal> sucursals = sucursalRepository.findAll();
        return sucursals.stream()
                .map(sucursal -> {
                    SucursalDTO sucursalDTO = convertirASucursalDTO(sucursal);
                    sucursalDTO.setPk_SucursalID(sucursal.getPk_SucursalID());
                    return sucursalDTO;
                })
                .collect(Collectors.toList());
    }


    private Sucursal convertirASucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNomSucursal(sucursalDTO.getNomSucursal());
        sucursal.setPaisSucursal(sucursalDTO.getPaisSucursal());
        return sucursal;
    }

    private SucursalDTO convertirASucursalDTO(Sucursal sucursal) {
        SucursalDTO sucursalDTO = new SucursalDTO();
        sucursalDTO.setNomSucursal(sucursal.getNomSucursal());
        sucursalDTO.setPaisSucursal(sucursal.getPaisSucursal());
        return sucursalDTO;
    }
}
