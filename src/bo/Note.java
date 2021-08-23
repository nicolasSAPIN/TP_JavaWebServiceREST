package bo;

import java.util.Date;

public class Note {
	private int id;
	private Date date;
	private String content;
	
	public Note(int id, Date date, String content) {
		this.id = id;
		this.date = date;
		this.content = content;
	}

	public Note(Date date, String content) {
		this.date = date;
		this.content = content;
	}
	
	public Note() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
