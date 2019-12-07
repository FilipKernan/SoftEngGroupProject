package handlers;

import http.GetThirdPartySitesRequest;
import http.GetThirdPartySitesResponse;
import http.RegisterThirdPartyRequest;
import http.RegisterThirdPartyResponse;
import org.junit.Test;

public class ThirdPartySiteHandlerTests {

    @Test
    public void testCreation(){
        RegisterThirdPartyRequest addReq = new RegisterThirdPartyRequest("urlToDefinitelyAddPleaseMaybeIfYouCan", true);
        RegisterThirdPartyRequest delReq = new RegisterThirdPartyRequest("urlToDefinitelyAddPleaseMaybeIfYouCan", false);

        GetThirdPartySitesRequest getReq1 = new GetThirdPartySitesRequest();

        GetThirdPartySitesHandler getHandler = new GetThirdPartySitesHandler();
        RegisterThirdPartySitesHandler regHandler = new RegisterThirdPartySitesHandler();

        GetThirdPartySitesResponse getResp1 = getHandler.handleRequest(null, null);
        RegisterThirdPartyResponse addResp = regHandler.handleRequest(addReq, null);
        RegisterThirdPartyResponse addRespFail = regHandler.handleRequest(addReq, null);
        GetThirdPartySitesResponse getResp2 = getHandler.handleRequest(null, null);

        RegisterThirdPartyResponse delResp = regHandler.handleRequest(delReq, null);
        RegisterThirdPartyResponse delRespFail = regHandler.handleRequest(delReq, null);
        GetThirdPartySitesResponse getResp3 = getHandler.handleRequest(null, null);

        System.out.println(getResp1.toString());
        System.out.println(addResp.toString());
        System.out.println(addRespFail.toString());
        System.out.println(getResp2.toString());
        System.out.println(delResp.toString());
        System.out.println(delRespFail.toString());
        System.out.println(getResp3.toString());
    }

}
