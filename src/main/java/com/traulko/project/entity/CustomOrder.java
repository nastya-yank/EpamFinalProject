package com.traulko.project.entity;

import java.time.LocalDate;

/**
 * The {@code CustomOrder} class represents CustomOrder entity.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class CustomOrder {
    public enum Status {
        UNDER_CONSIDERATION, DENIED, PRODUCED;
    }

    private Integer orderId;
    private LocalDate creationDate;
    private LocalDate closingDate;
    private Status status;
    private User user;

    /**
     * Instantiates a new CustomOrder.
     */
    public CustomOrder() {
    }

    /**
     * Instantiates a new CustomOrder.
     *
     * @param orderId the order index
     * @param creationDate the date of creation
     * @param closingDate the date of closing
     * @param status the status
     * @param user the user
     */
    public CustomOrder(Integer orderId, LocalDate creationDate, LocalDate closingDate,
                       Status status, User user) {
        this.orderId = orderId;
        this.creationDate = creationDate;
        this.closingDate = closingDate;
        this.status = status;
        this.user = user;
    }

    /**
     * Gets CustomOrder id.
     *
     * @return the CustomOrder id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Sets CustomOrder id.
     *
     * @param orderId the CustomOrder id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets CustomOrder creation date.
     *
     * @return the CustomOrder creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets CustomOrder creation date.
     *
     * @param creationDate the CustomOrder creation date
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets CustomOrder closing date.
     *
     * @return the CustomOrder closing date
     */
    public LocalDate getClosingDate() {
        return closingDate;
    }

    /**
     * Sets CustomOrder closing date.
     *
     * @param closingDate the CustomOrder closing date
     */
    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    /**
     * Gets CustomOrder status.
     *
     * @return the CustomOrder status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets CustomOrder status.
     *
     * @param status the CustomOrder status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets CustomOrder user.
     *
     * @return the CustomOrder user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets CustomOrder user.
     *
     * @param user the CustomOrder user
     */
    public void setUser(User user) {
        this.user = user;
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
        CustomOrder other = (CustomOrder) obj;
        if (orderId == null) {
            if (other.orderId != null) {
                return false;
            }
        } else if (!orderId.equals(other.orderId)) {
            return false;
        }
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (!status.equals(other.status)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        if (other.creationDate != creationDate) {
            return false;
        }
        if (other.closingDate != closingDate) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((closingDate == null) ? 0 : closingDate.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomOrder{");
        sb.append("orderId=").append(orderId);
        sb.append(", creationDate=").append(creationDate);
        sb.append(", closingDate=").append(closingDate);
        sb.append(", status=").append(status);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
