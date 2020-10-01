/**
 * 
 */
package com.ito.dao;

/**
 * @author Mohan Kappadi
 *
 */
public class PackageDAO {

	/**
	 * DAO to hold Package information
	 */
	String recType;
	String clientId;
	String orderId;
	String packId;
	String packWt;
	String uofMeas;
	String overSize;
	String insAmt;
	String iCurCode;
	String carrCode;
	String shpOpt;
	String freight;
	
	public PackageDAO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param recType
	 * @param clientId
	 */
	public PackageDAO(String recType, String clientId) {
		super();
		this.recType = recType;
		this.clientId = clientId;
	}

	/**
	 * @param recType
	 * @param clientId
	 * @param orderId
	 * @param packId
	 */
	public PackageDAO(String recType, String clientId, String orderId, String packId) {
		super();
		this.recType = recType;
		this.clientId = clientId;
		this.orderId = orderId;
		this.packId = packId;
	}

	/**
	 * @param recType
	 * @param clientId
	 * @param orderId
	 * @param packId
	 * @param packWt
	 * @param uofMeas
	 * @param overSize
	 * @param insAmt
	 * @param iCurCode
	 * @param carrCode
	 * @param shpOpt
	 * @param freight
	 */
	public PackageDAO(String recType, String clientId, String orderId, String packId, String packWt, String uofMeas,
			String overSize, String insAmt, String iCurCode, String carrCode, String shpOpt, String freight) {
		super();
		this.recType = recType;
		this.clientId = clientId;
		this.orderId = orderId;
		this.packId = packId;
		this.packWt = packWt;
		this.uofMeas = uofMeas;
		this.overSize = overSize;
		this.insAmt = insAmt;
		this.iCurCode = iCurCode;
		this.carrCode = carrCode;
		this.shpOpt = shpOpt;
		this.freight = freight;
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
	 * @return the packId
	 */
	public String getPackId() {
		return packId;
	}

	/**
	 * @param packId the packId to set
	 */
	public void setPackId(String packId) {
		this.packId = packId;
	}

	/**
	 * @return the packWt
	 */
	public String getPackWt() {
		return packWt;
	}

	/**
	 * @param packWt the packWt to set
	 */
	public void setPackWt(String packWt) {
		this.packWt = packWt;
	}

	/**
	 * @return the uofMeas
	 */
	public String getUofMeas() {
		return uofMeas;
	}

	/**
	 * @param uofMeas the uofMeas to set
	 */
	public void setUofMeas(String uofMeas) {
		this.uofMeas = uofMeas;
	}

	/**
	 * @return the overSize
	 */
	public String getOverSize() {
		return overSize;
	}

	/**
	 * @param overSize the overSize to set
	 */
	public void setOverSize(String overSize) {
		this.overSize = overSize;
	}

	/**
	 * @return the insAmt
	 */
	public String getInsAmt() {
		return insAmt;
	}

	/**
	 * @param insAmt the insAmt to set
	 */
	public void setInsAmt(String insAmt) {
		this.insAmt = insAmt;
	}

	/**
	 * @return the iCurCode
	 */
	public String getiCurCode() {
		return iCurCode;
	}

	/**
	 * @param iCurCode the iCurCode to set
	 */
	public void setiCurCode(String iCurCode) {
		this.iCurCode = iCurCode;
	}

	/**
	 * @return the carrCode
	 */
	public String getCarrCode() {
		return carrCode;
	}

	/**
	 * @param carrCode the carrCode to set
	 */
	public void setCarrCode(String carrCode) {
		this.carrCode = carrCode;
	}

	/**
	 * @return the shpOpt
	 */
	public String getShpOpt() {
		return shpOpt;
	}

	/**
	 * @param shpOpt the shpOpt to set
	 */
	public void setShpOpt(String shpOpt) {
		this.shpOpt = shpOpt;
	}

	/**
	 * @return the freight
	 */
	public String getFreight() {
		return freight;
	}

	/**
	 * @param freight the freight to set
	 */
	public void setFreight(String freight) {
		this.freight = freight;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PackageDAO [recType=" + recType + ", clientId=" + clientId + ", orderId=" + orderId + ", packId="
				+ packId + ", packWt=" + packWt + ", uofMeas=" + uofMeas + ", overSize=" + overSize + ", insAmt="
				+ insAmt + ", iCurCode=" + iCurCode + ", carrCode=" + carrCode + ", shpOpt=" + shpOpt + ", freight="
				+ freight + "]";
	}
	
}
