package com.traulko.project.entity;

/**
 * The {@code CustomImage} class represents CustomImage entity.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class CustomImage {

    /**
     * The value is used for custom image id storage.
     */
    private Integer imageId;

    /**
     * The value is used for name storage.
     */
    private String name;

    /**
     * Instantiates a new CustomImage.
     */
    public CustomImage() {
    }

    /**
     * Instantiates a new CustomImage.
     *
     * @param imageId the image index
     * @param name the name
     */
    public CustomImage(Integer imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    /**
     * Gets CustomImage id.
     *
     * @return the CustomImage id
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * Sets CustomImage id.
     *
     * @param imageId the CustomImage id
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    /**
     * Gets CustomImage name.
     *
     * @return the CustomImage name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets CustomImage name.
     *
     * @param name the CustomImage name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomImage other = (CustomImage) obj;
        if (imageId == null) {
            if (other.imageId != null) {
                return false;
            }
        } else if (!imageId.equals(other.imageId)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Image{");
        sb.append("imageId=").append(imageId);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
