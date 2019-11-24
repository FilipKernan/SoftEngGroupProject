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

public class RegisterThirdPartySitesHandler implements RequestHandler<RegisterThirdPartyRequest, RegisterThirdPartyResponse> {

    public LambdaLogger logger;

    @Override
    public RegisterThirdPartyResponse handleRequest(RegisterThirdPartyRequest req, Context context){
        logger.log("in getThird");
        ThirdPartySitesDAO dao = new ThirdPartySitesDAO();

        RegisterThirdPartyResponse response;
        try{
            //TODO make unique ID
            String uniqueID = null;
            ThirdPartySite tps = new ThirdPartySite(req.getUrl(), uniqueID, null);
            if(req.isAddTPS()){
                if(dao.addThirdPartySite(tps)) {
                    response = new RegisterThirdPartyResponse(req.getUrl() + " added", 200);
                } else {
                    response = new RegisterThirdPartyResponse(req.getUrl(), 422, "Unable to add library");
                }
            } else {
                dao.deleteThirdPartySite(tps);
                response = new RegisterThirdPartyResponse(req.getUrl() + " deleted", 200);
            }
        } catch (Exception e) {
            response = new RegisterThirdPartyResponse(req.getUrl(), 403, e.getMessage());
        }
        return response;
    }
}
