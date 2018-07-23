package scb.business.alert.ejb;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CSVReader {

	public static void main(String[] args) {

		String csvFile = "C:/Users/1564241/Downloads/records.csv";
		String outPutFile = "C:/Users/1564241/Downloads/test.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			List<Integer> list = new ArrayList<Integer>();
			br = new BufferedReader(new FileReader(csvFile));
			PrintWriter pw = new PrintWriter(new File("test.csv"));
			while ((line = br.readLine()) != null) {
				String[] referenceno = line.split(cvsSplitBy);
				if (referenceno[0].equals("Reference"))
					continue;

				list.add(Integer.parseInt((referenceno[0])));
				if (!((toDouble(referenceno[3]) + toDouble(referenceno[4])) == toDouble(referenceno[5]))) {

					/*Test---------------------->*/

				}
			}
			Set<Integer> abc = findDuplicates(list);
			System.out.println("Duplicates" + abc);
			// createFailedData(abc);

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

	}

	

	public static Set<Integer> findDuplicates(
			List<Integer> listContainingDuplicates) {
		final Set<Integer> setToReturn = new HashSet();
		final Set<Integer> set1 = new HashSet();

		for (Integer yourInt : listContainingDuplicates) {
			if (!set1.add(yourInt)) {
				setToReturn.add(yourInt);
			}
		}
		return setToReturn;
	}

	public static double toDouble(String a) {
		int sign = 1;
		int start = 0;
		if (a.charAt(0) == '-') {
			start = 1;
			sign = -1;
		}
		double value = 0;
		boolean decimal = false;
		int exp = 0;

		for (int i = start; i < a.length(); i++) {
			if (a.charAt(i) == '.') {
				if (decimal)
					return 0.0;
				decimal = true;
			} else {
				value += (a.charAt(i) - 48) * Math.pow(10, a.length() - i - 1);
			}

			if (decimal)
				exp++;
		}

		value = value / Math.pow(10, exp);
		value *= sign;
		System.out.println(value);
		return value;

	}

	
}