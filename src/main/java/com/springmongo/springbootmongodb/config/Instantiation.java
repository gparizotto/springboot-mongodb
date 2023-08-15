package com.springmongo.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springmongo.springbootmongodb.domain.Post;
import com.springmongo.springbootmongodb.domain.User;
import com.springmongo.springbootmongodb.dto.AuthorDTO;
import com.springmongo.springbootmongodb.dto.CommentDTO;
import com.springmongo.springbootmongodb.repository.PostRepository;
import com.springmongo.springbootmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("15/07/2023"), "Partiu viagem", "Vou viajar para a Europa!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("16/10/2023"), "Hoje é meu aniversário", "Vai ter festa", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem maria!", sdf.parse("15/07/2023"),new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite maria!", sdf.parse("15/07/2023"),new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Feliz anoversario maria!", sdf.parse("16/10/2023"),new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
	
}
