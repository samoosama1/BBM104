/**
 * Functional Interface for prepending prefixes to strings.
 */
@FunctionalInterface
public interface Printable {
    String prefix(String s);
}
