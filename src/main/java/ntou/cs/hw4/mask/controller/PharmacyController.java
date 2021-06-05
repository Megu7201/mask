package ntou.cs.hw4.mask.controller;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ntou.cs.hw4.mask.entity.Pharmacy;
import ntou.cs.hw4.mask.entity.Note;
import ntou.cs.hw4.mask.entity.PharmacyRequest;
import ntou.cs.hw4.mask.service.PharmacyService;


@Controller
public class PharmacyController {
	
	@Autowired
	PharmacyService pharmacyService;
	

	@PostMapping("/SecutiryTest")
	public void SecutiryTest(@RequestBody Note note){
		
	}


	@GetMapping("/test")
	public String test(){
		return "test";
	}

	@GetMapping("pharmacy")
	public String getAllPharmacy(@RequestParam(required = false) String pharmacyName,@RequestParam(required = false) String zone, Model model){
		
		List<Pharmacy> pharmacy;
		if (pharmacyName == null) pharmacyName = "";
		if (zone == null) zone = "";
		pharmacyName = pharmacyName.trim();
		zone = zone.trim();
		pharmacy = pharmacyService.getPharmacies(pharmacyName,zone);
		model.addAttribute("pharmacy",pharmacy);
		return "pharmacy";
	}
	
	@GetMapping("pharmacy/{id}")
	public ResponseEntity<Pharmacy> getPharmacy(@PathVariable("id") String id){
		Pharmacy match = pharmacyService.getPharmacy(id);
		if (match != null)
			return ResponseEntity.ok().body(match);
		else
			return ResponseEntity.notFound().build();	
	}
	
	@PostMapping("pharmacy/{id}/note")
	public ResponseEntity<String> addNote(@Valid @RequestBody PharmacyRequest request){
		Pharmacy pharmacy = pharmacyService.createNote(request);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/pharmacy/{id}")
                .buildAndExpand(pharmacy.getId())
                .toUri();
		return ResponseEntity.created(location).body(request.getNote());
	}
	
	@PutMapping("pharmacy/{id}/note")
	public ResponseEntity<String> modifyNote(@Valid @RequestBody PharmacyRequest request){
		Boolean changed = pharmacyService.modifyNote(request);
		if(changed)
			return ResponseEntity.ok().body("Success");
		else
			return ResponseEntity.badRequest().body("Error");
	}
	
	@DeleteMapping("pharmacy/{id}/note")
	public ResponseEntity<String> deleteNote(@PathVariable("id") String id){
		pharmacyService.deleteNote(id);
		return ResponseEntity.ok().build();
		
	}
	
//	private List<Product> productDB = new ArrayList<>();
//	
//	@GetMapping("/")
//	public ResponseEntity<List<Product>> getAllProducts(Model model){
//		return ResponseEntity.ok().body(productDB);
//	}
//	
//	@PostMapping("/products")
//	public ResponseEntity<String> createProduct(@RequestBody Product request){
//		Product product = new Product();
//		product.setId(request.getId());
//		product.setName(request.getName());
//		product.setPrice(request.getPrice());
//		productDB.add(product);
//		return ResponseEntity.ok().body("{}");
//	}
//	
//	@GetMapping("/products/{id}")
//	public ResponseEntity<Product> getProduct(@PathVariable("id") String id){
//		Product matchedProduct = null;
//		
//		for ( Product p: productDB) {
//			if (p.getId().equals(id)) {
//				matchedProduct = p;
//			}
//		}
//		
//		if (matchedProduct != null)
//			return ResponseEntity.ok().body(matchedProduct);
//		else
//			return ResponseEntity.notFound().build();
//	}
}
