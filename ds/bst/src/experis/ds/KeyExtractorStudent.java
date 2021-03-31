package experis.ds;

public class KeyExtractorStudent implements KeyExtractor <Integer,Student>{

    @Override
    public Integer extract(Student student) {
        return student.getID();
    }
}
