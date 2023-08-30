package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.repository.SucursalRepository;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.services.SucursalServei;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private SucursalServei sucursalServei;

    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping({"/getAll"})
    public ModelAndView listSucursals() {
        ModelAndView mav = new ModelAndView("sucursals");
        mav.addObject("sucursals", sucursalRepository.findAll());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addSucursalForm() {
        ModelAndView mav = new ModelAndView("afegirSucursal");
        SucursalDTO newSucursal = new SucursalDTO();
        mav.addObject("sucursalDTO", newSucursal);
        return mav;
    }

    @PostMapping("/save")
    public String saveSucursal(@ModelAttribute SucursalDTO sucursalDTO) {
        sucursalServei.crearSucursal(sucursalDTO);
        return "redirect:/sucursal/getAll";
    }

    @GetMapping("/update")
    public ModelAndView updateSucursalForm(@RequestParam Integer id) {
        ModelAndView mav = new ModelAndView("actualitzarSucursal");
        SucursalDTO sucursalDTO = sucursalServei.obtenirSucursalPerId(id);
        mav.addObject("sucursalDTO", sucursalDTO);
        return mav;
    }
    
    @PostMapping("/saveActualitzacio")
    public String saveActualitzacioSucursal(@ModelAttribute SucursalDTO sucursalDTO) {
        sucursalServei.actualitzarSucursal(sucursalDTO.getPk_SucursalID(), sucursalDTO);
        return "redirect:/sucursal/getAll";
    }

    @GetMapping("/delete")
    public String deleteSucursal(@RequestParam Integer id) {
        sucursalServei.eliminarSucursal(id);
        return "redirect:/sucursal/getAll";
    }
}

