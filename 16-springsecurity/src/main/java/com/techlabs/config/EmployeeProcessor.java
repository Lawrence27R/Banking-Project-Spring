package com.techlabs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.techlabs.entity.EmployeeTransaction;

public class EmployeeProcessor implements ItemProcessor<EmployeeTransaction, EmployeeTransaction> {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeProcessor.class);

    @Override
    public EmployeeTransaction process(EmployeeTransaction item) throws Exception {
        logger.info("Processing item: {}", item);
        return item;
    }
}
