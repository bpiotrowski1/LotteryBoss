import java.util.List;

/**
 * Obiekt transportowy do odbierania parametrów z lotteryAgent
 * Musi być identyczny jak ten w aplikacji lotteryAgent
 * Musi zawierać także bezparametrowy konstruktor
 */
public class LotteryParameters {

    private  List<Integer> userNumbers;

    private Integer level;

    public LotteryParameters() {
    }
    public LotteryParameters(List<Integer> userNumbers, Integer level) {
        this.userNumbers = userNumbers;
        this.level = level;
    }

    public List<Integer> getUserNumbers() {
        return userNumbers;
    }

    public Integer getLevel() {
        return level;
    }
}
