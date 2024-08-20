package hnqd.project.ApartmentManagement.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.service.IJWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTServiceImpl implements IJWTService {

    @Value("${secret.key}")
    private String secretKey;

    @Override
    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 50*60*1000))
                .sign(Algorithm.HMAC256(secretKey.getBytes()));
    }
}
