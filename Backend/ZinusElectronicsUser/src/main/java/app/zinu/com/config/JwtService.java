package app.zinu.com.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	//Encryption key generator : allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx
	private static final String SECRET_KEY = "576D5A7134743777217A24432646294A404E635266556A586E3272357538782F";
	
	//4
	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	//3
	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	//5
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	
	//5
	public String generateToken(
			Map<String, Object> extractClaims,
			UserDetails userDetails
			){
		
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();

	}
	
	//6
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	//7
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	//8
	private Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}

	//1
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	//2
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
