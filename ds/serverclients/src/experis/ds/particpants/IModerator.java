package experis.ds.particpants;

public interface IModerator {
    String getWithoutCurses(String msg);
    boolean shouldBeBanned(int limit);
}
