package com.techlabs.service;

import com.techlabs.dto.BankDto;
import com.techlabs.entity.Bank;

public interface BankService {
	Bank addBank(BankDto bankDto);
}
