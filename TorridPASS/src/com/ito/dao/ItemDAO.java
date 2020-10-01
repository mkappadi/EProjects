/**
 * 
 */
package com.ito.dao;

/**
 * @author Mohan Kappadi
 *
 */
public class ItemDAO {

	/**
	 * DAO to store item information 
	 */
	
	String recType;
	String clientId;
	String orderId;
	String sku;
	String itemDesc;
	String itemQty;
	String iValue;
	String curCode;
	String hsNumber;
	String co;
	String pstExempt;
	String itemDesc2;
	
	public ItemDAO() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param recType
	 * @param clientId
	 */
	public ItemDAO(String recType, String clientId) {
		super();
		this.recType = recType;
		this.clientId = clientId;
	}


	/**
	 * @param recType
	 * @param clientId
	 * @param orderId
	 * @param sku
	 */
	public ItemDAO(String recType, String clientId, String orderId, String sku) {
		super();
		this.recType = recType;
		this.clientId = clientId;
		this.orderId = orderId;
		this.sku = sku;
	}

	/**
	 * @param recType
	 * @param clientId
	 * @param orderId
	 * @param sku
	 * @param itemDesc
	 * @param itemQty
	 * @param iValue
	 * @param curCode
	 * @param hsNumber
	 * @param co
	 * @param pstExempt
	 * @param itemDesc2
	 */
	public ItemDAO(String recType, String clientId, String orderId, String sku, String itemDesc, String itemQty,
			String iValue, String curCode, String hsNumber, String co, String pstExempt, String itemDesc2) {
		super();
		this.recType = recType;
		this.clientId = clientId;
		this.orderId = orderId;
		this.sku = sku;
		this.itemDesc = itemDesc;
		this.itemQty = itemQty;
		this.iValue = iValue;
		this.curCode = curCode;
		this.hsNumber = hsNumber;
		this.co = co;
		this.pstExempt = pstExempt;
		this.itemDesc2 = itemDesc2;
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
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}


	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}


	/**
	 * @return the itemDesc
	 */
	public String getItemDesc() {
		return itemDesc;
	}


	/**
	 * @param itemDesc the itemDesc to set
	 */
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}


	/**
	 * @return the itemQty
	 */
	public String getItemQty() {
		return itemQty;
	}


	/**
	 * @param itemQty the itemQty to set
	 */
	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}


	/**
	 * @return the iValue
	 */
	public String getiValue() {
		return iValue;
	}


	/**
	 * @param iValue the iValue to set
	 */
	public void setiValue(String iValue) {
		this.iValue = iValue;
	}


	/**
	 * @return the curCode
	 */
	public String getCurCode() {
		return curCode;
	}


	/**
	 * @param curCode the curCode to set
	 */
	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}


	/**
	 * @return the hsNumber
	 */
	public String getHsNumber() {
		return hsNumber;
	}


	/**
	 * @param hsNumber the hsNumber to set
	 */
	public void setHsNumber(String hsNumber) {
		this.hsNumber = hsNumber;
	}


	/**
	 * @return the co
	 */
	public String getCo() {
		return co;
	}


	/**
	 * @param co the co to set
	 */
	public void setCo(String co) {
		this.co = co;
	}


	/**
	 * @return the pstExempt
	 */
	public String getPstExempt() {
		return pstExempt;
	}


	/**
	 * @param pstExempt the pstExempt to set
	 */
	public void setPstExempt(String pstExempt) {
		this.pstExempt = pstExempt;
	}


	/**
	 * @return the itemDesc2
	 */
	public String getItemDesc2() {
		return itemDesc2;
	}


	/**
	 * @param itemDesc2 the itemDesc2 to set
	 */
	public void setItemDesc2(String itemDesc2) {
		this.itemDesc2 = itemDesc2;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemDAO [recType=" + recType + ", clientId=" + clientId + ", orderId=" + orderId + ", sku=" + sku
				+ ", itemDesc=" + itemDesc + ", itemQty=" + itemQty + ", iValue=" + iValue + ", curCode=" + curCode
				+ ", hsNumber=" + hsNumber + ", co=" + co + ", pstExempt=" + pstExempt + ", itemDesc2=" + itemDesc2
				+ "]";
	}
	
	
}
