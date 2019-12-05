package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.ThirdPartySitesDAO;
import http.GetThirdPartySitesResponse;
import http.RegisterThirdPartyRequest;
import http.RegisterThirdPartyResponse;
import model.ThirdPartySite;

import java.util.List;
import java.util.UUID;

public class RegisterThirdPartySitesHandler implements RequestHandler<RegisterThirdPartyRequest, RegisterThirdPartyResponse> {

    public LambdaLogger logger;

    @Override
    public RegisterThirdPartyResponse handleRequest(RegisterThirdPartyRequest req, Context context){
        ThirdPartySitesDAO dao = new ThirdPartySitesDAO();

        RegisterThirdPartyResponse response;
        try{
            String uniqueID = UUID.randomUUID().toString();
            ThirdPartySite tps = new ThirdPartySite(req.getUrl(), uniqueID);
            if(req.isAddTPS()){
                if(dao.addThirdPartySite(tps)) {
                    response = new RegisterThirdPartyResponse(req.getUrl() + " added", 200);
                } else {
                    response = new RegisterThirdPartyResponse(req.getUrl(), 422, "Unable to add library");
                }
            } else {
                dao.deleteThirdPartySite(req.getUrl());
                response = new RegisterThirdPartyResponse(req.getUrl() + " deleted", 200);
            }
        } catch (Exception e) {
            response = new RegisterThirdPartyResponse(req.getUrl(), 403, e.getMessage());
        }
        return response;
    }
}
