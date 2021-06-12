package org.quick;

public class Company {
private String companyId;
private String companyName;
private String registrationId;
private String contactNo;
private String address;
private String gstNo;
private String billType;
private String password;

    public Company(String companyId, String companyName, String registrationId, String contactNo, String address, String gstNo, String billType, String password) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.registrationId = registrationId;
        this.contactNo = contactNo;
        this.address = address;
        this.gstNo = gstNo;
        this.billType = billType;
        this.password = password;
    }

    public Company() {
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
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

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
