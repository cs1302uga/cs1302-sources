#set( $moduleId = $artifactId.trim().replace("-", ".").replaceAll("cs1302.", "cs1302uga.") )
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
