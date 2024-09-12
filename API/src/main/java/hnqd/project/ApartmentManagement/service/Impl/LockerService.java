package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.entity.Locker;
import hnqd.project.ApartmentManagement.repository.ILockerRepo;
import hnqd.project.ApartmentManagement.service.ILockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;

@Service
public class LockerService implements ILockerService {

    @Autowired
    private ILockerRepo lockerRepo;

    @Override
    public void createLocker() {
        Locker locker = new Locker();
        locker.setStatus("Blank");

        lockerRepo.save(locker);
    }

    @Override
    public void deleteLocker(int id) {
        lockerRepo.deleteById(id);
    }
}
