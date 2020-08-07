package models;

public class MovieModel {
	private String Title;
	private String Year;
	private String imdbID;
	private String Released;
	
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getRelased() {
		return Released;
	}
	public void setRelased(String relased) {
		Released = relased;
	}
	public String getImdbID() {
		return imdbID;
	}
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
}
