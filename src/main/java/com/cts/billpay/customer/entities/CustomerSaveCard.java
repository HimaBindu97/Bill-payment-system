package com.cts.billpay.customer.entities;

public class CustomerSaveCard {
private Long CustomerId;
private String cardNumber;
private String cardType;
private String cardValidity;
private String cvv;

public Long getCustomerId() {
	return CustomerId;
}
public void setCustomerId(Long customerId) {
	CustomerId = customerId;
}
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
