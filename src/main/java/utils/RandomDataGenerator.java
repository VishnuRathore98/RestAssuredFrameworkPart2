package utils;


public class RandomDataGenerator {
    public static net.datafaker.Faker datafaker = new net.datafaker.Faker();
    public static int getRandomNumber(int min, int max){
        return datafaker.number().numberBetween(min, max);
    }
}
