package fr.insa.server.config.AffectationManager;

import org.springframework.http.MediaType;

import jakarta.websocket.server.PathParam;



@Path("comparator")
class AffectationManagerApplicationTests {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "degemer mat deoc'h";
	}

    @GET
    @Path("longueur/{chaine}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getLongueur(@PathParam("chaine") String chaine) {
        return chaine.length();
    }

    @GET
    @Path("longueurDouble")
    @Produces(MediaType.TEXT_PLAIN)
    public int getLongueurDouble(@QueryParam("chaine") String chaine) {
        return chaine.length()*2;
    }

    @PUT
    @Path("/{idEtudiant}")
    @Produces(MediaType.TEXT_PLAIN)
    public int updateEtudiant(@PathParam("idEtudiant") int id) {
        System.out.println("mise à jour cool man !!!");
        return id;
    }

}

