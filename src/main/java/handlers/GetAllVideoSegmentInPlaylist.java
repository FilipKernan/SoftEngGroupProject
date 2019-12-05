package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.PlaylistRelationDAO;
import http.GetAllVideoSegmentsInPlaylistRequest;
import http.GetAllVideoSegmentsInPlaylistResponse;
import model.Playlist;
import model.VideoSegment;

import java.util.List;

public class GetAllVideoSegmentInPlaylist implements RequestHandler<GetAllVideoSegmentsInPlaylistRequest, GetAllVideoSegmentsInPlaylistResponse> {

    public LambdaLogger logger;

    List<VideoSegment> getVidSegs(String plID) throws Exception {
        PlaylistRelationDAO dao = new PlaylistRelationDAO();

        return dao.getVidSegsInPlaylist(plID);
    }

    @Override
    public GetAllVideoSegmentsInPlaylistResponse handleRequest(GetAllVideoSegmentsInPlaylistRequest input, Context context){
        GetAllVideoSegmentsInPlaylistResponse response;
        try{
            List<VideoSegment> list = getVidSegs(input.getPlID());
            response = new GetAllVideoSegmentsInPlaylistResponse(list, 200);

        } catch (Exception e) {
            response = new GetAllVideoSegmentsInPlaylistResponse(403, e.getMessage());
        }
        return response;
    }
}