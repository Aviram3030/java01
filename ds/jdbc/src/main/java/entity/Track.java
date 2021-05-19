package entity;

public class Track implements Model{
    private final int trackId;
    private final String name;
    private final int albumId;
    private final int mediaTypeId;
    private final int genreId;
    private final String composer;
    private final long milliSeconds;
    private final long bytes;
    private final double unitPrice;

    public Track(int trackId, String name, int albumId, int mediaTypeId, int genreId, String composer, long milliSeconds, long bytes, double unitPrice) {
        this.trackId = trackId;
        this.name = name;
        this.albumId = albumId;
        this.mediaTypeId = mediaTypeId;
        this.genreId = genreId;
        this.composer = composer;
        this.milliSeconds = milliSeconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public int getTrackId() {
        return trackId;
    }

    public String getName() {
        return name;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getMediaTypeId() {
        return mediaTypeId;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getComposer() {
        return composer;
    }

    public long getMilliSeconds() {
        return milliSeconds;
    }

    public long getBytes() {
        return bytes;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", name='" + name + '\'' +
                ", albumId=" + albumId +
                ", mediaTypeId=" + mediaTypeId +
                ", genreId=" + genreId +
                ", composer='" + composer + '\'' +
                ", milliSeconds=" + milliSeconds +
                ", bytes=" + bytes +
                ", unitPrice=" + unitPrice +
                "}";
    }

}
