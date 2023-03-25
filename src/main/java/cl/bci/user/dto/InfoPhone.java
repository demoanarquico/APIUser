package cl.bci.user.dto;

public class InfoPhone {
	
	private int id;
	private String number;
	private String citycode;
	private String contrycode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getContrycode() {
		return contrycode;
	}
	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}
	
	@Override
	public String toString() {
		return "InfoPhone [id=" + id + ", number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode
				+ "]";
	}

}
