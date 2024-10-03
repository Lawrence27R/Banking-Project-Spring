package com.techlabs.service;

import org.springframework.stereotype.Service;

import com.techlabs.REPO.BankRepository;
import com.techlabs.REPO.ClientRepository;
import com.techlabs.dto.BankDto;
import com.techlabs.entity.Bank;
import com.techlabs.entity.Client;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {

	private final BankRepository bankRepository;
	private final ClientRepository clientRepository;
	@Transactional
	@Override
	public Bank addBank(BankDto bankDto) {
		Client client = clientRepository.findById(bankDto.getClientRegistrationNumber())
				.orElseThrow(() -> new RuntimeException(
						"Client not found with registration number: " + bankDto.getClientRegistrationNumber()));

		Bank bank = new Bank();
		bank.setBankName(bankDto.getBankName());
		bank.setIfscCode(bankDto.getIfscCode());
		bank.setClient(client); // Set the client relationship

		return bankRepository.save(bank);
	}
}
