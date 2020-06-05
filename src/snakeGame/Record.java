package snakeGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Record {
	private int recordValue;
	private String recordNick;
	private LocalDateTime recordDate;
	private FileOutputStream recordFileStream;
	private ObjectOutputStream recordObjectStream;
	private String line;
	private String newLineRecord;
	private PrintWriter filePW;
	private BufferedReader recordBR;
	private BufferedWriter recordBW;
	private String[] listOfRecords;
	private String[] newListOfRecords;
	
	public Record() {
		try {
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String[] readRecord() {
		listOfRecords = new String[30];
		line = "";
		
		try {
			recordBR = new BufferedReader(new FileReader("records.txt"));
			for(int i = 0; i < 30; i++) {
				listOfRecords[i] = recordBR.readLine();
			}
			
			recordBR.close();
		} 
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return listOfRecords;
	}
	
	private int compareRecords(String[] listOfRecords, int recordValue) {
		int positionToInsert = 100;
		Integer[] tableOfActualRecords = new Integer[30];
		String temporaryString = "";
		
		for(int i = 0; i < 30; i++) {
			temporaryString = listOfRecords[i].substring(0, listOfRecords[i].indexOf(";"));
			tableOfActualRecords[i] = Integer.parseInt(temporaryString);
		}
		
		if(recordValue > tableOfActualRecords[29]) {
			for(int i = 29; i > -1; i--) {
				if(recordValue > tableOfActualRecords[i]) {
					positionToInsert = i;
				}
			}
		}
		
		return positionToInsert;
	}
	
	public void saveRecord(int recordValue, String recordNick) {
		recordDate = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
		
		int positionToInsert = compareRecords(readRecord(), recordValue);
		
		if(positionToInsert != 100) {
			newLineRecord = Integer.toString(recordValue).concat("; ").concat(recordNick).concat("; ").concat(dtf.format(recordDate));
			newListOfRecords = new String[30];
			
			for(int i = 0; i < positionToInsert; i++) {
				newListOfRecords[i] = listOfRecords[i];
			}
			newListOfRecords[positionToInsert] = newLineRecord;
			
			for(int i = positionToInsert + 1; i < 30; i++) {
				newListOfRecords[i] = listOfRecords[i-1];
			}
			
			try {
				recordBW = new BufferedWriter(new FileWriter("records.txt"));
				 for(int i = 0; i < 30; i++) {
					 recordBW.write(newListOfRecords[i]);
					 recordBW.newLine();
				 }
				recordBW.close();
				System.out.println("Rekord zapisany w pozycji "+positionToInsert);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Rekord pominiêtny w zapisie");
		}
		
	}
	


}
