package fr.epita.quiz.datamodel;

public class MCQQuestion {
	
	private Long id;  //id is the foreign key
	private String question;
	private String[] topics;
	private Integer difficulty;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String[] getTopics() {
		return topics;
	}
	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	
}
