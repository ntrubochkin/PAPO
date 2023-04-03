package com.noname.books_exchange.model;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class UserMsg {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idUserMsg;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    private Timestamp createAt;
    private String text;
    private String notes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status")
    private Status status;

    private int type;

    public int getIdUserMsg() {
        return idUserMsg;
    }

    public void setIdUserMsg(int idUserMsg) {
        this.idUserMsg = idUserMsg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        if (!user.equals(userMsg.user)) return false;
        if (!status.equals(userMsg.status)) return false;
        if (type != userMsg.type) return false;
        if (createAt != null ? !createAt.equals(userMsg.createAt) : userMsg.createAt != null) return false;
        if (text != null ? !text.equals(userMsg.text) : userMsg.text != null) return false;
        if (notes != null ? !notes.equals(userMsg.notes) : userMsg.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserMsg;
        result = 31 * result + user.getIdUser();
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + status.getIdStatus();
        result = 31 * result + type;
        return result;
    }
}
