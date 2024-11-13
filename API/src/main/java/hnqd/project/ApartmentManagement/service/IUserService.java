package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.UserRequest;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserService {
    User getUserByUsername(String username);
    User createUser(UserRequest userReq) throws IOException;
    Page<User> getUsers(Map<String, String> params);
    User updateUser(Integer userId, MultipartFile file, Map<String, String> userRequest) throws IOException;
    User getUserById(int id) throws CommonException.NotFoundException;
    void deleteUserById(int id);
    void forgotPassword(String email);
    void resetPassword(String email, String verificationCode, String newPassword);
}
