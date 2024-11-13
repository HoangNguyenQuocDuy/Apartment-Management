package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.entity.Thread;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IThreadRepo;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ThreadServiceImpl implements IThreadService {
    @Autowired
    private IThreadRepo threadRepo;
    @Autowired
    private IUserRepo userRepo;

    @Override
    public Thread createThread(int userId, String title) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new CommonException.NotFoundException("User not found")
        );

        Thread thread = new Thread();
        thread.setTitle(title);
        thread.setUser(user);
        thread.setCreatedAt(new Timestamp(new Date().getTime()));

        return threadRepo.save(thread);
    }

    @Override
    public Thread updateThread(int userId, int id, String title) {
        Thread thread = threadRepo.findById(id).orElseThrow(
                () -> new CommonException.NotFoundException("Thread not found")
        );

        checkPermission(userId, thread);

        thread.setTitle(title);
        thread.setUpdatedAt(new Timestamp(new Date().getTime()));

        return threadRepo.save(thread);
    }

    @Override
    public void deleteThread(int userId, int threadId) {
        Thread thread = threadRepo.findById(threadId).orElseThrow(
                () -> new CommonException.NotFoundException("Thread not found")
        );

        checkPermission(userId, thread);

        threadRepo.delete(thread);
    }

    @Override
    public List<Thread> getThreads(Map<String, String> params) {
        return threadRepo.findAll();
    }

    private void checkPermission(int userId, Thread thread) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new CommonException.NotFoundException("user not found")
        );

        if (!Objects.equals(thread.getUser().getId(), user.getId())) {
            throw new CommonException.PermissionDenied("You dont have permission to execute this operation");
        }
    }
}
