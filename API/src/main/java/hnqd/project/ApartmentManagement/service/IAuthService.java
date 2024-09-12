package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.AuthRequest;
import hnqd.project.ApartmentManagement.dto.AuthResponse;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {
    AuthResponse authenticate(AuthRequest request, HttpServletResponse response)
            throws CommonException.NotFoundException, CommonException.WrongPasswordException;
}
