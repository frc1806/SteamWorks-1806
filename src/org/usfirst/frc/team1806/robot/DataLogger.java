package org.usfirst.frc.team1806.robot;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;


public class DataLogger {
	
	Calendar c = Calendar.getInstance();
	String fileName = "/U/" + String.valueOf(c.getTime() + ".csv");
	FileWriter fileWriter;
	BufferedWriter bufferedWriter;
	
	public DataLogger(){
      
		//replace spaces and colons to make filename acceptable
		fileName = fileName.replaceAll("\\s", "_");
		fileName = fileName.replace(":", "-");

	}
		
	public void addTimestamp(){
		 FileWriter fileWriter;
			try {
				fileWriter = new FileWriter(fileName, true);
		      	 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		 		bufferedWriter.write("\r\n" + "New Teleop Cycle Started" + 
						  "P:" +Robot.flywheelSS.flyWheel.getP() +"     "+ 
						  "I:" + Robot.flywheelSS.flyWheel.getI() +"    "+ 
						  "D: "+ Robot.flywheelSS.flyWheel.getD() +"    "+
						  "F: "+Robot.flywheelSS.flyWheel.getF() +"   "+
						  "Target: " + Constants.camCoder + "\r\n");
		 		bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	}
	
	public void writeData(String data, String time, String power){
	

		


        try {
        	
        	 FileWriter fileWriter = new FileWriter(fileName, true);
        	 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        		

        		
        	
            // Assume default encoding.


            // Always wrap FileWriter in BufferedWriter.


            // Note that write() does not automatically
            // append a newline character.
            
            //bufferedWriter.newLine();
            
            bufferedWriter.write(data +  "," + time + "," + power + "\r\n");
 
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
//            System.out.println(
//                "failed to write to file '"
//                + fileName + "'");
//            
//            
            //to prevent spam
            //will disallow any more running of the data logger
//            Robot.statesObj.dataLogStateTracker = States.dataLogState.OFF;
        }	
	}
	
	public void writeNewTeleopCycle(){
		
		try {
        	
       	 FileWriter fileWriter = new FileWriter(fileName, true);
       	 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         StringBuilder sBuilder = new StringBuilder();
         
         sBuilder.append( Robot.matchTimer.get());
         sBuilder.append(",");
         sBuilder.append(Robot.flywheelSS.flyWheel.getSpeed());
         sBuilder.append(",");
         sBuilder.append(Robot.flywheelSS.getPower());
         sBuilder.append(",");
         sBuilder.append("\n");
         bufferedWriter.write(sBuilder.toString());
           // Always close files.
         bufferedWriter.close();
       }
       catch(IOException ex) {
//           System.out.println(
//               "failed to write to file '"
//               + fileName + "'");
//           
       }	
		
	}
	
	
}