package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomService {
    Room createRoom(Room room);
    List<Room> getRooms();
    Optional<Room> getRoomById(Integer id);
}
