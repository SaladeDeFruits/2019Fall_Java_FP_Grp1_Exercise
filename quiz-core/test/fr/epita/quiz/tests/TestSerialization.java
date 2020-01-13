package fr.epita.quiz.tests;

import java.util.Arrays;

import fr.epita.quiz.datamodel.MCQQuestion;


public class TestSerialization {

	private static final String TOPIC_DELIMITER = "|";
	private static final String DELIMITER = ";";
	
//	private static final String DELIMITER = "@@@@";	

	public static void main(String[] args) {
		MCQQuestion question = new MCQQuestion();
		question.setDifficulty(3);    //3 is a value in the middle
		question.setTopics(new String[] {"java","uml","oop"});
		question.setQuestion("What is the key word to define inheritance between 2 classes in java?");
		question.setId(1l); // 1l is convert integer 1 to long format

		//we want to format it to:
		//[write the field here] id; question; difficulty; topic1|topic2|topic3|...|topicn
		
		//formatting phase:
		String formatted = String.valueOf(question.getId())+DELIMITER; //String.valueOf(), this method 
		formatted += String.valueOf(question.getQuestion())+DELIMITER;  //if the text of questions contains the sparser semicolon?
		formatted += String.valueOf(question.getDifficulty())+DELIMITER+" ";
		String[] topics = question.getTopics();
		for(int i = 0; i < topics.length; i++) {
			formatted += topics[i] + TOPIC_DELIMITER;
		}
		//formatted = formatted.substring(0, formatted.length()-1);
		System.out.println(formatted);
		//Output result:
		//1; What is the key word to define inheritance between 2 classes in java?; 3; java|uml|oop
		
		//reconstruct phase
		String[] parts = formatted.split(DELIMITER);
		System.out.println(parts[2]);
		Long id = Long.valueOf(parts[0]); // Long.valueOf() could transfer string into Long
		String readQuestion = parts[1];
		Integer difficulty = Integer.valueOf(parts[2]);
		String rawTopics = parts[3];
		String[] subparts = rawTopics.split("\\"+TOPIC_DELIMITER); //String.split(reg,limit), double back-slash will keep the mark
		
		//String[] parts = formatted.split("|"); //String.split(reg,limit)
		
		//System.out.println(Arrays.asList(parts));
		//System.out.println("\\"); // output result  \
		
		MCQQuestion readInstance = new MCQQuestion();
		readInstance.setId(id);
		readInstance.setQuestion(readQuestion);
		readInstance.setDifficulty(difficulty);
		readInstance.setTopics(subparts);
		
	}

}
