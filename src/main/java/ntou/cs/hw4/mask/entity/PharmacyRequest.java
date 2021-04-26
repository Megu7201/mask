package ntou.cs.hw4.mask.entity;

import javax.validation.constraints.NotEmpty;

public class PharmacyRequest {
	
	@NotEmpty(message = "isn't provided.")
	private String pharmacyId;
	private String note;	
	
	
	public String getPharmacyId() {
		return pharmacyId;
	}
	
	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

}
