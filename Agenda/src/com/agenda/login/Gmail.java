package com.agenda.login;

import javax.mail.Authenticator; 
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("rldndvxt1122@gmail.com", "qkr941122!");
	}
}