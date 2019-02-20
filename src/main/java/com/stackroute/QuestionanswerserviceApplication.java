package com.stackroute;

import com.stackroute.domain.Question;
import com.stackroute.domain.User;
import com.stackroute.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class QuestionanswerserviceApplication implements CommandLineRunner {
	@Autowired
	QuestionRepository questionRepository;
	public static void main(String[] args) {
		SpringApplication.run(QuestionanswerserviceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		List<Question> questions = readQuestionsFromCSV("Questions.txt");
		for (Question q : questions) {
			System.out.println(questionRepository.save(q));
		}
	}
	public static List<Question> readQuestionsFromCSV(String fileName){
		List<Question> questions = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources
		try (BufferedReader br = Files.newBufferedReader(pathToFile,
				StandardCharsets.US_ASCII)) {

			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");

				Question question = createQuestion(attributes);

				// adding book into ArrayList
				questions.add(question);

				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
			}

		} catch (IOException ioe) {
			System.out.println(ioe);
		}

		return questions;
	}

	private static Question createQuestion(String[] metadata) throws NullPointerException{
		String question = metadata[0];
		List<String> topics;
		if(metadata[1].contains("$")){
		String topic[]=metadata[1].split("$");
		topics = Arrays.asList(topic);
		}
		else {
			topics = new ArrayList<>();
			topics.add(metadata[1]);
		}
		long timestamp = Long.parseLong(metadata[2]);
		User user = new User(metadata[3],metadata[4],metadata[5]);
//		int price = Integer.parseInt(metadata[1]);
//		String author = metadata[2];
//    public Question(final String question, final String description, final List<String> topics, final int upvotes, final long timestamp, final int downvotes, final User user, final List<Comment> comment, final List<Answer> answer) {

		// create and return question of this metadata
		return new Question(question, null,topics,0,timestamp,0,user,null,null);
	}


}
