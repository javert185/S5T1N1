package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pk_SucursalID;
    private String nomSucursal;
    private String paisSucursal;

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
	}
}