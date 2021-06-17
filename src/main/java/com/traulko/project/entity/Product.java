package com.traulko.project.entity;

/**
 * The {@code Product} class represents Product entity.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class Product {
    private Integer productId;
    private String title;
    private double price;
    private String description;
    private CustomImage image;

    /**
     * Instantiates a new Product.
     */
    public Product() {
    }

    /**
     * Instantiates a new CustomImage.
     *
     * @param productId the product index
     * @param description the description
     * @param image the image
     * @param title the title
     * @param price the price
     */
    public Product(Integer productId, String title, double price, String description, CustomImage image) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    /**
     * Gets Product id.
     *
     * @return the Product id
     */
    public CustomImage getImage() {
        return image;
    }

    /**
     * Sets Product image.
     *
     * @param image the Product image
     */
    public void setImage(CustomImage image) {
        this.image = image;
    }

    /**
     * Gets Product id.
     *
     * @return the Product id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Sets Product product index.
     *
     * @param productId the Product product index
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * Gets Product title.
     *
     * @return the Product title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets Product title.
     *
     * @param title the Product title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets Product price.
     *
     * @return the Product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets Product price.
     *
     * @param price the Product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets Product description.
     *
     * @return the Product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets Product description.
     *
     * @param description the Product description
     */
    public void setDescription(String description) {
        this.description = description;
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
        Product other = (Product) obj;
        if (productId == null) {
            if (other.productId != null) {
                return false;
            }
        } else if (!productId.equals(other.productId)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        if (image == null) {
            if (other.image != null) {
                return false;
            }
        } else if (!image.equals(other.image)) {
            return false;
        }
        if (Double.compare(other.price, price) != 0) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        int prime = 31;
        long temp;
        result = productId != null ? prime * productId.hashCode() : 0;
        result = prime * result + (title != null ? title.hashCode() : 0);
        result = prime * result + (description != null ? description.hashCode() : 0);
        result = prime * result + (image != null ? image.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(productId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", image=").append(image);
        sb.append('}');
        return sb.toString();
    }
}
