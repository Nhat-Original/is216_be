package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.github.nhatoriginal.spring.repository.UserRepository;

@Service
public class JwtService {
  @Value("${JWT_SECRET}")
  private String secretKey;
  @Value("${JWT_EXPIRATION}")
  private long jwtExpiration;
  private final UserRepository userRepository;

  public JwtService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String extractUsername(String token) {
    String id = extractClaim(token, Claims::getSubject);
    return Objects.requireNonNull(userRepository.findById(UUID.fromString(id)).orElse(null)).getEmail();
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    String roles = userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.joining(","));
    claims.put("role", roles);
    return generateToken(claims, userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails) {
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  private String buildToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails,
      long expiration) {
    User user = userRepository.findByEmail(userDetails.getUsername());
    return Jwts.builder().claims(extraClaims)
        .subject(String.valueOf(user.getId())).issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(expiration * 1000 + System.currentTimeMillis())).signWith(getSignInKey()).compact();

  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    try {
      return Jwts
          .parser()
          .verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse JWT token", e);
    }
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}