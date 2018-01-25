/**
 * 
 */
package com.ragas.security;

import java.util.Scanner;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Chandra Jagarlamudi
 *
 */
public class SpringAuthentication {

	private static AuthenticationManager authenticationManager = new RagasAuthenticationManagerImpl();

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Username: ");
		String name = sc.nextLine();
		System.out.println("Please enter Password: ");
		String password = sc.nextLine();

		Authentication request = new UsernamePasswordAuthenticationToken(name, password);
		Authentication result = authenticationManager.authenticate(request);
		SecurityContextHolder.getContext().setAuthentication(result);

		System.out.println("Successfully authenticated. Security context contains: "
				+ SecurityContextHolder.getContext().getAuthentication());
	}

}
