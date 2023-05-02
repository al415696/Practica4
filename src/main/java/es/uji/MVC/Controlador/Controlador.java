package es.uji.MVC.Controlador;

import es.uji.MVC.Modelo.CambioModelo;
import es.uji.MVC.Modelo.Modelo;
import es.uji.MVC.Vista.InterrogaVista;
import es.uji.MVC.Vista.Vista;

public class Controlador implements Controller {
    private CambioModelo modelo;
    private InterrogaVista vista;

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;

    }
}