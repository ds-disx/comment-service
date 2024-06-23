package org.disx;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Transactional
@Path("/comments")
public class CommentResource {

    @Inject
    CommentService commentService;

    @GET
    @Path("/hello")
    public String hello(){
        return "hello";
    }

    @GET
    public List<Comment> getComments(){
        return commentService.getComments();
    }

    @POST
    public Response saveComment(Comment comment){
        commentService.save(comment);
        return Response.ok().build();
    } 

    @GET
    @Path("/disx/{id}")
    public List<Comment> getCommentsByDisxId(@PathParam("id") Long disxId){
       return commentService.getCommentsByDisxId(disxId);
    }

    @DELETE
    @Path("/{id}")
    public void deleteComment(@PathParam("id") Long id){
        commentService.deleteComment(id);
    }
}
