//importing classes
import java.util.*;
import java.io.*;
import java.io.File;


public class Spider{

	//map object
   	static Map<String, String> index = new HashMap<String, String>();


   	//extension method
   	public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int extensionPos = filename.lastIndexOf('.');
        int lastUnixPos = filename.lastIndexOf('/');
        int lastWindowsPos = filename.lastIndexOf('\\');
        int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);

        int index = lastSeparator > extensionPos ? -1 : extensionPos;
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

	//crawler method
	public static boolean crawler(File dir){

        if (dir.exists()){

            //File array
            File[] list = dir.listFiles();

            //empty directory
            if (list == null){
            	System.out.println("The entered directory is empty!");
            	return false;
            }	

            //walking
            for ( File f : list ) {

            	//directory
                if ( f.isDirectory() ){
                    index.put(f.getName(),f.getAbsolutePath());
                    crawler( new File(f.getAbsolutePath()));
                }
                //file
                else {
                    index.put(f.getName(),f.getAbsolutePath());

                    //if text file is found
                    if(getExtension(f.getName()).equals("txt")){

                    	//reading text file
                    	try{

                    		//reading file
    	                	String content = new Scanner(f).useDelimiter("\\Z").next();

    						//making array
    						String[] tmpData = content.split(" ");

    						//traversing array
    						for (String s: tmpData) {   
    					        index.put(s,f.getAbsolutePath()); 
    					    }
    					}
    					catch(Exception e){

    					}							
                    }
                }
            }

            return true;    
	    }
        else{
            System.out.println("Directory doesn't exist!");
            return false;
        }
    }

    //main metod
    public static void main(String[] args){

        //Scanner object
        Scanner input = new Scanner(System.in);
        
        //user input
        System.out.println("Enter directory you would like to crawl");
        String userDir = input.nextLine();

        //file object
        File dir = new File(userDir);

        //indexing
        crawler(dir);

        //status
        System.out.println("\nIndexing is completed!");

        //asking for key
        System.out.println("Enter the key for which you would like to search");
        String key = input.nextLine();

        //traversing map
        System.out.println("\nKey: "+ key);
        for (Map.Entry<String, String> entry : index.entrySet()) {
        	if((entry.getKey().toLowerCase()).contains(key.toLowerCase())){
				System.out.println("Path : " + entry.getValue());
			}	
		}

    }	
}
