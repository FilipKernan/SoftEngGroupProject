package handlers;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import db.PlaylistDAO;
import db.ThirdPartySitesDAO;
import http.*;
import model.ThirdPartySite;

import java.util.List;

public class GetThirdPartySitesHandler implements RequestHandler<Object, GetThirdPartySitesResponse> {

    public LambdaLogger logger;

    List<ThirdPartySite> getThirdPartySites() throws Exception {
        ThirdPartySitesDAO dao = new ThirdPartySitesDAO();

        return dao.getAllThirdPartySites();
    }

    @Override
    public GetThirdPartySitesResponse handleRequest(Object o, Context context){
        GetThirdPartySitesResponse response;
        try{
            List<ThirdPartySite> list = getThirdPartySites();
            response = new GetThirdPartySitesResponse(list, 200);

        } catch (Exception e) {
            response = new GetThirdPartySitesResponse( 403, e.getMessage());
        }
        return response;
    }
}
