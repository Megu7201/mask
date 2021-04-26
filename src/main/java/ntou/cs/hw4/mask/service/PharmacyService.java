package ntou.cs.hw4.mask.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntou.cs.hw4.mask.entity.Note;
import ntou.cs.hw4.mask.entity.Pharmacy;
import ntou.cs.hw4.mask.entity.PharmacyRequest;
import ntou.cs.hw4.mask.model.MaskHandler;
import ntou.cs.hw4.mask.repository.NoteRepository;

@Service
public class PharmacyService {
	
	
	@Autowired
	private MaskHandler handler;
	@Autowired
	private NoteRepository repository;
	
	public PharmacyService(MaskHandler handler) {
		try {
			handler.initialize();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		this.handler = handler;
		
	}
	
	public Pharmacy getPharmacy(String id) {
		Pharmacy match = null;
		List<Pharmacy> pharmacy = new ArrayList<>();
		pharmacy = handler.findPharmacies("", "");
		for (Pharmacy element:pharmacy) {
			if(element.getId().equals(id)) {
				match = element;
				List<Note> note = repository.findAll();
				for(Note noteElement:note) {
					if(match.getId().equals(noteElement.getId())) {
						match.setNote(noteElement.getNote());
						break;
					}
					else {
						match.setNote(null);
					}
				}
				break;
			}
		}
		return match;
	}
	
	
	public List<Pharmacy> getPharmacies(String pharmacyName,String zone) {
		List<Note> note = repository.findAll();
		List<Pharmacy> pharmacy = this.handler.findPharmacies(pharmacyName, zone);
		int flag;
		for (Pharmacy pharmacyElement:pharmacy) {
			flag = 0;
			for(Note noteElement:note) {
				if(pharmacyElement.getId().equals(noteElement.getPharmacyId())) {
					pharmacyElement.setNote(noteElement.getNote());
					flag = 1;
					break;
				}
			}
			if (flag==0)
				pharmacyElement.setNote(null);
			
		}
		return pharmacy;
		
	}
	
	public Pharmacy createNote(PharmacyRequest request){
		
		Pharmacy match = null;
		List<Pharmacy> pharmacy = handler.findPharmacies("", "");
		for (Pharmacy element:pharmacy) {
			if(element.getId().equals(request.getPharmacyId())) {
				match = element;
				break;
			}
		}
		Note note = new Note();
		note.setId(request.getPharmacyId());
		note.setPharmacyId(request.getPharmacyId());
		note.setNote(request.getNote());
		match.setNote(request.getNote());
		repository.insert(note);
		return match;
	}
	
	public Boolean modifyNote(PharmacyRequest request) {
		List<Note> note = repository.findAll();
		for (Note element:note) {
			if(element.getPharmacyId().equals(request.getPharmacyId())) {
				Note temp = new Note();
				temp.setId(element.getId());
				temp.setNote(request.getNote());
				temp.setPharmacyId(element.getPharmacyId());
				repository.save(temp);
				return true;
			}
		}	
		return false;
	}
	
	public void deleteNote(String id) {
		List<Note> note = repository.findAll();
		for (Note element:note) {
			if(element.getId().equals(id)) {
				element.setNote(null);
			}
		}
		repository.deleteById(id);
		
	}
	
}
