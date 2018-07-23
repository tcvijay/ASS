package com.Rabobank.util;

import java.math.BigDecimal;

public class CutomerStatementDetails {
private int reference;
private String AccountNumber;
private String Description;
private BigDecimal startBalance;
private BigDecimal mutation;
private BigDecimal EndBalance;



public int getReference() {
	return reference;
}
public void setReference(int reference) {
	this.reference = reference;
}
public String getAccountNumber() {
	return AccountNumber;
}
public void setAccountNumber(String accountNumber) {
	AccountNumber = accountNumber;
}
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
}
public BigDecimal getStartBalance() {
	return startBalance;
}
public void setStartBalance(BigDecimal startBalance) {
	this.startBalance = startBalance;
}
public BigDecimal getMutation() {
	return mutation;
}
public void setMutation(BigDecimal mutation) {
	this.mutation = mutation;
}
public BigDecimal getEndBalance() {
	return EndBalance;
}
public void setEndBalance(BigDecimal endBalance) {
	EndBalance = endBalance;
}



}
