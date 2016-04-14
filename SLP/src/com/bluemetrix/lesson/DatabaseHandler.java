package com.bluemetrix.lesson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper  
{
	// All Static variables 
	// Database Version 
	private static final int DATABASE_VERSION = 1; 	   
	private static final String DB_PATH = "/data/data/com.example.langaugeapp.lesson/databases/"; 	 
	// Database Name 
	private static final String DATABASE_NAME = "SLPManager";     
	// Lessons table name 
	private static final String TABLE_LESSON = "lesson";   
	// Lessons Table Columns names  
	private static final String KEY_ID = "_id";
	private static final String KEY_STATUS = "lessonStatus";	   
	private SQLiteDatabase myDataBase;      
	private final Context myContext;   
	/**     * Constructor     
	 * * Takes and keeps a reference of the passed context in order to access to the application assets and resources.     
	 * * @param context     */ 
	  
	public DatabaseHandler(Context context) 
	{     	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);        
		this.myContext = context;    
	}	
	   
	public void createDataBase() throws IOException
	{     	
		boolean dbExist = checkDataBase();  
		   
		if(dbExist)
		{    		
			//do nothing - database already exist    	
		}
		else
		{     		
			//By calling this method and empty database will be created into the default system path              
			//of your application so we are gonna be able to overwrite that database with our database.        	
			this.getReadableDatabase();         	
			try 
			{     			
				copyDataBase();     		
			} 
			catch (IOException e) 
			{         		
				throw new Error("Error copying database");         
			}    	
		}     
	} 
		
	/**     
	 * Check if the database already exist to avoid re-copying the file each time you open the application.     
	 * @return true if it exists, false if it doesn't     
	 */   
	private boolean checkDataBase()
	{     	
		SQLiteDatabase checkDB = null;  	   
		try
		{    		
			String myPath = DB_PATH + DATABASE_NAME;    		
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);     	
		}
		catch(SQLiteException e)
		{     		
			//database does't exist yet.     	
		}     	
		if(checkDB != null)
		{     		
			checkDB.close();     	
		}     	
		return checkDB != null ? true : false;    
	}
		   
	/**     
	 * Copies your database from your local assets-folder to the just created empty database in the     
	 * system folder, from where it can be accessed and handled.    
	 * This is done by transfering bytestream.     
	 * */    
	private void copyDataBase() throws IOException
	{     	
		//Open your local db as the input stream    	
		InputStream myInput = myContext.getAssets().open(DATABASE_NAME);     	
		// Path to the just created empty db    	
		String outFileName = DB_PATH + DATABASE_NAME;     	
		//Open the empty db as the output stream    	
		OutputStream myOutput = new FileOutputStream(outFileName);     	
		//transfer bytes from the inputfile to the outputfile    	
		byte[] buffer = new byte[1024];    	
		int length;    	
		   
		while ((length = myInput.read(buffer))>0)
		{    		
			myOutput.write(buffer, 0, length);    	
		}     	
		//Close the streams    	
		myOutput.flush();    	
		myOutput.close();    	
		myInput.close();    
	} 
		      
	public void openDataBase() throws SQLException
	{     	
		//Open the database        
		String myPath = DB_PATH + DATABASE_NAME;    	
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);     
	}
	   
	@Override	
	public synchronized void close() 
	{     	   
		if(myDataBase != null)    		    
			   myDataBase.close();     	   
		   super.close(); 	
	} 	
	   
	@Override	
	public void onCreate(SQLiteDatabase db) 
	{ 	
		
	} 	
	   
	@Override	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{ 	
		   
	}
	    
	/** 
	 * All CRUD(Create, Read, Update, Delete) Operations 
	 */
	   
	// Adding new lesson status
	public void addLessonStatus(Lesson lesson)
	{
		//only put in id and status
		SQLiteDatabase db = this.getWritableDatabase();
	 	ContentValues values = new ContentValues(); 
	 	values.put(KEY_ID, lesson.getId());
	 	values.put(KEY_STATUS, lesson.getSdStatus());   
	 	db.insert(TABLE_LESSON, null, values);
	}
	   
	public Lesson getById(int id) 
	{  
		String SelectById = "SELECT _id, lessonStatus FROM lesson WHERE _ID= " + id;
		return (Lesson) (getReadableDatabase()
        .rawQuery(SelectById, null));
	}
	   
	// Updating lesson status 
	public int updateLesson(Lesson lesson) 
	{ 
		SQLiteDatabase db = this.getWritableDatabase(); 
	    ContentValues values = new ContentValues(); 	       
	    values.put(KEY_STATUS, lesson.getSdStatus());
	    // updating row 
	    return db.update(TABLE_LESSON, values, KEY_ID + " = ?", new String[] { String.valueOf(lesson.getId()) }); 
	}  	
}
