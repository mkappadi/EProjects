/**
 * 
 */
package com.ito.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author Mohan Kappadi
 *
 */
public class IOOps {

	/**
	 * 
	 */
	public IOOps() {
		
	}
	
/*	public static void main(String args[]) {
		System.out.println(getCurrentTimestamp());
	}*/
	
	static ArrayList<String> readDataFile(ArrayList<String> data, Properties pr) throws IOException {
		try {
			try (BufferedReader br = new BufferedReader(new FileReader(pr.getProperty("data.file.name")))) {
				String line;
				while ((line = br.readLine()) != null) {
					data.add(line.trim());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	static void writePassFile(ArrayList<String> passLine, Properties pr) throws IOException {
		try {
			FileWriter fw = new FileWriter(pr.getProperty("pass.file.path")+pr.getProperty("pass.file.name")+getCurrentDate()+pr.getProperty("pass.file.extension"));
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<String> it = passLine.iterator();
			while(it.hasNext()) {
				bw.write(it.next());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	static void truncateDataList(ArrayList<String> data,Properties pr) {
		for(int i=0; i<Integer.parseInt(pr.getProperty("header.lines.to.truncate"));i++)
			data.remove(0);
		for(int i=0; i<Integer.parseInt(pr.getProperty("footer.lines.to.truncate"));i++)
			data.remove(data.size()-1);
	}	
	
	private static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
		return dateFormat.format(date);
	}
}
