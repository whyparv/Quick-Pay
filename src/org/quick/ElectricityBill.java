package org.quick;

public class ElectricityBill {
private int serialNo;
private String companyId;
private String customerName;
private String contactNo;
private String address;
private String ivrsNo;
private String date;
private String dueDate;
private String meterNo;
private String phases;
private String unitConsumed;
private String pricePerUnit;
private String total;
private String status;

    public ElectricityBill() {
    }

    public ElectricityBill(String companyId, String customerName, String contactNo, String address, String ivrsNo, String date, String dueDate, String meterNo, String phases, String unitConsumed, String pricePerUnit, String total, String status) {
        
        this.companyId = companyId;
        this.customerName = customerName;
        this.contactNo = contactNo;
        this.address = address;
        this.ivrsNo = ivrsNo;
        this.date = date;
        this.dueDate = dueDate;
        this.meterNo = meterNo;
        this.phases = phases;
        this.unitConsumed = unitConsumed;
        this.pricePerUnit = pricePerUnit;
        this.total = total;
        this.status = status;
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

    public String getIvrsNo() {
        return ivrsNo;
    }

    public void setIvrsNo(String ivrsNo) {
        this.ivrsNo = ivrsNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public String getPhases() {
        return phases;
    }

    public void setPhases(String phases) {
        this.phases = phases;
    }

    public String getUnitConsumed() {
        return unitConsumed;
    }

    public void setUnitConsumed(String unitConsumed) {
        this.unitConsumed = unitConsumed;
    }

    public String getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(String pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
