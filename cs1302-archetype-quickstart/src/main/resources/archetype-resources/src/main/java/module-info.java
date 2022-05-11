#set( $moduleId = $artifactId.trim().replace("-", ".") )
/**
 * Defines the {@code $groupId:$artifactId} API.
 */
module $moduleId {
    requires transitive java.logging;
    requires transitive java.net.http;
    exports $package;
} // $moduleId
