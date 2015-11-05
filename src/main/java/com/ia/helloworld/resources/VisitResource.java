package com.ia.helloworld.resources;

import com.ia.helloworld.api.Visit;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/visit")
@Produces(MediaType.APPLICATION_JSON)
public class VisitResource {

    private final AtomicLong counter;

    public VisitResource() {

        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Visit visit(@QueryParam("id") Optional<String> id) {
        return new Visit(counter.incrementAndGet(), "Babagannoosh");
    }

    @POST
    public void insert() {

    }
}