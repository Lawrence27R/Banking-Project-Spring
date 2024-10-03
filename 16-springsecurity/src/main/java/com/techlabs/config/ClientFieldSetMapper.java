//package com.techlabs.config;
//
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.validation.BindException;
//
//import com.techlabs.entity.Client;
//import com.techlabs.entity.ClientStatus;
//
//public class ClientFieldSetMapper implements FieldSetMapper<Client> {
//
//    @Override
//    public Client mapFieldSet(FieldSet fieldSet) throws BindException {
//        Client client = new Client();
//        // Reading and setting client fields from the CSV or file
//        client.setRegistrationNumber(fieldSet.readLong("registrationNumber"));
//        client.setFirstname(fieldSet.readString("firstname"));
//        client.setLastname(fieldSet.readString("lastname"));
//        client.setKycStatus(ClientStatus.valueOf(fieldSet.readString("kycStatus"))); // Enum for status
////        client.setEmail(fieldSet.readString("email"));
//        client.setState(fieldSet.readString("state"));
//        client.setCity(fieldSet.readString("city"));
//        client.setAccountNumber(fieldSet.readLong("accountNumber"));
////        client.setUsername(fieldSet.readString("username"));
////        client.setPassword(fieldSet.readString("password"));
//        // Assuming that Bank, Beneficiary, and SuperAdmin relations are not handled here
//        return client;
//    }
//}
