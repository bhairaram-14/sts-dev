/*
 * package com.spring.springjwt.controller;
 * 
 * import java.net.URI; import java.util.List;
 * 
 * import javax.validation.Valid;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.spring.springjwt.entity.User; import
 * com.spring.springjwt.repository.UserRepo;
 * 
 * @RestController
 * 
 * @RequestMapping("/user") public class Usercontroller {
 * 
 * @Autowired UserRepo userRepo;
 * 
 * @GetMapping public List<User> fetch() { return userRepo.findAll(); }
 * 
 * @PostMapping public ResponseEntity<User> create(@RequestBody @Valid User
 * user) { user=userRepo.save(user); URI
 * useruri=URI.create("/user/"+user.getId()); return
 * ResponseEntity.created(useruri).body(user); }
 * 
 * 
 * }
 */