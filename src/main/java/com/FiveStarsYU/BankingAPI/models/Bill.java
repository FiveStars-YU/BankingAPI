package com.FiveStarsYU.BankingAPI.models;

import javax.persistence.*;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="billId")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BillStatus billStatus; //An enumerated value representing the Status of the bill that serializes to a string
    private String payee; //The entity the bill will be paid to
    private String nickname; //A nickname for the bill to help identify it
    private String creation_date; //Date the bill was created
    private String payment_date; //Date when bill is going to be paid or was paid
    private Integer recurring_date; //Day of month bill will recur
    private String upcoming_payment_date; // Next bill payment date, calculated from recurring date
    private Double payment_amount; //Bill amount
    private String account_id; //ID of account that this bill is associated with

    public Bill() {
    }


    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public Integer getRecurring_date() {
        return recurring_date;
    }

    public void setRecurring_date(Integer recurring_date) {
        this.recurring_date = recurring_date;
    }

    public String getUpcoming_payment_date() {
        return upcoming_payment_date;
    }

    public void setUpcoming_payment_date(String upcoming_payment_date) {
        this.upcoming_payment_date = upcoming_payment_date;
    }

    public Double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(Double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billStatus=" + billStatus +
                ", payee='" + payee + '\'' +
                ", nickname='" + nickname + '\'' +
                ", creation_date='" + creation_date + '\'' +
                ", payment_date='" + payment_date + '\'' +
                ", recurring_date=" + recurring_date +
                ", upcoming_payment_date='" + upcoming_payment_date + '\'' +
                ", payment_amount=" + payment_amount +
                ", account_id='" + account_id + '\'' +
                '}';
    }
}
