package com.techlabs.dto;

import com.techlabs.entity.ClientStatus;

public class ClientStatusUpdateDto {
    private ClientStatus status;

    // Getters and Setters
    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }
}

