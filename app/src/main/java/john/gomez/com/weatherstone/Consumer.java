package john.gomez.com.weatherstone;


public interface Consumer<T> {

    void acceptData(T data);

}
