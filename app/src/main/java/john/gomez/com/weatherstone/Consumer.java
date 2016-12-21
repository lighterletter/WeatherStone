package john.gomez.com.weatherstone;

/**
 * Created by john on 12/21/16.
 */

public interface Consumer<T> {

    void acceptData(T data);

}
