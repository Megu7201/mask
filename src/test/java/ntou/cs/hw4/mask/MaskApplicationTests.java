package ntou.cs.hw4.mask;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ntou.cs.hw4.mask.controller.PharmacyController;
import org.springframework.beans.factory.annotation.Autowired;
import ntou.cs.hw4.mask.entity.Pharmacy;
import ntou.cs.hw4.mask.model.MaskHandler;
import org.springframework.ui.Model;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import org.json.simple.JSONObject;
@SpringBootTest
@AutoConfigureMockMvc
class MaskApplicationTests {

	@Autowired
	PharmacyController controller;

	@Autowired
 	private MockMvc mockMvc;

	@Test
	public void contextLoads() {		
		String res = controller.test();
		assertEquals("test",res);
	}

	@Test
	public void indexTest() throws Exception{
		mockMvc.perform(get("/pharmacy")).andExpect(status().isOk());
		// test 2 
		// mockMvc.perform(get("/pharmacy")).andExpect(status().isCreated());
	} 

	@Test
	public void postNoteTest() throws Exception{
		JSONObject request = new JSONObject();
		request.put("pharmacyId","2101010013");
		request.put("note","asdslajkd");
		mockMvc.perform(post("/pharmacy/2101010013/note")
						.content(request.toString())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isCreated());
		// mockMvc.perform(post("/pharmacy/2101010013/note")
		// 				.content(request.toString())
		// 				.contentType(MediaType.APPLICATION_JSON)
		// 				.accept(MediaType.APPLICATION_JSON))
		// 				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void putNoteTest() throws Exception{
		JSONObject request = new JSONObject();
		request.put("pharmacyId","2101010013");
		request.put("note","aaa");
		mockMvc.perform(put("/pharmacy/2101010013/note")
						.content(request.toString())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

	@Test
	public void deleteNoteTest() throws Exception{
		mockMvc.perform(delete("/pharmacy/2101010013/note")
						.content("{pharmacyId: \"2101020013\"")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

}
