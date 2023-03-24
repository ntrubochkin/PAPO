package com.noname.books_exchange.model;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class UserMsg {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idUserMsg;
    private int idUser;
    private Timestamp createAt;
    private String text;
    private String notes;
    private int idStatus;
    private int type;

    public int getIdUserMsg() {
        return idUserMsg;
    }

    public void setIdUserMsg(int idUserMsg) {
        this.idUserMsg = idUserMsg;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMsg userMsg = (UserMsg) o;

        if (idUserMsg != userMsg.idUserMsg) return false;
        if (idUser != userMsg.idUser) return false;
        if (idStatus != userMsg.idStatus) return false;
        if (type != userMsg.type) return false;
        if (createAt != null ? !createAt.equals(userMsg.createAt) : userMsg.createAt != null) return false;
        if (text != null ? !text.equals(userMsg.text) : userMsg.text != null) return false;
        if (notes != null ? !notes.equals(userMsg.notes) : userMsg.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserMsg;
        result = 31 * result + idUser;
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + idStatus;
        result = 31 * result + type;
        return result;
    }
}
