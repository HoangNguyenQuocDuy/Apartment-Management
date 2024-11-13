package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.ChatRoomRequestDto;
import hnqd.project.ApartmentManagement.dto.UserRequest;
import hnqd.project.ApartmentManagement.entity.Locker;
import hnqd.project.ApartmentManagement.entity.Room;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IChatRoomRepo;
import hnqd.project.ApartmentManagement.repository.ILockerRepo;
import hnqd.project.ApartmentManagement.repository.IRoomRepo;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IChatMessageService;
import hnqd.project.ApartmentManagement.service.IChatRoomService;
import hnqd.project.ApartmentManagement.service.IUserService;
import hnqd.project.ApartmentManagement.utils.EmailSender;
import hnqd.project.ApartmentManagement.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static SecureRandom random = new SecureRandom();
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
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private IChatRoomService chatRoomService;

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new CommonException.NotFoundException("User not found with username: " + username)
        );
    }

    @Override
    public User createUser(UserRequest userReq) {
        if (userRepo.existsByUsername(userReq.getUsername())) {
            throw new CommonException.DuplicationError("User with username has exists");
        }
        if (userRepo.existsByEmail(userReq.getEmail())) {
            throw new CommonException.DuplicationError("User with email has exists");
        }

        User user = new User();
        String password = generateRandomString(6);

        user.setStatus("New");
        user.setPhone(userReq.getPhone());
        user.setEmail(userReq.getEmail());
        user.setFirstname(userReq.getFirstname());
        user.setLastname(userReq.getLastname());
        user.setUsername(userReq.getUsername());
        user.setRoleName("ROLE_RESIDENT");
        user.setPassword(passwordEncoder.encode(password));


        if (userReq.getLockerId() != null) {
            Locker locker = lockerRepo.findById(userReq.getLockerId()).orElseThrow(
                    () -> new CommonException.NotFoundException("Locker not found with ID: " + userReq.getLockerId())
            );

            user.setLocker(locker);
            locker.setStatus("Using");
            lockerRepo.save(locker);
        }

        if (userReq.getRoomId() != 0) {
            Room room = roomRepo.findById(userReq.getRoomId()).orElseThrow(
                    () -> new CommonException.NotFoundException("Room not found with ID: " + userReq.getRoomId())
            );

            user.setRoom(room);
            room.setStatus("Rented");
            roomRepo.save(room);
        }

        int userId = userRepo.saveAndFlush(user).getId();

        String subject = "DV APARTMENT - REGISTRATION";
        String message = "Your account:\n - username: " + user.getUsername()
                + "\n - password: " + password
                + "\nPlease log in and change your password";

        emailSender.sendEmail(user.getEmail(), subject, message);

        return userRepo.findById(userId).get();
    }

    @Override
    public Page<User> getUsers(Map<String, String> params) {
        int page = Integer.parseInt(params.get("page"));
        int size = Integer.parseInt(params.get("size"));
        Pageable pageable = PageRequest.of(page, size);

        if (params.get("roleName") != null && !params.get("roleName").isEmpty()) {
            return userRepo.findAllByRoleName(params.get("roleName"), pageable);
        }

        return null;
    }

    @Override
    public User updateUser(Integer userId, MultipartFile file, Map<String, String> params) throws IOException {
        String fN = params.getOrDefault("firstname", "");
        String lN = params.getOrDefault("lastname", "");
        String phone = params.getOrDefault("phone", "");
        String email = params.getOrDefault("email", "");
        String status = params.getOrDefault("status", "");

        UserRequest userReq = UserRequest.builder()
                .file(file)
                .lockerId(Integer.valueOf(params.getOrDefault("lockerId", "0")))
                .password(params.get("password"))
                .roomId(Integer.valueOf(params.getOrDefault("roomId", "0")))
                .build();

        if (!Objects.equals(fN, "")) {
            userReq.setFirstname(fN);
        }
        if (!Objects.equals(lN, "")) {
            userReq.setLastname(lN);
        }
        if (!Objects.equals(phone, "")) {
            userReq.setPhone(phone);
        }
        if (!Objects.equals(status, "")) {
            userReq.setStatus(status);
        }

        User storedUser = userRepo.findById(userId).orElseThrow(
                () -> new CommonException.NotFoundException("User not found with ID: " + userId)
        );

        if (userReq.getPassword() != null && !userReq.getPassword().isEmpty()) {
            String storedPassword = storedUser.getPassword();

            User user = userRepo.findByRoleName("ROLE_ADMIN");

            ChatRoomRequestDto chatRoomRequestDto = new ChatRoomRequestDto();
            Set<Integer> userIds = new HashSet<>();
            userIds.add(user.getId());
            userIds.add(userId);
            chatRoomRequestDto.setUsersIds(userIds);

            chatRoomService.createRoom(chatRoomRequestDto);

            if (!passwordEncoder.matches(storedPassword, userReq.getPassword())) {
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
            locker.setStatus("Using");
            lockerRepo.save(locker);
        }

        if (userReq.getRoomId() != 0) {
            Room room = roomRepo.findById(userReq.getRoomId()).orElseThrow(
                    () -> new CommonException.NotFoundException("Room not found with ID: " + userReq.getRoomId())
            );

            storedUser.setRoom(room);
            room.setStatus("Using");
            roomRepo.save(room);
        }

        if (userReq.getFile() != null && !userReq.getFile().isEmpty()) {
            storedUser.setAvatar(uploadImage.uploadToCloudinary(userReq.getFile()));
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

    @Override
    public void deleteUserById(int id) {
        User storedUser = userRepo.findById(id).orElseThrow(
                () -> new CommonException.NotFoundException("User not found with ID: " + id)
        );

        storedUser.getLikedPosts().clear();
        userRepo.delete(storedUser);
    }

    @Override
    public void forgotPassword(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(
                () -> new CommonException.NotFoundException("User not found with email: " + email)
        );

        if (user == null) {
            throw new RuntimeException("User with Email: " + email + " not found!");
        }
        String verificationCode = generateVerificationCode();
        user.setResetPasswordCode(verificationCode);
        userRepo.save(user);
        emailSender.sendEmail(
                email,
                "YOUR VERIFICATION CODE FROM QD APARTMENT",
                "Your verification code is: " + verificationCode
        );
    }

    @Override
    public void resetPassword(String email, String verificationCode, String newPassword) {
        User user = userRepo.findByEmail(email).orElseThrow(
                () -> new CommonException.NotFoundException("User not found with email: " + email)
        );

        if (user == null) {
            throw new RuntimeException("User with Email: " + email + " not found!");
        }

        if (!user.getResetPasswordCode().equals(verificationCode)) {
            throw new IllegalArgumentException("Invalid verification code");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordCode(null);
        userRepo.save(user);
    }


    private String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            randomString.append(CHARACTERS.charAt(randomIndex));
        }

        return randomString.toString();
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}
