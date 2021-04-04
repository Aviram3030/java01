package experis.ds.database.creators;

import java.util.HashMap;

public class PublishersNameList {
    private HashMap<String, Publisher> publishers = new HashMap<>();

    public Publisher add(String publisherName) {
        Publisher publisher = publishers.get(publisherName);

        if(publisher == null){
            publisher = new Publisher(publisherName);
            publishers.put(publisherName, publisher);
        }
        return publisher;
    }
}
