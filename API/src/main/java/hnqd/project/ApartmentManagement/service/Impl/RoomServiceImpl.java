package hnqd.project.ApartmentManagement.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import hnqd.project.ApartmentManagement.entity.Room;
import hnqd.project.ApartmentManagement.repository.IRoomRepo;
import hnqd.project.ApartmentManagement.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RoomServiceImpl implements IRoomService {
    @Autowired
    private IRoomRepo roomRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Room createRoom(Room room) {
        if (room.getFile() != null) {
            try {
                Map res = cloudinary.uploader().upload(room.getFile().getBytes(), ObjectUtils.asMap("folder", "Apartment Management"));
                room.setImage(res.get("secure_url").toString());
            } catch (IOException e) {
                Logger.getLogger(RoomServiceImpl.class.getName()).log(Level.SEVERE, null, e);
                throw new RuntimeException(e);
            }
        }

        room.setStatus("Available");
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
