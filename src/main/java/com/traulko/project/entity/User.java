package com.traulko.project.entity;

/**
 * The {@code User} class represents User entity.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class User {
    public enum Role {
        ADMIN, USER, GUEST;
    }

    public enum Status {
        ENABLE, BLOCKED, NOT_CONFIRMED;
    }

    private Integer userId;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private Role role;
    private Status status;
    private double balance;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new CustomImage.
     *
     * @param userId the user index
     * @param name the name
     * @param balance the balance
     * @param email the email
     * @param patronymic the patronymic
     * @param role the role
     * @param status the status
     * @param surname the surname
     */
    public User(Integer userId, String email, String name, String surname, String patronymic, Role role,
                Status status, double balance) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.role = role;
        this.status = status;
        this.balance = balance;
    }

    /**
     * Gets User balance.
     *
     * @return the User balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets Product description.
     *
     * @param description the Product description
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets User id.
     *
     * @return the User id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets User user index.
     *
     * @param userId the User user index
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets User name.
     *
     * @return the User name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets User name.
     *
     * @param name the User name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets User surname.
     *
     * @return the User surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets User surname.
     *
     * @param surname the User surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets User patronymic.
     *
     * @return the User patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Sets User patronymic.
     *
     * @param patronymic the User patronymic
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Gets User email.
     *
     * @return the User email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets User email.
     *
     * @param email the User email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets User role.
     *
     * @return the User role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets User role.
     *
     * @param role the User role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets User status.
     *
     * @return the User status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets User status.
     *
     * @param status the User status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (patronymic == null) {
            if (other.patronymic != null) {
                return false;
            }
        } else if (!patronymic.equals(other.patronymic)) {
            return false;
        }
        if (surname == null) {
            if (other.surname != null) {
                return false;
            }
        } else if (!surname.equals(other.surname)) {
            return false;
        }
        if (role == null) {
            if (other.role != null) {
                return false;
            }
        } else if (role != other.role) {
            return false;
        }
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (status != other.status) {
            return false;
        }
        if (Double.compare(other.balance, balance) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((patronymic == null) ? 0 : patronymic.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        long temp = Double.doubleToLongBits(balance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", role=").append(role);
        sb.append(", status=").append(status);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
