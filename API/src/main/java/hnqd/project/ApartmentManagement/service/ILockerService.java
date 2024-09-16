package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.Locker;

import java.util.List;
import java.util.Map;

public interface ILockerService {
    Locker createLocker();
    Locker updateLocker(int lockerId, Map<String,String> body);
    void deleteLocker(int id);
    List<Locker> getLockers(Map<String, String> params);
    Locker getLockerById(int id);
}
