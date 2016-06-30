package com.example.andres7.testriotapi;

/**
 *  Clase con todos los datos de usuario para crear el perfil
 */
public class DatosUsuario {

    // General
    private String nombre_usuario;
    private String imagen_usuario;
    private String nivel_usuario;
    // League
    private String league;
    // Stats
    private String juegos_ganados;
    private String juegos_totales;
    private String kills;
    private String max_kills;
    private String quadrakills;
    private  String pentakills;
    private String assistencias;
    // Campeon
    private String mejor_campeon;
    private String imagen_campeon;


    // Getters y Setters para manipular la clase

    public DatosUsuario(String nombre_usuario){
        this.nombre_usuario = nombre_usuario;
    }

    public String getNombreUsuario(){
        return nombre_usuario;
    }

    public void setImagenUsuario(String version, String imagen_usuario){
        // URL de las imagenes de usuario
        this.imagen_usuario =   "http://ddragon.leagueoflegends.com/cdn/"+version+
                                "/img/profileicon/"+imagen_usuario+".png";
    }

    public String getImagenUsuario(){
        return imagen_usuario;
    }

    public void setNivelUsuario(String nivel_usuario){this.nivel_usuario = nivel_usuario;}

    public String getNivelUsuario(){
        return nivel_usuario;
    }

    public void setLeague(String league_name, String tier, String division, String points){
        this.league = league_name + " " + tier + " " + division + ": " + points + " points"  ;
    }

    public String getLeague(){
        return league;
    }

    public void setMejorCampeon(String mejor_campeon, String titulo){
        this.mejor_campeon= mejor_campeon + ": " + titulo;
    }

    public String getMejorCampeon(){
        return mejor_campeon;
    }

    public void setImagenCampeon(String version, String imagen_campeon){
        // URL de las imagenes de campeones
        this.imagen_campeon =   "http://ddragon.leagueoflegends.com/cdn/"+version+
                                "/img/champion/"+imagen_campeon+".png";
    }

    public String getImagenCampeon(){
        return  imagen_campeon;
    }

    public void setJuegosGanados(String juegos_ganados){
        this.juegos_ganados= juegos_ganados;
    }

    public String getJuegosGanados(){
        return  juegos_ganados;
    }

    public void setJuegosTotales(String juegos_totales){
        this.juegos_totales= juegos_totales;
    }

    public String getJuegosTotales(){
        return  juegos_totales;
    }

    public void setKills(String kills){
        this.kills = kills;
    }

    public String getKills(){
        return  kills;
    }

    public void setMaxKills(String max_kills){
        this.max_kills = max_kills;
    }

    public String getMaxKills(){
        return  max_kills;
    }

    public void setQuadraKills(String quadrakills){
        this.quadrakills = quadrakills;
    }

    public String getQuadraKills(){
        return  quadrakills;
    }

    public void setPentakills(String pentakills){
        this.pentakills = pentakills;
    }

    public String getPentakills(){
        return  pentakills;
    }

    public void setAssistencias(String assistencias){
        this.assistencias = assistencias;
    }

    public String getAssistencias(){
        return  assistencias;
    }

}
