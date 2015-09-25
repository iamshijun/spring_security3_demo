package spring_security3_chapter4;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;

public class BasicAuthenticationTest {

	@Test
	public void testBasicAuthentication() throws UnsupportedEncodingException {

		String header = "Basic dm9yZGVsOnZvcmRlbA==";
		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException(
					"Failed to decode basic authentication token");
		}

		String token = new String(decoded, /* getCredentialsCharset(request) */
				"UTF-8");

		int delim = token.indexOf(":");

		System.out.println(Arrays.toString(new String[] {
				token.substring(0, delim), token.substring(delim + 1) }));
	}
}
