package dataSources;

public class Track {
    private String focus;
    private String description;
    private String duration;
    private Integer trackId;

    public Track(String focus, String description, String duration) {
        this.focus = focus;
        this.description = description;
        this.duration = duration;
    }

    //GETTERS AND SETTERS

    public String getFocus() {
        return focus;
    }
    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getTrackId() {
        return trackId;
    }
    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    //Equals and hashcode()

    @Override
    public boolean equals(Object o) {iiiii
        if (this == o) return true;ii
        if (o == null || getClass() != o.getClass()) return false;ii

        Track track = (Track) o;

        if (!focus.equals(track.focus)) return false;
        if (!description.equals(track.description)) return false;
        return duration.equals(track.duration);
    }

    @Override
    public int hashCode() {
        int result = focus.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + duration.hashCode();
        return result;
    }
}
