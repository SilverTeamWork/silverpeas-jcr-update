package org.silverpeas.jcr.update;

import org.apache.jackrabbit.oak.plugins.index.IndexUtils;
import org.apache.jackrabbit.oak.spi.lifecycle.RepositoryInitializer;
import org.apache.jackrabbit.oak.spi.state.NodeBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class SilverpeasIndexDefinitionsCreator implements RepositoryInitializer {
    @Override
    public void initialize(@NotNull NodeBuilder root) {
        NodeBuilder index = IndexUtils.getOrCreateOakIndex(root);

        createIndexDefinitionOnProperty(index, "name", SilverpeasNodeProperties.SLV_PROPERTY_NAME);
        createIndexDefinitionOnProperty(index, "description", SilverpeasNodeProperties.SLV_PROPERTY_DESCRIPTION);
        createIndexDefinitionOnProperty(index, "NameOrDescription", SilverpeasNodeProperties.SLV_PROPERTY_NAME,
                SilverpeasNodeProperties.SLV_PROPERTY_DESCRIPTION);
        createIndexDefinitionOnProperty(index, "oldSilverpeasId", SilverpeasNodeProperties.SLV_PROPERTY_OLD_ID);
        createIndexDefinitionOnProperty(index, "foreignKey", SilverpeasNodeProperties.SLV_PROPERTY_FOREIGN_KEY);
        createIndexDefinitionOnProperty(index, "owner", SilverpeasNodeProperties.SLV_PROPERTY_OWNER);
        createIndexDefinitionOnProperty(index, "expiryDate", SilverpeasNodeProperties.SLV_PROPERTY_EXPIRY_DATE);
        createIndexDefinitionOnProperty(index, "alertDate", SilverpeasNodeProperties.SLV_PROPERTY_ALERT_DATE);
        createIndexDefinitionOnProperty(index, "order", SilverpeasNodeProperties.SLV_PROPERTY_ORDER);
        createIndexDefinitionOnProperty(index, "versioned", SilverpeasNodeProperties.SLV_PROPERTY_VERSIONED);
    }

    private void createIndexDefinitionOnProperty(NodeBuilder indexRoot, String indexName, String... propertyName) {
        IndexUtils.createIndexDefinition(indexRoot, indexName, true, false,
                Arrays.asList(propertyName),
                List.of(SilverpeasNodeProperties.SLV_SIMPLE_DOCUMENT));
    }
}
