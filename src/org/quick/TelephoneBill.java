package org.quick;

public class TelephoneBill {
  private int serialNo;
private String companyId;
private String customerName;
private String contactNo;
private String address;
private String telephoneId;
private String date;
private String dueDate;
private String plan;
private String total;
private String status; 

    public TelephoneBill( String companyId, String customerName, String contactNo, String address, String telephoneId, String date, String dueDate, String plan, String total, String status) {
        
        this.companyId = companyId;
        this.customerName = customerName;
        this.contactNo = contactNo;
        this.address = address;
        this.telephoneId = telephoneId;
        this.date = date;
        this.dueDate = dueDate;
        this.plan = plan;
        this.total = total;
        this.status = status;
    }

    public TelephoneBill() {
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(String telephoneId) {
        this.telephoneId = telephoneId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
