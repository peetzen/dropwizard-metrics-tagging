# Tagging support for Dropwizard metrics
[![CircleCI](https://img.shields.io/circleci/build/gh/peetzen/dropwizard-metrics-tagging)](https://circleci.com/gh/peetzen/dropwizard-metrics-tagging)
[![Maven Central](https://img.shields.io/maven-central/v/de.peetzen.dropwizard/dropwizard-metrics-tagging)](https://search.maven.org/artifact/de.peetzen.dropwizard/dropwizard-metrics-tagging)
[![License](https://img.shields.io/github/license/peetzen/dropwizard-metrics-tagging)](http://www.apache.org/licenses/LICENSE-2.0.html)

The _Dropwizard Metrics_ (v4.x) do not natively support tags, however tags can be encoded as part of the metric name. 
This library offers a _MetricName_ class for converting tagged metrics to the legacy format.

## Documentation
This library adds support for creating metric names with tags.
 
The `MetricName` class is based on the code for [Dropwizard metrics (v5.x)](https://github.com/dropwizard/metrics/blob/release/5.0.x/metrics-core/src/main/java/io/dropwizard/metrics5/MetricName.java) 
and adds methods for converting to the legacy metric name format. 
In addition, it contains support for dealing with tags from the `MetricTaggingContext` _ThreadLocal_ context.

## Getting started
The artifacts including source and binaries are available on the central Maven repositories.

For maven: 
```xml
<dependency>
  <groupId>de.peetzen.dropwizard.metrics</groupId>
  <artifactId>metrics-tagging</artifactId>
  <version>1.0.0</version>
</dependency>
```

For gradle:
```yaml
implementation group: 'de.peetzen.dropwizard', name: 'dropwizard-metrics-tagging', version: '1.0.0'
```

Fully compatible with Dropwizard Metrics version v4.x.

### Usage

Create a metric name with a single explicit tag.
```java
    String name = MetricName.build("my","metric").tagged("tenant", "tenant-id").toLegacyFormat();
    assert name.equals("my.metric[tenant:tenant-id]");
```

Create a metric name with a single tag fetched from the `MetricTaggingContext` context.
```java
    MetricName metricName = MetricName.build("my","metric");
    MetricTaggingContext.put("tenant", "tenant-id")

    String name = metricName.taggedUsingContext().toLegacyFormat();
    assert name.equals("my.metric[tenant:tenant-id]");
```

