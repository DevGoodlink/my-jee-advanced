/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consomateurwebserviceweather;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class Consomateurwebserviceweather {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(getCitiesByCountry("Morocco"));
    }

    private static String getCitiesByCountry(java.lang.String countryName) {
        GlobalWeather service = new GlobalWeather();
        GlobalWeatherSoap port = service.getGlobalWeatherSoap();
        return port.getCitiesByCountry(countryName);
    }
    
}
