package com.imarques.abelibrary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.imarques.abelibrary.model.Comment;
import com.imarques.abelibrary.util.DataContainer;

@Service
public class CommentService {
	private Long commentId= Long.valueOf(1);
	
	private static List<Comment> comments = new ArrayList<Comment>();
	
	public DataContainer<Comment> find(List<Long> ids, int limit, int offset) {
		List<Comment> commentsTemp = comments.stream().filter(comment -> ids.contains(comment.getId())).collect(Collectors.toList());
		DataContainer<Comment> result = new DataContainer<Comment>(limit, offset, comments.size(), commentsTemp.subList(offset, (offset+limit <= commentsTemp.size() ? offset+limit : commentsTemp.size())));
		return result;
	}
	
	public Comment getComment(Long id) {
		for (Comment comment: comments) {
			if (comment.getId().equals(id)) {
				return comment;
			}
		}
		return null;
	}
	
	public Comment save(Long isbn, String text) {
		Comment comment = new Comment();
		comment.setId(commentId++);
		comment.setIsbn(isbn);
		comment.setText(text);
		comments.add(comment);
		return comment;
	}
}
