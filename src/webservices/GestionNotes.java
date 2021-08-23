package webservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bo.Note;

@Path("/notes")
public class GestionNotes {
	private static List<Note> notes = new ArrayList<Note>();
	
	static {
		notes.add(new Note(1, new Date(), "Une premiere note"));
		notes.add(new Note(2, new Date(), "Une deuxieme note"));
	}
	
	@GET
	public List<Note> getNotes() {
		return notes;
	}
	
	@GET @Path("/{id : \\d+}")
	public Note getNote(@PathParam("id") int id) {
		Note noteARetourner = null;
		for(Note current : notes) {
			if (current.getId() == id) {
				noteARetourner = current;
				break;
			}
		}
		return noteARetourner;
	}
	
	@POST
	public Note ajouterNote(@FormParam("content") String content) {
		Note note = new Note(notes.size() + 1, new Date(), content);
		notes.add(note);
		return note;
	}
	
	@PUT @Path("/{id : \\d+}")
	public Note modifierNote(@PathParam("id") int id,
								@FormParam("content") String content) {
		Note noteAModifier = null;
		for (Note current : notes) {
			if (current.getId() == id) {
				current.setContent(content);
				noteAModifier = current;
				break;
			}
		}
		return noteAModifier;
	}
	
	@DELETE @Path("/{id : \\d+}")
	public boolean supprimerNote(@PathParam("id") int id) {
		Note noteASupprimer = null;
		for (Note current : notes) {
			if (current.getId() == id) {
				noteASupprimer = current;
				break;
			}
		}
		return notes.remove(noteASupprimer);
	}
}
