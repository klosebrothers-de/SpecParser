[![Maven Central](https://img.shields.io/maven-central/v/de.klosebrothers/specparser.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22de.klosebrothers%22%20AND%20a:%22specparser%22)
## Gauge Specification Parser

This project Parses a Gauge specification into an appropriate data structure.
This structure can then be modified and rendered back to a .spec file.

It supports:

* Headings
* Scenarios
* Steps
* Tags
* Context Steps
* Tear Down Steps
* Comments after Tags / before Tear Down Steps

It doesn't support yet:

* Tables
* Parameters
