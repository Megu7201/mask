package ntou.cs.hw4.mask.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pharmacy")
public class Pharmacy {
	private String id;
	private String name;
	private String address;
	private String phone;
	private int numberOfAdultMasks;
	private int numberOfChildrenMasks;
	private String updatedTime;
	private String note;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getNumberOfAdultMasks() {
		return numberOfAdultMasks;
	}

	public void setNumberOfAdultMasks(int numberOfAdultMasks) {
		this.numberOfAdultMasks = numberOfAdultMasks;
	}

	public int getNumberOfChildrenMasks() {
		return numberOfChildrenMasks;
	}

	public void setNumberOfChildrenMasks(int numberOfChildrenMasks) {
		this.numberOfChildrenMasks = numberOfChildrenMasks;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return "Pharmacy [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", numberOfAdultMasks=" + numberOfAdultMasks + ", numberOfChildrenMasks=" + numberOfChildrenMasks
				+ ", updatedTime=" + updatedTime + "]";
	}

}
