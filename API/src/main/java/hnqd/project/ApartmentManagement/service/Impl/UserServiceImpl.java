package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User createUser(User user) {
        user.setStatus("Active");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
