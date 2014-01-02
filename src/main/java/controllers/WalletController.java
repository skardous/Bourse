package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.activation.UnknownObjectException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ejb.AccountBean;
import ejb.WalletBean;
import service.AccountService;
import model.Compte;
import model.Portefeuille;

@ManagedBean
@ViewScoped
public class WalletController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{sessionController.session.connectedUser.portefeuille}")
	private Portefeuille wallet;
	
	@EJB
	private WalletBean bean;
	
	private String orderCompanyCode;
	private String orderCompanySE;
	private int orderNumber;

	public Portefeuille getWallet() {
		return wallet;
	}

	public void setWallet(Portefeuille wallet) {
		this.wallet = wallet;
	}
	
	public void orderBuy() throws UnknownObjectException, MalformedURLException, IOException {
		bean.orderBuy(wallet, orderCompanyCode, orderCompanySE, orderNumber);
	}

	public String getOrderCompanyCode() {
		return orderCompanyCode;
	}

	public void setOrderCompanyCode(String orderCompanyCode) {
		this.orderCompanyCode = orderCompanyCode;
	}

	public String getOrderCompanySE() {
		return orderCompanySE;
	}

	public void setOrderCompanySE(String orderCompanySE) {
		this.orderCompanySE = orderCompanySE;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

}
