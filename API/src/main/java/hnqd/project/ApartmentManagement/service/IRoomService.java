package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.RoomRequest;
import hnqd.project.ApartmentManagement.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRoomService {
    Room createRoom(RoomRequest room) throws IOException;
    List<Room> getRooms(Map<String, String> params);
    Optional<Room> getRoomById(Integer id);
    Page<Room> getRoomsPaging(Map<String, String> params);
    Room updateRoom(RoomRequest roomReq, int roomId) throws IOException;
    void deleteRoom(Integer id);
}
