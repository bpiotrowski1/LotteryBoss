import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Endpint HTTP (jax-rs) na którym przyjmowane są liczby do porównania
 * Wywołąnie POST  na localhost:8082/lotteryBoss/api/results np przez POSTMANA
 **/
@Path("/results")
public class Results {

    //Wstrzyknięcie instancji numbergeneratora
    //Odpowiedzialna jest za to cześć JAVA EE o nazwie CDI
    @Inject
    private NumberGenerator numberGenerator;

    @POST
    @Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8") //Odpowiedz wygenerowana z tej metody bedzie w formacie json
    @Consumes(MediaType.APPLICATION_JSON) // przyjmujemy tresc tylko w formacie json
    public Response getLotteryResults(LotteryParameters lotteryParameters) {
        List<Integer> winningNumbers = numberGenerator.getWinningNumbers(); //pobieramy liczby wygrywajace
        boolean isSuccess = isSuccess(lotteryParameters, winningNumbers); //okreslamy wynik gry

        // Budowanie wspolnej czesci odpowiedzi dla obu scenariuszy
        StringBuilder responseMessageBuilder = new StringBuilder();
        responseMessageBuilder
                .append("Level= ").append(lotteryParameters.getLevel())
                .append("\n")
                .append("Liczby wygrywające to:")
                .append(winningNumbers)
                .append("\n");

        if (isSuccess) {
            responseMessageBuilder.append("Wygrałeś milion!\n");
        } else {
            responseMessageBuilder.append("Niestety nie wygrałeś, spróbuj jeszcze raz!\n");
        }

        return Response
                .ok(responseMessageBuilder.toString())
                .build();
    }

    private boolean isSuccess(LotteryParameters lotteryParameters, List<Integer> winningNumbers) {
        // sprawdzenie jaka czesc zbioru przysłanego z lotteryAgent zawiera sie w liczbach wygrywajacych.
        // Wygrywamy gdy jest to przynajmniej tyle na ile wskazuje wartosc level lub wiecej
        return lotteryParameters
                .getUserNumbers()
                .stream()
                .filter(userNumber -> winningNumbers.contains(userNumber))
                .count() >= lotteryParameters.getLevel();
    }

}
