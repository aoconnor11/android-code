package com.bluemetrix.lesson;

import java.io.File;
import java.io.Serializable;

public class Lesson implements Serializable
{	
	private String id;
	private int sdStatus;
	private String lessonTitle;
	private File audioLesson;
	private String preQuestion;
	private String extras;
	private String transcript;
	private String question1;
	private String question2;
	private String question3;
	private String option1A;
	private String option1B;
	private String option1C;
	private String option2A;
	private String option2B;
	private String option2C;
	private String option3A;
	private String option3B;
	private String option3C;
	private String answer1;
	private String answer2;
	private String answer3;
	private String audioPath;//move stuff from assets to sd card		
	
	public Lesson()
	{
		
	}
	
	public Lesson(String id)
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public int getSdStatus() 
	{//get status
		return sdStatus;
	}

	public void setSdStatus(int sdStatus) 
	{
		this.sdStatus = sdStatus;
	}

	public String getLessonTitle() 
	{
		return lessonTitle;
	}

	public void setLessonTitle(String lessonTitle) 
	{
		this.lessonTitle = lessonTitle;
	}

	public File getAudioLesson() 
	{
		return audioLesson;
	}

	public void setAudioLesson(File audioLesson) 
	{
		this.audioLesson = audioLesson;
	}

	public String getTranscript() 
	{
		return transcript;
	}

	public void setTranscript(String transcript) 
	{
		this.transcript = transcript;
	}

	public String getQuestion1() 
	{
		return question1;
	}

	public void setQuestion1(String question1) 
	{
		this.question1 = question1;
	}

	public String getQuestion2() 
	{
		return question2;
	}

	public void setQuestion2(String question2) 
	{
		this.question2 = question2;
	}

	public String getQuestion3() 
	{
		return question3;
	}

	public void setQuestion3(String question3) 
	{
		this.question3 = question3;
	}

	public String getAnswer1() 
	{
		return answer1;
	}

	public void setAnswer1(String answer1) 
	{
		this.answer1 = answer1;
	}

	public String getAnswer2() 
	{
		return answer2;
	}

	public void setAnswer2(String answer2) 
	{
		this.answer2 = answer2;
	}

	public String getAnswer3() 
	{
		return answer3;
	}

	public void setAnswer3(String answer3) 
	{
		this.answer3 = answer3;
	}

	public String getAudioPath() 
	{
		return audioPath;
	}

	public void setAudioPath(String audioPath) 
	{
		this.audioPath = audioPath;
	}

	public String getOption1A() 
	{
		return option1A;
	}

	public void setOption1A(String option1a) 
	{
		option1A = option1a;
	}

	public String getOption1B() 
	{
		return option1B;
	}

	public void setOption1B(String option1b) 
	{
		option1B = option1b;
	}

	public String getOption1C() 
	{
		return option1C;
	}

	public void setOption1C(String option1c) 
	{
		option1C = option1c;
	}

	public String getOption2A() 
	{
		return option2A;
	}

	public void setOption2A(String option2a) 
	{
		option2A = option2a;
	}

	public String getOption2B() 
	{
		return option2B;
	}

	public void setOption2B(String option2b) 
	{
		option2B = option2b;
	}

	public String getOption2C() 
	{
		return option2C;
	}

	public void setOption2C(String option2c) 
	{
		option2C = option2c;
	}

	public String getOption3A() 
	{
		return option3A;
	}

	public void setOption3A(String option3a) 
	{
		option3A = option3a;
	}

	public String getOption3B() 
	{
		return option3B;
	}

	public void setOption3B(String option3b) 
	{
		option3B = option3b;
	}

	public String getOption3C() 
	{
		return option3C;
	}

	public void setOption3C(String option3c) 
	{
		option3C = option3c;
	}

	public String getPreQuestion() 
	{
		return preQuestion;
	}

	public void setPreQuestion(String preQuestion) 
	{
		this.preQuestion = preQuestion;
	}

	public String getExtras() 
	{
		return extras;
	}

	public void setExtras(String extras) 
	{
		this.extras = extras;
	}	
}