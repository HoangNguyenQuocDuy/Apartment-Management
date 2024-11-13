package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.PostRequest;
import hnqd.project.ApartmentManagement.entity.Post;
import hnqd.project.ApartmentManagement.entity.Thread;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.IPostRepo;
import hnqd.project.ApartmentManagement.repository.IThreadRepo;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IPostService;
import hnqd.project.ApartmentManagement.utils.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private IPostRepo postRepo;
    @Autowired
    private IThreadRepo threadRepo;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private UploadImage uploadImage;

    @Override
    public Post createPost(PostRequest postRequest) throws IOException {
        User user = userRepo.findById(postRequest.getUserId()).orElseThrow(
                () -> new CommonException.NotFoundException("User not found")
        );
        Thread thread = threadRepo.findById(postRequest.getThreadId()).orElseThrow(
                () -> new CommonException.NotFoundException("Thread not found")
        );

        Post post = new Post();
        post.setUser(user);
        post.setThread(thread);
        post.setContent(post.getContent());
        post.setLikes(0);
        post.setPostImageUrls(uploadImage.UploadMultipleImageToCloudinary(postRequest.getFiles()));
        post.setCreatedAt(new Timestamp(new Date().getTime()));

        return postRepo.save(post);
    }

    @Override
    public void deletePost(int userId, int postId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new CommonException.NotFoundException("User not found")
        );

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new CommonException.NotFoundException("Post not found")
        );

        if (!Objects.equals(post.getUser().getId(), user.getId())) {
            throw new CommonException.PermissionDenied("You dont have permission to execute this operation");
        }

        postRepo.delete(post);
    }

    @Override
    public Post likedPost(int userId, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new CommonException.NotFoundException("Post not found")
        );
        User user = userRepo.findById(userId).orElseThrow(
                () -> new CommonException.NotFoundException("User not found")
        );

        Set<User> likeByUsers = post.getLikeByUsers();
        Set<Post> likePosts = user.getLikedPosts();

        if (post.getLikeByUsers().contains(user)) {
            likeByUsers.remove(user);
            likePosts.remove(post);
        } else {
            likeByUsers.add(user);
            likePosts.add(post);
        }
        post.setLikes(likeByUsers.size());
        post.setLikeByUsers(likeByUsers);

        user.setLikedPosts(likePosts);
        userRepo.save(user);
        
        return postRepo.save(post);
    }

    @Override
    public List<Post> getPosts(Map<String, String> params) {
        if (params.get("threadId") != null && !params.get("threadId").isEmpty()) {
            return postRepo.findAllByThreadId(Integer.parseInt(params.get("threadId")));
        }

        return postRepo.findAll();
    }


}
