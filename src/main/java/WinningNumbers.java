import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

/**
 * Endpoint HTTP służący do pobrania liczb wygrywających
 * Wywołąnie localhost:8082/lotteryBoss/api/numbers/winning
 **/
@Path("/numbers")
public class WinningNumbers {

    @Inject
    private NumberGenerator numberGenerator;

    @GET
    @Path("/winning")
    public Response getWinningNumbers() {
        String winningNumbers = numberGenerator.getWinningNumbers()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        return Response
                .ok(winningNumbers)
                .build();

    }

}