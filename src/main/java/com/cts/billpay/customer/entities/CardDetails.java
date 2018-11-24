package com.cts.billpay.customer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="card_details")
public class CardDetails {

	public CardDetails() {
		
	}
	
	@Id
	private String cardNumber;
	@Column
	private String cardType;
	@Column
	private String cardValidity;
	@Column
	private String cvv;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardValidity() {
		return cardValidity;
	}
	public void setCardValidity(String cardValidity) {
		this.cardValidity = cardValidity;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
}
