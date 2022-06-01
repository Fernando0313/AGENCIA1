package idat.com.configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class encriptacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin2"));
	}

}
