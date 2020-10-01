/**
 * 
 */
package com.ito.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import com.ito.dao.ItemDAO;
import com.ito.dao.OrderDAO;
import com.ito.dao.PackageDAO;
import com.ito.dao.TransactionDAO;

/**
 * @author Mohan Kappadi
 *
 */
public class FileTranslator {

	/**
	 *  Class to hold all transaction elements and translate the data file to PASS file
	 */
	
	private OrderDAO ord ;
	private TransactionDAO trans = new TransactionDAO();
	private ArrayList<ItemDAO> itemArr;
	private ArrayList<PackageDAO> packArr ;
	private static FileTranslator ft = new FileTranslator(); 
	private static Properties pr=new Properties();
	private static int orderCount;
	private static int itemCount;
	private static int packageCount;
	private static ArrayList<String> passLine = new ArrayList<String>();
	
	static {
		FileReader reader;
		try {
			reader = new FileReader("PASS.properties");
			pr.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private FileTranslator() {
	}
	
	public static FileTranslator getInstance() 
    { 
		if (ft == null) 
	        ft = new FileTranslator(); 
	
	    return ft; 
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			ArrayList <String> data = new ArrayList<String>();
			IOOps.readDataFile(data,pr);
			IOOps.truncateDataList(data,pr);
			Iterator<String> itr = data.iterator();
			int counter = 0;
			while(itr.hasNext() && (counter < Integer.parseInt(pr.getProperty("lines.to.use")) || Integer.parseInt(pr.getProperty("lines.to.use")) < 0 )) {
				processline(itr.next());
				counter++;
			}
			constructOrderItemPackageList();
			constructTranRec();
			IOOps.writePassFile(passLine,pr);
			
			if(new Boolean(pr.getProperty("ftp.file"))) {
				SFTPFile.sendFile(pr);
				System.out.println("PASS File has been generated and ftp'ed to sftp location");						
			}
			
			if(new Boolean(pr.getProperty("send.mail"))) {
				SendFileEmail.sendEmail(pr);
				System.out.println("e-mail with attachment has been sent");
			}
			System.out.println("Exiting Program");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Exception caught, please check the logs");
			e.printStackTrace();
		}
	}

	private static void processline(String line) {
		System.out.println(line);
		FileTranslator ft = FileTranslator.getInstance();
		String st [] = line.split(",");
		if(ft.ord == null) {
			//System.out.println("first Order");
			init();
			loadOrder(st);
			loadItem(st);
			loadPackage(st);
		} else if(ft.ord != null && !ft.ord.getOrderId().equals(st[Integer.parseInt(pr.getProperty("order.id.posn"))].trim())) {
			//System.out.println("New Order");
			constructOrderItemPackageList();
			init();
			loadOrder(st);
			loadItem(st);
			loadPackage(st);			
		} else {
			//System.out.println("Same Order");
			if(checkCurrentPackage(st[Integer.parseInt(pr.getProperty("hu.id.posn"))].trim())) {
				System.out.println("same Package hence loading item only");
				loadItem(st);
			} else {
				loadItem(st);
				loadPackage(st);
			}
		}
	}
	
	private static void init() {
		ft.ord = new OrderDAO();
		ft.itemArr = new ArrayList<ItemDAO>();
		ft.packArr = new ArrayList<PackageDAO>();
	}

	private static void loadOrder(String[] st) {
		ft.ord.setRecType(pr.getProperty("order.type"));
		ft.ord.setClientId(pr.getProperty("client.id"));		
		ft.ord.setOrderId(st[Integer.parseInt(pr.getProperty("order.id.posn"))].trim());
		ft.ord.setcName(st[Integer.parseInt(pr.getProperty("name.posn"))].trim());
		ft.ord.setcAddr1(st[Integer.parseInt(pr.getProperty("addr1.posn"))].trim());
		ft.ord.setcAddr2(st[Integer.parseInt(pr.getProperty("addr2.posn"))].trim());
		ft.ord.setcCity(st[Integer.parseInt(pr.getProperty("city.posn"))].trim());
		ft.ord.setcArea(st[Integer.parseInt(pr.getProperty("state.posn"))].trim());
		ft.ord.setcPstcode(st[Integer.parseInt(pr.getProperty("postal.code.posn"))].trim());
		ft.ord.setcCountry(pr.getProperty("dest.country"));
		ft.ord.setcPhone(st[Integer.parseInt(pr.getProperty("phone.posn"))].trim());
		ft.ord.setDateDirShpt(st[Integer.parseInt(pr.getProperty("shipp.date.posn"))].trim());
		
		//System.out.println(ft.ord.toString());
		orderCount ++;
	}

	private static void loadItem(String[] st) {
		ItemDAO id = new ItemDAO();
		id.setRecType(pr.getProperty("item.type"));
		id.setClientId(pr.getProperty("client.id"));
		id.setOrderId(st[Integer.parseInt(pr.getProperty("order.id.posn"))].trim());
		id.setSku(st[Integer.parseInt(pr.getProperty("sku.posn"))].trim());
		String itmdesc = st[Integer.parseInt(pr.getProperty("sku.desc.posn"))].trim();
		if(itmdesc.length()>50) {
			id.setItemDesc(itmdesc.substring(0, 49));
			id.setItemDesc2(itmdesc.substring(50));
		}else
			id.setItemDesc(itmdesc);
		String qty = st[Integer.parseInt(pr.getProperty("quantity.posn"))].trim();
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.UP);
		Float price = Float.parseFloat(st[Integer.parseInt(pr.getProperty("unit.price.posn"))].trim())
						*  Integer.parseInt(qty);
		id.setItemQty(qty);
		id.setiValue(df.format(price).toString());
		id.setCurCode(st[Integer.parseInt(pr.getProperty("currency.posn"))].trim());
		id.setHsNumber(st[Integer.parseInt(pr.getProperty("hts.code.posn"))].trim());
		id.setCo(st[Integer.parseInt(pr.getProperty("nafta.producer.posn"))].trim());
		id.setPstExempt(" ");
		//System.out.println(id.toString());
		ft.itemArr.add(id);
		
		itemCount++;
	}

	private static void loadPackage(String[] st) {
		PackageDAO pd = new PackageDAO();
		pd.setRecType(pr.getProperty("package.type"));
		pd.setClientId(pr.getProperty("client.id"));
		pd.setOrderId(st[Integer.parseInt(pr.getProperty("order.id.posn"))].trim());
		pd.setPackId(st[Integer.parseInt(pr.getProperty("hu.id.posn"))].trim());
		Float wt = Float.parseFloat(st[Integer.parseInt(pr.getProperty("weight.posn"))].trim());
		pd.setPackWt((wt.toString()));
		pd.setUofMeas(pr.getProperty("uom"));
		
		//System.out.println(pd.toString());
		ft.packArr.add(pd);
		
		packageCount++;
	}
	
	private static void constructOrderItemPackageList() {
		constructAndFormatOrder();
		constructAndFormatItem();
		constructAndFormatPackage();
	}

	private static void constructAndFormatOrder() {
		String order = ft.ord.getRecType() + format(ft.ord.getClientId(),false,Integer.parseInt(pr.getProperty("client.id.length"))) 
										   + format(ft.ord.getOrderId(),false,Integer.parseInt(pr.getProperty("order.id.length")))
										   + format(ft.ord.getcName(),false,Integer.parseInt(pr.getProperty("name.length")))
										   + format(ft.ord.getcAddr1(),false,Integer.parseInt(pr.getProperty("addr1.length")))
										   + format(ft.ord.getcAddr2(),false,Integer.parseInt(pr.getProperty("addr2.length")))
										   + format(ft.ord.getcCity(),false,Integer.parseInt(pr.getProperty("city.length")))
										   + format(ft.ord.getcArea(),false,Integer.parseInt(pr.getProperty("state.length")))
										   + format(ft.ord.getcPstcode(),false,Integer.parseInt(pr.getProperty("postal.code.length")))
										   + format(ft.ord.getcCountry(),false,Integer.parseInt(pr.getProperty("country.length")))
										   + format(ft.ord.getcPhone(),false,Integer.parseInt(pr.getProperty("phone.length")))
										   + format(specialformat(ft.ord.getDateDirShpt(),"Date"),false,Integer.parseInt(pr.getProperty("date.ship.length")))
									       + format("",false,Integer.parseInt(pr.getProperty("order.filler.length")));
		
		passLine.add(order.replaceAll("NULL", "    "));								   
		//System.out.println("Order = "+order);
	}

	private static void constructAndFormatItem() {
		String item;
		for(ItemDAO id:ft.itemArr) {
			item = id.getRecType() + format(id.getClientId(),false,Integer.parseInt(pr.getProperty("client.id.length")))
								   + format(id.getOrderId(),false,Integer.parseInt(pr.getProperty("order.id.length")))
								   + format(id.getSku(),false,Integer.parseInt(pr.getProperty("sku.length")))
								   + format(id.getItemDesc(),false,Integer.parseInt(pr.getProperty("item.desc.length")))
								   + format(id.getItemQty(),false,Integer.parseInt(pr.getProperty("item.qty.length")))
								   + decimalformat(id.getiValue(),pr.getProperty("item.value.length"))
								   + format(id.getCurCode(),false,Integer.parseInt(pr.getProperty("curr.code.length")))
								   + format(specialformat(id.getHsNumber(),"Dot"),false,Integer.parseInt(pr.getProperty("hs.nbr.length")))
								   + format(id.getCo(),false,Integer.parseInt(pr.getProperty("co.length")))
								   + format(id.getPstExempt(),false,Integer.parseInt(pr.getProperty("pst.exempt.length")))
								   + format(id.getItemDesc2(),false,Integer.parseInt(pr.getProperty("item.desc2.length")))
								   + format("",false,Integer.parseInt(pr.getProperty("item.filler.length")));
			passLine.add(item.replaceAll("NULL", "    "));
		}
		
	}
	
	private static void constructAndFormatPackage() {
		String pkg;
		for(PackageDAO pd:ft.packArr) {
			pkg = pd.getRecType() + format(pd.getClientId(),false,Integer.parseInt(pr.getProperty("client.id.length")))
								  + format(pd.getOrderId(),false,Integer.parseInt(pr.getProperty("order.id.length")))
								  + format(pd.getPackId(),false,Integer.parseInt(pr.getProperty("hu.id.length")))
								  + decimalformat(pd.getPackWt(),pr.getProperty("weight.length"))
								  + pd.getUofMeas().trim()
								  + format("",false,152);  //152 chars of Package string is blank as per mapping
			passLine.add(pkg.replaceAll("NULL", "    "));
		}
	}

	private static void constructTranRec() {
		ft.trans.setClientId(pr.getProperty("client.id"));
		ft.trans.setRecType(pr.getProperty("transaction.type"));
		ft.trans.setRecordSO(String.valueOf(orderCount));
		ft.trans.setRecordSI(String.valueOf(itemCount));
		ft.trans.setRecordSP(String.valueOf(packageCount));
		ft.trans.setTranId(pr.getProperty("tran.id"));
		ft.trans.setTranDat(getTodaysDate());
		
		String tran = ft.trans.getRecType() + format(ft.trans.getClientId(),false,Integer.parseInt(pr.getProperty("client.id.length")))
											+ ft.trans.getTranDat()
											+ ft.trans.getTranId()
											+ format("",false,Integer.parseInt(pr.getProperty("tran.filler1.length")))
											+ format(ft.trans.getRecordSO(),true,Integer.parseInt(pr.getProperty("order.count.length")))
											+ format(ft.trans.getRecordSI(),true,Integer.parseInt(pr.getProperty("item.count.length")))
											+ format(ft.trans.getRecordSP(),true,Integer.parseInt(pr.getProperty("package.count.length")))
											+ format("",false,Integer.parseInt(pr.getProperty("tran.filler2.length")));
		passLine.add(tran.replaceAll("NULL", ""));
	}

	private static String getTodaysDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
		return dateFormat.format(date);
	}
	
	private static String decimalformat(String packWt, String leng) {
		String [] wt = packWt.split("\\.");
		String [] le = leng.split("\\.");
		return new StringBuffer().append(format(wt[0],true,Integer.parseInt(le[0]))).append(format(wt[1],true,Integer.parseInt(le[1]))).toString();
	}	

	private static String specialformat(String str, String type) {
		if(type.equals("Date")) 
			return str.replaceAll("-", "");
		if(type.equals("Dot"))
			return str.replaceAll("\\.", "");
		return str;
	}

	private static String format(String str,boolean fillZero, int i) {
		StringBuffer sb = new StringBuffer(str==null?"":str);
		if(sb.length()<i)
			while(i-sb.length() > 0) {
				if(!fillZero)
					sb.append(' ');
				else
					sb.insert(0, "0");
			}
		return sb.toString();
	}

	private static boolean checkCurrentPackage(String st) {
		boolean found = false;
		if(packageCount > 0)
		{
			Iterator<PackageDAO> it = ft.packArr.iterator();
			while(it.hasNext() && !found) {
				if(st.equals(it.next().getPackId()))
					found = true;
			}
		}
		return found;
	}

	@SuppressWarnings("unused")
	private static Properties loadProperties(Properties pr) {
		FileReader reader;
		try {
			reader = new FileReader("PASS.properties");
			pr.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pr;
	}

	@Override
	public String toString() {
		return "FileTranslator [ord=" + ord + ", trans=" + trans + ", itemArr=" + itemArr + ", packArr=" + packArr
				+ "]";
	}

}
