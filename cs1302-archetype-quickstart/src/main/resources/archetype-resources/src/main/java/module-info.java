#set( $moduleId = $package.strip() )
/**
 * Defines the {@code $groupId:$artifactId} API.
 */
@SuppressWarnings("module")
module $moduleId {
    requires transitive com.google.gson;
    requires transitive java.logging;
    requires transitive java.net.http;
    requires transitive javafx.controls;
    exports $package;
} // $moduleId
