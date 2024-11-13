package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.CommentRequestDto;
import hnqd.project.ApartmentManagement.entity.Comment;

import java.util.List;

public interface ICommentService {
    Comment createComment(CommentRequestDto commentRequest);
    List<Comment> getAllCommentsByPostId(int postId);
    Comment updateComment(int commentId, int userId, String content);
    void deleteComment(int commentId, int userId);
}
