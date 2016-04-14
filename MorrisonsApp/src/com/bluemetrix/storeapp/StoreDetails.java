package com.bluemetrix.storeapp;



public class StoreDetails
{	
	private String title;
	private int id;
	
	public StoreDetails()
	{
		
	}
	


	public StoreDetails(String title, int id)
	{
		this.title = title;
		this.setId(id);
	}
	

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}




	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	
}
