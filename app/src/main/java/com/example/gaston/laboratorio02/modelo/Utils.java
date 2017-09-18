package com.example.gaston.laboratorio02.modelo;

import java.text.DecimalFormat;
import java.util.Random;

public class Utils {
    DecimalFormat f = new DecimalFormat("##.00");

    private ElementoMenu[] listaBebidas;
    private ElementoMenu[] listaPlatos;
    private ElementoMenu[] listaPostre;

    public ElementoMenu obtenerElementoMenu(String s) {
        for(ElementoMenu em : listaBebidas){
            if(em.getNombre().equals(s)){
                return em;
            }
        }
        for(ElementoMenu em : listaPlatos){
            if(em.getNombre().equals(s)){
                return em;
            }
        }
        for(ElementoMenu em : listaPostre){
            if(em.getNombre().equals(s)){
                return em;
            }
        }
        return null;
    }

    public class ElementoMenu {
        private Integer id;
        private String nombre;
        private Double precio;

        private TipoBebida tipoBebida;

        public ElementoMenu() {
        }

        public ElementoMenu(Integer i, String n, Double p, TipoBebida tb) {
            this.id = i;
            this.nombre = n;
            this.precio = p;
            this.tipoBebida = tb;
        }

        public ElementoMenu(Integer i, String n, TipoBebida tb) {
            this.id = i;
            this.nombre = n;
            this.tipoBebida = tb;
            Random r = new Random();
            this.precio= (r.nextInt(3)+1)*((r.nextDouble()*100));
        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Double getPrecio() {
            return precio;
        }


        public void setPrecio(Double precio) {
            this.precio = precio;
        }

        @Override
        public String toString() {
            return this.nombre+ "( "+f.format(this.precio)+")";
        }

        public TipoBebida getTipoBebida() {
            return tipoBebida;
        }

        public void setTipoBebida(TipoBebida tipoBebida) {
            this.tipoBebida = tipoBebida;
        }
    }

    public void iniciarListas(){
        // inicia lista de bebidas
        listaBebidas = new ElementoMenu[7];
        listaBebidas[0]=new ElementoMenu(1,"Coca", TipoBebida.BEBIDA);
        listaBebidas[1]=new ElementoMenu(4,"Jugo", TipoBebida.BEBIDA);
        listaBebidas[2]=new ElementoMenu(6,"Agua", TipoBebida.BEBIDA);
        listaBebidas[3]=new ElementoMenu(8,"Soda", TipoBebida.BEBIDA);
        listaBebidas[4]=new ElementoMenu(9,"Fernet", TipoBebida.BEBIDA);
        listaBebidas[5]=new ElementoMenu(10,"Vino", TipoBebida.BEBIDA);
        listaBebidas[6]=new ElementoMenu(11,"Cerveza", TipoBebida.BEBIDA);
        // inicia lista de platos
        listaPlatos= new ElementoMenu[14];
        listaPlatos[0]=new ElementoMenu(1,"Ravioles", TipoBebida.PRINCIPAL);
        listaPlatos[1]=new ElementoMenu(2,"Gnocchi", TipoBebida.PRINCIPAL);
        listaPlatos[2]=new ElementoMenu(3,"Tallarines", TipoBebida.PRINCIPAL);
        listaPlatos[3]=new ElementoMenu(4,"Lomo", TipoBebida.PRINCIPAL);
        listaPlatos[4]=new ElementoMenu(5,"Entrecot", TipoBebida.PRINCIPAL);
        listaPlatos[5]=new ElementoMenu(6,"Pollo", TipoBebida.PRINCIPAL);
        listaPlatos[6]=new ElementoMenu(7,"Pechuga", TipoBebida.PRINCIPAL);
        listaPlatos[7]=new ElementoMenu(8,"Pizza", TipoBebida.PRINCIPAL);
        listaPlatos[8]=new ElementoMenu(9,"Empanadas", TipoBebida.PRINCIPAL);
        listaPlatos[9]=new ElementoMenu(10,"Milanesas", TipoBebida.PRINCIPAL);
        listaPlatos[10]=new ElementoMenu(11,"Picada 1", TipoBebida.PRINCIPAL);
        listaPlatos[11]=new ElementoMenu(12,"Picada 2", TipoBebida.PRINCIPAL);
        listaPlatos[12]=new ElementoMenu(13,"Hamburguesa", TipoBebida.PRINCIPAL);
        listaPlatos[13]=new ElementoMenu(14,"Calamares", TipoBebida.PRINCIPAL);
        // inicia lista de postres
        listaPostre= new ElementoMenu[15];
        listaPostre[0]=new ElementoMenu(1,"Helado", TipoBebida.POSTRE);
        listaPostre[1]=new ElementoMenu(2,"Ensalada de Frutas", TipoBebida.POSTRE);
        listaPostre[2]=new ElementoMenu(3,"Macedonia", TipoBebida.POSTRE);
        listaPostre[3]=new ElementoMenu(4,"Brownie", TipoBebida.POSTRE);
        listaPostre[4]=new ElementoMenu(5,"Cheescake", TipoBebida.POSTRE);
        listaPostre[5]=new ElementoMenu(6,"Tiramisu", TipoBebida.POSTRE);
        listaPostre[6]=new ElementoMenu(7,"Mousse", TipoBebida.POSTRE);
        listaPostre[7]=new ElementoMenu(8,"Fondue", TipoBebida.POSTRE);
        listaPostre[8]=new ElementoMenu(9,"Profiterol", TipoBebida.POSTRE);
        listaPostre[9]=new ElementoMenu(10,"Selva Negra", TipoBebida.POSTRE);
        listaPostre[10]=new ElementoMenu(11,"Lemon Pie", TipoBebida.POSTRE);
        listaPostre[11]=new ElementoMenu(12,"KitKat", TipoBebida.POSTRE);
        listaPostre[12]=new ElementoMenu(13,"IceCreamSandwich", TipoBebida.POSTRE);
        listaPostre[13]=new ElementoMenu(14,"Frozen Yougurth", TipoBebida.POSTRE);
        listaPostre[14]=new ElementoMenu(15,"Queso y Batata", TipoBebida.POSTRE);

    }

    public ElementoMenu[] getListaPostre(){
        return listaPostre;
    }

    public ElementoMenu[] getListaBebidas(){
        return listaBebidas;
    }

    public ElementoMenu[] getListaPlatos(){
        return listaPlatos;
    }

}
