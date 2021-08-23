package dal;

import dal.jdbcimpl.NotesDaoJdbcImpl;

public abstract class DaoFactory {
	private static NotesDao notesDao;

	public static NotesDao getNotesDao() {
		if (notesDao == null) notesDao = new NotesDaoJdbcImpl();
		return notesDao;
	}
}
