package cl.bci.user.dto;

import java.util.List;

public class RespInfoUser  {

	private int id;
	private String name;
	private String email;
	private String password;
	private boolean isactive;
	private List<InfoPhone> phones;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public List<InfoPhone> getPhones() {
		return phones;
	}

	public void setPhones(List<InfoPhone> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "RespInfoUser [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", isactive=" + isactive + ", phones=" + phones + "]";
	}

}
