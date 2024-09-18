package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.UserRequest;
import hnqd.project.ApartmentManagement.entity.Locker;
import hnqd.project.ApartmentManagement.entity.Room;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.ILockerRepo;
import hnqd.project.ApartmentManagement.repository.IRoomRepo;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IUserService;
import hnqd.project.ApartmentManagement.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ILockerRepo lockerRepo;
    @Autowired
    private IRoomRepo roomRepo;
    @Autowired
    private UploadImage uploadImage;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User createUser(User user) throws IOException {
        if (user.getId() == null) {
            user.setStatus("New");
            user.setRoleName("ROLE_RESIDENT");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getFile() != null && !user.getFile().isEmpty()) {
            user.setAvatar(uploadImage.uploadToCloudinary(user.getFile()));
        }

        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User updateUser(Integer userId, MultipartFile file, Map<String, String> params) throws IOException {

        UserRequest userReq = UserRequest.builder()
                .userId(userId)
                .lastName(params.get("lastName"))
                .firstName(params.get("firstName"))
                .phone(params.get("phone"))
                .status(params.get("status"))
                .file(file)
                .lockerId(Integer.valueOf(params.getOrDefault("lockerId", "0")))
                .password(params.get("password"))
                .roomId(Integer.valueOf(params.getOrDefault("roomId", "0")))
                .build();

        User storedUser = userRepo.findById(userReq.getUserId()).orElseThrow(
                () -> new CommonException.NotFoundException("User not found with ID: " + userReq.getUserId().toString())
        );

        if (userReq.getPassword() != null && !userReq.getPassword().isEmpty()) {
            String storedPassword = storedUser.getPassword();

            if (!userReq.getPassword().equals(storedPassword)) {
                storedUser.setPassword(passwordEncoder.encode(userReq.getPassword()));
            } else {
                throw new CommonException.DuplicatePasswordException("Duplicate password!");
            }
        }

        if (userReq.getLockerId() != 0) {
            Locker locker = lockerRepo.findById(userReq.getLockerId()).orElseThrow(
                    () -> new CommonException.NotFoundException("Locker not found with ID: " + userReq.getLockerId())
            );

            storedUser.setLocker(locker);
            locker.setStatus("Used");
            lockerRepo.save(locker);
        }

        if (userReq.getRoomId() != 0) {
            Room room = roomRepo.findById(userReq.getRoomId()).orElseThrow(
                    () -> new CommonException.NotFoundException("Room not found with ID: " + userReq.getRoomId())
            );

            storedUser.setRoom(room);
            room.setStatus("Used");
            roomRepo.save(room);
        }

        if (userReq.getFile() != null && !userReq.getFile().isEmpty()) {
            storedUser.setAvatar(uploadImage.uploadToCloudinary(userReq.getFile()));
        }

        if (userReq.getFirstName() != null && !userReq.getFirstName().isEmpty()) {
            storedUser.setUsername(userReq.getFirstName());
        }
        if (userReq.getLastName() != null && !userReq.getLastName().isEmpty()) {
            storedUser.setLastname(userReq.getLastName());
        }
        if (userReq.getPhone() != null && !userReq.getPhone().isEmpty()) {
            storedUser.setPhone(userReq.getPhone());
        }
        if (userReq.getStatus() != null && !userReq.getStatus().isEmpty()) {
            storedUser.setStatus(userReq.getStatus());
        }

        return userRepo.save(storedUser);
    }

    @Override
    public User getUserById(int id) throws CommonException.NotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new CommonException.NotFoundException("User not found with ID: " + id));
    }
}
