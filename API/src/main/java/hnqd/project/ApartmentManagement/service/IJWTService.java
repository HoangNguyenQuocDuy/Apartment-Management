package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.User;

public interface IJWTService {
    String generateToken(User user);
}
