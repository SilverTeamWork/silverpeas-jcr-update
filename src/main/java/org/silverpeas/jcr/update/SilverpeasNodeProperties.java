package org.silverpeas.jcr.update;

public class SilverpeasNodeProperties {

    private SilverpeasNodeProperties() {
    }

    /**
     * Silverpeas document node qname.
     */
    public static final String SLV_SIMPLE_DOCUMENT = "slv:simpleDocument";

    /**
     * Root node for XPath Queries
     */
    public static final String SLV_PROPERTY_NAME = "slv:name";
    public static final String SLV_PROPERTY_DESCRIPTION = "slv:description";
    public static final String SLV_PROPERTY_ORDER = "slv:order";
    public static final String SLV_PROPERTY_EXPIRY_DATE = "slv:expiryDate";
    public static final String SLV_PROPERTY_ALERT_DATE = "slv:alertDate";
    public static final String SLV_PROPERTY_FOREIGN_KEY = "slv:foreignKey";
    public static final String SLV_PROPERTY_OWNER = "slv:owner";
    public static final String SLV_PROPERTY_OLD_ID = "slv:oldSilverpeasId";
    public static final String SLV_PROPERTY_VERSIONED = "slv:versioned";
}
