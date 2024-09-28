package com.CestaSolidaria.domain.pix.dto;

import java.util.Base64;

public record DataQr(String qrCodeImage) {
	
    public DataQr(byte[] b) {
        this(Base64.getEncoder().encodeToString(b));
    }
}
