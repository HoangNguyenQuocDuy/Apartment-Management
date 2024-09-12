package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.AuthRequest;
import hnqd.project.ApartmentManagement.dto.AuthResponse;
import hnqd.project.ApartmentManagement.dto.UserResponse;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IAuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTServiceImpl jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse authenticate(AuthRequest request, HttpServletResponse response) throws CommonException.NotFoundException
            , CommonException.WrongPasswordException {
            User user = userRepo.findByUsername(request.getUsername()).orElseThrow(()-> new CommonException.NotFoundException("User not found!"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new CommonException.WrongPasswordException("Invalid password!");
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String jwtAccessToken = jwtService.generateToken(user);

            response.setHeader("Authentication", "Bearer " + jwtAccessToken);

            return AuthResponse.builder()
                    .accessToken(jwtAccessToken)
                    .userResponse(
                            UserResponse.builder()
                                    .username(user.getUsername())
                                    .email(user.getEmail())
                                    .phone(user.getPhone())
                                    .firstName(user.getFirstname())
                                    .lastName(user.getLastname())
                                    .status(user.getStatus())
                                    .roleName(user.getRoleName())
                                    .resetPasswordCode(user.getResetPasswordCode())
                                    .build()
                    )
                    .build();
    }
}
