/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsrest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


/**
 * REST Web Service
 *
 * @author YASSALIE
 */
@Path("helloworld")
public class HellWorld {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public HellWorld() {
    }
    @GET
    @Produces("text/html")    
    public String getHtml(){
        return "<h1>First web service RESTFULL</h1>";
    }

}
