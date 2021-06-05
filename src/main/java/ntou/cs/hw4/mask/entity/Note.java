package ntou.cs.hw4.mask.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "note")
public class Note {
	private String id;
	private String pharmacyId;
	private String note;
	// test 1  
	public String getId() {
		return id;
	}
	public Note(){
	}
	public Note(String note){
	}

	public void setId(String id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Note [id=" + id + ", pharmacyId=" + pharmacyId + ", note=" + note + "]";
	}

}
