package bp.PAI_jwt.playlist;

import bp.PAI_jwt.model.Track;

class PlaylistUtilities {
    public static long getTotalDuration(Playlist playlist) {
        long totalDuration = 0;
        for (Track track : playlist.getTracks()) {
            totalDuration += Long.parseLong(track.getDurationMs());
        }
        return totalDuration;
    }

    public static void printTrackNames(Playlist playlist) {
        for (Track track : playlist.getTracks()) {
            System.out.println(track.getName());
        }
    }

    public static Track findTrackByName(Playlist playlist, String trackName) {
        for (Track track : playlist.getTracks()) {
            if (track.getName().equals(trackName)) {
                return track;
            }
        }
        return null;
    }
}