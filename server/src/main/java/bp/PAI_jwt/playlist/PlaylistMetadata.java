package bp.PAI_jwt.playlist;

class PlaylistMetadata {
    private String ownerName;
    private String createdBy;
    private long createdAt;
    private String description;

    public PlaylistMetadata(String ownerName, String createdBy, long createdAt, String description) {
        this.ownerName = ownerName;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }
}
