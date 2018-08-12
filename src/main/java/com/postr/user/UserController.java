package com.postr.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.postr.posts.PostModel;
import com.postr.posts.PostRepository;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	PostRepository postDao;
	
	@GetMapping("/post")
	public List<PostModel> getPosts() {
		List<PostModel> foundPosts = postDao.findAll();
		return foundPosts;
	}
	
	@GetMapping("/post/{id}")
	public ResponseEntity<PostModel> getPost(@PathVariable(value="id") Integer id){
		PostModel foundPost = postDao.findById(id).orElse(null);
		
		if(foundPost == null) {
            return ResponseEntity.notFound().header("PostModel","Nothing found with that id").build();
        }
        return ResponseEntity.ok(foundPost);
	}
	
	
	@PostMapping("/user")
	public ResponseEntity<PostModel> postPost(@RequestBody PostModel postModel){
		PostModel createdPost = postDao.save(postModel);
		
		return ResponseEntity.ok(createdPost);
	}
	
	@PostMapping("/register")
	public ModelAndView registerAccount(@ModelAttribute("user") UserDto accountDto, BindingResult result, WebRequest request, Errors errors) {
		UserModel registered = new UserModel();
			registered = createUserAccount(accountDto, result);
			return new ModelAndView("successRegister", "user", accountDto);	
	}
	
	private UserModel createUserAccount(UserDto accountDto, BindingResult result) {
		UserModel registered = null;
		registered = userService.registerNewUserAccount(accountDto);
		return registered;
	}
}
