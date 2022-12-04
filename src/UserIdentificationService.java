public class UserIdentificationService {

    RateLimiterService rateLimiterService;

    public UserIdentificationService() {
        this.rateLimiterService = new RateLimiterService();
    }

    public String serveRequest(String clientId) {

        boolean isAllowed = rateLimiterService.isRateLimitedUserRequest(clientId);
        if(isAllowed) {
            return "Request served";
        } else return "Too many Requests please try again";
    }
}
