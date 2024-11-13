package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.PostRequest;
import hnqd.project.ApartmentManagement.entity.Post;
import hnqd.project.ApartmentManagement.entity.Thread;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IPostService {
    Post createPost(PostRequest postRequest) throws IOException;
//    Thread update   Thread(int id, String title);
    void deletePost(int userId, int postId);
    Post likedPost(int userId, int postId);
    List<Post> getPosts(Map<String, String> params);
}
