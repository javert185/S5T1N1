package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.repository.SucursalRepository;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.services.SucursalServei;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private SucursalServei sucursalServei;
    
    @Autowired 
    private SucursalRepository sucursalRepository;
    
    @GetMapping({"/mostrar", "/", "/list"})
    public ModelAndView mostrarSucursals() {
    	ModelAndView mav = new ModelAndView("sucursals.html");
    	List<Sucursal> list = sucursalRepository.findAll();
    	mav.addObject("sucursals", list);
    	return mav;
    }

    @PostMapping("/add")
    public ResponseEntity<?> afegirSucursal(@RequestBody SucursalDTO sucursalDTO) {
    	
    	System.out.println("Passant pel controller");
    	
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            SucursalDTO addedSucursal = sucursalServei.crearSucursal(sucursalDTO);
            map.put("status", 1);
            map.put("message", "Registre guardat amb èxit!");
            map.put("data", addedSucursal);            
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "S'ha produït un error en desar les dades");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualitzarSucursal(@PathVariable Integer id, @RequestBody SucursalDTO sucursalDTO) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            SucursalDTO existingSucursal = sucursalServei.obtenirSucursalPerId(id);
            if (existingSucursal != null) {
                existingSucursal.setNomSucursal(sucursalDTO.getNomSucursal());
                existingSucursal.setPaisSucursal(sucursalDTO.getPaisSucursal());
                sucursalServei.actualitzarSucursal(id, existingSucursal);
                map.put("status", 1);
                map.put("data", existingSucursal);
                return new ResponseEntity<>(map, HttpStatus.OK);
            } else {
                map.put("status", 0);
                map.put("message", "No s'ha trobat cap dada");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "S'ha produït un error en actualitzar les dades");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarSucursal(@PathVariable("id") Integer id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            sucursalServei.eliminarSucursal(id);
            map.put("status", 1);
            map.put("message", "Registre eliminat amb èxit!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "No s'ha trobat cap dada");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<SucursalDTO> obtenirSucursalPerId(@PathVariable Integer id) {
        Map<String, Object> map = new LinkedHashMap<>();
        SucursalDTO sucursalDTO = sucursalServei.obtenirSucursalPerId(id);
        if (sucursalDTO != null) {
            map.put("status", 1);
            map.put("data", sucursalDTO);
            return new ResponseEntity<>(sucursalDTO, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "No s'ha trobat cap dada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SucursalDTO>> obtenirTotesLesSucursals() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<SucursalDTO> sucursals = sucursalServei.obtenirTotesLesSucursals();
        if (!sucursals.isEmpty()) {
            map.put("status", 1);
            map.put("data", sucursals);
            return new ResponseEntity<>(sucursals, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "No s'ha trobat cap dada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

