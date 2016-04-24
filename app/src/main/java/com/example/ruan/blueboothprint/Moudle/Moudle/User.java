package com.example.ruan.blueboothprint.Moudle.Moudle;

import java.util.Map;

/**
 * Created by Administrator on 2016/3/17.
 */
public class User {

    /**
     * userid : 15119481373
     * truename :
     * password : 123456
     * company :
     * viplev : 0
     * sexy :
     * wbid :
     * qq :
     * wxid :
     * email :
     * icon : 17522620378192.jpg
     * picture :
     * d2code :
     * address : 北京市朝阳区
     * postcode :
     * idcard :
     * note :
     * registerdate : 2016/1/28 16:11:45
     * bankaccount :
     * bankname :
     * balance :
     * available :
     * state :
     * appversion :
     * Integral : 0
     * height : 175
     * birthday : 1994-7-4
     * occupation : 广东省上海
     * education : 大专
     * autograph : 明天放假
     * phone :
     */

    private String userid;
    private String truename;
    private String password;
    private String company;
    private String viplev;
    private String sexy;
    private String wbid;
    private String qq;
    private String wxid;
    private String email;
    private String icon;
    private String picture;
    private String d2code;
    private String address;
    private String postcode;
    private String idcard;
    private String note;
    private String registerdate;
    private String bankaccount;
    private String bankname;
    private String balance;
    private String available;
    private String state;
    private String appversion;
    private String Integral;
    private String height;
    private String birthday;
    private String occupation;
    private String education;
    private String autograph;
    private String phone;
    /**
     * nickname : 阮家辉
     */

    private String nickname;

    public User() {
    }

    public User(Map<String, String> map) {
        getMap(map);
    }

    public User getUser() {
        return this;
    }

    private void getMap(Map<String, String> map) {
        if (map != null) {
            setNickname(map.get("nickname"));
            setUserid(map.get("userid"));
            setPassword(map.get("password"));
            setAddress(map.get("address"));
            setAppversion(map.get("appversion"));
            setAutograph(map.get("autograph"));
            setAvailable(map.get("available"));
            setBalance(map.get("balance"));
            setBankaccount(map.get("bankaccount"));
            setCompany(map.get("company"));
            setBankname(map.get("bankname"));
            setD2code(map.get("d2code"));
            setEmail(map.get("email"));
            setEducation(map.get("education"));
            setBirthday(map.get("birthday"));
            setHeight(map.get("height"));
            setIcon(map.get("icon"));
            setWxid(map.get("wxid"));
            setWbid(map.get("wbid"));
            setViplev(map.get("viplev"));
            setTruename(map.get("truename"));
            setState(map.get("state"));
            setSexy(map.get("sexy"));
            setIntegral(map.get("integeral"));
            setQq(map.get("qq"));
            setRegisterdate(map.get("registerdate"));
            setPhone(map.get("phone"));
            setPostcode(map.get("postcode"));
            setIdcard(map.get("idcard"));
            setPicture(map.get("picture"));
            setNote(map.get("note"));
            setOccupation(map.get("occupation"));
        }
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setViplev(String viplev) {
        this.viplev = viplev;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public void setWbid(String wbid) {
        this.wbid = wbid;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setD2code(String d2code) {
        this.d2code = d2code;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public void setIntegral(String Integral) {
        this.Integral = Integral;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public String getTruename() {
        return truename;
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
    }

    public String getViplev() {
        return viplev;
    }

    public String getSexy() {
        return sexy;
    }

    public String getWbid() {
        return wbid;
    }

    public String getQq() {
        return qq;
    }

    public String getWxid() {
        return wxid;
    }

    public String getEmail() {
        return email;
    }

    public String getIcon() {
        return icon;
    }

    public String getPicture() {
        return picture;
    }

    public String getD2code() {
        return d2code;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getIdcard() {
        return idcard;
    }

    public String getNote() {
        return note;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public String getBankname() {
        return bankname;
    }

    public String getBalance() {
        return balance;
    }

    public String getAvailable() {
        return available;
    }

    public String getState() {
        return state;
    }

    public String getAppversion() {
        return appversion;
    }

    public String getIntegral() {
        return Integral;
    }

    public String getHeight() {
        return height;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getEducation() {
        return education;
    }

    public String getAutograph() {
        return autograph;
    }

    public String getPhone() {
        return phone;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
