package webservices;

import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.NoteManager;
import bo.Note;

@Path("/notesBDD")
public class GestionNotesAvecBDD {
	private static NoteManager nm;
	
	static {
		nm = new NoteManager();
	}
	
	@GET
	public List<Note> getNotes() {
		return nm.getAll();
	}
	
	@POST
	public Note ajouterNote(@FormParam("content") String content) {
		Note note = new Note(new Date(), content);
		return nm.insert(note);
	}
	
	@PUT @Path("/{id : \\d+}")
	public Note modifierNote(@PathParam("id") int id,
								@FormParam("content") String content) {
		Note note = nm.getById(id);
		note.setContent(content);
		return nm.update(note);
	}
	
	@DELETE @Path("/{id : \\d+}")
	public boolean supprimerNote(@PathParam("id") int id) {
		return nm.delete(id);
	}
}
