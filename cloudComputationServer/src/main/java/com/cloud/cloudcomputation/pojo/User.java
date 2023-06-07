package com.cloud.cloudcomputation.pojo;


/**
* 
* @TableName User
*/
public class User {

    /**
    * 
    */
    private Integer userId;
    /**
    * 
    */
    private Integer userPrimary;
    /**
    * 
    */
    private String userPassword;
    /**
    * 
    */
    private String userName;

    private int userScore;

    public User(Integer userId, Integer userPrimary, String userPassword, String userName, int userScore) {
        this.userId = userId;
        this.userPrimary = userPrimary;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userScore = userScore;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public User(Integer userid, Integer userprimary, String userpassword, String username) {
        this.userId = userid;
        this.userPrimary = userprimary;
        this.userPassword = userpassword;
        this.userName = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userPrimary=" + userPrimary +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userScore=" + userScore +
                '}';
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserPrimary() {
        return userPrimary;
    }

    public void setUserPrimary(Integer userPrimary) {
        this.userPrimary = userPrimary;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
