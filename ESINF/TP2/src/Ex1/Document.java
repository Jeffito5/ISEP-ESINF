package Ex1;

/**
 * @author Luís Araújo
 */
public interface Document extends Comparable<Document> {
    Integer getPriority();

    Integer getSize();

    String getName();

    String getAuthor();
}

