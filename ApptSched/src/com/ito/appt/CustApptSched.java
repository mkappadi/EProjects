/**
 * 
 */
package com.ito.appt;

import java.util.Date;

/**
 * @author Mohan Kappadi
 *
 */
public class CustApptSched {

	private int appt_id;
	 
	private String wh_id;
	
	private String asn_nbr;
	
	private String po_nbr;
	
	private String trailer_id;
	
	private int number_of_cartons;
	
	private Date expected_date;
	
	private String carrier_scac;
	
	private String seal_nbr;
	
	private Date actual_ship_date;
	
	private Date created_dttm;
	
	private Date arthur_upload_dttm;
	
	private Date fcs_run_dttm;
	
	private Date actual_arrival_dttm;
	
	private Date actual_unload_dttm;
	
	public CustApptSched() {
		
		this.wh_id = null;
		this.asn_nbr = null;
		this.po_nbr = null;
		this.trailer_id = null;
		this.number_of_cartons = 0;
		this.expected_date = null;
		this.carrier_scac = null;
		this.seal_nbr = null;
		this.actual_ship_date = null;
		this.created_dttm = null;
		this.arthur_upload_dttm = null;
		this.fcs_run_dttm = null;
		this.actual_arrival_dttm = null;
		this.actual_unload_dttm = null;
	}

	public CustApptSched(int appt_id, String wh_id, String asn_nbr, String po_nbr, String trailer_id,
			int number_of_cartons, Date expected_date, String carrier_scac, String seal_nbr, Date actual_ship_date,
			Date created_dttm, Date arthur_upload_dttm, Date fcs_run_dttm, Date actual_arrival_dttm,
			Date actual_unload_dttm) {
		super();
		this.appt_id = appt_id;
		this.wh_id = wh_id;
		this.asn_nbr = asn_nbr;
		this.po_nbr = po_nbr;
		this.trailer_id = trailer_id;
		this.number_of_cartons = number_of_cartons;
		this.expected_date = expected_date;
		this.carrier_scac = carrier_scac;
		this.seal_nbr = seal_nbr;
		this.actual_ship_date = actual_ship_date;
		this.created_dttm = created_dttm;
		this.arthur_upload_dttm = arthur_upload_dttm;
		this.fcs_run_dttm = fcs_run_dttm;
		this.actual_arrival_dttm = actual_arrival_dttm;
		this.actual_unload_dttm = actual_unload_dttm;
	}

	int getAppt_id() {
		return appt_id;
	}

	void setAppt_id(int appt_id) {
		this.appt_id = appt_id;
	}

	String getWh_id() {
		return wh_id;
	}

	void setWh_id(String wh_id) {
		this.wh_id = wh_id;
	}

	String getAsn_nbr() {
		return asn_nbr;
	}

	void setAsn_nbr(String asn_nbr) {
		this.asn_nbr = asn_nbr;
	}

	String getPo_nbr() {
		return po_nbr;
	}

	void setPo_nbr(String po_nbr) {
		this.po_nbr = po_nbr;
	}

	String getTrailer_id() {
		return trailer_id;
	}

	void setTrailer_id(String trailer_id) {
		this.trailer_id = trailer_id;
	}

	int getNumber_of_cartons() {
		return number_of_cartons;
	}

	void setNumber_of_cartons(int number_of_cartons) {
		this.number_of_cartons = number_of_cartons;
	}

	Date getExpected_date() {
		return expected_date;
	}

	void setExpected_date(Date expected_date) {
		this.expected_date = expected_date;
	}

	String getCarrier_scac() {
		return carrier_scac;
	}

	void setCarrier_scac(String carrier_scac) {
		this.carrier_scac = carrier_scac;
	}

	String getSeal_nbr() {
		return seal_nbr;
	}

	void setSeal_nbr(String seal_nbr) {
		this.seal_nbr = seal_nbr;
	}

	Date getActual_ship_date() {
		return actual_ship_date;
	}

	void setActual_ship_date(Date actual_ship_date) {
		this.actual_ship_date = actual_ship_date;
	}

	Date getCreated_dttm() {
		return created_dttm;
	}

	void setCreated_dttm(Date created_dttm) {
		this.created_dttm = created_dttm;
	}

	Date getArthur_upload_dttm() {
		return arthur_upload_dttm;
	}

	void setArthur_upload_dttm(Date arthur_upload_dttm) {
		this.arthur_upload_dttm = arthur_upload_dttm;
	}

	Date getFcs_run_dttm() {
		return fcs_run_dttm;
	}

	void setFcs_run_dttm(Date fcs_run_dttm) {
		this.fcs_run_dttm = fcs_run_dttm;
	}

	Date getActual_arrival_dttm() {
		return actual_arrival_dttm;
	}

	void setActual_arrival_dttm(Date actual_arrival_dttm) {
		this.actual_arrival_dttm = actual_arrival_dttm;
	}

	Date getActual_unload_dttm() {
		return actual_unload_dttm;
	}

	void setActual_unload_dttm(Date actual_unload_dttm) {
		this.actual_unload_dttm = actual_unload_dttm;
	}

	@Override
	public String toString() {
		return "CustApptSched [appt_id=" + appt_id + ", wh_id=" + wh_id + ", asn_nbr=" + asn_nbr + ", po_nbr=" + po_nbr
				+ ", trailer_id=" + trailer_id + ", number_of_cartons=" + number_of_cartons + ", expected_date="
				+ expected_date + ", carrier_scac=" + carrier_scac + ", seal_nbr=" + seal_nbr + ", actual_ship_date="
				+ actual_ship_date + ", created_dttm=" + created_dttm + ", arthur_upload_dttm=" + arthur_upload_dttm
				+ ", fcs_run_dttm=" + fcs_run_dttm + ", actual_arrival_dttm=" + actual_arrival_dttm
				+ ", actual_unload_dttm=" + actual_unload_dttm + "]";
	}


	
}