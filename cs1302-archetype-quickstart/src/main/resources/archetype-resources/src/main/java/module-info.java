#set( $moduleId = $artifactId.trim().replace("-", ".") )
/**
 * Defines the {@code $groupId:$artifactId} API.
 */
module $moduleId {
    requires transitive com.google.gson;
    requires transitive java.logging;
    requires transitive java.net.http;
    requires transitive javafx.controls;
    exports $package;
} // $moduleId
