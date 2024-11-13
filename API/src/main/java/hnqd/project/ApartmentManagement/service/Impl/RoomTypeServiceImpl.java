package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.entity.RoomType;
import hnqd.project.ApartmentManagement.repository.IRoomTypeRepo;
import hnqd.project.ApartmentManagement.service.IRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements IRoomTypeService {
    @Autowired
    private IRoomTypeRepo roomTypeRepo;


    @Override
    public List<RoomType> getRoomTypes() {
        return roomTypeRepo.findAll();
    }
}
