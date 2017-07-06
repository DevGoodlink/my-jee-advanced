/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import logic.Student;

/**
 * REST Web Service
 *
 * @author YASSALIE
 */
@Path("student")
public class StudentRessources {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonneRessources
     */
    public StudentRessources() {
    }

    
    @POST
    @Path("/add")
    public Response addStudent(@FormParam("name") String name, @FormParam("age") int age){
        Student s = new Student();
        s.setAge(age);s.setName(name);
        /*URI studentUri = context.getAbsolutePathBuilder()
                .path(s.getName()).build();
        return Response.created(studentUri).build();*/
        return Response.status(Response.Status.CREATED).entity("add student called -- name = "+s.getName()+
                " age = "+s.getAge()).build();
    }
}
