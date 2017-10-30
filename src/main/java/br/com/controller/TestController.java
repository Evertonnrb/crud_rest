package br.com.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestController {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMensagem(@QueryParam("usu") String usuario){
        return "Bem vindo "+usuario;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("usu/{id}")
    public String getUsuario(@PathParam("id") Long id){
        return "Retornando ID do usuario "+id;
    }
}
