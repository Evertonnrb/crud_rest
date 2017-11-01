package br.com.controller;

import br.com.bean.Chamado;
import br.com.enumerado.Status;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("chamados")
public class ChamadoController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Chamado> listChamados() {
//        Chamado chamado1 = new Chamado("Chamado1", "Mensagem1", Status.NOVO);
//        Chamado chamado2 = new Chamado("Chamado2", "Mensagem2", Status.PENDENTE);
//        Chamado chamado3 = new Chamado("Chamado3", "Mensagem3", Status.FECHADO);
        List<Chamado> chamados = new ArrayList<>();
//        chamados.add(chamado1);
//        chamados.add(chamado2);
//        chamados.add(chamado3);
        return chamados;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Chamado getChamdo(@PathParam("id") Long id) {
         Chamado c1 = new Chamado();
        return c1;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(Chamado c){
        System.out.println(c.toString());
        return Response.status(Response.Status.OK).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(Chamado c){
        System.out.println(c.toString());
        return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("{id}") Long id){
        System.out.println("Deletando ID "+id);
        return Response.status(Response.Status.OK).build();
    }
    
    
    
    
    
    
    
    
    
    
    
}
