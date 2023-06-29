package org.silverpeas.jcr.update;

import org.apache.jackrabbit.oak.plugins.index.IndexUtils;
import org.apache.jackrabbit.oak.spi.lifecycle.RepositoryInitializer;
import org.apache.jackrabbit.oak.spi.state.NodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class SilverpeasIndexDefinitionsCreator implements RepositoryInitializer {

  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SilverpeasIndexDefinitionsCreator.class);

  private static final Path INDEX_CONF_PATH = Path.of(System.getenv("SILVERPEAS_HOME"), "configuration", "silverpeas",
      "resources", "silverpeas-oak-index.properties");

  @Override
  public void initialize(@NotNull NodeBuilder root) {
    if (Files.exists(INDEX_CONF_PATH)) {
      LOG.info("Load index configuration file {}", INDEX_CONF_PATH);
      try (Reader reader = Files.newBufferedReader(INDEX_CONF_PATH)) {
        Properties indexConf = new Properties();
        indexConf.load(reader);

        NodeBuilder index = IndexUtils.getOrCreateOakIndex(root);
        indexConf.forEach((k, v) -> {
          String indexName = k.toString();
          String[] nodeProps = Stream.of(v.toString().split(" ")).map(String::trim).toArray(String[]::new);
          createIndexDefinitionOnProperty(index, indexName, nodeProps);
        });
      } catch (Exception e) {
        LOG.error(e.getMessage(), e);
      }
    } else {
      LOG.warn("No index configuration file found at {}", INDEX_CONF_PATH);
    }
  }

  private void createIndexDefinitionOnProperty(NodeBuilder indexRoot, String indexName, String... propertyNames) {
    if (LOG.isInfoEnabled()) {
      String propLabel = propertyNames.length > 1 ? "properties" : "property";
      LOG.info("Create index {} on {} {}", indexName, propLabel, String.join(" and ", propertyNames));
    }
    IndexUtils.createIndexDefinition(indexRoot, indexName, true, false,
        Arrays.asList(propertyNames),
        List.of(SilverpeasNodeProperties.SLV_SIMPLE_DOCUMENT));
  }
}
