/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.net.URI;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

/**
 * REST Web Service
 *
 * @author YASSALIE
 */
@Path("Ordinateur")
public class OrdinateurResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OrdinateurResource
     */
    public OrdinateurResource() {
    }

    /**
     * Retrieves representation of an instance of logic.OrdinateurResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}/")
    @Produces({"application/json","application/xml"})//{MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Ordinateur getOrdinateur(@PathParam("id")Long id) {
        //TODO return proper representation object
        return new Ordinateur(id,"Samsung",2000F);
    }

    /**
     * PUT method for updating or creating an instance of OrdinateurResource
     * @param ordinateur
     * @param content representation for the resource
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response createOrdinateur(JAXBElement<Ordinateur> ordinateurJaxBElement){
        Ordinateur ordinateur = ordinateurJaxBElement.getValue();
        
        URI ordinateurUri = context.getAbsolutePathBuilder()
                .path(ordinateur.getId().toString()).build();
        return Response.created(ordinateurUri).build();
        
    }
    /*public String addOrdinateur(@PathParam("o")Ordinateur o) {
        return "L'ordinateur "+o.getMarque() + " a bien été créé.";
    }*/
}
