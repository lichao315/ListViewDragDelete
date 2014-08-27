package com.example.listviewswipedelete;

import java.io.Serializable;
import java.util.ArrayList;

public class BlogItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1000L;
	private Integer id;
	private String quizzer;
	private String quizzerPortrait;
	private String grade;
	private String lesson;
	private String classes;
	private String askDate;
	private String title;
	private String imgUrl;
	private String question;
	private ArrayList<Answer> answerList;

	public static class Answer implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1001L;

		public Answer() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Answer(String answerer, String answererPortrait, String answerDate, String answer) {
			super();
			this.answerer = answerer;
			this.answererPortrait = answererPortrait;
			this.answerDate = answerDate;
			this.answer = answer;
		}

		private String answerer;
		private String answererPortrait;
		private String answerDate;
		private String answer;

		public String getAnswerer() {
			return answerer;
		}

		public void setAnswerer(String answerer) {
			this.answerer = answerer;
		}

		public String getAnswererPortrait() {
			return answererPortrait;
		}

		public void setAnswererPortrait(String answererPortrait) {
			this.answererPortrait = answererPortrait;
		}

		public String getAnswerDate() {
			return answerDate;
		}

		public void setAnswerDate(String answerDate) {
			this.answerDate = answerDate;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "Answer [answerer=" + answerer + ", answererPortrait=" + answererPortrait + ", answerDate=" + answerDate + ", answer=" + answer + "]";
		}

	}

	public BlogItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlogItem(Integer id, String quizzer, String quizzerPortrait, String grade, String lesson, String classes, String askDate, String title, String imgUrl, String question, ArrayList<Answer> answerList) {
		super();
		this.id = id;
		this.quizzer = quizzer;
		this.quizzerPortrait = quizzerPortrait;
		this.grade = grade;
		this.lesson = lesson;
		this.classes = classes;
		this.askDate = askDate;
		this.title = title;
		this.imgUrl = imgUrl;
		this.question = question;
		this.answerList = answerList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuizzer() {
		return quizzer;
	}

	public void setQuizzer(String quizzer) {
		this.quizzer = quizzer;
	}

	public String getQuizzerPortrait() {
		return quizzerPortrait;
	}

	public void setQuizzerPortrait(String quizzerPortrait) {
		this.quizzerPortrait = quizzerPortrait;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getAskDate() {
		return askDate;
	}

	public void setAskDate(String askDate) {
		this.askDate = askDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(ArrayList<Answer> answerList) {
		this.answerList = answerList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BlogItem [id=" + id + ", quizzer=" + quizzer + ", quizzerPortrait=" + quizzerPortrait + ", grade=" + grade + ", lesson=" + lesson + ", classes=" + classes + ", askDate=" + askDate + ", title=" + title + ", imgUrl=" + imgUrl + ", question=" + question + ", answerList=" + answerList + "]";
	}

}
