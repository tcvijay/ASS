package com.rabobank.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.junit.Test;
import com.rabobank.wrapper.CutomerStatementDetails;

public class CustomerStatementValidatorTest {
	CustomerStatementValidator cVal = new CustomerStatementValidator();
@Test
public void testPopulateWrapper() {
	try {		
		String[] record = new String[6];
		record[0]="183356";
		record[1]="NL74ABNA0248990274";
		record[2]="Subscription for Peter de Vries";
		record[3]="92.98";
		record[4]="-46.65";
		record[5]="46.33";
		CutomerStatementDetails cWrap =  (CutomerStatementDetails) cVal.populateWrapper(record);
		assertEquals(record[0],String.valueOf(cWrap.getReference()));
		assertEquals(record[1],String.valueOf(cWrap.getAccountNumber()));
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
	} 	
}
@Test
public void testValidateRecord() {
	HashMap<Integer,CutomerStatementDetails> recordCollection = new HashMap<Integer,CutomerStatementDetails>();
	String[] record1 = new String[6];
	record1[0]="183356";
	record1[1]="NL74ABNA0248990274";
	record1[2]="Subscription for Peter de Vries";
	record1[3]="92.98";
	record1[4]="-46.65";
	record1[5]="46.33";
	CutomerStatementDetails cWrap1 =  (CutomerStatementDetails) cVal.populateWrapper(record1);
	//recordCollection.put(cWrap1.getReference(),cWrap1);
	assertFalse(cVal.validateRecord(recordCollection, cWrap1));
	String[] record2 = new String[6];
	record2[0]="183356";
	record2[1]="NL74ABNA0248990274";
	record2[2]="Subscription for Peter de Vries";
	record2[3]="92.98";
	record2[4]="-46.65";
	record2[5]="22.33";	
	CutomerStatementDetails cWrap2 =  (CutomerStatementDetails) cVal.populateWrapper(record2);	
	recordCollection.put(cWrap2.getReference(),cWrap2);
	assertTrue(cVal.validateRecord(recordCollection, cWrap2));
}
}
