package com.mvcoder.security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvcoder.bean.User;
import com.mvcoder.utils.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class TokenManager {
	
	private static final String KEY = "pcchecker";

    private  Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public  String createJWT(User user, long ttlMillions){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		//密钥
		byte[] keyBytes = DatatypeConverter.parseBase64Binary(KEY);

		//通过密钥和签名算法 - 指定加密方式
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, signatureAlgorithm.getJcaName());
		
		long nowTime = System.currentTimeMillis();
		
		TokenBody body = new TokenBody();
		body.setIat(nowTime);;
		if(ttlMillions > 0){
			body.setExp(nowTime + ttlMillions);
		}
		body.setId(user.getUserId());
		body.setUsername(user.getUsername());
		
		JwtBuilder builder = Jwts.builder().setSubject(gson.toJson(body))
				.signWith(signatureAlgorithm, keySpec);
		return builder.compact();
		
	}
	
	public  String createJWT(int id ,String username,long ttlMillions){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		byte[] keyBytes = DatatypeConverter.parseBase64Binary(KEY);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, signatureAlgorithm.getJcaName());
		
		long nowTime = System.currentTimeMillis();
		
		TokenBody body = new TokenBody();
		body.setIat(nowTime);;
		if(ttlMillions > 0){
			body.setExp(nowTime + ttlMillions);
		}
		body.setId(id);
		body.setUsername(username);
		JwtBuilder builder = Jwts.builder().setSubject(gson.toJson(body))
				.signWith(signatureAlgorithm, keySpec);
		return builder.compact();
		
	}
	
	public  TokenBody decryptJWT(String token){
		Claims claims = null;
		try{
			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY))
					.parseClaimsJws(token).getBody();
		}catch(Exception e){
			return null;
		}
		String bodyStr = claims.getSubject();
		
		TokenBody body = gson.fromJson(bodyStr, TokenBody.class);
		return body;
	}
	
	
	//�����������Ƿǳ�����ȫ�ģ�
	// Ӧ�ý��з�����У�飬���磬�����ǵ��û���Ϣ���豸��Ϣ������url���м��μ��ܴ���
	//��token��������������֤�����url���豸��Ϣ֮�����ȷ�ԣ����������ͨ������������֤ʧ�ܡ�
	//����Token��У��Ӧ���Ƕ� HttpRequest�ģ������ǽ������Token���н��ܡ�
	
	public static void main(String[] args) {
		TokenManager m = new TokenManager();
        User user = new User();
        user.setUserId(100);
        user.setUsername("admin");
		long now = System.currentTimeMillis();
		String token = m.createJWT(user, Constants.TOKEN_TTL);
		System.out.println("token : " + token);
		System.out.println("Token create time ： "+ (System.currentTimeMillis() - now));

		long now1 = System.currentTimeMillis();
		TokenBody body = m.decryptJWT(token);
		System.out.println("after : "+(System.currentTimeMillis() - now1));

		if(body != null){
			System.out.println("id : " + body.getId() + " , username : " + body.getUsername());
			System.out.println("expire Time : " + body.getExp());
		}else{
			System.out.println("������");
		}
		
	}

}
