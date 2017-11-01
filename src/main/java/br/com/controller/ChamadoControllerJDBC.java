package br.com.controller;

import br.com.bean.Chamado;
import br.com.enumerado.Status;
import br.com.dao.ChamadoDaoJDBC;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("chamadosJDBC")
public class ChamadoControllerJDBC {

    /**
     * Lista os chamados do DB Caso aconteça algum erro será armazenado o log no
     * tomcat
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Chamado> listChamados() {
        try {
            ChamadoDaoJDBC daoJDBC = new ChamadoDaoJDBC();
            return daoJDBC.listar();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Chamado getChamdo(@PathParam("id") Long id) {
        try {
            ChamadoDaoJDBC daoJDBC = new ChamadoDaoJDBC();
            return daoJDBC.selecionar(id);
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(Chamado c) {
        try {
            c.setStatus(Status.NOVO);
            ChamadoDaoJDBC daoJDBC = new ChamadoDaoJDBC();
            daoJDBC.inserir(c);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(Chamado c) {
        try {
            c.setStatus(Status.PENDENTE);
            ChamadoDaoJDBC daoJDBC = new ChamadoDaoJDBC();
            daoJDBC.alterar(c);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("{id}") Long id) {
        try {
            ChamadoDaoJDBC daoJDBC = new ChamadoDaoJDBC();
            daoJDBC.excluir(id);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Response concluir(Chamado c) {
        try {
            c.setStatus(Status.FECHADO);
            ChamadoDaoJDBC daoJDBC = new ChamadoDaoJDBC();
            daoJDBC.alterar(c);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
