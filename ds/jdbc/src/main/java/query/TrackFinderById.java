package query;

import entity.Track;

import java.util.List;

public class TrackFinderById {
    public Track getTrack(List<Track> tracks, String data){
        int id = Integer.parseInt(data);
        return searchById(tracks, id);
    }

    private Track searchById(List<Track> tracks, int requestedId) {
        int first = 0;
        int last = tracks.size() - 1;
        int mid = (first + last) / 2;
        while(first <= last){
            Track currentTrack = tracks.get(mid);
            int albumId = currentTrack.getAlbumId();

            if (albumId == requestedId){
                return currentTrack;
            }else if (albumId < requestedId){
                first = mid + 1;
            }else{
                last = mid - 1;
            }
            mid = (first + last) / 2;
        }
        return null;
    }
}
