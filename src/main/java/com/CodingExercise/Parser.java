/*This class is used to open the CSV file, parse it, log the transaxtions
    and write the poorly formed lines into the error file
*/

package com.CodingExercise;

import java.io.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class Parser {
    private String fileName;
    
    public void parse(File file) throws IOException {
        String[] nextRecord;
        String[] headers;
        File inputFile;
        List<String[]> rejects = new ArrayList<String[]>();
        int successCount = 0;
        int totalCount = 0;
        
        inputFile = file;
        
        fileName = inputFile.getName().replace(".csv", ".db");
        
        File outputFile = new File(".\\" + fileName.replace(".db", "-bad.csv"));
        
        try { 
            CSVReader readerHeader = new CSVReader(new FileReader(inputFile));
            
            headers = readerHeader.readNext();
            
            DAO.shared.createTable(headers);
            rejects.add(headers);
            
            CSVReader reader = new CSVReaderBuilder(new FileReader(inputFile))
                    .withSkipLines(1).build();
   
            while((nextRecord = reader.readNext()) != null){
                totalCount++;
                if(nextRecord.length > headers.length)
                    rejects.add(nextRecord);
                else {
                    DAO.shared.insertRecord(nextRecord, headers);
                    successCount++;
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace(); 
        }
        
        writeOutputFile(outputFile, rejects);
        
        LogClass.setFileName(".\\" + fileName.replace(".db", ".log"));
        LogClass.getLogger().info("# of Records Received - " + totalCount);
        LogClass.getLogger().info("# of Records Successful - " + successCount);
        LogClass.getLogger().info("# of Records Failed  - " + 
                String.valueOf(rejects.size() - 1));
        
        DAO.shared.close();
        
    }
    
    private void writeOutputFile(File outputFile, List<String[]> data) { 
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(outputFile));
            writer.writeAll(data);
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
