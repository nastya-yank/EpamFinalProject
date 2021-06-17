package com.traulko.project.entity;

/**
 * The {@code UserBasketProduct} class represents UserBasketProduct entity.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class UserBasketProduct {
    private Integer basketId;
    private User user;
    private Product product;

    /**
     * Instantiates a new UserBasketProduct.
     */
    public UserBasketProduct() {
    }

    /**
     * Instantiates a new CustomImage.
     *
     * @param basketId the basket index
     * @param product the product
     * @param user the user
     */
    public UserBasketProduct(Integer basketId, User user, Product product) {
        this.basketId = basketId;
        this.user = user;
        this.product = product;
    }

    /**
     * Gets UserBasketProduct id.
     *
     * @return the UserBasketProduct id
     */
    public Integer getBasketId() {
        return basketId;
    }

    /**
     * Sets UserBasketProduct basket index.
     *
     * @param basketId the UserBasketProduct basket index
     */
    public void setBasketId(Integer basketId) {
        this.basketId = basketId;
    }

    /**
     * Gets UserBasketProduct user.
     *
     * @return the UserBasketProduct user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets UserBasketProduct user.
     *
     * @param user the UserBasketProduct user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets UserBasketProduct product.
     *
     * @return the UserBasketProduct product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets UserBasketProduct product.
     *
     * @param product the UserBasketProduct product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserBasketProduct other = (UserBasketProduct) obj;
        if (basketId == null) {
            if (other.basketId != null) {
                return false;
            }
        } else if (!basketId.equals(other.basketId)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((basketId == null) ? 0 : basketId.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Basket{");
        sb.append("basketId=").append(basketId);
        sb.append(", user=").append(user);
        sb.append(", product=").append(product);
        sb.append('}');
        return sb.toString();
    }
}
