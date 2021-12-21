package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class SslServerController{
	@RequestMapping("/hash")
	public String myHash() throws NoSuchAlgorithmException{
		String data = "Hello World Check Sum!";
		String name = "Patrick Valencia";
		String[] splitname=name.split(" ");
		String firstname = splitname [0];
		String lastname = splitname[splitname.length - 1];
		name = firstname + " " + lastname;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] sha256 = md.digest(name.getBytes(StandardCharsets.UTF_8));
		return "data: " + data + "</br></br>" + "Name: " + name + "</br></br>" + "Name of Cipher Algorithm Used: CheckSum value: " + bytesToHex(sha256);
		
	}
	public String bytesToHex(byte[] sha256) {
		BigInteger hex = new BigInteger(1, sha256);
		StringBuilder checksum = new StringBuilder(hex.toString(16));
		
		while (checksum.length()<32) {
			checksum.insert(0, "0");
			}
		return checksum.toString();
	}
}