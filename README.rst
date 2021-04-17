cs1302-sources
##############

Originally designed for the CSCI 1302 Software Development course at the
University of Georgia, the |cs1302_sources| project includes Open Source
Software (OSS) and Open Educational Resources (OERs) designed for CS2.

* **Project Site:** |cs1302uga|_

* **Modules:**

  - TODO

Publishing to Maven Central
===========================

Publishing artifacts requires signing privledges for the |groupID|
coordinate on Sonatype's Open Source Software Repository Hosting (OSSRH) service.
Currently, only |mepcotterell|_ can publish to |groupID|.

Snapshots
*********

To deploy a |SNAPSHOT|_ (i.e., a "development" version), follow the
instructions provided below. Deploying a |SNAPSHOT| does not change
the version number in the POM in any way.

.. code::

   $ mvn clean deploy
   $ mvn site site:stage

Release
*******

To deploy a release, use the following command:

.. code::

   $ mvn -B release:clean release:prepare
   $ mvn -B release:perform

.. footer::

   Copyright |copy| Michael E. Cotterell and the University of Georgia
   (see `LICENSE <LICENSE>`_). The content and opinions expressed on this page
   do not necessarily reflect the views of nor are they endorsed by the
   University of Georgia or the University System of Georgia.

.. |copy| unicode:: U+000A9 .. COPYRIGHT SIGN

.. |cs1302_sources| replace:: **cs1302-sources**
.. |groupId| replace:: ``io.github.cs1302uga``

.. |cs1302uga| replace:: CSCI 1302 @ UGA CS
.. _cs1302uga: https://cs1302uga.github.io/

.. |SNAPSHOT| replace:: SNAPSHOT
.. _SNAPSHOT: https://maven.apache.org/guides/getting-started/index.html#What_is_a_SNAPSHOT_version

.. |mepcotterell| replace:: ``@mepcotterell``
.. _mepcotterell: https://github.com/mepcotterell
