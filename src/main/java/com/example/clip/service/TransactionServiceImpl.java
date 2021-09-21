package com.example.clip.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.clip.entity.Disbursement;
import com.example.clip.entity.Payment;
import com.example.clip.entity.Users;
import com.example.clip.exception.NotDataFoundException;
import com.example.clip.repository.DisbursementRepository;
import com.example.clip.repository.PaymentRepository;
import com.example.clip.repository.UsersRepository;
import com.example.clip.request.PaymentRequest;
import com.example.clip.utils.Constants;
import com.example.clip.vo.DisbursementVO;
import com.example.clip.vo.ReportVO;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private DisbursementRepository disbursementRepository;

	@Autowired
	private MessageSource messageSource;
	
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	@Override
	public <T> T create(PaymentRequest paymentRequest) {
		
		Users users=usersRepository.findById(paymentRequest.getUserId()).get();		
		if(users.getId()==null) {
			throw new NotDataFoundException(messageSource.getMessage("sin.informacion", null, Locale.getDefault()));
		}

		Payment payment = new Payment();
		payment.setAmount(paymentRequest.getAmount());
		payment.setStatus(Constants.STATUS_NEW);
		payment.setLTransaction(new Date());
		payment.setUsers(users);
		paymentRepository.save(payment);
		
		return (T) messageSource.getMessage("create.save", new String[] {users.getName()}, Locale.getDefault());

	}

	@Override
	public List<Users> getUsers() {
		List<Users> users = usersRepository.findAll();
		if(users.isEmpty()) {
			throw new NotDataFoundException(messageSource.getMessage("sin.informacion", null, Locale.getDefault()));			
		}
		return users;
	}
	
	@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	@Override
	public List<DisbursementVO> disbursementProcess() {
		List<Payment> payment = paymentRepository.findByStatus(Constants.STATUS_NEW);
		if(payment.isEmpty()) {
			throw new NotDataFoundException(messageSource.getMessage("sin.informacion", null, Locale.getDefault()));				
		}
		List<DisbursementVO> response = new ArrayList<>();
		BigDecimal disbursmentFee = new BigDecimal(3.5);
		payment.forEach(p -> {
			Disbursement disbursement = new Disbursement();
			disbursement.setAmount(p.getAmount().subtract(disbursmentFee));						
			disbursement.setUserId(p.getUsers().getId());
			disbursement = disbursementRepository.save(disbursement);
			disbursement.setLastTransaction(new Date());
			p.setStatus(Constants.STATUS_PROCESSED);
			response.add(new DisbursementVO(disbursement.getAmount(),
					p.getUsers().getName().concat(" ").concat(p.getUsers().getFirstName()),
					disbursement.getLastTransaction()));
			paymentRepository.save(p);
			
		});
		return response;

	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public ReportVO getReportByIdUser(Long id) {
		ReportVO reportVO = new ReportVO();
		Users users=usersRepository.findById(id).get();		
		if(users.getId()==null) {
			throw new NotDataFoundException(messageSource.getMessage("sin.informacion", null, Locale.getDefault()));				
		}				
		reportVO.setIdUsuario(users.getId());
		reportVO.setName(users.getName());
		reportVO.setPayments_sum((int) users.getPayment().stream().count());
		reportVO.setNewPayments(users.getPayment().stream().filter(p -> p.getStatus().equals(Constants.STATUS_NEW)).count());
		reportVO.setSumAmount(users.getPayment().stream().filter(p -> p.getStatus().equals(Constants.STATUS_NEW)).map(p -> p.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));
		return reportVO;
	}
}
