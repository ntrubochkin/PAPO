package com.noname.books_exchange.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class VerificationInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "generatedAt", nullable = false)
    private Timestamp generatedAt;
    @Basic
    @Column(name = "generatedString", nullable = false, length = 128)
    private String generatedString;

    public int getIdUser() {
        return idUser;
    }

    public VerificationInfo() { }

    public VerificationInfo(int idUser, String generatedString) {
        this.idUser = idUser;
        this.generatedString = generatedString;
        this.generatedAt = new Timestamp(System.currentTimeMillis());
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Timestamp getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Timestamp generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getGeneratedString() {
        return generatedString;
    }

    public void setGeneratedString(String generatedString) {
        this.generatedString = generatedString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationInfo that = (VerificationInfo) o;

        if (idUser != that.idUser) return false;
        if (generatedAt != null ? !generatedAt.equals(that.generatedAt) : that.generatedAt != null) return false;
        if (generatedString != null ? !generatedString.equals(that.generatedString) : that.generatedString != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (generatedAt != null ? generatedAt.hashCode() : 0);
        result = 31 * result + (generatedString != null ? generatedString.hashCode() : 0);
        return result;
    }
}
