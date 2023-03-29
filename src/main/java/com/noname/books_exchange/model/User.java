package com.noname.books_exchange.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "user_info")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idUser;
    private String firstName;
    private String lastName;
    private String surName;
    private String email;
    private String password;
    private int rating;
    private Timestamp createdAt;
    private boolean enabled;
    private byte[] avatar;
    private boolean isStaff;
    private boolean isSuperAdmin;
    private String userName;
    private String avatarType;

    @OneToMany(mappedBy = "user")
    private List<BookResponse> bookResponseList;

    @OneToMany(mappedBy = "user")
    private List<OfferList> offerLists;

    @OneToMany(mappedBy = "user")
    private List<WishList> wishLists;

    @OneToMany(mappedBy = "user")
    private List<UserAddress> userAddressList;

    @OneToMany(mappedBy = "user")
    private List<UserMsg> userMsgList;

    public User() { }

    public User(String firstName, String lastName, String surName, String email, String password, byte[] avatar, String avatarType, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.avatarType = avatarType;
        this.userName = userName;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surname) {
        this.surName = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarType() {
        return avatarType;
    }

    public void setAvatarType(String avatarType) {
        this.avatarType = avatarType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != user.idUser) return false;
        if (rating != user.rating) return false;
        if (enabled != user.enabled) return false;
        if (isStaff != user.isStaff) return false;
        if (isSuperAdmin != user.isSuperAdmin) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (surName != null ? !surName.equals(user.surName) : user.surName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (createdAt != null ? !createdAt.equals(user.createdAt) : user.createdAt != null) return false;
        if (!Arrays.equals(avatar, user.avatar)) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + Arrays.hashCode(avatar);
        result = 31 * result + (isStaff ? 1 : 0);
        result = 31 * result + (isSuperAdmin ? 1 : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
