package com.example.clip.service;

import java.util.List;

import com.example.clip.entity.Users;
import com.example.clip.request.PaymentRequest;
import com.example.clip.vo.DisbursementVO;
import com.example.clip.vo.ReportVO;

public interface TransactionService {
	
	public <T> T create(PaymentRequest paymentRequest);

	public List<Users> getUsers();

	public List<DisbursementVO> disbursementProcess();
	
	public ReportVO getReportByIdUser(Long id);
}
