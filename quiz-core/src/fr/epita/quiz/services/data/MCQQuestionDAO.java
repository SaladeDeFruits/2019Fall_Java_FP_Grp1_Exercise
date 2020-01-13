package fr.epita.quiz.services.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.epita.quiz.datamodel.MCQQuestion;

public class MCQQuestionDAO {
	private static final String TOPIC_DELIMITER = "|";
	private static final String DELIMITER = ";";
	private static final String filename = "DataRecords.txt";
	private File file = new File(filename);
	
	public void create(MCQQuestion question) {
		String formatted = String.valueOf(question.getId())+DELIMITER; //String.valueOf(), this method 
		formatted += " "+ String.valueOf(question.getQuestion())+DELIMITER;  //if the text of questions contains the sparser semicolon?
		formatted += " "+ String.valueOf(question.getDifficulty())+DELIMITER+" ";
		String[] topics = question.getTopics();
		for(int i = 0; i < topics.length; i++) {
			formatted += topics[i] + TOPIC_DELIMITER;
		}
		//TODO complete by appending the formatted line in file
		try {
		    Files.write(Paths.get(filename), formatted.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
			System.out.println("Nonexistence file.");
		}
		
	}
	
	public List<MCQQuestion> readAll(){
		List<MCQQuestion> results = new ArrayList<>();
		//TODO complete by reading all the lines from a file
		//clue, using scanner to read line
		//while there is something to read in the file;
		if(file.exists()){
			Scanner input0;
			try {
				input0 = new Scanner(file);
				while(input0.hasNextLine()) {
					String formatted = input0.nextLine();  //TODO this is the current read line
					String[] parts = formatted.split(DELIMITER);
					System.out.println(parts[2]);
					Long id = Long.valueOf(parts[0]); // Long.valueOf() could transfer string into Long type
					String readQuestion = parts[1];
					Integer difficulty = Integer.valueOf(parts[2]);
					String rawTopics = parts[3];
					String[] subparts = rawTopics.split("\\"+TOPIC_DELIMITER); //String.split(reg,limit), double back-slash will keep the mark
				
					MCQQuestion readInstance = new MCQQuestion();
					readInstance.setId(id);
					readInstance.setQuestion(readQuestion);
					readInstance.setDifficulty(difficulty);
					readInstance.setTopics(subparts);
					
					results.add(readInstance);
				}
				input0.close();	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return results;
		
		
		
	}
	
	public MCQQuestion getById(Long id) {
		//TODO complete
		List<MCQQuestion> mcqQuestionList = readAll();
		for(MCQQuestion mcqQuestion1:mcqQuestionList) {
			if(mcqQuestion1.getId().equals(id)) return mcqQuestion1;
		}
		return null;
	}

}
