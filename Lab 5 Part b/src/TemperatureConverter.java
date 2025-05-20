/**
 * For conversion of farenheit to celcius.
 */
public class TemperatureConverter {

    /**
     *
     * @param fahrenheitStr String to be taken from user as farenheit value
     * @return celcius value obtained from calculation
     * @throws IllegalArgumentException if input is not valid number exception will be thrown
     * Since we do not need instance specific data, it is simple calculation only, I use static
     */
    public static double fahrenheitToCelsius(String fahrenheitStr) throws IllegalArgumentException {
        try {
            //text value need to parse to double
            double fahrenheit = Double.parseDouble(fahrenheitStr);

            //Converting Fahrenheit to Celsius
            return (fahrenheit-32)*5/9;

        } catch (NumberFormatException e) {
            //if numberFormat is wrong, eg space, text values or something which is not number
            throw new IllegalArgumentException("Invalid number. Please enter valid one");
        }
    }
}
