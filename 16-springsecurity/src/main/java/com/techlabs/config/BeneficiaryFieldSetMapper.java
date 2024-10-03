package com.techlabs.config;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.techlabs.entity.Beneficiary;

public class BeneficiaryFieldSetMapper implements FieldSetMapper<Beneficiary> {

    @Override
    public Beneficiary mapFieldSet(FieldSet fieldSet) {
        Beneficiary beneficiaries = new Beneficiary();
        beneficiaries.setBeneficiaryId(fieldSet.readInt("beneficiaryId"));
        beneficiaries.setBeneficiaryName(fieldSet.readString("beneficiaryName"));
        beneficiaries.setBeneficiaryAccountNumber(fieldSet.readLong("beneficiaryAccountNumber"));
        beneficiaries.setBeneficiaryIfsc(fieldSet.readString("beneficiaryIfsc"));
        beneficiaries.setBeneficiaryAmount(fieldSet.readDouble("beneficiaryAmount"));
        
        // Client field should be handled separately if needed since it's a relationship to another entity
        return beneficiaries;
    }
}
