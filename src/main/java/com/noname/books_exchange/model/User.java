package com.noname.books_exchange.model;

import java.sql.Timestamp;
import java.util.Arrays;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DearUser")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdUser", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "FirstName", nullable = false, length = 25)
    private String firstName;
    @Basic
    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;
    @Basic
    @Column(name = "SurName", nullable = true, length = 25)
    private String surName;
    @Basic
    @Column(name = "Email", nullable = false, length = 256)
    private String email;
    @Basic
    @Column(name = "Password", nullable = false, length = 128)
    private String password;
    @Basic
    @Column(name = "Rating", nullable = false)
    private int rating;
    @Basic
    @Column(name = "CreatedAt", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "Enabled", nullable = false)
    private boolean enabled;
    @Basic
    @Column(name = "Avatar", nullable = true)
    private byte[] avatar;
    @Basic
    @Column(name = "IsStaff", nullable = false)
    private boolean isStaff;
    @Basic
    @Column(name = "IsSuperAdmin", nullable = false)
    private boolean isSuperAdmin;
    @Basic
    @Column(name = "UserName", nullable = false, length = 20)
    private String userName;

    public User() { }

    //VK constructor
    public User(String firstName, String lastName, String email, byte[] avatar, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
        this.userName = userName;
        this.password = "hehehehe";
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public User(String firstName, String lastName, String surName, String email, String password, byte[] avatar, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
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
