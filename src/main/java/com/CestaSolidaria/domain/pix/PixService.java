package com.CestaSolidaria.domain.pix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.pix.dto.DataPix;
import com.CestaSolidaria.domain.pix.dto.DataQr;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class PixService {
	
	private static final String CHAVE_PIX = "suachave@pix.com";
	
	public DataQr gerarQR(DataPix data) {
		
        String emvCode = gerarCodigoEMVPix(CHAVE_PIX, data.valor());

        byte[] qrCodeImage = gerarImagemQRCode(emvCode);
        
		return new DataQr(qrCodeImage);
	}
	
    private String gerarCodigoEMVPix(String chavePix, String valor) {
        String emvCode = "00020126360014BR.GOV.BCB.PIX0114" + chavePix + "520400005303986";
        if (valor != null && !valor.isEmpty()) {
            emvCode += "5406" + valor;
        }
        return emvCode.replaceAll("\\s+", "");
    }

    private byte[] gerarImagemQRCode(String emvCode) {
        int width = 300;
        int height = 300;
        BitMatrix bitMatrix = null;
		try {
			bitMatrix = new QRCodeWriter().encode(emvCode, BarcodeFormat.QR_CODE, width, height);
		} catch (WriterException e) {
			e.printStackTrace();
		}

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

        return outputStream.toByteArray();
    }


}
