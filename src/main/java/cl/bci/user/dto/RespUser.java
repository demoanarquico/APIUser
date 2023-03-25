package cl.bci.user.dto;

public class RespUser {
	
	private int id;
	private String created;
	private String modified;
	private String last_login;
	private String token;
	private boolean isactive;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	@Override
	public String toString() {
		return "RespUser [id=" + id + ", created=" + created + ", modified=" + modified + ", last_login=" + last_login
				+ ", token=" + token + ", isactive=" + isactive + "]";
	}

}
