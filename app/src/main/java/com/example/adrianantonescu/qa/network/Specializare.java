package com.example.adrianantonescu.qa.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Specializare implements Serializable {
    private List<Colectiv> informaticaEconomica;
    private Colectiv ciberneticaEconomica;
    private Colectiv statistica;

    public Specializare() {
    }

    public Specializare(List<Colectiv> informaticaEconomica, Colectiv ciberneticaEconomica, Colectiv statistica) {
        this.informaticaEconomica = informaticaEconomica;
        this.ciberneticaEconomica = ciberneticaEconomica;
        this.statistica = statistica;
    }

    public List<Colectiv> getInformaticaEconomica() {
        return informaticaEconomica;
    }

    public void setInformaticaEconomica(Colectiv colectiv){
        if(informaticaEconomica==null)
            informaticaEconomica=new ArrayList<>();
        if(colectiv!=null)
            informaticaEconomica.add(colectiv);
    }
    public void setInformaticaEconomica(List<Colectiv> informaticaEconomica) {
        this.informaticaEconomica = informaticaEconomica;
    }

    public Colectiv getCiberneticaEconomica() {
        return ciberneticaEconomica;
    }

    public void setCiberneticaEconomica(Colectiv ciberneticaEconomica) {
        this.ciberneticaEconomica = ciberneticaEconomica;
    }

    public Colectiv getStatistica() {
        return statistica;
    }

    public void setStatistica(Colectiv statistica) {
        this.statistica = statistica;
    }

    @Override
    public String toString() {
        StringBuilder info=new StringBuilder();
        for(int i=0;i<this.informaticaEconomica.size();i++)
            info.append(this.informaticaEconomica.get(i));
        return info.toString()+this.ciberneticaEconomica+this.statistica;
    }
}
