package cz.spsmb.rest;

import cz.spsmb.dao.AnimalRepository;
import cz.spsmb.dao.CarRepository;
import cz.spsmb.model.Animal;
import cz.spsmb.model.Car;
import cz.spsmb.model.Person;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import cz.spsmb.dao.PersonRepository;

import java.util.List;

@Path("/animals")
public class AnimalResource {

    @Inject
    AnimalRepository animalRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Animal> animals = animalRepository.listAll();
        return Response.ok().entity(animals).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Animal animal = animalRepository.findById(id);
        return Response.ok().entity(animal).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        animalRepository.deleteById(id);
        return Response.ok().entity("ok").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Animal animal) {
        animal.setId(0l);
        if (animal.getName() != null || animal.getKind() != null) {
            animalRepository.persist(animal);
            return Response.ok().entity("ok").build();
        }
            return Response.status(400).entity("Animal must have attributes \"name\" and \"kind\".").build();


    }
}
