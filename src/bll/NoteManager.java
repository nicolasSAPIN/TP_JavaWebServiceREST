package bll;

import java.util.List;

import bo.Note;
import dal.DaoFactory;
import dal.NotesDao;

public class NoteManager {
	private NotesDao dao;
	
	public NoteManager() {
		dao = DaoFactory.getNotesDao();
	}
	
	public List<Note> getAll() {
		return dao.getAll();
	}
	
	public Note getById(int id) {
		return dao.getById(id);
	}

	public Note insert(Note note) {
		return dao.insert(note);
	}
	
	public Note update(Note note) {
		return dao.update(note);
	}
	
	public boolean delete(int id) {
		return dao.delete(id);
	}
}
