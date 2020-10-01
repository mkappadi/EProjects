/**
 * 
 */
package com.ito.dao;

/**
 * @author Mohan Kappadi
 *
 */
public class TransactionDAO {

	/**
	 *  DAO to hold Transaciton Object
	 */
	
	String recType;
	String clientId;
	String tranDat;
	String tranId;
	String recordSO;
	String recordSI;
	String recordSP;
	
	public TransactionDAO() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param recType
	 * @param clientId
	 */
	public TransactionDAO(String recType, String clientId) {
		super();
		this.recType = recType;
		this.clientId = clientId;
	}


	/**
	 * @param recType
	 * @param clientId
	 * @param tranId
	 */
	public TransactionDAO(String recType, String clientId, String tranId) {
		super();
		this.recType = recType;
		this.clientId = clientId;
		this.tranId = tranId;
	}


	/**
	 * @param recType
	 * @param clientId
	 * @param tranDat
	 * @param tranId
	 * @param recordSO
	 * @param recordSI
	 * @param recordSP
	 */
	public TransactionDAO(String recType, String clientId, String tranDat, String tranId, String recordSO,
			String recordSI, String recordSP) {
		super();
		this.recType = recType;
		this.clientId = clientId;
		this.tranDat = tranDat;
		this.tranId = tranId;
		this.recordSO = recordSO;
		this.recordSI = recordSI;
		this.recordSP = recordSP;
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
	 * @return the tranDat
	 */
	public String getTranDat() {
		return tranDat;
	}


	/**
	 * @param tranDat the tranDat to set
	 */
	public void setTranDat(String tranDat) {
		this.tranDat = tranDat;
	}


	/**
	 * @return the tranId
	 */
	public String getTranId() {
		return tranId;
	}


	/**
	 * @param tranId the tranId to set
	 */
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}


	/**
	 * @return the recordSO
	 */
	public String getRecordSO() {
		return recordSO;
	}


	/**
	 * @param recordSO the recordSO to set
	 */
	public void setRecordSO(String recordSO) {
		this.recordSO = recordSO;
	}


	/**
	 * @return the recordSI
	 */
	public String getRecordSI() {
		return recordSI;
	}


	/**
	 * @param recordSI the recordSI to set
	 */
	public void setRecordSI(String recordSI) {
		this.recordSI = recordSI;
	}


	/**
	 * @return the recordSP
	 */
	public String getRecordSP() {
		return recordSP;
	}


	/**
	 * @param recordSP the recordSP to set
	 */
	public void setRecordSP(String recordSP) {
		this.recordSP = recordSP;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionDAO [recType=" + recType + ", clientId=" + clientId + ", tranDat=" + tranDat + ", tranId="
				+ tranId + ", recordSO=" + recordSO + ", recordSI=" + recordSI + ", recordSP=" + recordSP + "]";
	}

	
}
