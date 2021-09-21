package com.example.clip.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import com.example.clip.entity.Users;
import com.example.clip.service.TransactionService;
import com.example.clip.vo.DisbursementVO;
import com.example.clip.vo.ReportVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	@MockBean
    private TransactionService transactionService;

	
	@Autowired
	private MockMvc mockMvc;


	@Test
	public void getUsersPymentTest() throws Exception {
		 final FileInputStream fileInputStream = new FileInputStream(Paths.get("src/test/resources/usersPyment.json").toFile());
	     final String staticResponse = StreamUtils.copyToString(fileInputStream, Charset.defaultCharset());

        List<Users> usersVO =  mapper.readValue(staticResponse, new TypeReference<List<Users>>(){});
        doReturn(usersVO).when(transactionService).getUsers();
		this.mockMvc.perform(get("/api/clip/users/payment").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getDisbursementTest() throws Exception {
		 final FileInputStream fileInputStream = new FileInputStream(Paths.get("src/test/resources/disbursement.json").toFile());
	     final String staticResponse = StreamUtils.copyToString(fileInputStream, Charset.defaultCharset());
		
        List<DisbursementVO> disbursementVO =  mapper.readValue(staticResponse, new TypeReference<List<DisbursementVO>>(){});
        doReturn(disbursementVO).when(transactionService).disbursementProcess();
		this.mockMvc.perform(post("/api/clip/disbursement").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void getReportTest() throws Exception {
		final FileInputStream fileInputStream = new FileInputStream(Paths.get("src/test/resources/report.json").toFile());
	    final String staticResponse = StreamUtils.copyToString(fileInputStream, Charset.defaultCharset());

	    ReportVO reportVO =  mapper.readValue(staticResponse, ReportVO.class);
        doReturn(reportVO).when(transactionService).getReportByIdUser(1L);
		this.mockMvc.perform(post("/api/clip/disbursement").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}
	
	
}
