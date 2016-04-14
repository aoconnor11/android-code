package com.bluemetrix.game.activities;


import com.bluemetrix.R;
import com.bluemetrix.game.nodes.SceneSelectionNode;
import com.bluemetrix.game.activities.InitialView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SceneSelectionView extends Activity 
{
	private Button select;
	private Button home;
	private RelativeLayout rel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scene_selection);	
		
		select = (Button) findViewById(R.id.buttonAtTheAirport);

		//link to Initial View
		select.setOnClickListener(new View.OnClickListener() 
		{
		        public void onClick(View view) 
		        {
		               startGame();
		        }
		}); 
				
		//Setting Background Picture
		rel = (RelativeLayout) findViewById(R.id.relLayoutSceneSelection);
				
		//Customise view with text/background picture
		SceneSelectionNode node = new SceneSelectionNode(1);
		node.addNodeDetails(1, select, rel);	
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.activity_screen_one, menu);
		return true;
	}
	
	private void startGame() 
	{
	    Intent launchGame = new Intent(this, InitialView.class);
	    launchGame.putExtra("option", 1);
	    startActivity(launchGame);
	}	
}