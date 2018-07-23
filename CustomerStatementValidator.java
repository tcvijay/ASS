package com.rabobank.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;



public class CustomerStatementValidator {

	public static void main(String[] args) {
		Vector<CutomerStatementDetails> errorRecords;
		//Reading the file 
		errorRecords = readData("records.csv");
		if(errorRecords.size()>0)
			printErrorRecords(errorRecords);
	}
	
	
/**
 * 
 * this method will return the error records 
 * 
 * @param errorRecords
 */
	private static void printErrorRecords(Vector<CutomerStatementDetails> errorRecords) {
		System.out.println("::FAILED RECORDS::");
		for(int index=0;index<errorRecords.size();index++)
			System.out.println("ReferenceNo -> " + errorRecords.get(index).getReference() + "  Description -> "+errorRecords.get(index).getDescription());		
	}

	/**
	 *  
	 *  read the data from the file 
	 *  record.csv file be local path
	 * @param filePath 
	 * @return errorRecords 
	 */
	private static Vector readData(String filePath) {
		BufferedReader br = null;
		String line = "";
		boolean errorFlag;
		CutomerStatementDetails cStDet;
		HashMap<Integer,CutomerStatementDetails> recordCollection = new HashMap<Integer,CutomerStatementDetails>();
		Vector<CutomerStatementDetails> errorRecords = new Vector<CutomerStatementDetails>();
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				errorFlag = false;
				String[] record = line.split(",");
				try {
					Integer.parseInt(record[0]);
				} catch (Exception e) {
					continue;
				}
				cStDet = populateWrapper(record);
				errorFlag = validateRecord(recordCollection,cStDet);
				if(!errorFlag)
					recordCollection.put(cStDet.getReference(),cStDet);
				else
					errorRecords.add(cStDet);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return errorRecords;
	}

	/**
	 *  populate the information 
	 *  
	 * @param record
	 * @return the CutomerStatementDetails 
	 */
	public static CutomerStatementDetails populateWrapper(String[] record) {
		CutomerStatementDetails cStDet = new CutomerStatementDetails();
		cStDet.setReference(Integer.parseInt(record[0]));
		cStDet.setAccountNumber(record[1]);
		cStDet.setDescription(record[2]);
		cStDet.setStartBalance(new BigDecimal(record[3]));
		cStDet.setMutation(new BigDecimal(record[4]));
		cStDet.setEndBalance(new BigDecimal(record[5]));
		return cStDet;
	}

	/**
	 * validate the record
	 * @param recordCollection
	 * @param cStDet
	 * @return boolean
	 */
	public static boolean validateRecord(HashMap<Integer, CutomerStatementDetails> recordCollection, CutomerStatementDetails cStDet) {
		return recordCollection.containsKey(cStDet.getReference()) || (cStDet.getEndBalance().compareTo(cStDet.getStartBalance().add(cStDet.getMutation()))!=0);
	}
}
