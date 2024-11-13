package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.entity.Thread;

import java.util.List;
import java.util.Map;

public interface IThreadService {
    Thread createThread(int userId, String title);
    Thread updateThread(int userId, int id, String title);
    void deleteThread(int userId, int threadId);
    List<Thread> getThreads(Map<String, String> params);
}
