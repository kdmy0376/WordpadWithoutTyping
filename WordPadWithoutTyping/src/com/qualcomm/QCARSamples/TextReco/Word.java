package com.qualcomm.QCARSamples.TextReco;

public class Word {
	
	private int word_id;
	private String word_word;
	private String word_noun;
	private String word_pron;
	private String word_meaning;
	
	public Word(){
		
	}
	public Word(int id, String word, String noun, String pron, String meaning){
		word_id = id;
		word_word = word;
		word_noun = noun;
		word_pron = pron;
		word_meaning = meaning;
	}
	
	public Word(String word, String noun, String pron, String meaning){
		word_word = word;
		word_noun = noun;
		word_pron = pron;
		word_meaning = meaning;
	}
	
	
	public int getWord_id() {
		return word_id;
	}
	public void setWord_id(int word_id) {
		this.word_id = word_id;
	}
	public String getWord_word() {
		return word_word;
	}
	public void setWord_word(String word_word) {
		this.word_word = word_word;
	}
	public String getWord_noun() {
		return word_noun;
	}
	public void setWord_noun(String word_noun) {
		this.word_noun = word_noun;
	}
	public String getWord_pron() {
		return word_pron;
	}
	public void setWord_pron(String word_pron) {
		this.word_pron = word_pron;
	}
	public String getWord_meaning() {
		return word_meaning;
	}
	public void setWord_meaning(String word_meaning) {
		this.word_meaning = word_meaning;
	}

}
