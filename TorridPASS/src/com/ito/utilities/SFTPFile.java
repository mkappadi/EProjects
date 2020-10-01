/**
 * 
 */
package com.ito.utilities;


import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author Mohan Kappadi
 *
 */

public class SFTPFile
{
	private static FileReader reader;
	private static Properties pr=new Properties();
	
	static {
		try {
			reader = new FileReader("PASS.properties");
			pr.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		sendFile(pr);
	}
	
	public static void sendFile(Properties pr) throws Exception
	{
		String user = pr.getProperty("sftp.user.name");
		String password = pr.getProperty("sftp.user.password");
		String host = pr.getProperty("sftp.server.name");
		int port=Integer.parseInt(pr.getProperty("sftp.port.nbr"));
		String localFile = pr.getProperty("pass.file.path")+pr.getProperty("pass.file.name")+getCurrentDate()+pr.getProperty("pass.file.extension");
		String remoteFile = pr.getProperty("sftp.remote.location");
		

		try
		{
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing Connection...");
			session.connect();
			System.out.println("Connection established.");
			System.out.println("Creating SFTP Channel.");
			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
			System.out.println("SFTP Channel created.");

			//sftpChannel.put(localFile,remoteFile);
			System.out.println("File Transferred");
			
			sftpChannel.exit();
		}
		catch(Exception e){
			System.err.print(e);
			throw e;
			}
	}
	
	private static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
		return dateFormat.format(date);
	}
}
