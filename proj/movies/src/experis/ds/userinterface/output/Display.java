package experis.ds.userinterface.output;

import experis.ds.domainentities.Movie;

public interface Display<T>{
    public void getOutput(T movies);
}
