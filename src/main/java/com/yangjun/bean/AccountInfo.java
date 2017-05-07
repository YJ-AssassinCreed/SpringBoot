package com.yangjun.bean;

/**
 * Created by admin on 2017/2/26.
 */
public class AccountInfo {
    private int id;
    private String account_name;
    private String mail;
    private String m_tel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getM_tel() {
        return m_tel;
    }

    public void setM_tel(String m_tel) {
        this.m_tel = m_tel;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "id=" + id +
                ", account_name='" + account_name + '\'' +
                ", mail='" + mail + '\'' +
                ", m_tel='" + m_tel + '\'' +
                '}';
    }
}
