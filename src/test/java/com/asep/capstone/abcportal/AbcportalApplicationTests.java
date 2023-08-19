package com.asep.capstone.abcportal;

import com.asep.capstone.abcportal.dto.JobPostDto;
import com.asep.capstone.abcportal.entity.*;
import com.asep.capstone.abcportal.repositories.*;
import com.asep.capstone.abcportal.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class AbcportalApplicationTests {


	@Mock
	private PostRepository postRepository;

	@Mock
	private PostLikesRepository postLikesRepository;

	@Mock
	private PostCommentRepository postCommentRepository;

	@Mock
	private JobPostRepository jobPostRepository;

	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private ApplicationAppRepository appRepository;



	@InjectMocks
	private PostServices postServices;

	@InjectMocks
	private JobAndCompanyService jobAndCompanyService;

	@InjectMocks
	private ApplicationService applicationService;

	@InjectMocks
	private PostCommentServices commentServices;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSavePost() {
		UserApp userApp = new UserApp();
		Post post = new Post();
		post.setDescription("test post");

		when(postRepository.save(post)).thenReturn(post);

		Post savedPost = postServices.savePost(post, userApp);

		Mockito.verify(postRepository, times(1)).save(post);

		assertNotNull(savedPost);
		assertNotNull(savedPost.getDatetime());
		assertEquals(userApp, savedPost.getUserApp());
	}


	@Test
	void testLikePost_AddLike() {
		Long postId = 123L;
		UserApp user = new UserApp();
		Post post = new Post();
		post.setPostId(postId);

		when(postRepository.findById(postId)).thenReturn(Optional.of(post));
		when(postLikesRepository.findPostLikesByPostAndUser(postId, user.getUserId())).thenReturn(null);

		String result = postServices.likePost(postId, user, null);

		Mockito.verify(postLikesRepository, times(1)).save(any(PostLikes.class));
		Mockito.verify(postLikesRepository, times(0)).delete(any(PostLikes.class));

		assertEquals("Like Increase", result);
	}

	@Test
	void testLikePost_RemoveLike() {
		Long postId = 123L;
		UserApp user = new UserApp();
		Post post = new Post();
		post.setPostId(postId);

		when(postRepository.findById(postId)).thenReturn(Optional.of(post));
		when(postLikesRepository.findPostLikesByPostAndUser(postId, user.getUserId())).thenReturn(new PostLikes());

		String result = postServices.likePost(postId, user, new PostLikes());

		Mockito.verify(postLikesRepository, times(0)).save(any(PostLikes.class));
		Mockito.verify(postLikesRepository, times(1)).delete(any(PostLikes.class));

		assertEquals("like Decrease", result);
	}



	@Test
	void testSaveComment() {

		String commentMessage = "Test comment";
		Long postId = 123L;

		Post post = new Post();
		post.setPostId(postId);

		Comment comment = new Comment();
		comment.setComment(commentMessage);
		comment.setPost(post);

		UserApp userApp = new UserApp();
		when(postRepository.findById(postId)).thenReturn(Optional.of(post));
		Comment commentResult = commentServices.saveComment(postId, commentMessage, userApp);
		Mockito.verify(postCommentRepository, times(1)).save(any(Comment.class));

	}


	@Test
	void testPostJob() {
		String companyId = "1";
		String jobTitle = "Software Engineer";
		String jobDesc = "Job description here";
		String jobType = "FULL_TIME"; // Valid job type
		JobPostDto jobPostDto = new JobPostDto();
		jobPostDto.setCompanyId(companyId);
		jobPostDto.setJobTitle(jobTitle);
		jobPostDto.setJobDesc(jobDesc);
		jobPostDto.setJobType(jobType);

		Company company = new Company();
		when(companyRepository.findById(Long.parseLong(companyId))).thenReturn(Optional.of(company));
		jobAndCompanyService.postJob(jobPostDto);

		Mockito.verify(jobPostRepository, times(1)).save(any(JobPost.class));
	}


	@Test
	void testSaveApplication() {
		String porto = "http://example.com/portfolio";
		String jobId = "1";
		UserApp currentUser = new UserApp();
		JobPost jobPost = new JobPost();
		jobPost.setJobPostId(Long.parseLong(jobId));

		when(jobPostRepository.findById(Long.parseLong(jobId))).thenReturn(Optional.of(jobPost));
		applicationService.saveApplication(porto, jobPost, currentUser);

		Mockito.verify(appRepository, times(1)).save(any(Application.class));
	}






}
