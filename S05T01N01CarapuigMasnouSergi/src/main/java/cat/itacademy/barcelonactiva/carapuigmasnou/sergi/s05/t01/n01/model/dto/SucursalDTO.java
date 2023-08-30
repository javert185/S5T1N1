package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.dto;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;


public class SucursalDTO {
	
    private Integer pk_SucursalID;
    private String nomSucursal;
    private String paisSucursal;
    
    @Column(name = "tipus_sucursal", insertable = false)
    private String tipusSucursal;
    
    private static final List<String> paisosUE = Arrays.asList(
            "Àustria", "Bèlgica", "Bulgària", "Croàcia", "Xipre", "República Txeca", "Dinamarca",
            "Estònia", "Finlàndia", "França", "Alemanya", "Grècia", "Hongria", "Irlanda", "Itàlia",
            "Letònia", "Lituània", "Luxemburg", "Malta", "Països Baixos", "Polònia", "Portugal",
            "Romania", "Eslovàquia", "Eslovènia", "Espanya", "Suècia"
        );

    public SucursalDTO() {
    }

    public SucursalDTO(Integer pk_SucursalID, String nomSucursal, String paisSucursal) {
        this.pk_SucursalID = pk_SucursalID;
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
        this.tipusSucursal = determinarTipusSucursal(paisSucursal);
    }
    
    public SucursalDTO(String nomSucursal, String paisSucursal) {
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
        this.tipusSucursal = determinarTipusSucursal(paisSucursal);
    }

    public Integer getPk_SucursalID() {
        return pk_SucursalID;
    }

    public void setPk_SucursalID(Integer pk_SucursalID) {
        this.pk_SucursalID = pk_SucursalID;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public String getPaisSucursal() {
        return paisSucursal;
    }

    public void setPaisSucursal(String paisSucursal) {
        this.paisSucursal = paisSucursal;
        this.tipusSucursal = determinarTipusSucursal(paisSucursal);
    }

    public String getTipusSucursal() {
        return tipusSucursal;
    }

    private String determinarTipusSucursal(String pais) {
        if (paisosUE.contains(pais)) {
            return "UE";
        } else {
            return "Fora UE";
        }
    }

    public static List<String> getPaisosUE() {
        return paisosUE;
    }
}
