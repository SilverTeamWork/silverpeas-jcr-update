This library is to be hooked to the migration process of the JCR from Apache Jackrabbit 2 to Apache
Jackrabbit Oak.

It provides custom behaviour to the JCR migration in which some nodes are modified to satisfy some
requirements expected by Oak. For example, to set the mixing `jcr:versionable` to the versioned 
nodes in the JCR instead of the more simple mixin `jcr:simpleVersionable`.

For more information about the migration process, please read its documentation at 
[the Apache Jackrabbit Oak Repository migration page](https://jackrabbit.apache.org/oak/docs/migration.html).