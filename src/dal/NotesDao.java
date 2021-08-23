package dal;

import java.util.List;

import bo.Note;

public interface NotesDao {

	List<Note> getAll();

	Note insert(Note note);
	
	Note update(Note note);
	
	boolean delete(int id);

	Note getById(int id);
}
