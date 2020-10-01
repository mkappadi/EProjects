/**
 * 
 */
package com.ito.appt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Mohan Kappadi
 *
 */
public class ExcelSheetToDataBase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExcelSheetToDataBase mainObj = new ExcelSheetToDataBase();
			ArrayList<CustApptSched> apptSchedObjList = mainObj.readData();
			mainObj.writeData(apptSchedObjList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeData(ArrayList<CustApptSched> apptSchedObjList) {
		try {

			Connection connection = null;
			try {
				connection = createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreparedStatement pstt = connection.prepareStatement("INSERT INTO dbo.t_cust_appt_sched ( wh_id, asn_nbr, po_nbr, trailer_id, number_of_cartons,expected_date,carrier_scac, seal_nbr, actual_ship_date, created_dttm, arthur_upload_dttm, fcs_run_dttm, actual_arrival_dttm, actual_unload_dttm) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			for (CustApptSched custApptSchedObj : apptSchedObjList) {

				pstt.setString(1, custApptSchedObj.getWh_id());
				pstt.setString(2, custApptSchedObj.getAsn_nbr());
				pstt.setString(3, custApptSchedObj.getPo_nbr());
				pstt.setString(4, custApptSchedObj.getTrailer_id());
				pstt.setInt(5, custApptSchedObj.getNumber_of_cartons());
				pstt.setDate(6, new java.sql.Date(custApptSchedObj.getExpected_date().getTime()));
				pstt.setString(7, custApptSchedObj.getCarrier_scac());
				pstt.setString(8, custApptSchedObj.getSeal_nbr());
				pstt.setDate(9, new java.sql.Date(custApptSchedObj.getActual_ship_date().getTime()));
				pstt.setDate(10, new java.sql.Date(custApptSchedObj.getCreated_dttm().getTime()));
				pstt.setDate(11, new java.sql.Date(custApptSchedObj.getArthur_upload_dttm().getTime()));
				pstt.setDate(12, new java.sql.Date(custApptSchedObj.getFcs_run_dttm().getTime()));
				pstt.setDate(13, new java.sql.Date(custApptSchedObj.getActual_arrival_dttm().getTime()));
				pstt.setDate(14, new java.sql.Date(custApptSchedObj.getActual_unload_dttm().getTime()));
				
				pstt.executeUpdate();
			}

			connection.close();

		} catch (SQLException sqlException) {
			System.out.println("Exception occured while wrtiting data into database.");
			try {
				throw sqlException;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Connection createConnection() throws Exception {
		try {
			
			Properties pr = new Properties();
			FileReader reader = new FileReader("ApptSched.properties");
			pr.load(reader);
			
			Class.forName(pr.getProperty("driver_class_name"));
			System.out.println("Driver loaded");
			//String password = pr.getProperty("password");
			System.out.println(pr.getProperty("url")+pr.getProperty("username") +pr.getProperty("password") );
			Connection obj = DriverManager.getConnection(pr.getProperty("url"),pr.getProperty("username"), pr.getProperty("password") );
			return obj;
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("-----------Exception occured while establishing the JDBC Connection-------------------");
			e.printStackTrace();
			throw e;
		}

	}

	private ArrayList<CustApptSched> readData() {
		ArrayList<CustApptSched> apptSchedObjList = new ArrayList<>();
		try {

			Properties pr = new Properties();
			FileReader reader = new FileReader("ApptSched.properties");
			pr.load(reader);

			FileInputStream file = new FileInputStream(new File(pr.getProperty("ExcelFilePath")));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Row row;
			SimpleDateFormat sdf = new SimpleDateFormat(pr.getProperty("date_format"));

			// To iterate through each and every row in the excel sheet

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				
				// Instance creation of CustApptSched class for each and every row
				
				CustApptSched apptSchedObj = new CustApptSched();

				// Saving the row value to access the cell value
				
				row = sheet.getRow(i);

				if (row.getCell(Integer.parseInt(pr.getProperty("wh_id"))) != null) {

					apptSchedObj.setWh_id(row.getCell(Integer.parseInt(pr.getProperty("wh_id"))).toString());
				}

				if (row.getCell(Integer.parseInt(pr.getProperty("asn_nbr"))) != null) {

					apptSchedObj.setAsn_nbr(row.getCell(Integer.parseInt(pr.getProperty("asn_nbr"))).toString());
				}

				if (row.getCell(Integer.parseInt(pr.getProperty("po_nbr"))) != null) {

					apptSchedObj.setPo_nbr(row.getCell(Integer.parseInt(pr.getProperty("po_nbr"))).toString());
				}

				if (row.getCell(Integer.parseInt(pr.getProperty("trailer_id"))) != null) {

					apptSchedObj.setTrailer_id(row.getCell(Integer.parseInt(pr.getProperty("trailer_id"))).toString());
				}

				if (row.getCell(Integer.parseInt(pr.getProperty("number_of_cartons"))) != null) {

					apptSchedObj.setNumber_of_cartons(Integer.parseInt(row.getCell(Integer.parseInt(pr.getProperty("number_of_cartons"))).toString()));
				}

				if (row.getCell(Integer.parseInt(pr.getProperty("expected_date"))) != null) {

					apptSchedObj.setExpected_date(sdf.parse(row.getCell(Integer.parseInt(pr.getProperty("expected_date"))).toString()));
	
				}

				if (row.getCell(Integer.parseInt(pr.getProperty("carrier_scac"))) != null) {

					apptSchedObj.setCarrier_scac(row.getCell(Integer.parseInt(pr.getProperty("carrier_scac"))).toString());
			
				}

				if (row.getCell(Integer.parseInt(pr.getProperty("seal_nbr"))) != null) {

					apptSchedObj.setSeal_nbr(row.getCell(Integer.parseInt(pr.getProperty("seal_nbr"))).toString());
				}

				/*if (row.getCell(Integer.parseInt(pr.getProperty("actual_ship_date"))) != null) {

					apptSchedObj.setActual_ship_date(sdf.parse(row.getCell(Integer.parseInt(pr.getProperty("actual_ship_date"))).toString()));
					

				}

				if (row.getCell(Integer.parseInt(pr.getProperty("created_dttm"))) != null) {
					apptSchedObj.setCreated_dttm(sdf.parse(row.getCell(Integer.parseInt(pr.getProperty("created_dttm"))).toString()));

				}

				if (row.getCell(Integer.parseInt(pr.getProperty("arthur_upload_dttm"))) != null) {

					apptSchedObj.setArthur_upload_dttm(sdf.parse(row.getCell(Integer.parseInt(pr.getProperty("arthur_upload_dttm"))).toString()));

				}

				if (row.getCell(Integer.parseInt(pr.getProperty("fcs_run_dttm"))) != null) {

					apptSchedObj.setFcs_run_dttm(sdf.parse(row.getCell(Integer.parseInt(pr.getProperty("fcs_run_dttm"))).toString()));

				}

				if (row.getCell(Integer.parseInt(pr.getProperty("actual_arrival_dttm"))) != null) {

					apptSchedObj.setActual_arrival_dttm(sdf.parse(row.getCell(Integer.parseInt(pr.getProperty("actual_arrival_dttm"))).toString()));

				}

				if (row.getCell(Integer.parseInt(pr.getProperty("actual_unload_dttm"))) != null) {

					apptSchedObj.setActual_unload_dttm(sdf.parse(row.getCell(Integer.parseInt(pr.getProperty("actual_unload_dttm"))).toString()));
				}*/

				apptSchedObjList.add(apptSchedObj);
			}

			workbook.close();

		} catch ( IOException | NumberFormatException | ParseException e) {

			System.out.println("Exception occured while reading the data from the Excel sheet ");
			e.printStackTrace();
		}

		return apptSchedObjList;		
		
	}

}
