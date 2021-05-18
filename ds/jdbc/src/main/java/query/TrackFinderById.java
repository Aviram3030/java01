package query;

import entity.Track;

import java.util.List;

public class TrackFinderById {
    public Track getTrack(List<Track> tracks, String data){
        int id = Integer.parseInt(data);
        return searchById(tracks, id);
    }

    //binary search
    private Track searchById(List<Track> tracks, int requestedId) {
        int first = 0;
        int last = tracks.size() - 1;
        int mid;
        while(first <= last){
            mid = (first + last) / 2;
            Track track = tracks.get(mid);
            int trackID = track.getTrackId();

            if (trackID == requestedId){
                return track;
            }else if (trackID < requestedId){
                first = mid + 1;
            }else{
                last = mid - 1;
            }
        }
        return null;
    }
}
