/**
 * 
 */
package com.ito.dao;

/**
 * @author Mohan Kappadi
 *
 */
public class OrderDAO {

	/**
	 *  Stores the information of Order Object
	 */
	
	String recType;
	String clientId;
	String orderId;
	String cName;
	String cAddr1;
	String cAddr2;
	String cCity;
	String cArea;
	String cPstcode;
	String cCountry;
	String cPhone;
	String dateDirShpt;

	
	public OrderDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param recType
	 * @param clientId
	 */
	public OrderDAO(String recType, String clientId) {
		super();
		this.recType = recType;
		this.clientId = clientId;
	}
	
	
	/**
	 * @param recType
	 * @param clientId
	 * @param orderId
	 */
	public OrderDAO(String recType, String clientId, String orderId) {
		super();
		this.recType = recType;
		this.clientId = clientId;
		this.orderId = orderId;
	}

	/**
	 * @param recType
	 * @param clientId
	 * @param orderId
	 * @param cName
	 * @param cAddr1
	 * @param cAddr2
	 * @param cCity
	 * @param cArea
	 * @param cPstcode
	 * @param cCountry
	 * @param cPhone
	 * @param dateDirShpt
	 */
	public OrderDAO(String recType, String clientId, String orderId, String cName, String cAddr1, String cAddr2,
			String cCity, String cArea, String cPstcode, String cCountry, String cPhone, String dateDirShpt) {
		super();
		this.recType = recType;
		this.clientId = clientId;
		this.orderId = orderId;
		this.cName = cName;
		this.cAddr1 = cAddr1;
		this.cAddr2 = cAddr2;
		this.cCity = cCity;
		this.cArea = cArea;
		this.cPstcode = cPstcode;
		this.cCountry = cCountry;
		this.cPhone = cPhone;
		this.dateDirShpt = dateDirShpt;
	}


	/**
	 * @return the recType
	 */
	public String getRecType() {
		return recType;
	}
	/**
	 * @param recType the recType to set
	 */
	public void setRecType(String recType) {
		this.recType = recType;
	}
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the cName
	 */
	public String getcName() {
		return cName;
	}
	/**
	 * @param cName the cName to set
	 */
	public void setcName(String cName) {
		this.cName = cName;
	}
	/**
	 * @return the cAddr1
	 */
	public String getcAddr1() {
		return cAddr1;
	}
	/**
	 * @param cAddr1 the cAddr1 to set
	 */
	public void setcAddr1(String cAddr1) {
		this.cAddr1 = cAddr1;
	}
	/**
	 * @return the cAddr2
	 */
	public String getcAddr2() {
		return cAddr2;
	}
	/**
	 * @param cAddr2 the cAddr2 to set
	 */
	public void setcAddr2(String cAddr2) {
		this.cAddr2 = cAddr2;
	}
	/**
	 * @return the cCity
	 */
	public String getcCity() {
		return cCity;
	}
	/**
	 * @param cCity the cCity to set
	 */
	public void setcCity(String cCity) {
		this.cCity = cCity;
	}
	/**
	 * @return the cArea
	 */
	public String getcArea() {
		return cArea;
	}
	/**
	 * @param cArea the cArea to set
	 */
	public void setcArea(String cArea) {
		this.cArea = cArea;
	}
	/**
	 * @return the cPstcode
	 */
	public String getcPstcode() {
		return cPstcode;
	}
	/**
	 * @param cPstcode the cPstcode to set
	 */
	public void setcPstcode(String cPstcode) {
		this.cPstcode = cPstcode;
	}
	/**
	 * @return the cCountry
	 */
	public String getcCountry() {
		return cCountry;
	}
	/**
	 * @param cCountry the cCountry to set
	 */
	public void setcCountry(String cCountry) {
		this.cCountry = cCountry;
	}
	/**
	 * @return the cPhone
	 */
	public String getcPhone() {
		return cPhone;
	}
	/**
	 * @param cPhone the cPhone to set
	 */
	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	/**
	 * @return the dateDirShpt
	 */
	public String getDateDirShpt() {
		return dateDirShpt;
	}
	/**
	 * @param dateDirShpt the dateDirShpt to set
	 */
	public void setDateDirShpt(String dateDirShpt) {
		this.dateDirShpt = dateDirShpt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderDAO [recType=" + recType + ", clientId=" + clientId + ", orderId=" + orderId + ", cName=" + cName
				+ ", cAddr1=" + cAddr1 + ", cAddr2=" + cAddr2 + ", cCity=" + cCity + ", cArea=" + cArea + ", cPstcode="
				+ cPstcode + ", cCountry=" + cCountry + ", cPhone=" + cPhone + ", dateDirShpt=" + dateDirShpt + "]";
	}
	
}
