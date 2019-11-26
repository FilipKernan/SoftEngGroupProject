package handlers;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import db.PlaylistDAO;
import http.ReturnPlaylistsResponse;
import model.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetAllPlaylistsHandler implements RequestHandler<Object, ReturnPlaylistsResponse> {

    public LambdaLogger logger;

    List<Playlist> getPlaylists() throws Exception {
        PlaylistDAO dao = new PlaylistDAO();

        return dao.getAllPlaylists();
    }

    @Override
    public ReturnPlaylistsResponse handleRequest(Object input, Context context){
       ReturnPlaylistsResponse response;
        try{
            List<Playlist> list = getPlaylists();
            response = new ReturnPlaylistsResponse(list, 200);

        } catch (Exception e) {
            response = new ReturnPlaylistsResponse(403, e.getMessage());
        }
        return response;
    }
}