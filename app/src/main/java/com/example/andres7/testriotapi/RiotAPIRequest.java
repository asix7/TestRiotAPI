package com.example.andres7.testriotapi;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class RiotAPIRequest extends AsyncTask<String, Void, DatosUsuario>{

    private final String key = "fdbc894e-2a9f-483c-a3f7-8137c3f9fdf9";

    @Override
    protected DatosUsuario doInBackground(String... strings) {

        DatosUsuario datos_usuario = new DatosUsuario(strings[0]);
        String respuesta = "";

        // Realizar las request para llenar la informacion del usuario
        try {
            // Obtener version mas reciente de la base de datos para las imagenes
            respuesta = urlRequest(getVersion());
            JSONArray version_response = new JSONArray(respuesta);
            String version = version_response.getString(0);

            // Datos Generales
            respuesta = urlRequest(getUser(datos_usuario.getNombreUsuario().toLowerCase()));
            JSONObject json_response = new JSONObject(respuesta);
            json_response = json_response.getJSONObject(datos_usuario.getNombreUsuario().toLowerCase());

            datos_usuario.setImagenUsuario(version, json_response.get("profileIconId").toString());
            datos_usuario.setNivelUsuario(json_response.get("summonerLevel").toString());
            String user_id = json_response.get("id").toString();

            // Liga del Usuario
            respuesta = urlRequest(getLeagues(user_id));
            json_response = new JSONObject(respuesta);
            json_response = json_response.getJSONArray(user_id).getJSONObject(0);
            String league_name = json_response.get("name").toString();
            String tier = json_response.get("tier").toString();
            json_response = json_response.getJSONArray("entries").getJSONObject(0);
            String division = json_response.get("division").toString();
            String points = json_response.get("leaguePoints").toString();
            datos_usuario.setLeague(league_name, tier, division, points);

            // Usuario stats
            respuesta = urlRequest(getStats(user_id));
            json_response = new JSONObject(respuesta);
            json_response = json_response.getJSONArray("champions").getJSONObject(
                            json_response.getJSONArray("champions").length() - 1).getJSONObject("stats");
            datos_usuario.setJuegosGanados(json_response.get("totalSessionsWon").toString());
            datos_usuario.setJuegosTotales(json_response.get("totalSessionsPlayed").toString());
            datos_usuario.setKills(json_response.get("totalChampionKills").toString());
            datos_usuario.setMaxKills(json_response.get("mostChampionKillsPerSession").toString());
            datos_usuario.setQuadraKills(json_response.get("totalQuadraKills").toString());
            datos_usuario.setPentakills(json_response.get("totalPentaKills").toString());
            datos_usuario.setAssistencias(json_response.get("totalAssists").toString());

            // Mejor Campeon
            respuesta = urlRequest(getChampionMastery(user_id));
            json_response = new JSONArray(respuesta).getJSONObject(0);
            String mejor_campeon_id = json_response.get("championId").toString();

            respuesta = urlRequest(getCampeon(mejor_campeon_id));
            json_response = new JSONObject(respuesta);
            datos_usuario.setMejorCampeon(json_response.get("name").toString(),
                                            json_response.get("title").toString());
            datos_usuario.setImagenCampeon(version, json_response.get("name").toString());

        } catch(Exception e){
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            datos_usuario = null;
        }
        return datos_usuario;
    }

    // Diferentes request para realizar

    private String getVersion(){
        return "https://global.api.pvp.net/api/lol/static-data/na/v1.2/versions?";
    }
    private String getUser(String username){
        return "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/" + username + "?";
    }

    private String getCampeon(String champion_id){

        return "https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/"+ champion_id +
                "?locale=en_US&champData=image&";

    }

    private String getLeagues(String user_id){
        return "https://na.api.pvp.net/api/lol/na/v2.5/league/by-summoner/"+ user_id +"/entry?";
    }

    private String getStats(String user_id){
        return "https://na.api.pvp.net/api/lol/na/v1.3/stats/by-summoner/"+user_id+"/ranked?season=SEASON2016&";
    }

    private String getChampionMastery(String user_id){
        return "https://na.api.pvp.net/championmastery/location/NA1/player/" + user_id + "/champions?";
    }

    /**
     * Realiza un url request con la llave privada asignada
     * @param url String con la request
     * @return La respuesta del url como String
     * @throws IOException
     */
    private String urlRequest(String url) throws IOException{
        URL riotAPI = new URL(url + "api_key=" + key);
        BufferedReader in = new BufferedReader(new InputStreamReader(riotAPI.openStream()));
        String inputLine;
        StringBuilder sBuilder = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            sBuilder.append(inputLine + "\n");
        }

        return sBuilder.toString();
    }

}
