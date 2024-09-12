package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.UserRequest;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserService {
    Optional<User> getUserByUsername(String username);
    User createUser(User user) throws IOException;
    List<User> getUsers();
    User updateUser(MultipartFile file, Map<String, String> userRequest) throws IOException;
    User getUserById(int id) throws CommonException.NotFoundException;
}
