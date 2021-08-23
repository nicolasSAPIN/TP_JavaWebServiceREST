package dal.jdbcimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bo.Note;
import dal.NotesDao;

public class NotesDaoJdbcImpl implements NotesDao {
	private DataSource dataSource;
	
	public NotesDaoJdbcImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public List<Note> getAll() {
		List<Note> result = new ArrayList<Note>();
		try (Connection cnx = dataSource.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM notes ORDER BY heure ASC;");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Note note = new Note(rs.getInt("id"), rs.getDate("heure"), rs.getString("content"));
				result.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public Note getById(int id) {
		Note result = null;
		try (Connection cnx = dataSource.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM notes WHERE id=?;");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new Note(rs.getInt("id"), rs.getDate("heure"), rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Note insert(Note note) {
		try (Connection cnx = dataSource.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("INSERT INTO notes(heure, content) values (?,?);", Statement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, new Date(note.getDate().getTime()));
			pstmt.setString(2, note.getContent());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				note.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return note;
	}

	@Override
	public Note update(Note note) {
		try (Connection cnx = dataSource.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("UPDATE notes SET content=? WHERE id=?;");
			pstmt.setString(1, note.getContent());
			pstmt.setInt(2, note.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return note;
	}

	@Override
	public boolean delete(int id) {
		int result = 0;
		try (Connection cnx = dataSource.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM notes WHERE id=?;");
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result > 0;
	}
}