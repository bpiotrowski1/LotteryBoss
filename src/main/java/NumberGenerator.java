import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Klasa singletona która losuje zaraz po stworzeniu instancji zbiór liczb wygrywających
 */
@Singleton
@Startup // Określenie że instancja singletona ma być utworzona przez serwer już na starcie aplikacji a nie dopiero w momencie pierwszego wykorzystania
public class NumberGenerator {


    private List<Integer> winningNumbers;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NumberGenerator.class);

    @PostConstruct //Adnotacja określa że ta metoda ma być wywołana zaraz po stworzeniu instancji czyli na samym początku
    public void postConstruct() {
        winningNumbers = drawWinningNumbers();
        logger.info("Wylosowano: "+ winningNumbers);
    }

    private List<Integer> drawWinningNumbers() {
        List<Integer> nummbers = new Random()
                .ints( 1, 50)
                .distinct()
                .limit(6)
                .sorted()
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
        return nummbers;


    }

    public List<Integer> getWinningNumbers() {
        return Collections.unmodifiableList(winningNumbers);
    }


}
