package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.RoomRequest;
import hnqd.project.ApartmentManagement.entity.Room;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IRoomRepo;
import hnqd.project.ApartmentManagement.repository.IRoomTypeRepo;
import hnqd.project.ApartmentManagement.service.IRoomService;
import hnqd.project.ApartmentManagement.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements IRoomService {
    @Autowired
    private IRoomRepo roomRepo;
    @Autowired
    private UploadImage uploadImage;

    @Autowired
    private IRoomTypeRepo roomTypeRepo;

    @Override
    public Room createRoom(RoomRequest roomReq) throws IOException {
        Room room = new Room();

        if (roomReq.getFile() != null) {
            room.setImage(uploadImage.uploadToCloudinary(roomReq.getFile()));
        }

        room.setStatus("Available");
        room.setName(roomReq.getName());
        room.setRoomType(roomTypeRepo.findById(roomReq.getRoomTypeId())
                .orElseThrow(() -> new CommonException.NotFoundException("Room type not found")));

        return roomRepo.save(room);
    }

    @Override
    public List<Room> getRooms() {
        return roomRepo.findAll();
    }

    @Override
    public Optional<Room> getRoomById(Integer id) {
        return roomRepo.findById(id);
    }
}
