package sample.io;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;

public interface IoInterface<T,t> {
    int create(T t) throws IOException;
    int update(T t) throws IOException;
    T read(String id) throws IOException;
    Boolean delete(String id);
    String readAll();
    Long count();

}
