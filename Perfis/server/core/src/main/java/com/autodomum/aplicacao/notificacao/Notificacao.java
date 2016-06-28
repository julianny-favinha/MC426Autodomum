package com.autodomum.aplicacao.notificacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class Notificacao {

	public static void sendNotificaco(String text){
		
		FileInputStream file;
		try {
			file = new FileInputStream(new File(Thread.currentThread().getContextClassLoader().getResource("Certificados.p12").getFile()));
			ApnsService service =
				    APNS.newService()
				    .withCert(file, "Simbolinha")
				    .withSandboxDestination()
				    .build();
			String payload = APNS.newPayload().alertBody(text).build();
			String token = "29369190c3d6d1faea803323cf1359a12564f2ef45994fb69dee6f4128b0de07";
			service.push(token, payload);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
