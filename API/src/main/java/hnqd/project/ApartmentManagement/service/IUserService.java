package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> getUserByUsername(String username);
    User createUser(User user);
    List<User> getUsers();
    User updateUser(User user);
}
