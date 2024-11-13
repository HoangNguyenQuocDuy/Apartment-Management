package hnqd.project.ApartmentManagement.service.Impl;

import hnqd.project.ApartmentManagement.dto.CommentRequestDto;
import hnqd.project.ApartmentManagement.entity.Comment;
import hnqd.project.ApartmentManagement.entity.Post;
import hnqd.project.ApartmentManagement.entity.User;
import hnqd.project.ApartmentManagement.exceptions.CommonException;
import hnqd.project.ApartmentManagement.repository.ICommentRepo;
import hnqd.project.ApartmentManagement.repository.IPostRepo;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepo commentRepo;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IPostRepo postRepo;

    @Override
    public Comment createComment(CommentRequestDto commentRequest) {
        User user = userRepo.findById(commentRequest.getUserId()).orElseThrow(
                () -> new CommonException.NotFoundException("User Not Found")
        );
        Post post = postRepo.findById(commentRequest.getPostId()).orElseThrow(
                () -> new CommonException.NotFoundException("Post Not Found")
        );

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedDate(new Timestamp(new Date().getTime()));
        comment.setContent(commentRequest.getContent());

        return commentRepo.save(comment);
    }

    @Override
    public List<Comment> getAllCommentsByPostId(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new CommonException.NotFoundException("Post Not Found")
        );

        return commentRepo.findAllByPostId(postId);
    }

    @Override
    public Comment updateComment(int commentId, int userId, String content) {
        Comment comment = checkOwnComment(commentId, userId);

        comment.setContent(content);

        return commentRepo.save(comment);
    }

    @Override
    public void deleteComment(int commentId, int userId) {
        Comment comment = checkOwnComment(commentId, userId);
        commentRepo.delete(comment);
    }

    private Comment checkOwnComment(int commentId, int userId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(
                () -> new CommonException.NotFoundException("Comment Not Found")
        );
        User user = userRepo.findById(userId).orElseThrow(
                () -> new CommonException.NotFoundException("User Not Found")
        );

        if (user.getId() != comment.getUser().getId()) {
            throw new CommonException.PermissionDenied("Permission denied");
        }

        return comment;
    }
}
